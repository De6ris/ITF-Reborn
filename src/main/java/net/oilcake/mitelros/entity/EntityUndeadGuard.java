package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.EntitySelectorHunter;

import java.util.ArrayList;
import java.util.List;

public class EntityUndeadGuard extends EntitySkeleton implements IRangedAttackMob {
  IEntitySelector hunterSelector = (IEntitySelector)new EntitySelectorHunter();
  
  ItemStack stowed_item_stack;
  
  int num_arrows;
  
  private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
  
  private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide((EntityCreature)this, EntityMob.class, 1.2D, false);
  
  public EntityUndeadGuard(World par1World) {
    super(par1World);
    getNavigator().setAvoidsWater(true);
    this.tasks.clear();
    this.targetTasks.clear();
    setSkeletonType(2);
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeSun((EntityCreature)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.75D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLiving.class, 0, false, true, this.hunterSelector));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIMoveToRepairItem((EntityLiving)this, 1.0F, true));
    setCanPickUpLoot(false);
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27000001072883606D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, 6.0D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, 12.0D);
  }
  
  public void setCombatTask() {
    this.tasks.removeTask((EntityAIBase)this.aiAttackOnCollide);
    this.tasks.removeTask((EntityAIBase)this.aiArrowAttack);
    ItemStack var1 = getHeldItemStack();
    if (var1 != null && var1.getItem() instanceof net.minecraft.ItemBow && this.num_arrows > 0) {
      this.tasks.addTask(4, (EntityAIBase)this.aiArrowAttack);
      this.tasks.addTask(3, (EntityAIBase)new EntityAISeekFiringPosition((EntityLiving)this, 1.0F, true));
    } else {
      this.tasks.addTask(4, (EntityAIBase)this.aiAttackOnCollide);
    } 
  }
  
  public boolean isHoldingRangedWeapon() {
    return getHeldItem() instanceof net.minecraft.ItemBow;
  }
  
  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (this.num_arrows == 0 && getHeldItemStack() != null && 
      getHeldItemStack().getItem() instanceof net.minecraft.ItemBow)
      setHeldItemStack(null); 
    if (getHeldItemStack() == null && getSkeletonType() == 0) {
      setSkeletonType(2);
      setCombatTask();
    } 
    if (this.ticksExisted > 300) {
      entityFX(EnumEntityFX.steam_with_hiss);
      setDead();
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
    playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
    this.worldObj.spawnEntityInWorld((Entity)var3);
    this.num_arrows--;
  }
  
  protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {}
  
  public int getRandomSkeletonType(World world) {
    return 2;
  }
  
  public int getExperienceValue() {
    return 0;
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    items.add(new RandomItemListEntry(Item.daggerRustedIron, 2));
    if (!Minecraft.isInTournamentMode()) {
      items.add(new RandomItemListEntry(Item.swordRustedIron, 1));
      items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 1));
    } 
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Item.bootsChainRustedIron)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Item.legsChainRustedIron)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Item.plateChainRustedIron)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Item.helmetChainRustedIron)).randomizeForMob((EntityLiving)this, true));
  }
}
