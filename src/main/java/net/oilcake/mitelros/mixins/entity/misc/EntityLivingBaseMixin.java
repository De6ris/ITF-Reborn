package net.oilcake.mitelros.mixins.entity.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {

    public EntityLivingBaseMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public PotionEffect getActivePotionEffect(Potion par1Potion) {
        return null;
    }

    @ModifyReturnValue(method = "getSpeedBoostVsSlowDown", at = @At("RETURN"))
    private float itfEffects(float original) {
        if (original < 0) original /= (1.0F - this.getResistanceToParalysis());
        PotionEffect stunning_effect = this.getActivePotionEffect(PotionExtend.stunning);
        float stunning_amount = stunning_effect == null ? 0.0F : (float) (stunning_effect.getAmplifier() + 99) * -0.5F;
        double overall_speed_modifier = original + stunning_amount;
        if (overall_speed_modifier < 0.0D)
            overall_speed_modifier *= (1.0F - this.getResistanceToParalysis());
        return (float) overall_speed_modifier;
    }

    @Shadow
    public float getResistanceToParalysis() {
        return 0.0F;
    }

    @Inject(method = "onDeathUpdate", at = @At(value = "FIELD", shift = At.Shift.AFTER, target = "Lnet/minecraft/EntityLivingBase;deathTime:I", ordinal = 1))
    private void injectTick(CallbackInfo callbackInfo) {
        if (this.getAsEntityLivingBase() instanceof EntityCubic || this.getAsEntityLivingBase() instanceof EntityWight || this.getAsEntityLivingBase() instanceof EntityInvisibleStalker) {
            this.deathTime = 20;
        }
    }

    @Shadow
    public int deathTime;
}
