package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.Material;

public class ItemUtil {
    public static Item getNugget(Material material) {
        return Item.ironNugget.getForMaterial(material);
    }
}
