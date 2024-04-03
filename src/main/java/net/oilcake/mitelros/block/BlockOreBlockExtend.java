package net.oilcake.mitelros.block;

import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockConstants;
import net.minecraft.BlockHardness;
import net.minecraft.CreativeTabs;
import net.minecraft.ItemIngot;
import net.minecraft.ItemRock;
import net.minecraft.Material;

public class BlockOreBlockExtend extends Block {
  public BlockOreBlockExtend(int par1, Material material) {
    super(par1, material, new BlockConstants());
    modifyMinHarvestLevel(1);
    setCreativeTab(CreativeTabs.tabBlock);
    setMaxStackSize(4);
    setHardnessRelativeToWood(BlockHardness.log);
  }
  
  public float getCraftingDifficultyAsComponent(int metadata) {
    return this.blockMaterial.isMetal() ? (ItemIngot.getCraftingDifficultyAsComponent(this.blockMaterial) * 9.0F) : (ItemRock.getCraftingDifficultyAsComponent(this.blockMaterial) * ((this.blockMaterial == Material.quartz) ? 4 : 9));
  }
  
  public int dropBlockAsEntityItem(BlockBreakInfo info) {
    return super.dropBlockAsEntityItem(info);
  }
}
