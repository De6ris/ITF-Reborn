package net.oilcake.mitelros.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityBoneLord;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLiving;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.RandomItemListEntry;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.WeightedRandom;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;

public class EntityWitherBoneLord extends EntityBoneLord {
  public EntityWitherBoneLord(World par1World) {
    super(par1World);
    setSkeletonType(1);
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.followRange, 64.0D);
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27000001072883606D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, 8.0D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0D);
  }
  
  protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
    int looting = damage_source.getLootingModifier();
    int num_drops = this.rand.nextInt(3 + looting) - 1;
    if (num_drops > 0 && !recently_hit_by_player)
      num_drops -= this.rand.nextInt(num_drops + 1); 
    for (int i = 0; i < num_drops; i++)
      dropItem(Item.coal.itemID, 1); 
    if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(getBaseChanceOfRareDrop()) < 5 + looting * 2)
      dropItemStack(new ItemStack(Item.skull.itemID, 1, 1), 0.0F); 
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    items.add(new RandomItemListEntry((Item)Items.tungstenSword, 2));
    if (!Minecraft.isInTournamentMode()) {
      if (this.worldObj.getDayOfWorld() >= 25 && !Minecraft.isInTournamentMode())
        items.add(new RandomItemListEntry((Item)Items.tungstenBattleAxe, 1)); 
      if (this.worldObj.getDayOfWorld() >= 50 && !Minecraft.isInTournamentMode())
        items.add(new RandomItemListEntry((Item)Items.tungstenWarHammer, 1)); 
    } 
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Items.tungstenBoots)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Items.tungstenLeggings)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Items.tungstenChestplate)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Items.tungstenHelmet)).randomizeForMob((EntityLiving)this, true));
  }
  
  public EntityDamageResult attackEntityAsMob(Entity target) {
    EntityDamageResult result = super.attackEntityAsMob(target);
    if (result != null && !result.entityWasDestroyed()) {
      if (result.entityLostHealth() && target instanceof net.minecraft.EntityPlayer)
        target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.wither.id, 850)); 
      return result;
    } 
    return result;
  }
  
  public Class getTroopClass() {
    return EntityWitherBodyguard.class;
  }
  
  public int getExperienceValue() {
    return super.getExperienceValue() * 2;
  }
  
  public boolean isHarmedByFire() {
    return false;
  }
  
  public boolean isHarmedByLava() {
    return false;
  }
}
