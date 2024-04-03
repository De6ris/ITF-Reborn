package net.oilcake.mitelros.mixins.network;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFNetHandler;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

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
