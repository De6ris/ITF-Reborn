package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IBeaconUpdateHandler;
import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.TileEntityBeacon;
import net.oilcake.mitelros.api.ITFTileEntityBeacon;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;

public class BeaconListener implements IBeaconUpdateHandler {
    @Override
    public boolean onBlockValidModify(TileEntityBeacon tileEntityBeacon, int blockID, boolean original) {
        if (((ITFTileEntityBeacon) tileEntityBeacon).itf$GetIsAdvanced()) {
            if (blockID == Block.blockGold.blockID || blockID == Block.blockSilver.blockID || blockID == Block.blockCopper.blockID) {
                return false;
            }
        }
        if (blockID == Block.blockAncientMetal.blockID || blockID == Blocks.blockNickel.blockID || blockID == Blocks.blockTungsten.blockID) {
            return true;
        }
        return original;
    }

    @Override
    public boolean onItemValidModify(TileEntityBeacon tileEntityBeacon, ItemStack itemStack, boolean original) {
        Item item = itemStack.getItem();
        return original || item == Items.tungstenIngot || item == Items.nickelIngot || item == Items.uruIngot;
    }
}
