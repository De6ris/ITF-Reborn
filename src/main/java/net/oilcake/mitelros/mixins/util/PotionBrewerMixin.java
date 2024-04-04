package net.oilcake.mitelros.mixins.util;

import java.util.HashMap;

import net.minecraft.Potion;
import net.minecraft.PotionHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionHelper.class)
public class PotionBrewerMixin {
    @Shadow
    @Final
    @Mutable
    public static String sugarEffect;

    @Shadow
    @Final
    @Mutable
    public static String spiderEyeEffect;

    @Shadow
    @Final
    @Mutable
    public static String fermentedSpiderEyeEffect;

    @Shadow
    @Final
    @Mutable
    public static String speckledMelonEffect;

    @Shadow
    @Final
    @Mutable
    public static String blazePowderEffect;

    @Shadow
    @Final
    @Mutable
    public static String magmaCreamEffect;

    @Shadow
    @Final
    @Mutable
    public static String redstoneEffect;

    @Shadow
    @Final
    @Mutable
    public static String glowstoneEffect;

    @Shadow
    @Final
    @Mutable
    public static String gunpowderEffect;

    @Shadow
    @Final
    @Mutable
    public static String goldenCarrotEffect;

    @Shadow
    @Final
    private static HashMap potionRequirements;

    @Inject(method = {"<clinit>()V"}, at = {@At("RETURN")})
    private static void injectClinit(CallbackInfo callbackInfo) {
        potionRequirements.put(Integer.valueOf(Potion.resistance.getId()), "0 & 1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.wither.getId()), "0 & !1 & 2 & 3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.regeneration.getId()), "0 & !1 & !2 & !3 & 0+6");
        potionRequirements.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & 1+6");
        potionRequirements.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & 0+6");
        potionRequirements.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3");
        potionRequirements.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.weakness.getId()), "!0 & !1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3");
        potionRequirements.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(Potion.damageBoost.getId()), "0 & !1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(Potion.nightVision.getId()), "!0 & 1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(Potion.invisibility.getId()), "!0 & 1 & 2 & 3 & 2+6");
        blazePowderEffect = "+0-1-2+3&4-4+13";
        sugarEffect = "-0+1-2-3&4-4+13";
        spiderEyeEffect = "-0-1+2-3&4-4+13";
        fermentedSpiderEyeEffect = "-0+3-4+13";
        goldenCarrotEffect = "-0+1+2-3+13&4-4";
        magmaCreamEffect = "+0+1-2-3&4-4+13";
        glowstoneEffect = "+5-6-7";
        speckledMelonEffect = "+0-1+2-3&4-4+13";
        redstoneEffect = "-5+6-7";
        gunpowderEffect = "+14&13-13";
        potionAmplifiers.put(Integer.valueOf(Potion.moveSpeed.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.digSpeed.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.damageBoost.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.regeneration.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.harm.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.heal.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.poison.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.resistance.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(Potion.wither.getId()), "5");
        field_77925_n = new HashMap<>();
        potionPrefixes = new String[]{
                "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat",
                "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming",
                "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid",
                "potion.prefix.gross", "potion.prefix.stinky"};
    }

    private static final HashMap potionAmplifiers = new HashMap<>();

    @Shadow
    @Final
    @Mutable
    private static HashMap field_77925_n;

    @Shadow
    @Final
    @Mutable
    private static String[] potionPrefixes;
}
