package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityLiving;
import net.minecraft.EntityLongdead;
import net.minecraft.EntityLongdeadGuardian;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityLongdeadGuardian.class})
public class EntityLongdeadGuardianMixin extends EntityLongdead {
  ItemStack stowed_item_stack;
  
  public EntityLongdeadGuardianMixin(World world) {
    super(world);
  }
  
  @Overwrite
  public void addRandomWeapon() {
    super.addRandomWeapon();
    if (getHeldItem() instanceof net.minecraft.ItemBow)
      this.stowed_item_stack = (new ItemStack(Item.daggerAncientMetal)).randomizeForMob((EntityLiving)this, true); 
    if (getHeldItem() instanceof net.minecraft.ItemSword)
      this.stowed_item_stack = (new ItemStack((Item)Item.bowAncientMetal)).randomizeForMob((EntityLiving)this, true); 
  }
}
