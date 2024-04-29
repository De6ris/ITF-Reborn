package net.oilcake.mitelros.mixins.entity.mob;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.mob.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.mob.EntitySpiderKing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityBoneLord.class)
public class EntityBoneLordMixin extends EntitySkeleton {
    public EntityBoneLordMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "applyEntityAttributes", at = @At("RETURN"))
    protected void applyEntityAttributes(CallbackInfo ci) {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.followRange, 128.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.26D);
        setEntityAttribute(SharedMonsterAttributes.attackDamage, (ITFConfig.TagBattleSuffer.getIntegerValue() > 1) ? 7.0D : 5.0D);
        setEntityAttribute(SharedMonsterAttributes.maxHealth, (ITFConfig.TagBattleSuffer.getIntegerValue() > 1) ? 30.0D : 20.0D);
    }

    @ModifyReturnValue(method = "getTroopClass", at = @At("RETURN"))
    private Class battleSuffer(Class original) {
        if (ITFConfig.TagBattleSuffer.getIntegerValue() < 2) return original;
        return this.isAncientBoneLord() ? EntityLongdeadGuardian.class : EntityBoneBodyguard.class;
    }

    @Override// TODO bad override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        if (ITFConfig.TagUnderAlliance.get() && this.rand.nextInt(3) == 0 && this.ridingEntity == null && getSkeletonType() != 1 && isAncientBoneLord()) {
            EntitySpiderKing ridingSpider = new EntitySpiderKing(this.worldObj);
            ridingSpider.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            ridingSpider.onSpawnWithEgg(null);
            this.worldObj.spawnEntityInWorld(ridingSpider);
            mountEntity(ridingSpider);
        }
        return par1EntityLivingData;
    }
}
