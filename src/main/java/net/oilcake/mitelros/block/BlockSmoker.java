package net.oilcake.mitelros.block;

import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockFurnace;
import net.minecraft.CreativeTabs;
import net.minecraft.IconRegister;
import net.minecraft.Item;
import net.minecraft.Material;
import net.oilcake.mitelros.util.ExperimentalConfig;

public class BlockSmoker extends BlockFurnace {
  protected BlockSmoker(int par1, boolean par2) {
    super(par1, Material.stone, par2);
    setCreativeTab(CreativeTabs.tabDecorations);
  }
  
  public void registerIcons(IconRegister mt) {
    this.furnaceIconFront = mt.registerIcon(this.isActive ? "smoker/smoker_front_on" : "smoker/smoker_front_off");
    this.furnaceIconTop = mt.registerIcon("smoker/smoker_top");
    this.blockIcon = mt.registerIcon("smoker/smoker_side");
  }
  
  public int getMaxHeatLevel() {
    return 2;
  }
  
  public int getIdleBlockID() {
    return Blocks.blockSmokerIdle.blockID;
  }
  
  public int getActiveBlockID() {
    return Blocks.blockSmokerBurning.blockID;
  }
  
  public int dropBlockAsEntityItem(BlockBreakInfo info) {
    if (((Boolean)ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue).booleanValue()) {
      if (info.wasExploded()) {
        dropBlockAsEntityItem(info, Block.cobblestone.blockID);
        dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.3F);
        return 0;
      } 
      dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 8, 1.0F);
      dropBlockAsEntityItem(info, Block.wood.blockID, 0, 4, 1.0F);
      return 0;
    } 
    if (info.wasExploded()) {
      dropBlockAsEntityItem(info, Block.cobblestone.blockID);
      dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.3F);
      return 0;
    } 
    return super.dropBlockAsEntityItem(info);
  }
}
