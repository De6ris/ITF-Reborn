package net.oilcake.mitelros.network;

import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.oilcake.mitelros.api.ITFNetHandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class S2CUpdateITFStatus extends Packet {
    private int water;

    public S2CUpdateITFStatus() {
    }

    public S2CUpdateITFStatus(int water) {
        this.water = water;
    }

    public int getWater() {
        return water;
    }

    @Override
    public void readPacketData(DataInput dataInput) throws IOException {
        this.water = dataInput.readInt();
    }

    @Override
    public void writePacketData(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.water);
    }

    @Override
    public void processPacket(NetHandler netHandler) {
        ((ITFNetHandler) netHandler).itf$HandleUpdateITFStatus(this);
    }

    @Override
    public int getPacketSize() {
        return 4;
    }
}
