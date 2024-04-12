package net.oilcake.mitelros.mixins.network;

import net.minecraft.INetworkManager;
import net.minecraft.NetHandler;
import net.minecraft.NetServerHandler;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.api.ITFNetHandler;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({NetServerHandler.class})
public abstract class NetServerHandlerMixin extends NetHandler implements ITFNetHandler {
    @Shadow
    public ServerPlayer playerEntity;

    public void handleDecreaseWater(PacketDecreaseWater packet) {
        (this.playerEntity.getAsPlayer()).decreaseWaterServerSide(packet.hungerWater);
    }

    @Shadow
    public boolean isServerHandler() {
        return false;
    }

    @Shadow
    public INetworkManager getNetManager() {
        return null;
    }
}
