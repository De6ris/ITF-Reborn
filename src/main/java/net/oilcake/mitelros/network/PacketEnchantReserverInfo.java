package net.oilcake.mitelros.network;

import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.oilcake.mitelros.api.ITFNetHandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PacketEnchantReserverInfo extends Packet {
    private int EXP;

    public PacketEnchantReserverInfo() {
    }

    public PacketEnchantReserverInfo(int exp) {
        this.EXP = exp;
    }

    public int getEXP() {
        return this.EXP;
    }

    public void readPacketData(DataInput dataInput) throws IOException {
        this.EXP = dataInput.readInt();
    }

    public void writePacketData(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.EXP);
    }

    public int getPacketSize() {
        return 2;
    }

    public void processPacket(NetHandler var1) {
        ((ITFNetHandler) var1).processEnchantReserverInfo(this);
    }
}
