package net.oilcake.mitelros.mixins.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(cn.xylose.mitemod.breadskin.render.RenderHud.class)
public class BreadSkinMixin {
    @ModifyArg(method = "drawNutrientsBarSeparate", at = @At(value = "INVOKE", target = "Lnet/minecraft/Gui;drawString(Lnet/minecraft/FontRenderer;Ljava/lang/String;III)V", ordinal = 2), index = 1)
    private static String compat1(String par2Str) {
        return par2Str.substring(0, par2Str.length() - 6) + "960000";
    }

    @ModifyArg(method = "drawNutrientsBarSeparate", at = @At(value = "INVOKE", target = "Lnet/minecraft/Gui;drawString(Lnet/minecraft/FontRenderer;Ljava/lang/String;III)V", ordinal = 5), index = 1)
    private static String compat3(String par2Str) {
        return par2Str.substring(0, par2Str.length() - 6) + "960000";
    }

    @ModifyConstant(method = "drawNutrientsBarSeparate", constant = @Constant(floatValue = 1600.0F))
    private static float compat2(float constant) {
        return 9600.0F;
    }

    @ModifyConstant(method = "getRateNutrient", constant = @Constant(longValue = 160000L))
    private static long compat(long constant) {
        return 960000L;
    }

    @ModifyConstant(method = "getRateNutrient", constant = @Constant(floatValue = 160000.0F))
    private static float compat(float constant) {
        return 960000.0F;
    }
}
