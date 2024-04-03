package net.oilcake.mitelros.mixins.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLivingBase;
import net.minecraft.NBTTagCompound;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.oilcake.mitelros.item.potion.PotionExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({EntityLivingBase.class})
public abstract class EntityLivingMixin extends Entity {

    public EntityLivingMixin(World par1World) {
        super(par1World);
    }

    @ModifyReturnValue(method = "getArmSwingAnimationEnd", at = @At("RETURN"))
    private int modify(int original) {
        if (original != 6) return original;
        if (isPotionActive(PotionExtend.freeze))
            return (6 + (1 + getActivePotionEffect(PotionExtend.freeze).getAmplifier()) * 4);
        return 6;
    }

    @Shadow
    public boolean isPotionActive(Potion par1Potion) {
        return false;
    }

    @Shadow
    public PotionEffect getActivePotionEffect(Potion par1Potion) {
        return null;
    }

    @Inject(method = "getSpeedBoostVsSlowDown", at = @At("HEAD"), cancellable = true)
    public final void getSpeedBoostVsSlowDown(CallbackInfoReturnable<Float> cir) {
        PotionEffect slowdown_effect = getActivePotionEffect(Potion.moveSlowdown);
        PotionEffect haste_effect = getActivePotionEffect(Potion.moveSpeed);
        PotionEffect freeze_effect = getActivePotionEffect(PotionExtend.freeze);
        float slow_amount = (slowdown_effect == null) ? 0.0F : ((slowdown_effect.getAmplifier() + 1) * -0.2F);
        float haste_amount = (haste_effect == null) ? 0.0F : ((haste_effect.getAmplifier() + 1) * 0.2F);
        float freeze_amount = (freeze_effect == null) ? 0.0F : ((freeze_effect.getAmplifier() + 1) * -0.24F);
        if (this.isInWeb)
            slow_amount -= 0.75F;
        double overall_speed_modifier = (slow_amount + haste_amount + freeze_amount);
        if (overall_speed_modifier < 0.0D)
            overall_speed_modifier *= (1.0F - getResistanceToParalysis());
        cir.setReturnValue((float) overall_speed_modifier);
    }

    @Shadow
    public float getResistanceToParalysis() {
        return 0.0F;
    }

    @Shadow
    protected void dropFewItems(boolean par1, DamageSource damage_source) {
    }


    @Inject(locals = LocalCapture.CAPTURE_FAILHARD, method = {"attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"}, at = {@At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/EntityLivingBase;attackEntityFromHelper(Lnet/minecraft/Damage;Lnet/minecraft/EntityDamageResult;)Lnet/minecraft/EntityDamageResult;")})
    private void injectAfterDamageCallback(Damage damage, CallbackInfoReturnable<EntityDamageResult> c, EntityDamageResult result) {
        checkForAfterDamage(damage, result);
    }

    @Unique
    protected void checkForAfterDamage(Damage damage, EntityDamageResult result) {
    }
}
