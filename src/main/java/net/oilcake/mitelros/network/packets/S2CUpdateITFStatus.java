package net.oilcake.mitelros.network.packets;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.network.ITFNetwork;

public class S2CUpdateITFStatus implements Packet {
    private final int water;

    public S2CUpdateITFStatus(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readInt());
    }

    public S2CUpdateITFStatus(int water) {
        this.water = water;
    }

    public int getWater() {
        return water;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.water);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        ((ITFFoodStats) entityPlayer.getFoodStats()).itf$SetWater(this.water, false);
    }

    @Override
    public ResourceLocation getChannel() {
        return ITFNetwork.UpdateITFStatus;
    }
}
