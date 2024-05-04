package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemArrow.class)
public abstract class ItemArrowMixin extends Item {
    @Shadow
    @Final
    @Mutable
    public static Material[] material_types;

    @Shadow
    @Final
    public Material arrowhead_material;

    @Inject(method = "<clinit>()V", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback) {
        Material[] original = material_types;
        Material[] expanded = new Material[original.length + 3];
        System.arraycopy(original, 0, expanded, 0, original.length);
        expanded[original.length] = Materials.nickel;
        expanded[original.length + 1] = Materials.tungsten;
        expanded[original.length + 2] = Materials.magical;
        material_types = expanded;
    }

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void addInformationITF(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info && this.arrowhead_material == Materials.nickel) {
            info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
        }
    }

    @Inject(method = "getChanceOfRecovery", at = @At("HEAD"), cancellable = true)
    private void itfArrow(CallbackInfoReturnable<Float> cir) {
        if (this.arrowhead_material == Materials.nickel)
            cir.setReturnValue(0.7F);
        if (this.arrowhead_material == Materials.tungsten)
            cir.setReturnValue(0.9F);
        if (this.arrowhead_material == Materials.magical)
            cir.setReturnValue(0.0F);
    }
}
