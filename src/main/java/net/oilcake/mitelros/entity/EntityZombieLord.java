package net.oilcake.mitelros.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.EntityLiving;
import net.minecraft.EntityRevenant;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.RandomItemListEntry;
import net.minecraft.WeightedRandom;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;

public class EntityZombieLord extends EntityRevenant {
  public EntityZombieLord(World par1World) {
    super(par1World);
  }
  
  public void addRandomWeapon() {
    List<RandomItemListEntry> items = new ArrayList();
    items.add(new RandomItemListEntry((Item)Items.VibraniumSword, 2));
    RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
    setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving)this, true));
  }
  
  protected void addRandomEquipment() {
    addRandomWeapon();
    setBoots((new ItemStack((Item)Items.VibraniumBoots)).randomizeForMob((EntityLiving)this, true));
    setLeggings((new ItemStack((Item)Items.VibraniumLeggings)).randomizeForMob((EntityLiving)this, true));
    setCuirass((new ItemStack((Item)Items.VibraniumChestplate)).randomizeForMob((EntityLiving)this, true));
    setHelmet((new ItemStack((Item)Items.VibraniumHelmet)).randomizeForMob((EntityLiving)this, true));
  }
}
