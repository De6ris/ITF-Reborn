package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.Block;
import net.minecraft.BlockWorkbench;
import net.minecraft.IRecipe;
import net.minecraft.Item;
import net.minecraft.ItemBucket;
import net.minecraft.ItemKnife;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.minecraft.RecipeHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({RecipeHelper.class})
public class RecipeHelperMixin {
    /**
     * @author
     * @reason
     */
//    @Overwrite TODO what does it do
//    public static void addRecipe(IRecipe recipe, boolean include_in_lowest_crafting_difficulty_determination) {
//        if (include_in_lowest_crafting_difficulty_determination)
//            recipe.setIncludeInLowestCraftingDifficultyDetermination();
//        ItemStack[] recipe_items = recipe.getComponents();
//        ItemStack recipe_output = recipe.getRecipeOutput();
//        Item output_item = recipe_output.getItem();
//        output_item.setAsCraftingProduct();
//        output_item.recipes[output_item.num_recipes++] = recipe;
//        float difficulty = 0.0F;
//        for (ItemStack recipeItem : recipe_items) {
//            ItemStack itemStack1 = recipeItem;
//            if (itemStack1 != null) {
//                Item item = itemStack1.getItem();
//                if (item.getHasSubtypes() && itemStack1.getItemSubtype() != 32767) {
//                    itemStack1.setAsComponentOfCraftingProduct(recipe_output);
//                } else {
//                    item.setAsComponentOfCraftingProduct(recipe_output);
//                }
//                float component_difficulty = itemStack1.getCraftingDifficultyAsComponent();
//                if (component_difficulty < 0.0F) {
//                    float highest_durability_that_is_less_than_tool_material = itemStack1.getItem().getLowestCraftingDifficultyToProduce();
//                    if (highest_durability_that_is_less_than_tool_material != Float.MAX_VALUE) {
//                        itemStack1.getItem().setCraftingDifficultyAsComponent(highest_durability_that_is_less_than_tool_material);
//                        component_difficulty = highest_durability_that_is_less_than_tool_material;
//                    } else if (item.hasMaterial(Material.rusted_iron)) {
//                        Item peer = Item.getMatchingItem(item.getClass(), Material.copper);
//                        if (peer != null) {
//                            if (item.getMaterialForDurability() == null)
//                                Minecraft.setErrorMessage("addRecipe: getMaterialForDurability()==null for component " + item);
//                            item.setCraftingDifficultyAsComponent(peer.getCraftingDifficultyAsComponent((ItemStack) null) * item.getMaterialForDurability().durability / peer.getMaterialForDurability().durability);
//                            component_difficulty = item.getCraftingDifficultyAsComponent((ItemStack) null);
//                        }
//                    }
//                }
//                if (component_difficulty < 0.0F) {
//                    Minecraft.setErrorMessage("Warning: recipe for " + recipe_output.getDisplayName() + ", component crafting difficulty not set: " + itemStack1.getItem().getItemDisplayName(itemStack1) + " [" + (itemStack1.itemID - 256) + "]");
//                } else {
//                    difficulty += component_difficulty;
//                }
//            }
//        }
//        if (difficulty < 0.0F)
//            Minecraft.setErrorMessage("addRecipe: recipe output cannot have a crafting difficulty < 0.0F");
//        recipe.setDifficulty(difficulty);
//        if (recipe_output.stackSize < 1) {
//            Minecraft.setErrorMessage("stackSize is 0 for recipe output (" + recipe_output.getDisplayName() + ")");
//        } else {
//            float f = difficulty / recipe_output.stackSize;
//        }
//        ItemStack output_item_stack = recipe.getRecipeOutput();
//        if (output_item_stack != null) {
//            output_item = output_item_stack.getItem();
//            if (output_item.containsMetal()) {
//                if (output_item instanceof net.minecraft.ItemIngot)
//                    return;
//                if (output_item.getClass() == ItemKnife.class)
//                    return;
//                if (output_item.getClass() == ItemBucket.class && RecipeHelper.hasAsComponent(recipe, ItemBucket.class))
//                    return;
//                recipe.setMaterialToCheckToolBenchHardnessAgainst(output_item.getHardestMetalMaterial());
//            } else if (output_item.itemID == Block.planks.blockID) {
//                recipe.setMaterialToCheckToolBenchHardnessAgainst(Material.wood);
//            } else if (output_item.itemID == Block.workbench.blockID) {
//                Material tool_material = BlockWorkbench.getToolMaterial(output_item_stack.getItemSubtype());
//                if (tool_material.isMetal()) {
//                    Material next_strongest_material = null;
//                    float highest_durability_that_is_less_than_tool_material = 0.0F;
//                    for (int i = 0; i < BlockWorkbench.tool_materials.length; i++) {
//                        Material material = BlockWorkbench.tool_materials[i];
//                        if (material != Material.obsidian && material.durability < tool_material.durability && material.durability > highest_durability_that_is_less_than_tool_material) {
//                            next_strongest_material = material;
//                            highest_durability_that_is_less_than_tool_material = material.durability;
//                        }
//                    }
//                    recipe.setMaterialToCheckToolBenchHardnessAgainst(next_strongest_material);
//                }
//            }
//        }
//    }
}
