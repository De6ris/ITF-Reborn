package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;

public class EntityGhost extends EntityInvisibleStalker {
  public EntityGhost(World par1World) {
    super(par1World);
  }
  
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0D);
  }
  
  protected float getSoundVolume(String sound) {
    return 0.75F;
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    items.add(new RandomItemListEntry(Item.swordRustedIron, 1));
    if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode())
      items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 3)); 
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    addRandomWeapon();
    setCuirass((new ItemStack((Item)Item.plateRustedIron)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Item.helmetRustedIron)).randomizeForMob((EntityLiving)this, true));
  }
  
  public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
    setCanPickUpLoot(true);
    addRandomEquipment();
    return super.onSpawnWithEgg(par1EntityLivingData);
  }
}
