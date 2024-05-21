package net.oilcake.mitelros.api;

import net.oilcake.mitelros.network.*;

public interface ITFNetHandler {
    default void processEnchantReserverInfo(S2CEnchantReserverInfo packet) {
    }

    default void handleDecreaseWater(C2SDecreaseWater packet) {
    }

    default void handleUpdateNutrition(S2CUpdateNutrition packet) {
    }

    default void handleEnchantmentInfo(S2CEnchantmentInfo packet) {
    }
}
