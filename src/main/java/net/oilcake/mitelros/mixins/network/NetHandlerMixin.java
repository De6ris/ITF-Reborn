package net.oilcake.mitelros.mixins.network;

import net.minecraft.NetHandler;
import net.oilcake.mitelros.api.ITFNetHandler;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import net.oilcake.mitelros.network.PacketUpdateNutrition;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NetHandler.class)
public class NetHandlerMixin implements ITFNetHandler {

    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
    }

    public void handleDecreaseWater(PacketDecreaseWater packet) {
    }

    @Override
    public void handleUpdateNutrition(PacketUpdateNutrition packet) {
    }
}
