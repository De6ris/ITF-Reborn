package net.oilcake.mitelros.item.api;

import net.minecraft.ItemClub;
import net.minecraft.Material;

public class ItemMorningStar extends ItemClub {
    public ItemMorningStar(int par1, Material material) {
        super(par1, material);
    }

    @Override
    public String getToolType() {
        return "morningstar";
    }

    @Override
    public int getNumComponentsForDurability() {
        return 1;
    }
}
