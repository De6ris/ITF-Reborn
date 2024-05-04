package net.oilcake.mitelros.block;

import net.minecraft.BlockBeacon;
import net.minecraft.TileEntity;
import net.minecraft.TileEntityBeacon;
import net.minecraft.World;
import net.oilcake.mitelros.api.ITFTileEntityBeacon;

public class BlockUruBeacon extends BlockBeacon {
    public BlockUruBeacon(int par1) {
        super(par1);
        this.setHardness(5.0F);
        this.setLightValue(1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World par1World) {
        return ((ITFTileEntityBeacon) (new TileEntityBeacon())).setIsAdvanced(true);
    }
}
