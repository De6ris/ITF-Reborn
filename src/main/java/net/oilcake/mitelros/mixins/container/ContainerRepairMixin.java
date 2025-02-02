package net.oilcake.mitelros.mixins.container;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.AnvilStatus;
import net.oilcake.mitelros.api.ITFContainerRepair;
import net.oilcake.mitelros.api.ITFEnchantment;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ContainerRepair.class)
public abstract class ContainerRepairMixin extends Container implements ITFContainerRepair {
    @Shadow
    private IInventory inputSlots;
    @Unique
    private int xpDifference;
    @Unique
    private AnvilStatus anvilStatus = AnvilStatus.EnchantmentConflict;

    @Override
    public int itf$GetXPDifference() {
        return this.xpDifference;
    }

    @Override
    public AnvilStatus itf$GetAnvilStatus() {
        return this.anvilStatus;
    }

    public ContainerRepairMixin(EntityPlayer player) {
        super(player);
    }

    @ModifyExpressionValue(method = "updateRepairOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;isEnchantable()Z"))
    private boolean itfEnchantable(boolean original) {
        return original || this.inputSlots.getStackInSlot(0).isItemEnchanted();
    }

    @WrapOperation(method = "updateRepairOutput", at = @At(ordinal = 0, value = "INVOKE", target = "Lnet/minecraft/ItemStack;isItemEnchanted()Z"))
    public boolean itfIsEnchanted(ItemStack instance, Operation<Boolean> original) {
        return false;
    }

    @ModifyExpressionValue(method = "updateRepairOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/EnchantmentHelper;hasValidEnchantmentForItem(Lnet/minecraft/NBTTagList;Lnet/minecraft/Item;)Z"))
    private boolean itfValidEnchantment(boolean original, @Local NBTTagList enchantmentsOfBook) {
        if (!original) {
            this.anvilStatus = AnvilStatus.NoAvailableEnchantment;
            return false;
        }
        int xpDifference = 0;
        ItemStack item_stack_in_first_slot = this.inputSlots.getStackInSlot(0);
        Map enchantmentOnItem = EnchantmentHelper.getEnchantmentsMap(item_stack_in_first_slot);
        int xpMultiplier = enchantmentOnItem.isEmpty() ? ITFConfig.AnvilXPMultiplierInit.getIntegerValue() : ITFConfig.AnvilXPMultiplier.getIntegerValue();// if not enchanted, we take less
        for (int i = 0; i < enchantmentsOfBook.tagCount(); ++i) {
            NBTTagCompound tag = (NBTTagCompound) enchantmentsOfBook.tagAt(i);
            int id = tag.getShort("id");
            int bookLevel = tag.getShort("lvl");
            Enchantment enchantment = Enchantment.enchantmentsList[id];
            if (enchantmentOnItem.containsKey(id)) {
                int originalLevel = (int) enchantmentOnItem.get(id);
                if (bookLevel > originalLevel) {
                    xpDifference -= calculateEquivalentLevel(id, bookLevel) * xpMultiplier;
                } else if (bookLevel == originalLevel) {
                    if (bookLevel < enchantment.getNumLevels()) {
                        xpDifference -= calculateEquivalentLevel(id, bookLevel + 1) * xpMultiplier;
                    }
                }
            } else if (enchantment.canEnchantItem(item_stack_in_first_slot.getItem()) && canApplyTogether(enchantmentOnItem, enchantment)) {
                xpDifference -= calculateEquivalentLevel(id, bookLevel) * xpMultiplier;
            }
        }
        this.xpDifference = xpDifference;
        boolean needsXP = xpDifference < 0;
        boolean rewardsXP = xpDifference > 0;
        if (rewardsXP) {
            this.anvilStatus = AnvilStatus.Satisfied;
            return true;
        } else if (needsXP) {
            boolean enoughXP = this.player.experience + xpDifference >= 0;
            this.anvilStatus = enoughXP ? AnvilStatus.Satisfied : AnvilStatus.LackExp;
            return enoughXP;
        } else {
            this.anvilStatus = AnvilStatus.EnchantmentConflict;
            return false;
        }
    }

    @ModifyExpressionValue(method = "updateRepairOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/Enchantment;canEnchantItem(Lnet/minecraft/Item;)Z"))
    private boolean checkConflict(boolean original, @Local Enchantment enchantment) {
        ItemStack item_stack_in_first_slot = this.inputSlots.getStackInSlot(0);
        Map enchantmentMap = EnchantmentHelper.getEnchantmentsMap(item_stack_in_first_slot);
        return original && canApplyTogether(enchantmentMap, enchantment);
    }

    @Inject(method = "updateRepairOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;clearEnchantTagList()V"))
    private void rewardXP(CallbackInfo ci) {
        ItemStack item_stack_in_first_slot = this.inputSlots.getStackInSlot(0);
        Map mapBefore = EnchantmentHelper.getEnchantmentsMap(item_stack_in_first_slot);
        int xpReward = 0;
        for (Object o : mapBefore.keySet()) {
            xpReward += calculateEquivalentLevel((int) o, (int) mapBefore.get(o)) * ITFConfig.AnvilXPMultiplierReward.getIntegerValue();
        }
        this.xpDifference = xpReward;
    }

    @Unique
    private static boolean canApplyTogether(Map enchantmentMap, Enchantment enchantment) {
        for (Object o : enchantmentMap.keySet()) {
            Enchantment enchantmentInMap = Enchantment.get((int) o);
            if (enchantmentInMap.effectId == enchantment.effectId) continue;
            if (!enchantmentInMap.canApplyTogether(enchantment)) return false;
        }
        return true;
    }

    @Unique
    private static int calculateEquivalentLevel(int id, int level) {
        Enchantment enchantment = Enchantment.get(id);
        if (((ITFEnchantment) enchantment).isCurse()) return 0;
        int original = enchantment.hasLevels() ?
                enchantment.getMinEnchantmentLevelsCost(level) :
                enchantment.getMinEnchantmentLevelsCost();
        return original * (((ITFEnchantment) enchantment).isTreasure() ? 2 * ITFConfig.AnvilXPMultiplierTreasure.getIntegerValue() : 2);
    }
}
