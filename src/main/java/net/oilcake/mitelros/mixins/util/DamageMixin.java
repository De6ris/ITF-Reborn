package net.oilcake.mitelros.mixins.util;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Damage.class)
public class DamageMixin {
    @Shadow
    private float amount;

    @ModifyExpressionValue(method = "applyTargetDefenseModifiers", at = @At(value = "INVOKE", target = "Lnet/minecraft/Enchantment;getLevelFraction(Lnet/minecraft/ItemStack;)F"))
    private float morningStar(float original) {
        boolean using_morningstar = getItemAttackedWith() != null &&
                getItemAttackedWith().getItem() instanceof net.oilcake.mitelros.item.ItemMorningStar;
        return original + (using_morningstar ? 0.6F : 0.0F);
    }

    @Inject(method = "applyTargetDefenseModifiers", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void tagArmament(EntityLivingBase target, EntityDamageResult result, CallbackInfoReturnable<Float> cir, float total_protection, float amount_dealt_to_armor, float piercing, float effective_protection) {
        if (target instanceof EntityPlayer && effective_protection >= this.amount) {
            cir.setReturnValue(Math.max(this.amount - effective_protection, ITFConfig.TagArmament.get() ? 0.0F : 1.0F));
        }
    }

    @ModifyArg(method = "applyTargetDefenseModifiers", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 1), index = 1)
    private float tagInstinctSurvival(float a) {// TODO needs checking
        return ITFConfig.TagInstinctSurvival.get() ? 0.0F : 1.0F;
    }

    @Shadow
    public ItemStack getItemAttackedWith() {
        return null;
    }
}
