package net.oilcake.mitelros.entity.misc;

import net.minecraft.EntityLivingBase;
import net.minecraft.EntityThrowable;
import net.minecraft.World;

public abstract class EntityAbstractWandShoot extends EntityThrowable {

    public EntityAbstractWandShoot(World par1World) {
        super(par1World);
    }

    public EntityAbstractWandShoot(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
        this.motionX *= 3.0F;
        this.motionY *= 3.0F;
        this.motionZ *= 3.0F;// 3x speed
    }

    public EntityAbstractWandShoot(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }// won't fall
}
