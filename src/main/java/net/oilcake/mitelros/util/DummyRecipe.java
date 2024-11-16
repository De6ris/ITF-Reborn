package net.oilcake.mitelros.util;

import net.minecraft.ItemStack;
import net.minecraft.ShapelessRecipes;

import java.util.List;

public class DummyRecipe extends ShapelessRecipes {
    public DummyRecipe(ItemStack recipe_output, List recipe_items, boolean include_in_lowest_crafting_difficulty_determination) {
        super(recipe_output, recipe_items, include_in_lowest_crafting_difficulty_determination);
    }

}
