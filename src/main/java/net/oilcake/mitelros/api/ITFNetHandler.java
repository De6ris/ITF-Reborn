package net.oilcake.mitelros.api;

import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import net.oilcake.mitelros.network.PacketEnchantmentInfo;
import net.oilcake.mitelros.network.PacketUpdateNutrition;

public interface ITFNetHandler {
    default void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
    }

    default void handleDecreaseWater(PacketDecreaseWater packet) {
    }

    default void handleUpdateNutrition(PacketUpdateNutrition packet){}
    default void handleEnchantmentInfo(PacketEnchantmentInfo packet){}
}
