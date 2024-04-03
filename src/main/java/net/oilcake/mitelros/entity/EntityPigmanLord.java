package net.oilcake.mitelros.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.EntityPigZombie;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.RandomItemListEntry;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.WeightedRandom;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;

public class EntityPigmanLord extends EntityPigZombie {
  private int angerLevel;
  
  private int randomSoundDelay;
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.maxHealth, 30.0D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, 9.0D);
  }
  
  public EntityPigmanLord(World par1World) {
    super(par1World);
  }
  
  protected EntityPlayer findPlayerToAttack(float max_distance) {
    Entity previous_target = getEntityToAttack();
    EntityPlayer target = super.findPlayerToAttack(max_distance);
    if (target != null && target != previous_target)
      becomeAngryAt((Entity)target); 
    return target;
  }
  
  private void becomeAngryAt(Entity par1Entity) {
    this.entityToAttack = par1Entity;
    this.angerLevel = 4000;
    this.randomSoundDelay = this.rand.nextInt(40);
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    items.add(new RandomItemListEntry((Item)Items.tungstenSword, 2));
    if (!Minecraft.isInTournamentMode()) {
      items.add(new RandomItemListEntry((Item)Items.tungstenBattleAxe, 1));
      items.add(new RandomItemListEntry((Item)Items.tungstenWarHammer, 1));
    } 
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  public void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Items.tungstenBoots)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Items.tungstenLeggings)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Items.tungstenChestplate)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Items.tungstenHelmet)).randomizeForMob((EntityLiving)this, true));
  }
  
  public int getExperienceValue() {
    return super.getExperienceValue() * 2;
  }
}
