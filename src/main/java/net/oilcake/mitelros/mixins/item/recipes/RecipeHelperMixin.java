package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.block.api.ITFWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeHelper.class)
public class RecipeHelperMixin {
    @Inject(method = "addRecipe", at = @At("TAIL"))
    private static void inject(IRecipe recipe, boolean include_in_lowest_crafting_difficulty_determination, CallbackInfo ci) {
        ItemStack recipe_output = recipe.getRecipeOutput();
        if (recipe_output == null) return;

        Item output_item = recipe_output.getItem();
        if (output_item.itemID == Blocks.blockAzurite.blockID || output_item.itemID == Blocks.azuriteCluster.blockID) {
            recipe.setMaterialToCheckToolBenchHardnessAgainst(Material.copper);
        }

        if (output_item.itemID == Blocks.itfWorkBench.blockID) {
            recipe.setMaterialToCheckToolBenchHardnessAgainst(ITFWorkbench.getMaterialToCheckToolBenchHardnessAgainst(recipe_output.getItemSubtype()));
        }
    }
}
