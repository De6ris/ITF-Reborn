package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenDesert;
import net.minecraft.EntityZombie;
import net.minecraft.SpawnListEntry;
import net.oilcake.mitelros.entity.EntityHusk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BiomeGenDesert.class})
public class BiomeDesertMixin extends BiomeGenBase {
  protected BiomeDesertMixin(int par1) {
    super(par1);
  }
  
  @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
  public void injectCtor(CallbackInfo callbackInfo) {
    removeEntityFromSpawnableLists(EntityZombie.class);
    this.spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 100, 1, 4));
  }
}
