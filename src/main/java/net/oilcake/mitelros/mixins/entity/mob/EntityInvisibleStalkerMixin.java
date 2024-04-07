package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityMob;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityInvisibleStalker.class})
public class EntityInvisibleStalkerMixin extends EntityMob {
  public EntityInvisibleStalkerMixin(World world) {
    super(world);
  }
  
  @Overwrite
  public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
    setCanPickUpLoot(true);
    return super.onSpawnWithEgg(par1EntityLivingData);
  }
}
