package net.oilcake.mitelros.mixins.network;

import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.oilcake.mitelros.api.ITFNetHandler;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({NetHandler.class})
public class NetHandlerMixin implements ITFNetHandler {

    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
    }

    public void handleDecreaseWater(PacketDecreaseWater packet) {
    }
}
