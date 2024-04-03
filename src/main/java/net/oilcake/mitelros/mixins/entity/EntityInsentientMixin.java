package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.Minecraft;
import net.minecraft.World;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityLiving.class})
public abstract class EntityInsentientMixin extends EntityLivingBase {
  public EntityInsentientMixin(World par1World) {
    super(par1World);
  }
  
  @Overwrite
  public boolean isTargetWithinStrikingDistance(EntityLivingBase target) {
    if (!isAIEnabled()) {
      Minecraft.setErrorMessage("isTargetWithinStrikingDistance: doesn't handle old AI mobs yet");
      return false;
    } 
    if (getAsEntityLiving() instanceof net.minecraft.EntityAnimal) {
      double var2 = (this.width * 1.75F * this.width * 1.75F + target.width);
      if (getHeldItemStack() != null)
        var2 += getHeldItemStack().getItem().getReachBonus(); 
      return (getDistanceSq(target.posX, target.boundingBox.minY, target.posZ) <= var2);
    } 
    if (((Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue).booleanValue()) {
      boolean condition1 = (getDistance(target.posX, target.boundingBox.minY - (this.boundingBox.maxY - this.boundingBox.minY) * 2.0D / 3.0D, target.posZ) <= getReach());
      boolean condition2 = (getDistance(target.posX, target.boundingBox.maxY + (this.boundingBox.maxY - this.boundingBox.minY) * 2.0D / 3.0D, target.posZ) <= getReach());
      return (condition1 || condition2);
    } 
    return (getDistance(target.posX, target.boundingBox.minY, target.posZ) <= getReach());
  }
  
  @Overwrite
  public float getReach() {
    if (!isAIEnabled()) {
      Minecraft.setErrorMessage("getReach: doesn't handle old AI mobs yet");
      return 0.0F;
    } 
    if (((Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue).booleanValue())
      return 1.5F + getHeldItemReachBonus(); 
    return 1.5F + getHeldItemReachBonus() * 0.6F;
  }
}
