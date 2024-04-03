package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import net.minecraft.EntityArrow;
import net.minecraft.EntityBrick;
import net.minecraft.EntityFallingSand;
import net.minecraft.EntityFireball;
import net.minecraft.EntityFishHook;
import net.minecraft.EntityGelatinousSphere;
import net.minecraft.EntityItemFrame;
import net.minecraft.EntityLeashKnot;
import net.minecraft.EntityLiving;
import net.minecraft.EntityMinecart;
import net.minecraft.EntityPainting;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityPotion;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.EntityXPOrb;
import net.minecraft.Item;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.Packet;
import net.minecraft.Packet20NamedEntitySpawn;
import net.minecraft.Packet23VehicleSpawn;
import net.minecraft.Packet24MobSpawn;
import net.minecraft.Packet25EntityPainting;
import net.minecraft.Packet26EntityExpOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EntityTrackerEntry.class})
public class EntityTrackerEntryMixin {
  @Shadow
  public Entity myEntity;
  
  @Shadow
  public int lastHeadMotion;
  
  @Overwrite
  private Packet getPacketForThisEntity() {
    if (this.myEntity.isDead)
      this.myEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity"); 
    if (this.myEntity instanceof net.minecraft.EntityItem)
      return (Packet)new Packet23VehicleSpawn(this.myEntity, 2, 1); 
    if (this.myEntity instanceof net.minecraft.ServerPlayer)
      return (Packet)new Packet20NamedEntitySpawn((EntityPlayer)this.myEntity); 
    if (this.myEntity instanceof EntityMinecart) {
      EntityMinecart var9 = (EntityMinecart)this.myEntity;
      return (Packet)new Packet23VehicleSpawn(this.myEntity, 10, var9.getMinecartType());
    } 
    if (this.myEntity instanceof net.minecraft.EntityBoat)
      return (Packet)new Packet23VehicleSpawn(this.myEntity, 1); 
    if (!(this.myEntity instanceof net.minecraft.IAnimals) && !(this.myEntity instanceof net.minecraft.EntityDragon)) {
      if (this.myEntity instanceof EntityFishHook) {
        EntityPlayer var8 = ((EntityFishHook)this.myEntity).angler;
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 90, (var8 != null) ? var8.entityId : this.myEntity.entityId);
      } 
      if (this.myEntity instanceof EntityArrow) {
        Entity var7 = ((EntityArrow)this.myEntity).shootingEntity;
        Packet23VehicleSpawn packet = new Packet23VehicleSpawn(this.myEntity, 60, (var7 != null) ? var7.entityId : this.myEntity.entityId);
        packet.arrow_item_id = ((EntityArrow)this.myEntity).item_arrow.itemID;
        packet.launcher_was_enchanted = ((EntityArrow)this.myEntity).launcher_was_enchanted;
        packet.arrow_stuck_in_block = ((EntityArrow)this.myEntity).isInGround();
        return (Packet)packet;
      } 
      if (this.myEntity instanceof net.minecraft.EntitySnowball)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 61); 
      if (this.myEntity instanceof EntityPotion)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 73, ((EntityPotion)this.myEntity).getPotionType()); 
      if (this.myEntity instanceof net.minecraft.EntityExpBottle)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 75); 
      if (this.myEntity instanceof net.minecraft.EntityEnderPearl)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 65); 
      if (this.myEntity instanceof net.minecraft.EntityEnderEye)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 72); 
      if (this.myEntity instanceof net.minecraft.EntityFireworkRocket)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 76); 
      if (this.myEntity instanceof EntityFireball) {
        EntityFireball var6 = (EntityFireball)this.myEntity;
        Packet23VehicleSpawn var2 = null;
        byte var3 = 63;
        if (this.myEntity instanceof net.minecraft.EntitySmallFireball) {
          var3 = 64;
        } else if (this.myEntity instanceof net.minecraft.EntityWitherSkull) {
          var3 = 66;
        } 
        if (var6.shootingEntity != null) {
          var2 = new Packet23VehicleSpawn(this.myEntity, var3, ((EntityFireball)this.myEntity).shootingEntity.entityId);
        } else {
          var2 = new Packet23VehicleSpawn(this.myEntity, var3, 0);
        } 
        var2.approx_motion_x = (float)var6.accelerationX;
        var2.approx_motion_y = (float)var6.accelerationY;
        var2.approx_motion_z = (float)var6.accelerationZ;
        return (Packet)var2;
      } 
      if (this.myEntity instanceof net.minecraft.EntityEgg)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 62); 
      if (this.myEntity instanceof EntityBrick)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, (((EntityBrick)this.myEntity).getModelItem() == Item.netherrackBrick) ? 501 : 500); 
      if (this.myEntity instanceof net.oilcake.mitelros.entity.EntityWandIceBall)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 100); 
      if (this.myEntity instanceof net.oilcake.mitelros.entity.EntityWandFireball)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 101); 
      if (this.myEntity instanceof net.oilcake.mitelros.entity.EntityWandShockWave)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 102); 
      if (this.myEntity instanceof EntityGelatinousSphere) {
        EntityGelatinousSphere sphere = (EntityGelatinousSphere)this.myEntity;
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 600 + sphere.getModelSubtype());
      } 
      if (this.myEntity instanceof net.minecraft.EntityWeb)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 700); 
      if (this.myEntity instanceof net.minecraft.EntityTNTPrimed)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 50); 
      if (this.myEntity instanceof net.minecraft.EntityEnderCrystal)
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 51); 
      if (this.myEntity instanceof EntityFallingSand) {
        EntityFallingSand var5 = (EntityFallingSand)this.myEntity;
        return (Packet)new Packet23VehicleSpawn(this.myEntity, 70, var5.blockID | var5.metadata << 16);
      } 
      if (this.myEntity instanceof EntityPainting)
        return (Packet)new Packet25EntityPainting((EntityPainting)this.myEntity); 
      if (this.myEntity instanceof EntityItemFrame) {
        EntityItemFrame var4 = (EntityItemFrame)this.myEntity;
        Packet23VehicleSpawn var2 = new Packet23VehicleSpawn(this.myEntity, 71, var4.hangingDirection);
        var2.setUnscaledPositionWithIntegers(var4.xPosition, var4.yPosition, var4.zPosition);
        return (Packet)var2;
      } 
      if (this.myEntity instanceof EntityLeashKnot) {
        EntityLeashKnot var1 = (EntityLeashKnot)this.myEntity;
        Packet23VehicleSpawn var2 = new Packet23VehicleSpawn(this.myEntity, 77);
        var2.setUnscaledPositionWithIntegers(var1.xPosition, var1.yPosition, var1.zPosition);
        return (Packet)var2;
      } 
      if (this.myEntity instanceof EntityXPOrb)
        return (Packet)new Packet26EntityExpOrb((EntityXPOrb)this.myEntity); 
      throw new IllegalArgumentException("Don't know how to add " + this.myEntity.getClass() + "!");
    } 
    if (this.myEntity instanceof EntityLiving) {
      this.lastHeadMotion = MathHelper.floor_float(this.myEntity.getRotationYawHead() * 256.0F / 360.0F);
      return (Packet)new Packet24MobSpawn((EntityLiving)this.myEntity);
    } 
    Minecraft.setErrorMessage("getPacketForThisEntity: entity not handled: " + this.myEntity);
    throw new IllegalArgumentException("Don't know how to add " + this.myEntity.getClass() + "!");
  }
}
