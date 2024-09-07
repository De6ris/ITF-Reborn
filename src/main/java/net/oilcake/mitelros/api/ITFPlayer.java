package net.oilcake.mitelros.api;

import net.minecraft.IInventory;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverInventory;
import net.oilcake.mitelros.status.*;

public interface ITFPlayer {
    default void itf$DisplayGUIEnchantReserver(int x, int y, int z, EnchantReserverInventory slots) {
    }

    default void itf$DisplayGuiMinePocket(IInventory minePocketInventory) {
    }

    NewPlayerManager itf_GetNewPlayerManager();

    MiscManager itf_GetMiscManager();

    int itf$GetWater();

    int itf$AddWater(int water);

    FeastManager itf$GetFeastManager();

    void itf$DecreaseWaterServerSide(float hungerWater);

    boolean itf$IsMalnourishedFinal();

    int itf$MalnourishedLevel();

    DrunkManager itf$GetDrunkManager();

    HuntManager itf$GetHuntManager();
}
