package net.oilcake.mitelros.entity.misc;

import net.minecraft.*;

public class EntityWandPearl extends EntityAbstractWandShoot {
    public EntityWandPearl(World world) {
        super(world);
    }

    public EntityWandPearl(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
    }

    public EntityWandPearl(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override
    protected void onImpact(RaycastCollision rc) {
        if (rc.isEntity()) {
            rc.getEntityHit().attackEntityFrom(new Damage(DamageSource.causeThrownDamage(this, this.getThrower()), 1.0f).setKnockbackOnly());
        }
        for (int var2 = 0; var2 < 32; ++var2) {
            this.worldObj.spawnParticle(EnumParticle.portal_underworld, this.posX, this.posY + this.rand.nextDouble() * 2.0, this.posZ, this.rand.nextGaussian(), 0.0, this.rand.nextGaussian());
        }
        if (!this.worldObj.isRemote) {
            if (this.getThrower() != null && this.getThrower() instanceof ServerPlayer) {
                ServerPlayer var3 = (ServerPlayer) this.getThrower();
                if (!var3.playerNetServerHandler.connectionClosed && var3.worldObj == this.worldObj) {
                    if (this.getThrower().isRiding()) {
                        this.getThrower().mountEntity(null);
                    }
                    this.getThrower().setPositionAndUpdate(this.posX, this.posY, this.posZ);
                    this.getThrower().fallDistance = 0.0f;
                    this.playSound("mob.endermen.portal", 1.0f, 1.0f);
                }
            }
            this.setDead();
        }
    }

    @Override
    public Item getModelItem() {
        return Item.enderPearl;
    }
}
