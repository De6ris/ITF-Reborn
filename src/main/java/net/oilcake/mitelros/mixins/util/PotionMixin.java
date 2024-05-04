package net.oilcake.mitelros.mixins.util;

import net.minecraft.Potion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Potion.class)
public abstract class PotionMixin {
    @Shadow
    @Final
    public static Potion field_76443_y;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void setIcon(CallbackInfo ci) {
        field_76443_y.setIconIndex(4, 2);
    }
}
