package net.oilcake.mitelros.mixins.world;

import java.util.Random;
import net.minecraft.Block;
import net.minecraft.ComponentNetherBridgeCorridor;
import net.minecraft.EnumDirection;
import net.minecraft.Item;
import net.minecraft.NBTTagCompound;
import net.minecraft.StructureBoundingBox;
import net.minecraft.StructureComponent;
import net.minecraft.WeightedRandomChestContent;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ComponentNetherBridgeCorridor.class})
public class WorldGenNetherBridgeCorridor2Mixin extends StructureComponent {
  @Shadow
  protected void func_143012_a(NBTTagCompound nbtTagCompound) {}
  
  @Shadow
  protected void func_143011_b(NBTTagCompound nbtTagCompound) {}
  
  @Overwrite
  public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
    fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 4, 5, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 1, 4, 4, 1, Block.netherFence.blockID, Block.netherFence.blockID, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 3, 4, 4, 3, Block.netherFence.blockID, Block.netherFence.blockID, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 5, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 4, 3, 5, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 1, 4, 4, Block.netherFence.blockID, Block.netherBrick.blockID, false);
    fillWithBlocks(par1World, par3StructureBoundingBox, 3, 3, 4, 3, 4, 4, Block.netherFence.blockID, Block.netherBrick.blockID, false);
    if (this.field_111021_b) {
      int i = getYWithOffset(2);
      int var5 = getXWithOffset(3, 3);
      int var6 = getZWithOffset(3, 3);
      if (par3StructureBoundingBox.isVecInside(var5, i, var6)) {
        this.field_111021_b = false;
        generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 3, 2, 3, Block.chest.blockID, chest_contents, 2 + par2Random.nextInt(4), (float[])null, (par2Random.nextInt(2) == 0) ? EnumDirection.WEST : EnumDirection.SOUTH);
      } 
    } 
    fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
    for (int var4 = 0; var4 <= 4; var4++) {
      for (int var5 = 0; var5 <= 4; var5++)
        fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, var4, -1, var5, par3StructureBoundingBox); 
    } 
    return true;
  }
  
  private static final WeightedRandomChestContent[] chest_contents = new WeightedRandomChestContent[] { 
      new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 2, 5), new WeightedRandomChestContent(Items.tungstenIngot.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenNugget.itemID, 0, 3, 7, 10), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 2, 5, 10), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 5, 10, 20), new WeightedRandomChestContent(Items.tungstenSword.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenWarHammer.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenHelmetChain.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenChestplateChain.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenLeggingsChain.itemID, 0, 1, 1, 5), 
      new WeightedRandomChestContent(Items.tungstenBootsChain.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenHelmet.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Items.tungstenChestplate.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Items.tungstenLeggings.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Items.tungstenBoots.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.warHammerGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.swordGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.battleAxeGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.helmetGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.plateGold.itemID, 0, 1, 1, 10), 
      new WeightedRandomChestContent(Item.legsGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.bootsGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Items.ignitionGold.itemID, 0, 1, 1, 18), new WeightedRandomChestContent(Items.ignitionTungsten.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.netherStalkSeeds.itemID, 0, 3, 7, 5), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 20), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.horseArmorCopper.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Items.totemOfDestroy.itemID, 0, 1, 1, 5) };
  
  @Shadow
  private boolean field_111021_b;
}
