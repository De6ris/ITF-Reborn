package net.oilcake.mitelros.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFBiomeDecorator;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.world.WorldGenStoneCone;

import java.util.Random;

public class BiomeWindsweptPleatu extends BiomeGenBase {
  public BiomeWindsweptPleatu(int par1) {
    super(par1);
    removeEntityFromSpawnableLists(EntitySkeleton.class);
    removeEntityFromSpawnableLists(EntityCow.class);
    removeEntityFromSpawnableLists(EntityChicken.class);
    removeEntityFromSpawnableLists(EntityPig.class);
    this.spawnableMonsterList.add(new SpawnListEntry(EntityStray.class, 100, 1, 4));
    this.spawnableCreatureList.add(new SpawnListEntry(EntityDireWolf.class, 20, 3, 6));
    ((ITFBiomeDecorator)this.theBiomeDecorator).setFlowersPerChunk(-999);
    ((ITFBiomeDecorator)this.theBiomeDecorator).setGrassPerChunk(1);
    ((ITFBiomeDecorator)this.theBiomeDecorator).setFlowersExtendPerChunk(-999);
    this.topBlock = (byte)Block.grass.blockID;
    this.fillerBlock = (byte)Block.cobblestone.blockID;
    setColor(10526880);
    setBiomeName("WindsweptPleatu");
    setEnableSnow();
    setMinMaxHeight(0.9F, 4.0F);
    setTemperatureRainfall(0.0F, 0.5F);
  }
  
  public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
    return (par1Random.nextInt(3) == 0) ? (WorldGenerator)new WorldGenTaiga1() : (WorldGenerator)new WorldGenTaiga2(false);
  }
  
  public void decorate(World par1World, Random par2Random, int par3, int par4) {
    super.decorate(par1World, par2Random, par3, par4);
    if (par2Random.nextInt(150) == 0) {
      int i = par3 + par2Random.nextInt(16) + 8;
      int j = par4 + par2Random.nextInt(16) + 8;
      WorldGenStoneCone var7 = new WorldGenStoneCone();
      if (par2Random.nextInt(30) == 0) {
        var7.setSuperLarge();
        if (Minecraft.inDevMode())
          System.out.println("Generate StoneCone at " + i + " " + j + " , superlarge."); 
      } else if (Minecraft.inDevMode()) {
        System.out.println("Generate StoneCone at " + i + " " + j);
      } 
      var7.generate(par1World, par2Random, i, par1World.getHeightValue(i, j) + 1, j);
    } 
    int var5 = 3 + par2Random.nextInt(6);
    int var6;
    for (var6 = 0; var6 < var5; var6++) {
      int i = par3 + par2Random.nextInt(4);
      int j = par2Random.nextInt(255);
      int x = par4 + par2Random.nextInt(16);
      int y = par1World.getBlockId(i, j, x);
      if (y == Block.stone.blockID)
        par1World.setBlock(i, j, x, Block.oreEmerald.blockID, 0, 2); 
    } 
    for (var6 = 0; var6 < var5; var6++) {
      int i = par3 + par2Random.nextInt(4);
      int j = par2Random.nextInt(128);
      int x = par4 + par2Random.nextInt(16);
      int y = par1World.getBlockId(i, j, x);
      if (y == Block.stone.blockID)
        par1World.setBlock(i, j, x, Block.oreEmerald.blockID, 0, 2); 
    } 
    for (var6 = 0; var6 < var5; var6++) {
      int i = par3 + par2Random.nextInt(4);
      int j = par2Random.nextInt(64);
      int x = par4 + par2Random.nextInt(16);
      int y = par1World.getBlockId(i, j, x);
      if (y == Block.stone.blockID)
        par1World.setBlock(i, j, x, Block.oreEmerald.blockID, 0, 2); 
    } 
    WorldGenMinable genMinable = (new WorldGenMinable(Block.cobblestone.blockID, 40, Block.stone.blockID)).setMinableBlockMetadata(0);
    int count = par2Random.nextInt(6) + 15;
    for (int temp = 0; temp < count; temp++) {
      int x = par3 + par2Random.nextInt(16);
      int y = par2Random.nextInt(128);
      int z = par4 + par2Random.nextInt(16);
      genMinable.generate(par1World, par2Random, x, y, z);
    } 
  }
  
  private BiomeGenBase setMinMaxHeight(float par1, float par2) {
    this.minHeight = par1;
    this.maxHeight = par2;
    return this;
  }
  
  private BiomeGenBase setTemperatureRainfall(float par1, float par2) {
    if (par1 > 0.1F && par1 < 0.2F)
      throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow"); 
    this.temperature = par1;
    this.rainfall = par2;
    return this;
  }
}
