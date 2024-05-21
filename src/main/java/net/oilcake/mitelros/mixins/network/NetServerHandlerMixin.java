package net.oilcake.mitelros.mixins.network;

import net.minecraft.NetHandler;
import net.minecraft.NetServerHandler;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.api.ITFNetHandler;
import net.oilcake.mitelros.network.C2SDecreaseWater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetServerHandler.class)
public abstract class NetServerHandlerMixin extends NetHandler implements ITFNetHandler {
    @Shadow
    public ServerPlayer playerEntity;

    @Override
    public void handleDecreaseWater(C2SDecreaseWater packet) {
        (this.playerEntity.getAsPlayer()).decreaseWaterServerSide(packet.hungerWater);
    }
}
