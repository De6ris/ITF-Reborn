package net.oilcake.mitelros.api;

import net.oilcake.mitelros.network.C2SDecreaseWater;
import net.oilcake.mitelros.network.S2CEnchantReserverInfo;
import net.oilcake.mitelros.network.S2CEnchantmentInfo;
import net.oilcake.mitelros.network.S2CUpdateNutrition;

public interface ITFNetHandler {
    default void processEnchantReserverInfo(S2CEnchantReserverInfo packet) {
    }

    default void handleDecreaseWater(C2SDecreaseWater packet) {
    }

    default void handleUpdateNutrition(S2CUpdateNutrition packet){}
    default void handleEnchantmentInfo(S2CEnchantmentInfo packet){}
}
