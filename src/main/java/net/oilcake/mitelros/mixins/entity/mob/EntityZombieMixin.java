package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityZombie.class})
public class EntityZombieMixin extends EntityAnimalWatcher {
  @Shadow
  private boolean is_smart;
  
  @Shadow
  Item[] rare_drops_villager;
  
  public EntityZombieMixin(World world) {
    super(world);
  }
  
  @Inject(method = {"<init>(Lnet/minecraft/World;)V"}, at = {@At("RETURN")})
  public void injectCtor(CallbackInfo callbackInfo) {
    this.is_smart = true;
    this.rare_drops_villager = new Item[] { Item.seeds, Item.pumpkinSeeds, Item.melonSeeds, Item.carrot, (Item)Item.potato, Item.onion, (Item)Items.seedsBeetroot };
  }
  
  @Inject(method = {"onUpdate()V"}, at = {@At("RETURN")})
  public void ModifyAIInjector(CallbackInfo callbackInfo) {
    if (((Boolean) ITFConfig.TagWorshipDark.get()).booleanValue())
      tryDisableNearbyLightSource(); 
  }
  
  @Inject(method = {"onSpawnWithEgg(Lnet/minecraft/EntityLivingData;)Lnet/minecraft/EntityLivingData;"}, at = {@At("RETURN")}, cancellable = true)
  public void ModifyAIInjector(EntityLivingData par1EntityLivingData, CallbackInfoReturnable<EntityLivingData> callbackInfo) {
    if (((Boolean) ITFConfig.TagWorshipDark.get()).booleanValue())
      this.tasks.addTask(4, (EntityAIBase)new EntityAISeekLitTorch((EntityLiving)this, 1.0F)); 
  }
}
