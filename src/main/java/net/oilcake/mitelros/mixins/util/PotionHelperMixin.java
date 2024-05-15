package net.oilcake.mitelros.mixins.util;

import net.minecraft.Potion;
import net.minecraft.PotionHelper;
import net.oilcake.mitelros.item.potion.PotionExtend;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(PotionHelper.class)
public class PotionHelperMixin {
    @Shadow
    @Final
    private static HashMap potionRequirements;

    @Shadow
    @Final
    private static HashMap potionAmplifiers;

    @Inject(method = "<clinit>()V", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callbackInfo) {
        potionRequirements.put(Potion.resistance.getId(), "0 & 1 & 2 & !3 & 2+6");
        potionRequirements.put(Potion.wither.getId(), "0 & !1 & 2 & 3 & 2+6");
        potionRequirements.put(PotionExtend.stretch.getId(), "8 & 9 & 10 & 11 & 8+6");
        potionRequirements.put(PotionExtend.frostResistance.getId(), "8 & 9 & 10 & !11 & 8+6");
        potionAmplifiers.put(Potion.wither.getId(), "5");
        potionAmplifiers.put(PotionExtend.stretch.getId(), "5");
        potionAmplifiers.put(PotionExtend.frostResistance.getId(), "5");
    }
}