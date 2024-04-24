package net.oilcake.mitelros.network;

import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.oilcake.mitelros.api.ITFNetHandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class S2CEnchantmentInfo extends Packet {
    private int[] info;

    public S2CEnchantmentInfo() {
    }

    public S2CEnchantmentInfo(int[] info) {
        this.info = info;
    }

    public int[] getInfo() {
        return info;
    }

    @Override
    public void readPacketData(DataInput dataInput) throws IOException {
        for (int i = 0; i < 12; i++) {
            this.info[i] = dataInput.readByte();
        }
    }

    @Override
    public void writePacketData(DataOutput dataOutput) throws IOException {
        for (int i = 0; i < 12; i++) {
            dataOutput.writeByte(this.info[i]);
        }
    }

    @Override
    public void processPacket(NetHandler netHandler) {
        ((ITFNetHandler) netHandler).handleEnchantmentInfo(this);
    }

    @Override
    public int getPacketSize() {
        return 12;
    }
}
