package net.oilcake.mitelros.mixins.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.Entity;
import net.minecraft.EntityTracker;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EntityTracker.class})
public class EntityTrackerMixin {
  @Shadow
  private Set trackedEntities = new HashSet();
  
  @Overwrite
  public void addEntityToTracker(Entity par1Entity) {
    if (par1Entity instanceof ServerPlayer) {
      addEntityToTracker(par1Entity, 512, 2);
      ServerPlayer var2 = (ServerPlayer)par1Entity;
      Iterator<EntityTrackerEntry> var3 = this.trackedEntities.iterator();
      while (var3.hasNext()) {
        EntityTrackerEntry var4 = var3.next();
        if (var4.myEntity != var2)
          var4.tryStartWachingThis(var2); 
      } 
    } else if (par1Entity instanceof net.minecraft.EntityFishHook) {
      addEntityToTracker(par1Entity, 64, 5, true);
    } else if (par1Entity instanceof net.minecraft.EntityArrow) {
      addEntityToTracker(par1Entity, 64, 20, false);
    } else if (par1Entity instanceof net.minecraft.EntitySmallFireball) {
      addEntityToTracker(par1Entity, 64, 10, false);
    } else if (par1Entity instanceof net.minecraft.EntityFireball) {
      addEntityToTracker(par1Entity, 64, 10, false);
    } else if (par1Entity instanceof net.minecraft.EntitySnowball) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityEnderPearl) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityEnderEye) {
      addEntityToTracker(par1Entity, 64, 4, true);
    } else if (par1Entity instanceof net.minecraft.EntityEgg) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityBrick) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.oilcake.mitelros.entity.EntityWandFireball) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.oilcake.mitelros.entity.EntityWandIceBall) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.oilcake.mitelros.entity.EntityWandShockWave) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityWeb) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityGelatinousSphere) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityPotion) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityExpBottle) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityFireworkRocket) {
      addEntityToTracker(par1Entity, 64, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityItem) {
      addEntityToTracker(par1Entity, 64, 20, true);
    } else if (par1Entity instanceof net.minecraft.EntityMinecart) {
      addEntityToTracker(par1Entity, 80, 3, true);
    } else if (par1Entity instanceof net.minecraft.EntityBoat) {
      addEntityToTracker(par1Entity, 80, 3, true);
    } else if (par1Entity instanceof net.minecraft.EntitySquid) {
      addEntityToTracker(par1Entity, 64, 3, true);
    } else if (par1Entity instanceof net.minecraft.EntityWither) {
      addEntityToTracker(par1Entity, 80, 3, false);
    } else if (par1Entity instanceof net.minecraft.EntityBat) {
      addEntityToTracker(par1Entity, 80, 3, false);
    } else if (par1Entity instanceof net.minecraft.IAnimals) {
      addEntityToTracker(par1Entity, 80, 3, true);
    } else if (par1Entity instanceof net.minecraft.EntityDragon) {
      addEntityToTracker(par1Entity, 160, 3, true);
    } else if (par1Entity instanceof net.minecraft.EntityTNTPrimed) {
      addEntityToTracker(par1Entity, 160, 10, true);
    } else if (par1Entity instanceof net.minecraft.EntityFallingSand) {
      addEntityToTracker(par1Entity, 160, 20, true);
    } else if (par1Entity instanceof net.minecraft.EntityHanging) {
      addEntityToTracker(par1Entity, 160, 2147483647, false);
    } else if (par1Entity instanceof net.minecraft.EntityXPOrb) {
      addEntityToTracker(par1Entity, 160, 20, true);
    } else if (par1Entity instanceof net.minecraft.EntityEnderCrystal) {
      addEntityToTracker(par1Entity, 256, 2147483647, false);
    } 
  }
  
  @Shadow
  public void addEntityToTracker(Entity par1Entity, int par2, int par3, boolean par4) {}
  
  @Shadow
  public void addEntityToTracker(Entity par1Entity, int par2, int par3) {}
}
