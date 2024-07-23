package net.oilcake.mitelros.api;

import net.minecraft.Potion;
import net.minecraft.TileEntityBeacon;

public interface ITFTileEntityBeacon {
    TileEntityBeacon itf$SetIsAdvanced(boolean isAdvanced);

    boolean itf$GetIsAdvanced();

    Potion[][] itf$GetITFEffectList();
}
