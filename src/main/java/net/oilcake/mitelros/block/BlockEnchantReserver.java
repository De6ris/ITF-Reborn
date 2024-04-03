package net.oilcake.mitelros.block;

import java.util.List;
import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumFace;
import net.minecraft.ITileEntityProvider;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.ItemBlock;
import net.minecraft.Material;
import net.minecraft.TileEntity;
import net.minecraft.World;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.TileEntityEnchantReserver;

public class BlockEnchantReserver extends Block implements ITileEntityProvider {
  private Icon TEXTURE_TOP;
  
  private Icon TEXTURE_BOTOTM;
  
  private Icon TEXTURE_SIDE;
  
  protected BlockEnchantReserver(int par1) {
    super(par1, Material.anvil, new BlockConstants());
    setCreativeTab(CreativeTabs.tabDecorations);
    setMaxStackSize(1);
    setLightOpacity(0);
    setLightValue(0.75F);
  }
  
  public Icon getIcon(int side, int metadata) {
    switch (side) {
      case 1:
        return this.TEXTURE_TOP;
      case 0:
        return this.TEXTURE_BOTOTM;
      case 2:
      case 3:
      case 4:
      case 5:
        return this.TEXTURE_SIDE;
    } 
    return super.getIcon(side, metadata);
  }
  
  public void registerIcons(IconRegister mt) {
    this.TEXTURE_TOP = mt.registerIcon("enchant_reserver/enchant_reserver_top");
    this.TEXTURE_BOTOTM = mt.registerIcon("enchant_reserver/enchant_reserver_bottom");
    this.TEXTURE_SIDE = mt.registerIcon("enchant_reserver/enchant_reserver_side");
  }
  
  public void getItemStacks(int id, CreativeTabs creative_tabs, List list) {
    super.getItemStacks(id, creative_tabs, list);
  }
  
  public TileEntity createNewTileEntity(World world) {
    return (TileEntity)new TileEntityEnchantReserver();
  }
  
  public void addItemBlockMaterials(ItemBlock item_block) {
    item_block.addMaterial(new Material[] { Material.iron });
  }
  
  public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
    return true;
  }
  
  public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
    super.breakBlock(world, x, y, z, block_id, metadata);
    TileEntityEnchantReserver tileEntityEnchantReserver = (TileEntityEnchantReserver)world.getBlockTileEntity(x, y, z);
    tileEntityEnchantReserver.dropAllItems();
    world.removeBlockTileEntity(x, y, z);
  }
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
    if (!world.isAirOrPassableBlock(x, y + 1, z, false))
      return false; 
    if (player.onServer()) {
      TileEntityEnchantReserver tile_entity = (TileEntityEnchantReserver)world.getBlockTileEntity(x, y, z);
      if (tile_entity != null && !tile_entity.isUsing()) {
        ((ITFPlayer)player).displayGUIEnchantReserver(x, y, z, tile_entity.getSlots());
      } else {
        return false;
      } 
    } 
    return true;
  }
}
