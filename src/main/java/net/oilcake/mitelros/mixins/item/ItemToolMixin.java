package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemTool.class)
public class ItemToolMixin extends Item {
    @Shadow
    private Material effective_material;

    @Inject(method = "getMaterialHarvestEfficiency", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"), cancellable = true)
    private void itfMaterial(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(this.itfMaterial());
    }

    @ModifyReturnValue(method = "getMultipliedHarvestEfficiency", at = @At("RETURN"))
    private float qualityFactor(float original) {
        return original;
    }

    @Unique
    private float itfMaterial() {
        if (this.effective_material == Materials.uru)
            return 3.0F;
        if (this.effective_material == Materials.nickel)
            return 2.0F;
        if (this.effective_material == Materials.tungsten)
            return 2.75F;
        return 0;
    }
}
