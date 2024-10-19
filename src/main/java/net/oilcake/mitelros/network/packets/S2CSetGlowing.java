package net.oilcake.mitelros.network.packets;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFEntityLivingBase;
import net.oilcake.mitelros.network.ITFNetwork;

public class S2CSetGlowing implements Packet {
    private final int entityID;
    private final int duration;

    public S2CSetGlowing(int entityID, int duration) {
        this.entityID = entityID;
        this.duration = duration;
    }

    public S2CSetGlowing(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readInt(), packetByteBuf.readInt());
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.entityID);
        packetByteBuf.writeInt(this.duration);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        Entity entityByID = Minecraft.getMinecraft().theWorld.getEntityByID(this.entityID);
        if (entityByID instanceof EntityLivingBase entityLivingBase) {
            ((ITFEntityLivingBase) entityLivingBase).itf$SetGlow(duration);
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return ITFNetwork.SetGlowing;
    }
}
