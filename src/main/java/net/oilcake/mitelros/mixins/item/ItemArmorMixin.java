package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.QualityHandler;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemArmor.class)
public abstract class ItemArmorMixin extends Item implements IDamageableItem {
    @Mutable
    @Shadow
    @Final
    private boolean is_leather;

    @Shadow
    protected Material effective_material;

    @Shadow
    @Final
    private boolean is_chain_mail;

    @Inject(method = "<init>(ILnet/minecraft/Material;IZ)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {// for fixing with materials
        if (this.effective_material == Materials.wolf_fur || this.effective_material == Materials.ice_chunk) {
            this.is_leather = true;
        }
        setCraftingDifficultyAsComponent(this.effective_material.durability * 100.0F * getNumComponentsForDurability());
    }

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void inject(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info) {
            if (item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel)
                info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemarmor.tooltip.slimeresistance", new Object[0]));
        }
    }

    @Inject(method = "getMultipliedProtection", at = @At("RETURN"), cancellable = true)
    private void quality(ItemStack item_stack, CallbackInfoReturnable<Float> cir) {
        float qualityAmplifier = item_stack != null && item_stack.getItem().hasQuality() ? QualityHandler.getQualityAmplifier(item_stack.getQuality()) : 0;
        cir.setReturnValue(cir.getReturnValue() * (1.0F + qualityAmplifier / 100.0F));
    }

    @Inject(method = "getMultipliedDurability", at = @At(value = "RETURN"), cancellable = true)
    private void InjectFixDurability(CallbackInfoReturnable<Integer> callbackInfo) {
        int a = callbackInfo.getReturnValue();
        callbackInfo.setReturnValue(a <= 0 ? 1 : a);
    }

    @Inject(method = "getMaterialProtection", at = @At("HEAD"), cancellable = true)
    private void itfMaterials(CallbackInfoReturnable<Integer> cir) {
        int protection = this.itfMaterials();
        if (protection != 0) cir.setReturnValue(protection);
    }

    @Unique
    private int itfMaterials() {
        int protection;
        if (this.effective_material == Materials.wolf_fur) {
            protection = 3;
        } else if (this.effective_material == Materials.ice_chunk) {
            protection = 2;
        } else if (this.effective_material == Materials.vibranium) {
            protection = 6;
        } else if (this.effective_material == Materials.nickel) {
            protection = 8;
        } else if (this.effective_material == Materials.tungsten || this.effective_material == Materials.ancient_metal_sacred) {
            protection = 9;
        } else if (this.effective_material == Materials.uru) {
            protection = 10;
        } else {
            return 0;
        }
        if (this.is_chain_mail) {
            protection -= 2;
        }
        return protection;
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
