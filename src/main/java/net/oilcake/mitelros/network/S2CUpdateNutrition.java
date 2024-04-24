package net.oilcake.mitelros.network;

import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.oilcake.mitelros.api.ITFNetHandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class S2CUpdateNutrition extends Packet {
    private int protein;
    private int phytonutrients;
    private int water;
    private float temp;

    public S2CUpdateNutrition(int phytonutrients, int protein, int water, float temp) {
        this.phytonutrients = phytonutrients;
        this.protein = protein;
        this.water = water;
        this.temp = temp;
    }

    public int getProtein() {
        return protein;
    }

    public int getPhytonutrients() {
        return phytonutrients;
    }

    public int getWater() {
        return water;
    }

    public float getTemp() {
        return temp;
    }

    @Override
    public void readPacketData(DataInput dataInput) throws IOException {
        this.protein = dataInput.readInt();
        this.phytonutrients = dataInput.readInt();
        this.water = dataInput.readInt();
        this.temp = dataInput.readFloat();
    }

    @Override
    public void writePacketData(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.protein);
        dataOutput.writeInt(this.phytonutrients);
        dataOutput.writeInt(this.water);
        dataOutput.writeFloat(this.temp);
    }

    @Override
    public void processPacket(NetHandler netHandler) {
        ((ITFNetHandler) netHandler).handleUpdateNutrition(this);
    }

    @Override
    public int getPacketSize() {
        return 16;
    }
}
