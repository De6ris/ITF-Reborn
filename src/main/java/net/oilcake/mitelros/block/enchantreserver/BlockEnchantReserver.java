package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;

public class BlockEnchantReserver extends Block implements ITileEntityProvider {
    private Icon TEXTURE_TOP;

    private Icon TEXTURE_BOTTOM;

    private Icon TEXTURE_SIDE;

    public BlockEnchantReserver(int par1) {
        super(par1, Material.anvil, new BlockConstants());
        setCreativeTab(CreativeTabs.tabDecorations);
        setMaxStackSize(1);
        setLightOpacity(0);
        setLightValue(0.75F);
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        return switch (side) {
            case 1 -> this.TEXTURE_TOP;
            case 0 -> this.TEXTURE_BOTTOM;
            case 2, 3, 4, 5 -> this.TEXTURE_SIDE;
            default -> super.getIcon(side, metadata);
        };
    }

    @Override
    public void registerIcons(IconRegister mt) {
        this.TEXTURE_TOP = mt.registerIcon("enchant_reserver/top");
        this.TEXTURE_BOTTOM = mt.registerIcon("enchant_reserver/bottom");
        this.TEXTURE_SIDE = mt.registerIcon("enchant_reserver/side");
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnchantReserver();
    }

    @Override
    public void addItemBlockMaterials(ItemBlock item_block) {
        item_block.addMaterial(Material.iron);
    }

    @Override
    public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
        super.breakBlock(world, x, y, z, block_id, metadata);
        TileEntityEnchantReserver tileEntityEnchantReserver = (TileEntityEnchantReserver) world.getBlockTileEntity(x, y, z);
        tileEntityEnchantReserver.dropAllItems();
        world.removeBlockTileEntity(x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        if (!world.isAirOrPassableBlock(x, y + 1, z, false))
            return false;
        if (player.onServer()) {
            TileEntityEnchantReserver tile_entity = (TileEntityEnchantReserver) world.getBlockTileEntity(x, y, z);
            if (tile_entity != null && !tile_entity.isUsing()) {
                player.itf$DisplayGUIEnchantReserver(x, y, z, tile_entity.getSlots());
            } else {
                return false;
            }
        }
        return true;
    }
}
