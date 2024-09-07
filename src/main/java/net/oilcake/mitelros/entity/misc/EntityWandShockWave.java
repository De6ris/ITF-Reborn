package net.oilcake.mitelros.entity.misc;

import net.minecraft.*;

public class EntityWandShockWave extends EntityAbstractWandShoot {
    public EntityWandShockWave(World world) {
        super(world);
    }

    public EntityWandShockWave(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityWandShockWave(World world, double pos_x, double pos_y, double pos_z) {
        super(world, pos_x, pos_y, pos_z);
    }

    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
            if (rc.isEntity() && rc.getEntityHit() instanceof EntityLivingBase) {
                Entity var3 = rc.getEntityHit();
                float damage = 10.0F;
                var3.attackEntityFrom(new Damage(DamageSource.divine_lightning, damage));
                EntityLightningBolt lightingbolt = new EntityLightningBolt(this.worldObj, var3.posX, var3.posY, var3.posZ);
                this.worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
                setDead();
            } else if (rc.isEntity()) {
                Entity var3 = rc.getEntityHit();
                EntityLightningBolt lightingbolt = new EntityLightningBolt(this.worldObj, var3.posX, var3.posY, var3.posZ);
                this.worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
                setDead();
            } else {
                EntityLightningBolt lightingbolt = new EntityLightningBolt(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
                this.worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
                rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, this);
                setDead();
            }
        for (int var5 = 0; var5 < 32; var5++)
            this.worldObj.spawnParticle(EnumParticle.witchMagic, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        if (this.worldObj.isRemote)
            setDead();
    }

    public Item getModelItem() {
        return Item.eyeOfEnder;
    }
}
