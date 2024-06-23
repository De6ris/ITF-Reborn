package net.oilcake.mitelros.mixins.compat;

import cn.xylose.mitemod.breadskin.render.RenderHud;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderHud.class)
public class BreadSkinMixin {
    @ModifyExpressionValue(
            method = {"drawNutrientsBarMixed", "drawNutrientsBarSeparate"},
            at = {@At(value = "INVOKE",
                    target = "Lcn/xylose/mitemod/breadskin/api/BreadSkinClientPlayer;breadSkin$GetProtein()I"),
                    @At(value = "INVOKE",
                            target = "Lcn/xylose/mitemod/breadskin/api/BreadSkinClientPlayer;breadSkin$GetPhytonutrients()I")
            })
    private static int compat(int original) {
        return Math.max(original - 800000, 0);
    }
}
