package net.oilcake.mitelros.entity;

import net.minecraft.*;

public class EntityClusterSpider extends EntityArachnid {
    int num_webs;

    public EntityClusterSpider(World par1World) {
        super(par1World, 0.4F);
        this.num_webs = 4;
    }

    protected float getSoundVolume(String sound) {
        return super.getSoundVolume(sound) * 0.5F;
    }

    protected float getSoundPitch(String sound) {
        return super.getSoundPitch(sound) * 1.4F;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.maxHealth, 6.0D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, 3.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.75D);
    }

    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof net.minecraft.EntityLivingBase)
                target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 450, 1));
            return result;
        }
        return result;
    }

}
