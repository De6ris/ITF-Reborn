package net.oilcake.mitelros.block;

import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;

public class MaterialHandler {
    public static Material classifyMaterialForm(Material material) {
        if (material == Material.water || material == Materials.suspicious_water || material == Materials.dangerous_water)
            return Material.water;
        return material;
    }
}
