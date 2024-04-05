package net.oilcake.mitelros.block;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class BlockFlowerExtend extends BlockFlower {
  public static final int LUMINESCENT_HERB = 0;
  
  public static final int AZURE_BLUET = 1;
  
  public static final int CORNFLOWER = 2;
  
  public static final int LILY_OF_THE_VALLEY = 3;
  
  public static final int TULIP_PINK = 4;
  
  public static final int TULIP_WHITE = 5;
  
  public static final int TULIP_RED = 6;
  
  public static final int AGAVE = 7;
  
  public static final String[] types = new String[] { "luminescent_herb", "azure_bluet", "cornflower", "lily_of_the_valley", "pink_tulip", "white_tulip", "red_tulip", "agave" };
  
  private Icon[] icons;
  
  private static int[] candidates = new int[types.length];
  
  protected BlockFlowerExtend(int id, Material material) {
    super(id, material);
  }
  
  protected BlockFlowerExtend(int id) {
    this(id, Material.plants);
    setHardness(0.0F);
    setStepSound(soundGrassFootstep);
    setUnlocalizedName("flower");
    setTextureName("flowers/");
    setMaxStackSize(32);
  }
  
  public void registerIcons(IconRegister par1IconRegister) {
    this.icons = registerIcons(par1IconRegister, types, getTextureName());
  }
  
  public Icon getIcon(int side, int metadata) {
    return this.icons[getBlockSubtype(metadata)];
  }
  
  public String getMetadataNotes() {
    String[] array = new String[types.length];
    for (int i = 0; i < types.length; i++) {
      if (types[i] != null)
        array[i] = i + "=" + StringHelper.capitalize(types[i]); 
    } 
    return StringHelper.implode(array, ", ", true, false);
  }
  
  public boolean isValidMetadata(int metadata) {
    return (metadata >= 0 && metadata < types.length && types[metadata] != null);
  }
  
  public int getBlockSubtypeUnchecked(int metadata) {
    return metadata;
  }
  
  public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
    return (other_block == this) ? ((other_block_metadata != metadata)) : super.canBeReplacedBy(metadata, other_block, other_block_metadata);
  }
  
  public void setBlockBoundsBasedOnStateAndNeighbors(IBlockAccess block_access, int x, int y, int z) {
    int metadata = block_access.getBlockMetadata(x, y, z);
    float width = 0.2F;
    if (metadata == 0) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else if (metadata == 1) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else if (metadata == 2) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else if (metadata == 3) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else if (metadata == 4) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else if (metadata == 5) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else if (metadata == 6) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else if (metadata == 7) {
      setBlockBoundsForCurrentThread((0.5F - width), 0.0D, (0.5F - width), (0.5F + width), 0.75D, (0.5F + width));
    } else {
      Minecraft.setErrorMessage("setBlockBoundsBasedOnStateAndNeighbors: unhandled case");
    } 
  }
  
  public int getRandomSubtypeForBiome(Random random, BiomeGenBase biome) {
    if (random.nextInt(2) == 0)
      return 7; 
    int num_candidates = 0;
    for (int i = 0; i < types.length; i++) {
      if (types[i] != null && isBiomeSuitable(biome, i))
        candidates[num_candidates++] = i; 
    } 
    return (num_candidates == 0) ? -1 : candidates[random.nextInt(num_candidates)];
  }
  
  public int getRandomSubtypeThatCanOccurAt(World world, int x, int y, int z) {
    BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
    int subtype = getRandomSubtypeForBiome(world.rand, biome);
    if (subtype < 0)
      return -1; 
    while (!canOccurAt(world, x, y, z, subtype))
      subtype = getRandomSubtypeForBiome(world.rand, biome); 
    return subtype;
  }
  
  public boolean isBiomeSuitable(BiomeGenBase biome, int metadata) {
    if (!isValidMetadata(metadata)) {
      Minecraft.setErrorMessage("isBiomeSuitable: invalid metadata " + metadata);
      return false;
    } 
    int subtype = getBlockSubtype(metadata);
    if (types[subtype] == null) {
      Minecraft.setErrorMessage("isBiomeSuitable: invalid subtype " + subtype);
      return false;
    } 
    if (biome.isSwampBiome())
      return false; 
    if (subtype == 0 && biome.temperature < BiomeGenBase.plains.temperature)
      return false; 
    if (subtype != 0 && subtype != 7 && biome.temperature < BiomeGenBase.forestHills.temperature)
      return false; 
    if (subtype == 7 && biome.temperature < BiomeGenBase.icePlains.temperature)
      return false; 
    return !biome.isJungleBiome();
  }
  
  public boolean canOccurAt(World world, int x, int y, int z, int metadata) {
    return (isBiomeSuitable(world.getBiomeGenForCoords(x, z), metadata) && super.canOccurAt(world, x, y, z, metadata));
  }
  
  public int getPatchSize(int metadata, BiomeGenBase biome) {
    if (!isValidMetadata(metadata))
      Minecraft.setErrorMessage("getPatchSize: invalid metadata " + metadata); 
    int subtype = getBlockSubtype(metadata);
    if (subtype > 8)
      return 0; 
    return (biome != BiomeGenBase.plains && !biome.isJungleBiome()) ? 16 : 64;
  }
  
  public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
    return (isBiomeSuitable(world.getBiomeGenForCoords(x, z), metadata) && super.isLegalAt(world, x, y, z, metadata));
  }

}
