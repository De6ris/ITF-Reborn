package net.oilcake.mitelros.item.api;

import net.minecraft.Item;
import net.minecraft.Material;

public class ItemStandard extends Item {
    public ItemStandard(int id, Material material, String texture) {
        super(id, material, texture);
        setMaxStackSize(16);
        setCraftingDifficultyAsComponent(100.0F);
    }
}
