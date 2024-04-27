package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CraftingManager.class)
public class CraftingManagerMixin {

    @Redirect(method = "checkRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void noError(String text) {
    }

    @Inject(method = "findMatchingRecipe", at = @At(value = "INVOKE", target = "Lnet/minecraft/InventoryCrafting;getEventHandler()Lnet/minecraft/Container;"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void fixITFArmors(InventoryCrafting par1InventoryCrafting, World par2World, EntityPlayer player, CallbackInfoReturnable<CraftingResult> cir, int var3, ItemStack var4, ItemStack var5, int var6) {
        if (var4 == null || var5 == null) return;
        boolean hasWolfFur = var4.getItem() == Items.wolf_fur || var5.getItem() == Items.wolf_fur;
        boolean hasIceChunk = var4.getItem() == Items.iceChunk || var5.getItem() == Items.iceChunk;
        boolean canFix = hasWolfFur || hasIceChunk;
        if (var3 == 2 && canFix && (var4.getItem() instanceof ItemArmor && ((ItemArmor) var4.getItem()).isLeather() && var4.stackSize == 1 && var4.isItemDamaged() || var5.getItem() instanceof ItemArmor && ((ItemArmor) var5.getItem()).isLeather() && var5.stackSize == 1 && var5.isItemDamaged())) {
            ItemStack item_stack_armor;
            ItemStack item_stack_sinew;
            if (var4.getItem() == Items.wolf_fur || var4.getItem() == Items.iceChunk) {
                item_stack_sinew = var4;
                item_stack_armor = var5;
            } else {
                item_stack_sinew = var5;
                item_stack_armor = var4;
            }
            if (item_stack_armor.getItem().hasQuality() && player != null && item_stack_armor.getQuality().isHigherThan(player.getMaxCraftingQuality(item_stack_armor.getItem().getLowestCraftingDifficultyToProduce(), item_stack_armor.getItem(), item_stack_armor.getItem().getSkillsetsThatCanRepairThis()))) {
                cir.setReturnValue(null);
                return;
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
            cir.setReturnValue(crafting_result);
        }
    }
}
