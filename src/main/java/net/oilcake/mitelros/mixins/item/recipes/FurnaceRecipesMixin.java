package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
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
            float ingotToNugget = input_item_stack.getItem().isChainMail() ? 4.0F : 9.0F;
            float durabilityRatio = (input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / (float) input_item_stack.getMaxDamage();
            float component = armor.getNumComponentsForDurability();
            int quantity = (int) (durabilityRatio * component * ingotToNugget / 3.0F);
            ItemStack output;
            if (armor.getHardestMetalMaterial() == Material.rusted_iron) {
                quantity /= 3;
                output = new ItemStack(Item.getMatchingItem(ItemNugget.class, Material.iron), quantity);
            } else {
                output = new ItemStack(Item.getMatchingItem(ItemNugget.class, armor.getMaterialForRepairs()), quantity);
            }
            if (quantity == 0) {
                output.setStackSize(1);
            }
            result_item_stack = output;
        } else if (input_item_stack.getItem() instanceof ItemTool tool) {
            float durabilityRatio = (input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / (float) input_item_stack.getMaxDamage();
            float component = tool.getNumComponentsForDurability();
            int quantity = (int) (durabilityRatio * component * 3.0F);
            ItemStack output;
            if (tool.getHardestMetalMaterial() == Material.rusted_iron) {
                quantity /= 3;
                output = new ItemStack(Item.getMatchingItem(ItemNugget.class, Material.iron), quantity);
            } else {
                output = new ItemStack(Item.getMatchingItem(ItemNugget.class, tool.getMaterialForRepairs()), quantity);
            }
            if (quantity == 0) {
                output.setStackSize(1);
            }
            result_item_stack = output;
        } else if (input_item_id == Items.clayBowlRaw.itemID && input_item_stack.stackSize >= 4) {
            result_item_stack = new ItemStack(Items.clayBowlEmpty, 4);
        }
        if (result_item_stack != null) {
            cir.setReturnValue(heat_level < TileEntityFurnace.getHeatLevelRequired(input_item_stack.itemID) ? null : result_item_stack);
        }
    }
}