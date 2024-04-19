package net.oilcake.mitelros.api;

import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.status.*;

public interface ITFPlayer {

    void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots);

    NewPlayerManager getNewPlayerManager();

    MiscManager getMiscManager();

    TemperatureManager getTemperatureManager();

    int getWater();

    int addWater(int water);

    FeastManager getFeastManager();

    void decreaseWaterServerSide(float hungerWater);

    boolean isMalnourishedFinal();

    int malnourishedLevel();

    int getCurrent_insulin_resistance_lvl();

    DrunkManager getDrunkManager();

    HuntManager getHuntManager();
}
