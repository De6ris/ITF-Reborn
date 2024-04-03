package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenHell;
import net.minecraft.EntityDemonSpider;
import net.minecraft.EntityHellhound;
import net.minecraft.EntityInfernalCreeper;
import net.minecraft.SpawnListEntry;
import net.oilcake.mitelros.entity.EntityEvil;
import net.oilcake.mitelros.entity.EntityPigmanLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
  }
}
