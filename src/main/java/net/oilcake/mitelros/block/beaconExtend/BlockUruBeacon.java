package net.oilcake.mitelros.block.beaconExtend;

import net.minecraft.*;

public class BlockUruBeacon extends BlockBeacon {
    public BlockUruBeacon(int par1) {
        super(par1);
        this.setHardness(5.0F);
        this.setLightValue(1.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityUruBeacon();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        TileEntityUruBeacon tile_entity;
        if (player.onServer() && (tile_entity = (TileEntityUruBeacon) world.getBlockTileEntity(x, y, z)) != null) {
            player.displayGUIUruBeacon(tile_entity);
        }
        return true;
    }
}
