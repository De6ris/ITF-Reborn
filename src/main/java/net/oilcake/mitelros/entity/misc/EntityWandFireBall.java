package net.oilcake.mitelros.entity.misc;

import net.minecraft.*;

public class EntityWandFireBall extends EntityAbstractWandShoot {

    public EntityWandFireBall(World world) {
        super(world);
    }

    public EntityWandFireBall(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityWandFireBall(World world, double pos_x, double pos_y, double pos_z) {
        super(world, pos_x, pos_y, pos_z);
    }

    @Override
    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
            if (rc.isEntity() && rc.getEntityHit() instanceof EntityLivingBase) {
                Entity var3 = rc.getEntityHit();
                float damage = 0.0F;
                if (!(var3 instanceof net.minecraft.EntityEarthElemental) && !(var3 instanceof net.minecraft.EntityBlaze) && !(var3 instanceof net.minecraft.EntityFireElemental) && !(var3 instanceof net.minecraft.EntityMagmaCube) && !(var3 instanceof net.minecraft.EntityNetherspawn)) {
                    damage = 6.0F;
                    var3.setFire(10);
                }
                var3.attackEntityFrom(new Damage(DamageSource.inFire, damage));
                for (int i = 0; i < 8; i++)
                    this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            } else if (rc.isEntity()) {
                for (int i = 0; i < 32; i++)
                    this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            } else {
                rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, (Entity) this);
            }
        for (int var5 = 0; var5 < 32; var5++)
            this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        if (this.worldObj.isRemote)
            setDead();
    }

    public Item getModelItem() {
        return Item.fireballCharge;
    }
}
