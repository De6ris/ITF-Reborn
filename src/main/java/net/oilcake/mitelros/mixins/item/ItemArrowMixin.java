package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.xiaoyu233.fml.util.ReflectHelper;
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
        material_types = new Material[]{
                Material.flint, Material.obsidian, Material.copper, Material.silver, Material.gold, Material.iron, Material.rusted_iron, Material.ancient_metal, Material.mithril, Material.adamantium,
                Materials.nickel, Materials.tungsten, Materials.magical};
    }

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void addInformationITF(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info && this.arrowhead_material == Materials.nickel) {
            info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
        }
    }

    @Inject(method = "getChanceOfRecovery", at = @At("HEAD"), cancellable = true)
    private void itfArrow(CallbackInfoReturnable<Float> cir) {
        if (ReflectHelper.dyCast(this) == Items.arrowNickel)
            cir.setReturnValue(0.7F);
        if (ReflectHelper.dyCast(this) == Items.arrowTungsten)
            cir.setReturnValue(0.9F);
        if (ReflectHelper.dyCast(this) == Items.arrowMagical)
            cir.setReturnValue(0.0F);
    }
}
