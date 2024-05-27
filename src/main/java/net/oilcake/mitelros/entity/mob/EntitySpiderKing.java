package net.oilcake.mitelros.entity.mob;

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

    @Override
    protected String getLivingSound() {
        return "imported.mob.spiderking.say";
    }

    @Override
    protected String getHurtSound() {
        return "imported.mob.spiderking.hit";
    }

    @Override
    protected String getDeathSound() {
        return "imported.mob.spiderking.death";
    }

    @Override
    protected float getSoundVolume(String sound) {
        return super.getSoundVolume(sound) * 1.3F;
    }

    @Override
    protected float getSoundPitch(String sound) {
        return super.getSoundPitch(sound) * 0.6F;
    }

    @Override
    public boolean peacefulDuringDay() {
        return false;
    }

    @Override
    public float getNaturalDefense(DamageSource damage_source) {
        return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 3.0F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.maxHealth, 28.0D);
        setEntityAttribute(SharedMonsterAttributes.followRange, 56.0D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, 13.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.92D);
    }

    @Override
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

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.spawnSums = par1NBTTagCompound.getByte("num_troops_summoned");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.spawnSums > 0)
            par1NBTTagCompound.setByte("num_troops_summoned", (byte) this.spawnSums);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!(getWorld()).isRemote) {
            List nearby_spiders = this.worldObj.getEntitiesWithinAABB(EntityArachnid.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
            Iterator<EntityArachnid> i = nearby_spiders.iterator();
            if (getTicksExistedWithOffset() % 100 == 0)
                while (i.hasNext()) {
                    EntityArachnid spiders = i.next();
                    if (spiders != this) {
                        ((ITFSpider) spiders).setCounter(200);
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

    @Override
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

    @Override
    public int getExperienceValue() {
        return super.getExperienceValue() * 6;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

}
