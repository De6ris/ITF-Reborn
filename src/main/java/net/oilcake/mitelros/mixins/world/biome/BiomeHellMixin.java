package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.world.WorldGenSulphur;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin({BiomeGenHell.class})
public class BiomeHellMixin extends BiomeGenBase {
  protected BiomeHellMixin(int par1) {
    super(par1);
  }

  @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
  public void injectCtor(CallbackInfo callbackInfo) {
    this.spawnableMonsterList.clear();
    this.spawnableMonsterList.add(new SpawnListEntry(EntityInfernalCreeper.class, 20, 1, 3));
    this.spawnableMonsterList.add(new SpawnListEntry(EntityDemonSpider.class, 20, 1, 4));
    this.spawnableMonsterList.add(new SpawnListEntry(EntityHellhound.class, 20, 1, 4));
    this.spawnableMonsterList.add(new SpawnListEntry(EntityEvil.class, 50, 1, 4));
    this.spawnableMonsterList.add(new SpawnListEntry(EntityPigmanLord.class, 5, 1, 1));
    this.spawnableMonsterList.add(new SpawnListEntry(EntityPigmanGuard.class, 10, 1, 2));
    this.spawnableMonsterList.add(new SpawnListEntry(EntitySpirit.class,10,1,2));
    if (ITFConfig.TagDimensionInvade.get()) {
      this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderKing.class, 10, 1, 2));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityLongdeadGuardian.class, 40, 2, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityLongdead.class, 80, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityAncientBoneLord.class, 20, 1, 2));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityStalkerCreeper.class, 50, 1, 2));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 40, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherBoneLord.class, 10, 1, 2));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherBodyguard.class, 15, 2, 2));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityBlackWidowSpider.class, 40, 4, 2));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityWoodSpider.class, 60, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityStray.class, 40, 2, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 40, 2, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityCastleGuard.class, 20, 1, 2));
      this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityVampireBat.class, 20, 8, 8));
      this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityNightwing.class, 4, 1, 4));
    }
  }

  public void decorate(World par1World, Random par2Random, int par3, int par4) {
    super.decorate(par1World, par2Random, par3, par4);
    if (par2Random.nextInt(256) == 0) {
      int var5 = par3 + par2Random.nextInt(16) + 8;
      int var6 = par4 + par2Random.nextInt(16) + 8;
      WorldGenSulphur var7 = new WorldGenSulphur();
      if (par2Random.nextInt(8) == 0) {
        var7.setSuperLarge();
        if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6))
          if (Minecraft.inDevMode())
            System.out.println("Generate Sulphur at " + var5 + " " + var6 + " , superlarge.");
      } else {
        if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6))
          if (Minecraft.inDevMode())
            System.out.println("Generate Sulphur at " + var5 + " " + var6);
      }
    }
  }
}
