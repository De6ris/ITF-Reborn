package net.oilcake.mitelros.mixins.world;

import java.util.Random;
import net.minecraft.Block;
import net.minecraft.Minecraft;
import net.minecraft.World;
import net.minecraft.WorldGenMinable;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({WorldGenMinable.class})
public class WorldGenMinableMixin {
  @Shadow
  private int minableBlockId;
  
  @Shadow
  private int minable_block_metadata;
  
  @Shadow
  private int numberOfBlocks;
  
  @Shadow
  private int blockToReplace;
  
  @Shadow
  private boolean vein_size_increases_with_depth;
  
  @Overwrite
  public int getMinVeinHeight(World world) {
    if (world.isUnderworld())
      return 0; 
    Block block = Block.blocksList[this.minableBlockId];
    if (block == Block.dirt)
      return 32; 
    if (block == Block.gravel)
      return 24; 
    if (block == Block.oreCoal)
      return 16; 
    if (block == Block.oreCopper)
      return 0; 
    if (block == Block.oreSilver)
      return 0; 
    if (block == Block.oreGold)
      return 0; 
    if (block == Block.oreIron)
      return 0; 
    if (block == Blocks.oreNickel)
      return 0; 
    if (block == Blocks.oreTungsten)
      return 0; 
    if (block == Block.oreMithril)
      return 0; 
    if (block != Block.oreAdamantium && block != Block.silverfish) {
      if (block == Block.oreRedstone)
        return 0; 
      if (block == Block.oreDiamond)
        return 0; 
      if (block == Block.oreLapis)
        return 8; 
      Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + block.blockID);
      return -1;
    } 
    return 0;
  }
  
  @Overwrite
  public int getMaxVeinHeight(World world) {
    if (world.isUnderworld())
      return 255; 
    Block block = Block.blocksList[this.minableBlockId];
    if (block == Block.dirt)
      return 128; 
    if (block == Block.gravel)
      return 128; 
    if (block == Block.oreCoal)
      return 96; 
    if (block == Block.oreCopper)
      return 128; 
    if (block == Block.oreSilver)
      return 96; 
    if (block == Block.oreGold)
      return 48; 
    if (block == Block.oreIron)
      return 64; 
    if (block == Blocks.oreNickel)
      return 48; 
    if (block == Blocks.oreTungsten)
      return 32; 
    if (block == Block.oreMithril)
      return 32; 
    if (block != Block.oreAdamantium && block != Block.silverfish) {
      if (block == Block.oreRedstone)
        return 24; 
      if (block == Block.oreDiamond)
        return 32; 
      if (block == Block.oreLapis)
        return 40; 
      Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + block.blockID);
      return -1;
    } 
    return 24;
  }
  
  @Overwrite
  public int getRandomVeinHeight(World world, Random rand) {
    float relative_height;
    Block block = Block.blocksList[this.minableBlockId];
    if (world.isUnderworld()) {
      if (world.underworld_y_offset != 0) {
        if (block == Block.oreAdamantium || block == Blocks.oreTungsten)
          return rand.nextInt(16 + world.underworld_y_offset); 
        if (block instanceof net.minecraft.BlockOre && rand.nextFloat() < 0.75F)
          return rand.nextInt(16 + world.underworld_y_offset); 
      } 
      return rand.nextInt(256);
    } 
    if (block == Block.dirt) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height <= rand.nextFloat());
    } else if (block == Block.gravel) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height <= rand.nextFloat());
    } else if (block == Block.oreCoal) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height <= rand.nextFloat());
    } else if (block == Block.oreCopper) {
      if (rand.nextInt(2) == 0) {
        relative_height = rand.nextFloat() * 0.6F + 0.4F;
      } else {
        do {
          relative_height = rand.nextFloat();
        } while (relative_height >= rand.nextFloat());
      } 
    } else if (block == Block.oreSilver) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height >= rand.nextFloat());
    } else if (block == Block.oreGold) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height >= rand.nextFloat());
    } else if (block == Block.oreIron) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height >= rand.nextFloat());
    } else if (block == Blocks.oreNickel) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height >= rand.nextFloat());
    } else if (block == Blocks.oreTungsten) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height >= rand.nextFloat());
    } else if (block == Block.oreMithril) {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height >= rand.nextFloat());
    } else if (block != Block.oreAdamantium && block != Block.silverfish) {
      if (block == Block.oreRedstone) {
        do {
          relative_height = rand.nextFloat();
        } while (relative_height >= rand.nextFloat());
      } else if (block == Block.oreDiamond) {
        do {
          relative_height = rand.nextFloat();
        } while (relative_height >= rand.nextFloat());
      } else {
        if (block != Block.oreLapis) {
          Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + this.minableBlockId);
          return -1;
        } 
        relative_height = (rand.nextFloat() + rand.nextFloat()) / 2.0F;
      } 
    } else {
      do {
        relative_height = rand.nextFloat();
      } while (relative_height >= rand.nextFloat());
    } 
    int min_height = getMinVeinHeight(world);
    int height_range = getMaxVeinHeight(world) - min_height + 1;
    return min_height + (int)(relative_height * height_range);
  }
}
