package net.oilcake.mitelros.mixins.util;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.api.ItemMorningStar;
import net.oilcake.mitelros.util.quality.EnumEffectEntry;
import net.oilcake.mitelros.util.quality.EnumToolType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Damage.class)
public class DamageMixin {
    @Shadow
    private float amount;

    @ModifyConstant(method = "applyTargetDefenseModifiers", constant = @Constant(floatValue = 2.0f))
    private float qualityEffect(float constant, @Local EntityPlayer player) {
        ItemStack itemStack = player.getItemInUse();
        return constant * EnumToolType.getMultiplierForEntry(itemStack, EnumEffectEntry.Blocking);
    }

    @ModifyExpressionValue(method = "applyTargetDefenseModifiers", at = @At(value = "INVOKE", target = "Lnet/minecraft/Enchantment;getLevelFraction(Lnet/minecraft/ItemStack;)F"))
    private float morningStar(float original) {
        boolean using_morningstar = getItemAttackedWith() != null &&
                getItemAttackedWith().getItem() instanceof ItemMorningStar;
        return original + (using_morningstar ? 0.6F : 0.0F);
    }

    @Inject(method = "applyTargetDefenseModifiers", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 1), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void tagArmament(EntityLivingBase target, EntityDamageResult result, CallbackInfoReturnable<Float> cir, float total_protection, float amount_dealt_to_armor, float piercing, float effective_protection) {
        if (ITFConfig.TagArmament.getBooleanValue() && target instanceof EntityPlayer && effective_protection >= this.amount) {
            cir.setReturnValue(0.0F);
        }
    }

    @ModifyArg(method = "applyTargetDefenseModifiers", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F", ordinal = 1), index = 1)
    private float tagInstinctSurvival(float a) {
        return ITFConfig.TagInstinctSurvival.getBooleanValue() ? 0.0F : 1.0F;
    }

    @Shadow
    public ItemStack getItemAttackedWith() {
        return null;
    }
}
