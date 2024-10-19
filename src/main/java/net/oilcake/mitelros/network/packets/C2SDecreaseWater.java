package net.oilcake.mitelros.network.packets;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.network.ITFNetwork;

public class C2SDecreaseWater implements Packet {
    private final float hungerWater;

    public C2SDecreaseWater(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readFloat());
    }

    public C2SDecreaseWater(float hungerWater) {
        this.hungerWater = hungerWater;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeFloat(this.hungerWater);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        ((ITFPlayer) entityPlayer).itf$DecreaseWaterServerSide(this.hungerWater);
    }

    @Override
    public ResourceLocation getChannel() {
        return ITFNetwork.DecreaseWater;
    }
}
