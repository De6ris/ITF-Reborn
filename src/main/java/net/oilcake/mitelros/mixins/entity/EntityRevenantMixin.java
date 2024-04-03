package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import net.minecraft.EntityRevenant;
import net.minecraft.EntityZombie;
import net.minecraft.EnumEntityFX;
import net.minecraft.NBTTagCompound;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityRevenant.class})
public class EntityRevenantMixin extends EntityZombie {
  private boolean gathering_troops = false;
  
  private int spawnCounter;
  
  private int spawnSums;
  
  public EntityRevenantMixin(World world) {
    super(world);
  }
  
  @Overwrite
  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    setEntityAttribute(SharedMonsterAttributes.followRange, 40.0D);
    setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
    setEntityAttribute(SharedMonsterAttributes.attackDamage, ((Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue).booleanValue() ? 8.75D : 7.0D);
    setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612D);
    setEntityAttribute(SharedMonsterAttributes.maxHealth, ((Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue).booleanValue() ? 45.0D : 30.0D);
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
          if (((Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue).booleanValue())
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
