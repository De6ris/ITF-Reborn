package net.oilcake.mitelros.network;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.item.minePocket.ItemMinePocket;
import net.oilcake.mitelros.item.minePocket.MinePocketInventory;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class S2COpenWindow extends Packet100OpenWindow {
    public EnumInventoryType enumInventoryType;

    public S2COpenWindow() {
        super();
    }

    public S2COpenWindow(int windowId, EnumInventoryType enumInventoryType, String windowTitle, int slotsCount, boolean useProvidedWindowTitle) {
        super(windowId, 255, windowTitle, slotsCount, useProvidedWindowTitle);// 255 is dummy
        this.enumInventoryType = enumInventoryType;
    }

    @Override
    public boolean hasCoords() {
        return this.enumInventoryType.hasCoords;
    }

    @Override
    public boolean hasTileEntity() {
        return this.hasCoords();
    }

    @Override
    public void readPacketData(DataInput par1DataInput) throws IOException {
        this.windowId = par1DataInput.readByte() & 0xFF;
        this.enumInventoryType = EnumInventoryType.getFromID(par1DataInput.readByte() & 0xFF);
        this.windowTitle = Packet100OpenWindow.readString(par1DataInput, 32767);
        this.slotsCount = par1DataInput.readByte() & 0xFF;
        this.useProvidedWindowTitle = par1DataInput.readBoolean();
        if (this.hasCoords()) {
            this.x = par1DataInput.readInt();
            this.y = par1DataInput.readInt();
            this.z = par1DataInput.readInt();
        }
    }

    @Override
    public void writePacketData(DataOutput par1DataOutput) throws IOException {
        par1DataOutput.writeByte(this.windowId & 0xFF);
        par1DataOutput.writeByte(this.enumInventoryType.id & 0xFF);
        Packet100OpenWindow.writeString(this.windowTitle, par1DataOutput);
        par1DataOutput.writeByte(this.slotsCount & 0xFF);
        par1DataOutput.writeBoolean(this.useProvidedWindowTitle);
        if (this.hasCoords()) {
            if (!this.has_set_coords) {
                Minecraft.setErrorMessage("S2COpenWindow: coords not set for type " + this.enumInventoryType);
            }
            par1DataOutput.writeInt(this.x);
            par1DataOutput.writeInt(this.y);
            par1DataOutput.writeInt(this.z);
        }
    }

    @Override
    public int getPacketSize() {
        int bytes = 2 + Packet.getPacketSizeOfString(this.windowTitle) + 2;
        if (this.hasCoords()) {
            bytes += 12;
        }
        return bytes;
    }

    @Override
    public void handleOpenWindow(EntityClientPlayerMP player) {
        WorldClient world = player.worldObj.getAsWorldClient();
        TileEntity tile_entity = world.getBlockTileEntity(this.x, this.y, this.z);
        if (this.hasTileEntity() && tile_entity == null) {
            Minecraft.setErrorMessage("handleOpenWindow: no tile entity found at " + StringHelper.getCoordsAsString(this.x, this.y, this.z));
        }

        switch (this.enumInventoryType) {
            case EnchantReserver -> {
                ((ITFPlayer) player).displayGUIEnchantReserver(this.x, this.y, this.z, new EnchantReserverSlots(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
                player.openContainer.windowId = this.windowId;
            }
            case MinePocket -> {
                ((ITFPlayer) player).displayGuiMinePocket(new MinePocketInventory(this.windowTitle, false, player.getHeldItemStack()));
                player.openContainer.windowId = this.windowId;
            }
            default -> Minecraft.setErrorMessage("handleOpenWindow: type not handled " + this.enumInventoryType);
        }
    }

    public enum EnumInventoryType {
        EnchantReserver(0, true),
        MinePocket(1, false),
        ;

        public final int id;
        public final boolean hasCoords;

        EnumInventoryType(int id, boolean hasCoords) {
            this.id = id;
            this.hasCoords = hasCoords;
        }

        public static EnumInventoryType getFromID(int id) {
            return Arrays.stream(EnumInventoryType.values()).filter(x -> x.id == id).findFirst().orElseThrow(NoSuchElementException::new);
        }
    }
}
