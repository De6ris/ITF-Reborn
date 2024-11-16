package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.quality.EnumEffectEntry;
import net.oilcake.mitelros.util.quality.EnumToolType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemArmor.class)
public abstract class ItemArmorMixin extends Item implements IDamageableItem {
    @Shadow
    protected Material effective_material;

    @Shadow
    @Final
    private boolean is_chain_mail;

    @Inject(method = "<init>(ILnet/minecraft/Material;IZ)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        setCraftingDifficultyAsComponent(this.effective_material.durability * 100.0F * getNumComponentsForDurability());
    }

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void inject(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info) {
            if (item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel)
                info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemarmor.tooltip.slimeresistance", new Object[0]));
        }
    }

    @ModifyReturnValue(method = "getMultipliedProtection", at = @At("RETURN"))
    private float modifyProtectionByQuality(float original, @Local(argsOnly = true) ItemStack itemStack) {
        return original * EnumToolType.getMultiplierForEntry(itemStack, EnumEffectEntry.Protection);
    }

    @Inject(method = "getMultipliedDurability", at = @At(value = "RETURN"), cancellable = true)
    private void InjectFixDurability(CallbackInfoReturnable<Integer> callbackInfo) {
        int a = callbackInfo.getReturnValue();
        callbackInfo.setReturnValue(a <= 0 ? 1 : a);
    }

    @ModifyConstant(method = "getDamageFactor", constant = @Constant(floatValue = 0.5f))
    private float instinctSurvival(float constant) {
        return ITFConfig.TagInstinctSurvival.get() ? 0.75F : 0.5F;
    }

    @ModifyReturnValue(method = "getDamageFactor", at = @At(value = "RETURN", ordinal = 2))
    private float instinctSurvival_1(float original) {
        return original < 0.5f ? 2 * original : 1.0f;
    }
}
