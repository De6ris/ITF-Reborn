package net.oilcake.mitelros.mixins.util;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.SoundsMITE;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SoundsMITE.class)
public class SoundsMITEMixin {
    @ModifyExpressionValue(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/ResourcePack;resourceExists(Lnet/minecraft/ResourceLocation;)Z"))
    private boolean widen(boolean original) {
        return true;
    }
}
