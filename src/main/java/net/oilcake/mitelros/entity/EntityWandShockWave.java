package net.oilcake.mitelros.entity;

import net.minecraft.*;

public class EntityWandShockWave extends EntityThrowable {
    private final int xTile = -1;

    private final int yTile = -1;

    private final int zTile = -1;

    private int inTile;

    private int ticksInGround;

    private int ticksInAir;

    public EntityWandShockWave(World world) {
        super(world);
    }

    public EntityWandShockWave(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityWandShockWave(World world, double pos_x, double pos_y, double pos_z) {
        super(world, pos_x, pos_y, pos_z);
    }

    protected float getGravityVelocity() {
        return 0.0F;
    }

    public void onUpdate() {
        super.onUpdate();
        this.worldObj.spawnParticle(EnumParticle.magicCrit, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle(EnumParticle.witchMagic, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();
        if (this.throwableShake > 0)
            this.throwableShake--;
        if (this.inGround) {
            int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
            if (var1 == this.inTile) {
                this.ticksInGround++;
                if (this.ticksInGround == 1200)
                    setDead();
                return;
            }
            this.inGround = false;
            this.motionX *= (this.rand.nextFloat() * 0.2F);
            this.motionY *= (this.rand.nextFloat() * 0.2F);
            this.motionZ *= (this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            this.ticksInAir++;
        }
        Vec3 current_pos = this.worldObj.getVec3(this.posX, this.posY, this.posZ);
        Vec3 future_pos = this.worldObj.getVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        Raycast raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setForBluntProjectile(this).performVsBlocks();
        RaycastCollision var3 = raycast.getBlockCollision();
        if (var3 != null)
            raycast.setLimitToBlockCollisionPoint();
        if (onServer() && raycast.performVsEntities().hasEntityCollisions())
            var3 = raycast.getNearestCollision();
        if (var3 != null)
            if (var3.isBlock() && var3.getBlockHit() == Block.portal) {
                setInPortal(Block.portal.getDestinationDimensionID(this.worldObj, var3.block_hit_x, var3.block_hit_y, var3.block_hit_z));
            } else {
                onImpact(var3);
            }
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float var17 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        for (this.rotationPitch = (float) (Math.atan2(this.motionY, var17) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            ;
        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            this.prevRotationPitch += 360.0F;
        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            this.prevRotationYaw -= 360.0F;
        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            this.prevRotationYaw += 360.0F;
        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float var18 = 0.99F;
        float var19 = getGravityVelocity();
        if (isInWater()) {
            for (int var7 = 0; var7 < 4; var7++) {
                float var20 = 0.25F;
                this.worldObj.spawnParticle(EnumParticle.bubble, this.posX - this.motionX * var20, this.posY - this.motionY * var20, this.posZ - this.motionZ * var20, this.motionX, this.motionY, this.motionZ);
            }
            var18 = 0.8F;
        }
        this.motionX *= var18;
        this.motionY *= var18;
        this.motionZ *= var18;
        this.motionY -= var19;
        setPosition(this.posX, this.posY, this.posZ);
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
