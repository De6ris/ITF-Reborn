package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.EntityLivingData;
import net.minecraft.EntityMob;
import net.minecraft.EntityWight;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityWight.class})
public class EntityWightMixin extends EntityMob {
  public EntityWightMixin(World par1World) {
    super(par1World);
  }
  
  @Overwrite
  public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
    setCanPickUpLoot(true);
    return super.onSpawnWithEgg(par1EntityLivingData);
  }
}
