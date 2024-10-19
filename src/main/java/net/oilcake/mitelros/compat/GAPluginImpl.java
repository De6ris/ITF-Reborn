package net.oilcake.mitelros.compat;

import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ItemMorningStar;
import net.xiaoyu233.mitemod.miteite.api.GAPlugin;
import net.xiaoyu233.mitemod.miteite.api.GARegistry;
import net.xiaoyu233.mitemod.miteite.item.recipe.ForgingTableLevel;

public class GAPluginImpl implements GAPlugin {
    @Override
    public void register(GARegistry registry) {
        registry.registerWeaponCriteria(item -> item instanceof ItemMorningStar);

        registry.registerForgingRecipe(Materials.nickel, ForgingTableLevel.IRON, 2);
        registry.registerForgingRecipe(Materials.tungsten, ForgingTableLevel.ADAMANTIUM, 5);
        registry.registerForgingRecipe(Materials.uru, ForgingTableLevel.VIBRANIUM, 8);

        registry.registerExpForLevel(Materials.nickel, 150, 75);
        registry.registerExpForLevel(Materials.tungsten, 200, 110);
        registry.registerExpForLevel(Materials.uru, 200, 130);
    }
}
