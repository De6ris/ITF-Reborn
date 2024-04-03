package net.oilcake.mitelros.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Entity;
import net.minecraft.EntityArrow;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntitySkeleton;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.RandomItemListEntry;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.WeightedRandom;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;

public class EntityWitherBodyguard extends EntitySkeleton {
  ItemStack stowed_item_stack;
  
  int num_arrows;
  
  public EntityWitherBodyguard(World par1World) {
    super(par1World);
    setSkeletonType(1);
    setCanPickUpLoot(false);
    this.num_arrows = 6;
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.followRange, 64.0D);
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27000001072883606D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, 6.0D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, 12.0D);
  }
  
  public boolean isHoldingRangedWeapon() {
    return getHeldItem() instanceof net.minecraft.ItemBow;
  }
  
  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (this.stowed_item_stack != null && (getHeldItemStack() == null || getTicksExistedWithOffset() % 10 == 0))
      if (getHeldItemStack() == null) {
        swapHeldItemStackWithStowed();
      } else {
        EntityLivingBase entityLivingBase = getTarget();
        if (entityLivingBase != null && canSeeTarget(true)) {
          double distance = getDistanceToEntity((Entity)entityLivingBase);
          if (isHoldingRangedWeapon()) {
            if (distance < 5.0D)
              swapHeldItemStackWithStowed(); 
          } else if (distance > 6.0D && this.rand.nextBoolean()) {
            swapHeldItemStackWithStowed();
          } 
        } 
      }  
    if (this.num_arrows == 0 && getHeldItemStack() != null && 
      getHeldItemStack().getItem() instanceof net.minecraft.ItemBow)
      setHeldItemStack(null); 
    if (getHeldItemStack() == null && getSkeletonType() == 0) {
      setSkeletonType(2);
      setCombatTask();
    } 
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
    super.writeEntityToNBT(par1NBTTagCompound);
    par1NBTTagCompound.setByte("SkeletonType", (byte)getSkeletonType());
    par1NBTTagCompound.setByte("num_arrows", (byte)this.num_arrows);
  }
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
    super.readEntityFromNBT(par1NBTTagCompound);
    if (par1NBTTagCompound.hasKey("SkeletonType")) {
      byte var2 = par1NBTTagCompound.getByte("SkeletonType");
      setSkeletonType(var2);
    } 
    setCombatTask();
    this.num_arrows = par1NBTTagCompound.getByte("num_arrows");
  }
  
  public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
    EntityArrow var3 = new EntityArrow(this.worldObj, (EntityLivingBase)this, par1EntityLivingBase, 1.6F, (14 - this.worldObj.difficultySetting * 4), Items.arrowTungsten, false);
    int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItemStack());
    int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItemStack());
    double damage = (par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (this.worldObj.difficultySetting * 0.11F);
    var3.setDamage(damage);
    if (var4 > 0)
      var3.setDamage(var3.getDamage() + var4 * 0.5D + 0.5D); 
    if (var5 > 0)
      var3.setKnockbackStrength(var5); 
    var3.setFire(100);
    playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
    this.worldObj.spawnEntityInWorld((Entity)var3);
    this.num_arrows--;
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
    items.add(new RandomItemListEntry((Item)Items.tungstenDagger, 2));
    if (!Minecraft.isInTournamentMode()) {
      items.add(new RandomItemListEntry((Item)Items.tungstenSword, 1));
      items.add(new RandomItemListEntry((Item)Items.tungstenBattleAxe, 1));
    } 
    if (this.rand.nextInt(8) == 0)
      this.stowed_item_stack = (new ItemStack((Item)Items.bowTungsten)).randomizeForMob((EntityLiving)this, true); 
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Items.tungstenBootsChain)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Items.tungstenLeggingsChain)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Items.tungstenChestplateChain)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Items.tungstenHelmetChain)).randomizeForMob((EntityLiving)this, true));
  }
  
  public EntityDamageResult attackEntityAsMob(Entity target) {
    EntityDamageResult result = super.attackEntityAsMob(target);
    if (result != null && !result.entityWasDestroyed()) {
      if (result.entityLostHealth() && target instanceof net.minecraft.EntityPlayer)
        target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.wither.id, 300)); 
      return result;
    } 
    return result;
  }
  
  public void swapHeldItemStackWithStowed() {
    ItemStack item_stack = this.stowed_item_stack;
    this.stowed_item_stack = getHeldItemStack();
    setHeldItemStack(item_stack);
  }
  
  public boolean isHarmedByFire() {
    return false;
  }
  
  public boolean isHarmedByLava() {
    return false;
  }
}
