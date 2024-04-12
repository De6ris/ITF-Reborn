package net.oilcake.mitelros.mixins.world;

import net.minecraft.MapGenNetherBridge;
import net.minecraft.MapGenStructure;
import net.minecraft.SpawnListEntry;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin({MapGenNetherBridge.class})
public abstract class WorldGenNetherMixin extends MapGenStructure {
  @Shadow
  private List spawnList;
  
  @Inject(method = {"<init>()V"}, at = {@At("RETURN")})
  public void injectCtor(CallbackInfo callbackInfo) {
    this.spawnList.add(new SpawnListEntry(EntityWitherBoneLord.class, 5, 1, 1));
  }
}
