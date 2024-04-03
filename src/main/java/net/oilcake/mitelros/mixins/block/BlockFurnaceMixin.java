package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.util.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockFurnace.class})
public abstract class BlockFurnaceMixin extends BlockDirectionalWithTileEntity {
  protected BlockFurnaceMixin(int id, Material material, BlockConstants constants) {
    super(id, material, constants);
  }
  
  @Overwrite
  public int dropBlockAsEntityItem(BlockBreakInfo info) {
    if (((Boolean) Config.TagBenchingV2.get())) {
      Block model_block, furnace_block = Block.getBlock(getIdleBlockID());
      if (furnace_block == Block.furnaceClayIdle) {
        if (!info.wasExploded())
          return dropBlockAsEntityItem(info, Item.clay.itemID, 0, 16, 1.0F); 
        return dropBlockAsEntityItem(info, Item.clay.itemID, 0, 4, 1.25F);
      } 
      if (furnace_block == Block.furnaceHardenedClayIdle) {
        if (!info.wasExploded())
          return dropBlockAsEntityItem(info, Item.brick.itemID, 0, 32, 1.0F); 
        return dropBlockAsEntityItem(info, Item.brick.itemID, 0, 4, 1.25F);
      } 
      if (furnace_block == Block.furnaceSandstoneIdle) {
        model_block = Block.sandStone;
      } else if (furnace_block == Block.furnaceIdle) {
        model_block = Block.cobblestone;
      } else if (furnace_block == Block.furnaceObsidianIdle) {
        model_block = Block.obsidian;
      } else if (furnace_block == Block.furnaceNetherrackIdle) {
        model_block = Block.netherrack;
      } else {
        return 0;
      } 
      if (info.wasExploded())
        return model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0)); 
      return model_block.dropBlockAsEntityItem(info, model_block.blockID, 0, 8, 1.0F);
    } 
    if (info.wasExploded()) {
      Block model_block, furnace_block = Block.getBlock(getIdleBlockID());
      if (furnace_block == Block.furnaceClayIdle)
        return 0; 
      if (furnace_block == Block.furnaceSandstoneIdle) {
        model_block = Block.sandStone;
      } else if (furnace_block == Block.furnaceIdle) {
        model_block = Block.cobblestone;
      } else if (furnace_block == Block.furnaceObsidianIdle) {
        model_block = Block.obsidian;
      } else {
        if (furnace_block != Block.furnaceNetherrackIdle)
          return 0; 
        model_block = Block.netherrack;
      } 
      return model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0));
    } 
    return super.dropBlockAsEntityItem(info);
  }
  
  @Shadow
  public abstract int getIdleBlockID();
  
  @Overwrite
  public float getCraftingDifficultyAsComponent(int metadata) {
    return 1920.0F;
  }
}
