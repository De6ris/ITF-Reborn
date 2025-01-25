package net.oilcake.mitelros.item;

import huix.glacier.api.extension.item.IFusibleItem;
import net.minecraft.Item;
import net.minecraft.Material;

public class ItemPieces extends Item implements IFusibleItem {
    private final int heatLevelRequired;

    public ItemPieces(int id, Material material, String texture, int heatLevelRequired) {
        super(id, material, texture);
        setMaxStackSize(32);
        setCraftingDifficultyAsComponent(20.0F);
        this.heatLevelRequired = heatLevelRequired;
    }

    @Override
    public int getHeatLevelRequired() {
        return this.heatLevelRequired;
    }
}
