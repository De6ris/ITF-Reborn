package net.oilcake.mitelros.block.api;

import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;

public class ITFWorkbench {
    public static float getCraftingSpeedModifier(Material benchMaterial) {
        if (benchMaterial == Material.flint || benchMaterial == Material.obsidian) {
            return 0.25F;
        } else if (benchMaterial == Material.copper || benchMaterial == Material.silver || benchMaterial == Material.gold) {
            return 0.4F;
        } else if (benchMaterial == Material.iron || benchMaterial == Materials.nickel) {
            return 0.5F;
        } else if (benchMaterial == Material.ancient_metal) {
            return 0.75F;
        } else if (benchMaterial == Material.mithril) {
            return 1.0F;
        } else if (benchMaterial == Materials.tungsten) {
            return 1.5F;
        } else if (benchMaterial == Material.adamantium) {
            return 2.5F;
        }
        return 0.0F;
    }
}
