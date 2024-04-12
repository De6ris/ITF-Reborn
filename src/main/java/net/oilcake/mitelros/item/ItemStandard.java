package net.oilcake.mitelros.item;

import net.minecraft.CreativeTabs;
import net.minecraft.Item;
import net.minecraft.Material;

public class ItemStandard extends Item {
    public ItemStandard(int id, Material material, String texture) {
        super(id, material, texture);
        setMaxStackSize(16);
        setCraftingDifficultyAsComponent(100.0F);
        setCreativeTab(CreativeTabs.tabMaterials);
    }
}
