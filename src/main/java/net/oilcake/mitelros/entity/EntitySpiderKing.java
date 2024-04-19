package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFSpider;

import java.util.Iterator;
import java.util.List;

public class EntitySpiderKing extends EntityArachnid {

    private int spawnCounter;

    private int spawnSums;

    private boolean gathering_troops;

    public EntitySpiderKing(World par1World) {
        super(par1World, 1.45F);
        this.gathering_troops = false;
    }

    protected String getLivingSound() {
        return "imported.mob.spiderking.say";
    }

    protected String getHurtSound() {
        return "imported.mob.spiderking.hit";
    }

    protected String getDeathSound() {
        return "imported.mob.spiderking.death";
    }

    protected float getSoundVolume(String sound) {
        return super.getSoundVolume(sound) * 1.3F;
    }

    protected float getSoundPitch(String sound) {
        return super.getSoundPitch(sound) * 0.6F;
    }

    public boolean peacefulDuringDay() {
        return false;
    }

    public float getNaturalDefense(DamageSource damage_source) {
        return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 3.0F);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.maxHealth, 28.0D);
        setEntityAttribute(SharedMonsterAttributes.followRange, 56.0D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, 13.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.92D);
    }

    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityLivingBase) {
                target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 5));
                target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.poison.id, 850, 0));
            }
            return result;
        }
        return result;
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.spawnSums = par1NBTTagCompound.getByte("num_troops_summoned");
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.spawnSums > 0)
            par1NBTTagCompound.setByte("num_troops_summoned", (byte) this.spawnSums);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!(getWorld()).isRemote) {
            List nearby_spiders = this.worldObj.getEntitiesWithinAABB(EntityArachnid.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
            Iterator<EntityArachnid> i = nearby_spiders.iterator();
            if (getTicksExistedWithOffset() % 100 == 0)
                while (i.hasNext()) {
                    EntityArachnid spiders = i.next();
                    if (spiders != this) {
                        ((ITFSpider) spiders).getFrenziedManager().setCounter(200);
                        if (spiders.getHealthFraction() < 1.0F) {
                            spiders.healByPercentage(0.2F);
                            spiders.entityFX(EnumEntityFX.repair);
                        }
                    }
                }
            if (getTarget() != null &&
                    !isNoDespawnRequired() && getTarget() != null) {
                this.gathering_troops = true;
                func_110163_bv();
            }
            if (this.spawnSums < 8 && this.gathering_troops)
                if (this.spawnCounter < 20) {
                    this.spawnCounter++;
                } else {
                    EntityClusterSpider clusterSpider = new EntityClusterSpider(this.worldObj);
                    clusterSpider.setPosition(this.posX + this.rand.nextInt(4) - this.rand.nextInt(4), this.posY, this.posZ - this.rand.nextInt(4) + this.rand.nextInt(4));
                    clusterSpider.refreshDespawnCounter(-9600);
                    this.worldObj.spawnEntityInWorld(clusterSpider);
                    clusterSpider.onSpawnWithEgg(null);
                    clusterSpider.entityFX(EnumEntityFX.summoned);
                    this.spawnCounter = 0;
                    this.spawnSums++;
                }
        }
    }

    public void onDeathUpdate() {
        super.onDeathUpdate();
        if (this.deathTime == 20) {
            EntityPotion potion = new EntityPotion(this.worldObj, this, 16388);
            potion.setPosition(this.posX, this.posY - 1.0D, this.posZ);
            this.worldObj.spawnEntityInWorld(potion);
            potion = new EntityPotion(this.worldObj, this, 16426);
            potion.setPosition(this.posX, this.posY - 1.0D, this.posZ);
            this.worldObj.spawnEntityInWorld(potion);
        }
    }

    public int getExperienceValue() {
        return super.getExperienceValue() * 6;
    }

    public int getMaxSpawnedInChunk() {
        return 1;
    }

}
