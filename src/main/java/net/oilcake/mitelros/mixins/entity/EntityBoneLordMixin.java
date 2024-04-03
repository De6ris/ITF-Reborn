package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import net.minecraft.EntityBoneLord;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityLongdead;
import net.minecraft.EntityLongdeadGuardian;
import net.minecraft.EntitySkeleton;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntitySpiderKing;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityBoneLord.class})
public class EntityBoneLordMixin extends EntitySkeleton {
  public EntityBoneLordMixin(World par1World) {
    super(par1World);
  }
  
  @Overwrite
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.followRange, 128.0D);
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, ((Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue).booleanValue() ? 7.0D : 5.0D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, ((Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue).booleanValue() ? 30.0D : 20.0D);
  }
  
  @Overwrite
  public Class getTroopClass() {
    return isAncientBoneLord() ? (((Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue).booleanValue() ? EntityLongdeadGuardian.class : EntityLongdead.class) : (((Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue).booleanValue() ? EntityBoneBodyguard.class : EntitySkeleton.class);
  }
  
  public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
    par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
    if (((Boolean)StuckTagConfig.TagConfig.TagUnderAlliance.ConfigValue).booleanValue() && this.rand.nextInt(3) == 0 && this.ridingEntity == null && getSkeletonType() != 1 && isAncientBoneLord()) {
      EntitySpiderKing ridingSpider = new EntitySpiderKing(this.worldObj);
      ridingSpider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
      ridingSpider.onSpawnWithEgg((EntityLivingData)null);
      this.worldObj.spawnEntityInWorld((Entity)ridingSpider);
      mountEntity((Entity)ridingSpider);
    } 
    return par1EntityLivingData;
  }
}
