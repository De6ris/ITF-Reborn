package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemArrow.class)
public abstract class ItemArrowMixin extends Item {
    @Shadow
    @Final
    public Material arrowhead_material;

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void addInformationITF(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info && this.arrowhead_material == Materials.nickel) {
            info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
        }
    }
}
