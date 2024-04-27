package net.oilcake.mitelros.mixins.item;

import net.minecraft.ItemCarrotOnAStick;
import net.minecraft.ItemFishingRod;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemCarrotOnAStick.class)
public abstract class ItemCarrotStickMixin {
    @Shadow
    protected Material hook_material;

    @Inject(method = "getFishingRodItem", at = @At("HEAD"), cancellable = true)
    public void getFishingRodItem(CallbackInfoReturnable<ItemFishingRod> cir) {
        if (this.hook_material == Materials.nickel)
            cir.setReturnValue(Items.fishingRodNickel);
        if (this.hook_material == Materials.tungsten)
            cir.setReturnValue(Items.fishingRodTungsten);
    }
}
