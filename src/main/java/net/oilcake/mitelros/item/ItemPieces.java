package net.oilcake.mitelros.item;

import net.minecraft.Item;
import net.minecraft.Material;

public class ItemPieces extends Item {
    public ItemPieces(int id, Material material, String texture) {
        super(id, material, texture);
        setMaxStackSize(32);
        setCraftingDifficultyAsComponent(20.0F);
    }
}
