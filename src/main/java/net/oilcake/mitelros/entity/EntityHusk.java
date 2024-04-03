package net.oilcake.mitelros.entity;

import net.minecraft.Entity;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityZombie;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;

public class EntityHusk extends EntityZombie {
  public EntityHusk(World par1World) {
    super(par1World);
  }
  
  public void setVillager(boolean villager, int profession) {}
  
  public boolean isVillager() {
    return false;
  }
  
  public boolean catchesFireInSunlight() {
    return false;
  }
  
  public EntityDamageResult attackEntityAsMob(Entity target) {
    EntityDamageResult result = super.attackEntityAsMob(target);
    if (result != null && !result.entityWasDestroyed()) {
      if (result.entityLostHealth() && target instanceof net.minecraft.EntityPlayer)
        target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.hunger.id, 650)); 
      return result;
    } 
    return result;
  }
}
