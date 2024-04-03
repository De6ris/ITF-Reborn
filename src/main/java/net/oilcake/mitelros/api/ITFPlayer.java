package net.oilcake.mitelros.api;

import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.status.*;

public interface ITFPlayer {
    void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots);

    boolean InFreeze();

    float getBodyTemperature();

    NewPlayerManager getNewPlayerManager();

    DiarrheaManager getDiarrheaManager();

    int getFreezingCooldown();

    int getWater();

    int addWater(int water);

    FeastManager getFeastManager();

    void decreaseWaterServerSide(float hungerWater);

    boolean isMalnourishedFin();

    boolean isMalnourishedLv1();

    boolean isMalnourishedLv2();

    boolean isMalnourishedLv3();

    boolean DuringDehydration();

    int getCurrent_insulin_resistance_lvl();

    void setPhytonutrients(int phytonutrients);

    void setProtein(int protein);

    int getPhytonutrients();

    int getProtein();

    void addFreezingCooldown(int dummy);

    DrunkManager getDrunkManager();

    HuntManager getHuntManager();
}
