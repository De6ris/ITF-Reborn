package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin({BlockGravel.class})
public class BlockGravelMixin extends Block {
  protected BlockGravelMixin(int par1, Material par2Material, BlockConstants constants) {
    super(par1, par2Material, constants);
  }
  
  protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {}
  
  @Overwrite
  public int dropBlockAsEntityItem(BlockBreakInfo info) {
    if (info.getMetadata() == 1)
      return super.dropBlockAsEntityItem(info); 
    if (!info.wasExploded() && info.wasHarvestedByPlayer()) {
      int i, fortune = info.getHarvesterFortune();
      if (fortune > 3)
        fortune = 3; 
      Random rand = info.world.rand;
      if (rand.nextInt(12 - fortune * 2) > 2)
        return super.dropBlockAsEntityItem(info); 
      if (rand.nextInt(3) > 0) {
        if (rand.nextInt(16) == 0) {
          i = info.wasExploded() ? Item.chipFlint.itemID : Item.flint.itemID;
        } else {
          if (info.wasExploded())
            return super.dropBlockAsEntityItem(info); 
          i = Item.chipFlint.itemID;
        } 
      } else if (rand.nextInt(3) > 0) {
        i = Item.copperNugget.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = Item.silverNugget.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = Item.goldNugget.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = Items.nickelNugget.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = info.wasExploded() ? -1 : Item.shardObsidian.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = info.wasExploded() ? -1 : Item.shardEmerald.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = info.wasExploded() ? -1 : Item.shardDiamond.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = Item.mithrilNugget.itemID;
      } else if (rand.nextInt(3) > 0) {
        i = Items.tungstenNugget.itemID;
      } else {
        i = Item.adamantiumNugget.itemID;
      } 
      if (isNetherGravel(info.getMetadata())) {
        if (rand.nextInt(12 - fortune * 2) > 4)
          return super.dropBlockAsEntityItem(info); 
        if (rand.nextInt(8) > 0) {
          i = Item.shardNetherQuartz.itemID;
        } else if (rand.nextInt(8) > 0) {
          i = Item.goldNugget.itemID;
        } else {
          i = Items.tungstenNugget.itemID;
        } 
      } 
      if (i != -1)
        DedicatedServer.incrementTournamentScoringCounter(info.getResponsiblePlayer(), Item.getItem(i)); 
      if (info.wasHarvestedByPlayer() && (i == Item.chipFlint.itemID || i == Item.flint.itemID))
        info.getResponsiblePlayer().triggerAchievement((StatBase)AchievementList.flintFinder); 
      return dropBlockAsEntityItem(info, i);
    } 
    return super.dropBlockAsEntityItem(info);
  }
  
  @Shadow
  public boolean isNetherGravel(int metadata) {
    return false;
  }
}
