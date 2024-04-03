package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntitySpiderKing;
import net.oilcake.mitelros.util.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityBoneLord.class})
public class EntityBoneLordMixin extends EntitySkeleton {
  public EntityBoneLordMixin(World par1World) {
    super(par1World);
  }

  @Inject(method = "applyEntityAttributes", at = @At("RETURN"))
  protected void applyEntityAttributes(CallbackInfo ci) {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.followRange, 128.0D);
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, ((Boolean) Config.TagBattleSufferLVL2.get()).booleanValue() ? 7.0D : 5.0D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, ((Boolean) Config.TagBattleSufferLVL2.get()).booleanValue() ? 30.0D : 20.0D);
  }

  @Overwrite
  public Class getTroopClass() {
    return isAncientBoneLord() ? (((Boolean) Config.TagBattleSufferLVL2.get()).booleanValue() ? EntityLongdeadGuardian.class : EntityLongdead.class) : (((Boolean) Config.TagBattleSufferLVL2.get()).booleanValue() ? EntityBoneBodyguard.class : EntitySkeleton.class);
  }
  
  public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
    par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
    if (((Boolean) Config.TagUnderAlliance.get()).booleanValue() && this.rand.nextInt(3) == 0 && this.ridingEntity == null && getSkeletonType() != 1 && isAncientBoneLord()) {
      EntitySpiderKing ridingSpider = new EntitySpiderKing(this.worldObj);
      ridingSpider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
      ridingSpider.onSpawnWithEgg((EntityLivingData)null);
      this.worldObj.spawnEntityInWorld((Entity)ridingSpider);
      mountEntity((Entity)ridingSpider);
    } 
    return par1EntityLivingData;
  }
}
