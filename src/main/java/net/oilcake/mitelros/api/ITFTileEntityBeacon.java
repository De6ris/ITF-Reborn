package net.oilcake.mitelros.api;

import net.minecraft.Potion;
import net.minecraft.TileEntityBeacon;

public interface ITFTileEntityBeacon {
    TileEntityBeacon setIsAdvanced(boolean isAdvanced);

    boolean getIsAdvanced();

    Potion[][] getITFEffectList();
}
