package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.QualityHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
    private Material effective_material;

    @Shadow
    @Final
    private boolean is_chain_mail;

    @Inject(method = "<init>(ILnet/minecraft/Material;IZ)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.is_leather = (this.effective_material == Material.leather || this.effective_material == Materials.wolf_fur);
        setCraftingDifficultyAsComponent(this.effective_material.durability * 100.0F * getNumComponentsForDurability());
    }

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void inject(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info && item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel)
            info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemarmor.tooltip.slimeresistance", new Object[0]));
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

    @Inject(method = "getMaterialProtection", at = @At(value = "RETURN"), cancellable = true)
    private void InjectNewProtection(CallbackInfoReturnable<Integer> callbackInfo) {
        int protection = callbackInfo.getReturnValue();
        if (protection == 0) {
            if (this.effective_material == Materials.wolf_fur) {
                protection = 3;
            } else if (this.effective_material == Materials.vibranium) {
                protection = 6;
            } else if (this.effective_material == Materials.nickel) {
                protection = 8;
            } else if (this.effective_material == Materials.tungsten || this.effective_material == Materials.ancient_metal_sacred) {
                protection = 9;
            } else if (this.effective_material == Materials.uru) {
                protection = 10;
            }
            if (this.is_chain_mail) {
                protection -= 2;
            }
        }
        if (protection < 0) {
            protection = 0;
        }
        callbackInfo.setReturnValue(protection);
    }

    @Inject(method = "getDamageFactor", at = @At("RETURN"), cancellable = true)
    private void inject(ItemStack item_stack, EntityLivingBase owner, CallbackInfoReturnable<Float> cir) {
        float returned = cir.getReturnValue();
        if (!owner.isEntityPlayer() && ITFConfig.TagInstinctSurvival.get()) {
            cir.setReturnValue(returned + 0.25F);
            return;
        }
        if (ITFConfig.TagArmament.get()) {
            cir.setReturnValue(returned * 2);
        }
    }
}
