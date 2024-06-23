package net.oilcake.mitelros.api;

import net.oilcake.mitelros.network.C2SDecreaseWater;
import net.oilcake.mitelros.network.S2CEnchantReserverInfo;
import net.oilcake.mitelros.network.S2CEnchantmentInfo;
import net.oilcake.mitelros.network.S2CUpdateITFStatus;

public interface ITFNetHandler {
    default void itf$ProcessEnchantReserverInfo(S2CEnchantReserverInfo packet) {
    }

    default void itf$HandleDecreaseWater(C2SDecreaseWater packet) {
    }

    default void itf$HandleUpdateITFStatus(S2CUpdateITFStatus packet) {
    }

    default void itf$HandleEnchantmentInfo(S2CEnchantmentInfo packet) {
    }
}
