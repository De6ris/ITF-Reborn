package net.oilcake.mitelros.network.packets;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.network.ITFNetwork;

public class S2CBossInfo implements Packet {
    boolean found = false;
    int x;
    int y;
    int z;

    public S2CBossInfo(int x, int y, int z) {
        this.found = true;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public S2CBossInfo(PacketByteBuf packetByteBuf) {
        this.found = packetByteBuf.readBoolean();
        if (found) {
            this.x = packetByteBuf.readInt();
            this.y = packetByteBuf.readInt();
            this.z = packetByteBuf.readInt();
        }
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBoolean(this.found);
        if (this.found) {
            packetByteBuf.writeInt(this.x);
            packetByteBuf.writeInt(this.y);
            packetByteBuf.writeInt(this.z);
        }
    }// if found, add pos, if not found, won't add pos

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (this.found) {
            String s = this.directionSuggest(this.x - entityPlayer.getBlockPosX(), this.y - entityPlayer.getBlockPosY(), this.z - entityPlayer.getBlockPosZ());

        } else {

        }
    }

    private String directionSuggest(int dx, int dy, int dz) {
        int maxIndex = 0;
        int max = Math.abs(dx);
        if (Math.abs(dy) > max) {
            maxIndex = 1;
            max = Math.abs(dy);
        }
        if (Math.abs(dz) > max) {
            maxIndex = 2;
        }
        return switch (maxIndex) {
            case 0 -> dx > 0 ? "东" : "西";
            case 1 -> dy > 0 ? "上" : "下";
            default -> dz > 0 ? "南" : "北";
        };
    }

    @Override
    public ResourceLocation getChannel() {
        return ITFNetwork.BossInfo;
    }
}
