package net.oilcake.mitelros.api;

import net.minecraft.IInventory;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.status.*;

public interface ITFPlayer {
    default void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
    }

    void displayGuiMinePocket(IInventory inventory);

    NewPlayerManager getNewPlayerManager();

    MiscManager getMiscManager();

    TemperatureManager getTemperatureManager();

    int getWater();

    int addWater(int water);

    FeastManager getFeastManager();

    void decreaseWaterServerSide(float hungerWater);

    boolean isMalnourishedFinal();

    int malnourishedLevel();

    DrunkManager getDrunkManager();

    HuntManager getHuntManager();
}
