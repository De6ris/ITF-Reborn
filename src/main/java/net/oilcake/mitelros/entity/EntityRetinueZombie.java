package net.oilcake.mitelros.entity;

import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;

public class EntityRetinueZombie extends EntityZombie {
  private int conversionTime;
  Item[] rare_drops;

  public EntityRetinueZombie(World world) {
    super(world);
    this.tasks.addTask(3, (EntityAIBase)new EntityAISeekLitTorch((EntityLiving)this, 1.0F));
    this.rare_drops = new Item[]{Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget};
  }
  
  public void onUpdate() {
    if (!this.worldObj.isRemote && isConverting()) {
      int var1 = getConversionTimeBoost();
      this.conversionTime -= var1;
      if (this.conversionTime <= 0)
        convertToVillager(); 
    } 
    tryDisableNearbyLightSource();
    super.onUpdate();
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
    setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0D);
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    if (this.worldObj.getDayOfWorld() < 24) {
      items.add(new RandomItemListEntry(Item.clubWood, 2));
      items.add(new RandomItemListEntry(Item.daggerRustedIron, 1));
    } 
    if (this.worldObj.getDayOfWorld() >= 24)
      items.add(new RandomItemListEntry(Item.swordRustedIron, 1)); 
    if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode())
      items.add(new RandomItemListEntry(Item.pickaxeRustedIron, 1)); 
    if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode())
      items.add(new RandomItemListEntry(Item.warHammerRustedIron, 1)); 
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    List<RandomItemListEntry> helmet = new ArrayList();
    helmet.add(new RandomItemListEntry((Item)Item.helmetLeather, 6));
    if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
      helmet.add(new RandomItemListEntry((Item)Item.helmetChainRustedIron, 3));
//      helmet.add(new RandomItemListEntry((Item)Item.helmetChainCopper, 3));
    } 
    if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
//      helmet.add(new RandomItemListEntry((Item)Item.helmetCopper, 1));
      helmet.add(new RandomItemListEntry((Item)Item.helmetRustedIron, 1));
    } 
    List<RandomItemListEntry> plate = new ArrayList();
    plate.add(new RandomItemListEntry((Item)Item.plateLeather, 6));
    if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
      plate.add(new RandomItemListEntry((Item)Item.plateChainRustedIron, 3));
//      plate.add(new RandomItemListEntry((Item)Item.plateChainCopper, 3));
    } 
    if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
//      plate.add(new RandomItemListEntry((Item)Item.plateCopper, 1));
      plate.add(new RandomItemListEntry((Item)Item.plateRustedIron, 1));
    } 
    List<RandomItemListEntry> legs = new ArrayList();
    legs.add(new RandomItemListEntry((Item)Item.legsLeather, 6));
    if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
      legs.add(new RandomItemListEntry((Item)Item.legsChainRustedIron, 3));
//      legs.add(new RandomItemListEntry((Item)Item.legsChainCopper, 3));
    } 
    if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
//      legs.add(new RandomItemListEntry((Item)Item.legsCopper, 1));
      legs.add(new RandomItemListEntry((Item)Item.legsRustedIron, 1));
    } 
    List<RandomItemListEntry> boots = new ArrayList();
    boots.add(new RandomItemListEntry((Item)Item.bootsLeather, 6));
    if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
      boots.add(new RandomItemListEntry((Item)Item.bootsChainRustedIron, 3));
//      boots.add(new RandomItemListEntry((Item)Item.bootsChainCopper, 3));
    } 
    if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
//      boots.add(new RandomItemListEntry((Item)Item.bootsCopper, 1));
      boots.add(new RandomItemListEntry((Item)Item.bootsRustedIron, 1));
    } 
    if (this.worldObj.getDayOfWorld() > 31) {
      helmet.remove(0);
      plate.remove(0);
      legs.remove(0);
      boots.remove(0);
    } 
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, helmet);
    setHelmet((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
    entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, plate);
    setCuirass((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
    entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, legs);
    setLeggings((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
    entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, boots);
    setBoots((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
    addRandomWeapon();
  }
  
  public int getExperienceValue() {
    return super.getExperienceValue() * 2;
  }
  
  protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
    if (this.rand.nextFloat() < (recently_hit_by_player ? 0.5F : 0.25F))
      dropItem(Item.rottenFlesh.itemID, 1);
    if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(50) < 10 + damage_source.getLootingModifier() * 5) {
      this.dropItem(rare_drops[this.rand.nextInt(rare_drops.length)].itemID, 1);
    }
  }
}
