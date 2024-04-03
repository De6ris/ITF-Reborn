package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityMob;
import net.minecraft.EntityShadow;
import net.minecraft.World;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityShadow.class})
public class EntityShadowMixin extends EntityMob {
  private boolean cursed_player;
  
  public EntityShadowMixin(World par1World) {
    super(par1World);
    this.cursed_player = false;
  }
  
  @Overwrite
  public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
    setCanPickUpLoot(true);
    return super.onSpawnWithEgg(par1EntityLivingData);
  }
  
  @Overwrite
  public void onLivingUpdate() {
    if (!this.worldObj.isRemote) {
      if (isInSunlight()) {
        attackEntityFrom(new Damage(DamageSource.sunlight, 1000.0F));
      } else if (this.ticksExisted % 40 == 0) {
        float brightness = getBrightness(1.0F);
        int amount_to_heal = (int)((0.4F - brightness) * 10.0F);
        if (amount_to_heal > 0)
          heal(amount_to_heal); 
      } 
      if (getTarget() != null && !this.cursed_player && ((Boolean)StuckTagConfig.TagConfig.TagPseudovision.ConfigValue).booleanValue()) {
        EntityLivingBase entityLivingBase = getTarget();
        if (entityLivingBase instanceof net.minecraft.EntityPlayer) {
          this.cursed_player = true;
          (entityLivingBase.getAsPlayer()).vision_dimming += entityLivingBase.getAsEntityLivingBase().getAmountAfterResistance(2.0F, 4);
        } 
      } 
    } 
    tryDisableNearbyLightSource();
    super.onLivingUpdate();
  }
}
