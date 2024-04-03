package net.oilcake.mitelros.entity;

import net.minecraft.EntityLiving;
import net.minecraft.EntitySkeleton;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;

public class EntityBoneBodyguard extends EntitySkeleton {
  public EntityBoneBodyguard(World par1World) {
    super(par1World);
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.maxHealth, 6.0D);
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.28999999165534973D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
  }
  
  public void addRandomWeapon() {
    setHeldItemStack((new ItemStack((getSkeletonType() == 2) ? ((this.rand.nextInt(20) == 0) ? Item.battleAxeRustedIron : Item.swordRustedIron) : (Item)Item.bow)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Item.bootsChainRustedIron)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Item.legsChainRustedIron)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Item.plateChainRustedIron)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Item.helmetChainRustedIron)).randomizeForMob((EntityLiving)this, true));
  }
  
  public int getExperienceValue() {
    return super.getExperienceValue() * 2;
  }
}
