package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.mob.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.mob.EntitySpiderKing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityBoneLord.class})
public class EntityBoneLordMixin extends EntitySkeleton {
    public EntityBoneLordMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "applyEntityAttributes", at = @At("RETURN"))
    protected void applyEntityAttributes(CallbackInfo ci) {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.followRange, 128.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, (ITFConfig.TagBattleSuffer.getIntegerValue() > 1) ? 7.0D : 5.0D);
        setEntityAttribute(SharedMonsterAttributes.maxHealth, (ITFConfig.TagBattleSuffer.getIntegerValue() > 1) ? 30.0D : 20.0D);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public Class getTroopClass() {
        if (this.isAncientBoneLord()) {
            return (ITFConfig.TagBattleSuffer.getIntegerValue() > 1) ? EntityLongdeadGuardian.class : EntityLongdead.class;
        }
        return ((ITFConfig.TagBattleSuffer.getIntegerValue() > 1) ? EntityBoneBodyguard.class : EntitySkeleton.class);
    }

    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        if (((Boolean) ITFConfig.TagUnderAlliance.get()).booleanValue() && this.rand.nextInt(3) == 0 && this.ridingEntity == null && getSkeletonType() != 1 && isAncientBoneLord()) {
            EntitySpiderKing ridingSpider = new EntitySpiderKing(this.worldObj);
            ridingSpider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            ridingSpider.onSpawnWithEgg(null);
            this.worldObj.spawnEntityInWorld(ridingSpider);
            mountEntity(ridingSpider);
        }
        return par1EntityLivingData;
    }
}
