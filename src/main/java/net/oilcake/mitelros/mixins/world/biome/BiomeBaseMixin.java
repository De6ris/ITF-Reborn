package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.entity.EntityUnknown;
import net.oilcake.mitelros.util.Config;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin({BiomeGenBase.class})
public class BiomeBaseMixin {
  @Shadow
  private boolean enableSnow;
  
  @Shadow
  private boolean enableRain;
  
  @Shadow
  protected List spawnableMonsterList;
  
  @Shadow
  protected List spawnableCreatureList;
  
  @Shadow
  @Final
  public int biomeID;
  
  @Shadow
  public float rainfall;
  
  public void RegenAnimals() {
    removeEntityFromSpawnableLists(EntityCow.class);
    removeEntityFromSpawnableLists(EntityChicken.class);
    removeEntityFromSpawnableLists(EntitySheep.class);
    removeEntityFromSpawnableLists(EntityPig.class);
    this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 5, 4, 6));
    this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 5, 4, 6));
    this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 4, 6));
    this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 5, 4, 6));
    this.spawnableCreatureList.add(new SpawnListEntry(EntityUnknown.class, 110, 0, 0));
  }
  
  public void DisgenAnimals() {
    removeEntityFromSpawnableLists(EntityCow.class);
    removeEntityFromSpawnableLists(EntityChicken.class);
    removeEntityFromSpawnableLists(EntitySheep.class);
    removeEntityFromSpawnableLists(EntityPig.class);
    removeEntityFromSpawnableLists(EntityHorse.class);
  }
  
  @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
  public void injectCtor(CallbackInfo callbackInfo) {
    this.spawnableMonsterList.add(new SpawnListEntry(EntityRetinueZombie.class, (Config.TagFallenInMineLVL2.get() || Config.TagFallenInMineLVL1.get()) ? 35 : 10, 4, 4));
    this.spawnableMonsterList.add(new SpawnListEntry(EntityBoneBodyguard.class, (Config.TagBattleSufferLVL2.get() || Config.TagBattleSufferLVL1.get()) ? 35 : 10, 4, 4));
    if (Config.TagCreaturesV2.get())
      RegenAnimals(); 
    if (Config.TagApocalypse.get())
      DisgenAnimals(); 
  }
  
  @Shadow
  public void removeEntityFromSpawnableLists(Class _class) {}
  
  @Overwrite
  public boolean isHillyOrMountainous() {
    return (this.biomeID == BiomeGenBase.extremeHills.biomeID || this.biomeID == BiomeGenBase.iceMountains.biomeID || this.biomeID == BiomeGenBase.desertHills.biomeID || this.biomeID == BiomeGenBase.forestHills.biomeID || this.biomeID == BiomeGenBase.taigaHills.biomeID || this.biomeID == BiomeGenBase.extremeHillsEdge.biomeID || this.biomeID == BiomeGenBase.jungleHills.biomeID || this.biomeID == BiomeBases.BIOME_WINDSWEPT_PLEATU.biomeID);
  }
  
  @Overwrite
  public boolean canSpawnLightningBolt(boolean is_blood_moon) {
    return !this.enableSnow && (((this.enableRain && this.rainfall != 0.0F) || is_blood_moon));
  }
}
