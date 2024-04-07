package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.EntityAnimalWatcher;
import net.minecraft.EntityGhoul;
import net.minecraft.EntityLivingData;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityGhoul.class})
public class EntityGhoulMixin extends EntityAnimalWatcher {
  public EntityGhoulMixin(World world) {
    super(world);
  }
  
  @Overwrite
  public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
    setCanPickUpLoot(true);
    return super.onSpawnWithEgg(par1EntityLivingData);
  }
}
