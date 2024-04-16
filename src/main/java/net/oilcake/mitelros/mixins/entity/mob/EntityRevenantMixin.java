package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityRevenant.class})
public class EntityRevenantMixin extends EntityZombie {
  private boolean gathering_troops = false;
  
  private int spawnCounter;
  
  private int spawnSums;
  
  public EntityRevenantMixin(World world) {
    super(world);
  }

  @Inject(method = "applyEntityAttributes", at = @At("RETURN"))
  protected void applyEntityAttributes(CallbackInfo ci) {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.followRange, 40.0D);
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, ((Boolean) ITFConfig.TagFallenInMineLVL2.get()).booleanValue() ? 8.75D : 7.0D);
    setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, ((Boolean) ITFConfig.TagFallenInMineLVL2.get()).booleanValue() ? 45.0D : 30.0D);
  }
  
  public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
    super.readEntityFromNBT(par1NBTTagCompound);
    this.spawnSums = par1NBTTagCompound.getByte("num_troops_summoned");
  }
  
  public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
    super.writeEntityToNBT(par1NBTTagCompound);
    if (this.spawnSums > 0)
      par1NBTTagCompound.setByte("num_troops_summoned", (byte)this.spawnSums); 
  }
  
  public void onUpdate() {
    super.onUpdate();
    if (!(getWorld()).isRemote) {
      if (getTarget() != null && 
        !isNoDespawnRequired() && getTarget() != null) {
        this.gathering_troops = true;
        func_110163_bv();
      } 
      if (this.spawnSums <= 8 && this.gathering_troops)
        if (this.spawnCounter < 20) {
          if (((Boolean) ITFConfig.TagFallenInMineLVL2.get()).booleanValue())
            this.spawnCounter++; 
        } else {
          EntityRetinueZombie Belongings = new EntityRetinueZombie(this.worldObj);
          Belongings.setPosition(this.posX, this.posY, this.posZ);
          Belongings.refreshDespawnCounter(-9600);
          this.worldObj.spawnEntityInWorld((Entity)Belongings);
          Belongings.onSpawnWithEgg(null);
          Belongings.entityFX(EnumEntityFX.summoned);
          this.spawnCounter = 0;
          this.spawnSums++;
        }  
    } 
  }
}
