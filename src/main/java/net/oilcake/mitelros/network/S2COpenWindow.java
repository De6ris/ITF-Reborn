package net.oilcake.mitelros.network;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class S2COpenWindow extends Packet100OpenWindow {
    public int itf_inventoryType;

    public S2COpenWindow() {
        super();
    }

    public S2COpenWindow(int windowId, int inventoryType, String windowTitle, int slotsCount, boolean useProvidedWindowTitle) {
        super(windowId, inventoryType, windowTitle, slotsCount, useProvidedWindowTitle);
    }

    @Override
    public boolean hasCoords() {
        return true;
    }

    @Override
    public boolean hasTileEntity() {
        return this.hasCoords();
    }

    @Override
    public void readPacketData(DataInput par1DataInput) throws IOException {
        this.windowId = par1DataInput.readByte() & 0xFF;
        this.itf_inventoryType = par1DataInput.readByte() & 0xFF;
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
        par1DataOutput.writeByte(this.itf_inventoryType & 0xFF);
        Packet100OpenWindow.writeString(this.windowTitle, par1DataOutput);
        par1DataOutput.writeByte(this.slotsCount & 0xFF);
        par1DataOutput.writeBoolean(this.useProvidedWindowTitle);
        if (this.hasCoords()) {
            if (!this.has_set_coords) {
                Minecraft.setErrorMessage("S2COpenWindow: coords not set for type " + this.itf_inventoryType);
            }
            par1DataOutput.writeInt(this.x);
            par1DataOutput.writeInt(this.y);
            par1DataOutput.writeInt(this.z);
        }
    }

    @Override
    public void processPacket(NetHandler netHandler) {
        netHandler.handleOpenWindow(this);
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
        if (this.itf_inventoryType == 0) {
            ((ITFPlayer) player).displayGUIEnchantReserver(this.x, this.y, this.z, new EnchantReserverSlots(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
            player.openContainer.windowId = this.windowId;
        } else {
            Minecraft.setErrorMessage("handleOpenWindow: type not handled " + this.itf_inventoryType);
        }
    }
}
