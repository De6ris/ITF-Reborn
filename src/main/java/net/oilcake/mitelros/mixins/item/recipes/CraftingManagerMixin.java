package net.oilcake.mitelros.mixins.item.recipes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CraftingManager.class)
public abstract class CraftingManagerMixin {

    @WrapOperation(method = "checkRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void noError(String text, Operation<Void> original) {
    }// TODO fix it

    @Inject(method = "findMatchingRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/InventoryCrafting;getEventHandler()Lnet/minecraft/Container;"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void fixITFArmors(InventoryCrafting par1InventoryCrafting, World par2World, EntityPlayer player, CallbackInfoReturnable<CraftingResult> cir, int var3, ItemStack var4, ItemStack var5, int var6) {
        if (var3 != 2 || var4 == null || var5 == null) return;
        CraftingResult temp = this.fixArmor(player, var4, var5, Items.wolf_fur, Materials.wolf_fur);
        if (temp == null) {
            cir.setReturnValue(null);
            return;
        } else if (temp.isRepair()) {
            cir.setReturnValue(temp);
            return;
        }
        temp = this.fixArmor(player, var4, var5, Items.iceChunk, Materials.ice_chunk);
        if (temp == null) {
            cir.setReturnValue(null);
        } else if (temp.isRepair()) {
            cir.setReturnValue(temp);
        }
    }

    @Unique
    private CraftingResult fixArmor(EntityPlayer player, ItemStack var4, ItemStack var5, Item fix_item, Material material) {
        boolean canFix = var4.getItem() == fix_item || var5.getItem() == fix_item;
        if (canFix && (var4.getItem() instanceof ItemArmor armor4 && armor4.hasMaterial(material) && var4.stackSize == 1 && var4.isItemDamaged() || var5.getItem() instanceof ItemArmor armor5 && armor5.hasMaterial(material) && var5.stackSize == 1 && var5.isItemDamaged())) {
            ItemStack item_stack_armor;
            ItemStack item_stack_sinew;
            if (var4.getItem() == fix_item) {
                item_stack_sinew = var4;
                item_stack_armor = var5;
            } else {
                item_stack_sinew = var5;
                item_stack_armor = var4;
            }
            if (item_stack_armor.getItem().hasQuality() && player != null && item_stack_armor.getQuality().isHigherThan(player.getMaxCraftingQuality(item_stack_armor.getItem().getLowestCraftingDifficultyToProduce(), item_stack_armor.getItem(), item_stack_armor.getItem().getSkillsetsThatCanRepairThis()))) {
                return null;
            }
            int damage = item_stack_armor.getItemDamage();
            int damage_repaired_per_sinew = item_stack_armor.getMaxDamage() / item_stack_armor.getItem().getRepairCost();
            int num_sinews_to_use = damage / damage_repaired_per_sinew;
            if (damage % damage_repaired_per_sinew != 0) {
                ++num_sinews_to_use;
            }
            if (num_sinews_to_use > 1 && num_sinews_to_use * damage_repaired_per_sinew > damage) {
                --num_sinews_to_use;
            }
            if (num_sinews_to_use > item_stack_sinew.stackSize) {
                num_sinews_to_use = item_stack_sinew.stackSize;
            }
            int damage_repaired = num_sinews_to_use * damage_repaired_per_sinew;
            int damage_after_repair = Math.max(damage - damage_repaired, 0);
            ItemStack resulting_stack = item_stack_armor.copy().setItemDamage(damage_after_repair);
            CraftingResult crafting_result = new CraftingResult(resulting_stack, num_sinews_to_use * 50, item_stack_armor.getItem().getSkillsetsThatCanRepairThis(), null).setExperienceCostExempt().setQualityOverride(item_stack_armor.getQuality()).setConsumption(num_sinews_to_use);
            crafting_result.setRepair();
            return crafting_result;
        }
        return new CraftingResult(null, 0.0f, null, null);//dummy
    }
}
