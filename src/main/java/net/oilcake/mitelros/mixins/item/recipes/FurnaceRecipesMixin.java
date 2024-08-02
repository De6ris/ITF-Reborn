package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.ItemKettle;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.register.FurnaceRecipesExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceRecipes.class)
public class FurnaceRecipesMixin {
    @Inject(method = "getSmeltingResult", at = @At(value = "FIELD", target = "Lnet/minecraft/Block;sand:Lnet/minecraft/BlockSand;"), cancellable = true)
    private void itfFurnaceRecipes(ItemStack input_item_stack, int heat_level, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result_item_stack = null;
        int input_item_id = input_item_stack.itemID;
        if (input_item_stack.getItem() instanceof ItemArmor armor) {
            result_item_stack = FurnaceRecipesExtend.recycleArmors(input_item_stack, armor);
        } else if (input_item_stack.getItem() instanceof ItemTool tool) {
            result_item_stack = FurnaceRecipesExtend.recycleArmors(input_item_stack, tool);
        } else if (input_item_id == Items.clayBowlRaw.itemID) {
            if (input_item_stack.stackSize >= 4) {
                result_item_stack = new ItemStack(Items.clayBowlEmpty, 4);
            } else {
                cir.setReturnValue(null);
                return;
            }
        } else if (input_item_stack.getItem() instanceof ItemKettle) {
            result_item_stack = ItemKettle.boil(input_item_stack);
        }
        if (result_item_stack != null) {
            cir.setReturnValue(heat_level < TileEntityFurnace.getHeatLevelRequired(input_item_stack.itemID) ? null : result_item_stack);
        }
    }
}