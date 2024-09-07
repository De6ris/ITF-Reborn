package net.oilcake.mitelros.entity.misc;

import net.minecraft.*;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class EntityWandIceBall extends EntityAbstractWandShoot {

    public EntityWandIceBall(World world) {
        super(world);
    }

    public EntityWandIceBall(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public EntityWandIceBall(World world, double pos_x, double pos_y, double pos_z) {
        super(world, pos_x, pos_y, pos_z);
    }

    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
            if (rc.isEntity() && rc.getEntityHit() instanceof EntityLivingBase) {
                Entity var3 = rc.getEntityHit();
                float damage = 1.0F;
                var3.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 310, 2));
                if (var3 instanceof net.minecraft.EntityEarthElemental || var3 instanceof net.minecraft.EntityBlaze || var3 instanceof net.minecraft.EntityFireElemental || var3 instanceof net.minecraft.EntityMagmaCube || var3 instanceof net.minecraft.EntityNetherspawn)
                    damage = 7.5F;
                var3.attackEntityFrom(new Damage(DamageSourceExtend.freeze, damage));
                setDead();
            } else if (rc.isEntity()) {
                for (int i = 0; i < 32; i++)
                    this.worldObj.spawnParticle(EnumParticle.snowballpoof, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            } else {
                if (rc.getNeighborOfBlockHit() == Block.fire)
                    this.worldObj.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, (Entity) this);
                rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, (Entity) this);
            }
        for (int var5 = 0; var5 < 32; var5++)
            this.worldObj.spawnParticle(EnumParticle.snowballpoof, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        if (this.worldObj.isRemote)
            setDead();
    }

    public Item getModelItem() {
        return Item.snowball;
    }
}
