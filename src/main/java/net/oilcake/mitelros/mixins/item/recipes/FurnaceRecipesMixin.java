package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

@Mixin(FurnaceRecipes.class)
public class FurnaceRecipesMixin {
    @Shadow
    private Map smeltingList = new HashMap();

    /**
     * @author
     * @reason
     */
    @Overwrite
    public ItemStack getSmeltingResult(ItemStack input_item_stack, int heat_level) {
        if (input_item_stack == null) {
            return null;
        } else {
            int input_item_id = input_item_stack.itemID;
            if (heat_level == -1) {
                return (ItemStack) this.smeltingList.get(input_item_id);
            } else {
                ItemStack result_item_stack;
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
                } else if (input_item_id == Items.claybowlRaw.itemID) {
                    result_item_stack = input_item_stack.stackSize >= 4 ? new ItemStack(Items.claybowlEmpty, 4) : null;
                } else if (input_item_id == Block.sand.blockID) {
                    result_item_stack = (heat_level != 1 || input_item_stack.stackSize >= 4) && input_item_stack.stackSize >= 4 ? new ItemStack(heat_level == 1 ? Block.sandStone : Block.glass) : null;
                } else {
                    result_item_stack = (ItemStack) this.smeltingList.get(input_item_id);
                }

                return heat_level < TileEntityFurnace.getHeatLevelRequired(input_item_stack.itemID) ? null : result_item_stack;
            }
        }
    }


}