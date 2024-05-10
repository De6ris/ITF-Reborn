package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityZombieLord extends EntityRevenant {
  public EntityZombieLord(World par1World) {
    super(par1World);
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    items.add(new RandomItemListEntry((Item)Items.vibraniumSword, 2));
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Items.vibraniumBoots)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Items.vibraniumLeggings)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Items.vibraniumChestplate)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Items.vibraniumHelmet)).randomizeForMob((EntityLiving)this, true));
  }
}
