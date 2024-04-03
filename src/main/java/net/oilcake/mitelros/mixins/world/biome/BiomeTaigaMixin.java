package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.util.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin({BiomeGenTaiga.class})
public class BiomeTaigaMixin extends BiomeGenBase {
  protected BiomeTaigaMixin(int par1) {
    super(par1);
  }
  
  @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
  public void injectCtor(CallbackInfo callbackInfo) {
    removeEntityFromSpawnableLists(EntitySkeleton.class);
    this.spawnableMonsterList.add(new SpawnListEntry(EntityStray.class, 100, 1, 4));
    if (((Boolean) Config.TagCreaturesV2.get()))
      RegenHostileAnimals(); 
  }
  
  private void RegenHostileAnimals() {
    removeEntityFromSpawnableLists(EntityWolf.class);
    this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 10, 4, 8));
    removeEntityFromSpawnableLists(EntityDireWolf.class);
    this.spawnableCreatureList.add(new SpawnListEntry(EntityDireWolf.class, 5, 4, 6));
  }
  
  public void decorate(World par1World, Random par2Random, int par3, int par4) {
    super.decorate(par1World, par2Random, par3, par4);
    int var5 = 3 + par2Random.nextInt(6);
    for (int var6 = 0; var6 < var5; var6++) {
      int var7 = par3 + par2Random.nextInt(16);
      int var8 = par2Random.nextInt(28) + 4;
      int var9 = par4 + par2Random.nextInt(16);
      int var10 = par1World.getBlockId(var7, var8, var9);
      if (var10 == Block.stone.blockID)
        par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2); 
    } 
  }
}
