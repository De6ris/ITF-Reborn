package net.oilcake.mitelros.api;

import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;

public interface ITFNetHandler {
    default void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
    }

    default void handleDecreaseWater(PacketDecreaseWater packet) {
    }
}
