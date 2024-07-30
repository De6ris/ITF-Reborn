package net.oilcake.mitelros.mixins.entity.misc;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.mob.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.mob.EntityStray;
import net.oilcake.mitelros.entity.mob.EntityWitherBodyguard;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.quality.EnumEffectEntry;
import net.oilcake.mitelros.util.quality.EnumToolType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EntityArrow.class)
public abstract class EntityArrowMixin extends Entity {
    @Shadow
    public Entity shootingEntity;

    public EntityArrowMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public ItemStack getLauncher() {
        return null;
    }

    @Shadow
    private int knockbackStrength;

    @ModifyConstant(method = "onUpdate", constant = @org.spongepowered.asm.mixin.injection.Constant(floatValue = 0.25f, ordinal = 0))
    private float knockbackEffectsSkeleton(float constant) {
        return 0.25f * (this.knockbackStrength + 1);
    }

    @ModifyVariable(method = "setThrowableHeading", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private float itfSpeed(float velocity) {
        ItemStack launcher = this.getLauncher();
        if (launcher == null) return velocity;
        if (launcher.getItem() == Items.bowTungsten && this.shootingEntity instanceof EntityPlayer)
            velocity *= 1.35F;
        if (launcher.getItem() == Items.bowUru && this.shootingEntity instanceof EntityPlayer)
            velocity *= 1.45F;
        velocity *= EnumToolType.getMultiplierForEntry(launcher, EnumEffectEntry.ArrowSpeed);
        return velocity;
    }

    @ModifyExpressionValue(method = "onUpdate", at = @At(ordinal = 0, value = "INVOKE", target = "Lnet/minecraft/ItemArrow;getDamage()F"))
    private float addDamage(float original) {
        float dummy = 0.0F;
        if (ITFConfig.FinalChallenge.getBooleanValue()) dummy += Constant.calculateCurrentDifficulty() / 12.5F;
        if (this.shootingEntity.getClass() == EntityStray.class) dummy += 0.5F;
        if (this.shootingEntity.getClass() == EntityBoneBodyguard.class) dummy++;
        if (this.shootingEntity.getClass() == EntityWitherBodyguard.class) dummy += 1.5F;
        return original + dummy;
    }
}
