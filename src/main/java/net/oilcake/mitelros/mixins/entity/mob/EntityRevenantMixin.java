package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.mob.EntityRetinueZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRevenant.class)
public class EntityRevenantMixin extends EntityZombie {
    @Unique
    private boolean gathering_troops = false;

    @Unique
    private int spawnCounter;

    @Unique
    private int spawnSums;

    public EntityRevenantMixin(World world) {
        super(world);
    }

    @Inject(method = "applyEntityAttributes", at = @At("RETURN"))
    protected void applyEntityAttributes(CallbackInfo ci) {
        setEntityAttribute(SharedMonsterAttributes.attackDamage, ITFConfig.TagFallenInMine.get() > 1 ? 8.75D : 7.0D);
        setEntityAttribute(SharedMonsterAttributes.maxHealth, ITFConfig.TagFallenInMine.get() > 1 ? 45.0D : 30.0D);
    }

    @Override// TODO bad override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.spawnSums = par1NBTTagCompound.getByte("num_troops_summoned");
    }

    @Override// TODO bad override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.spawnSums > 0)
            par1NBTTagCompound.setByte("num_troops_summoned", (byte) this.spawnSums);
    }

    @Override// TODO bad override
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
                    if (ITFConfig.TagFallenInMine.get() > 1)
                        this.spawnCounter++;
                } else {
                    EntityRetinueZombie retinue = new EntityRetinueZombie(this.worldObj);
                    retinue.setPosition(this.posX, this.posY, this.posZ);
                    retinue.refreshDespawnCounter(-9600);
                    this.worldObj.spawnEntityInWorld(retinue);
                    retinue.onSpawnWithEgg(null);
                    retinue.entityFX(EnumEntityFX.summoned);
                    this.spawnCounter = 0;
                    this.spawnSums++;
                }
        }
    }
}
