package net.oilcake.mitelros.mixins.util;

import net.minecraft.Potion;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.api.ITFPotion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Potion.class)
public abstract class PotionMixin implements ITFPotion {
    @Shadow
    @Final
    public static Potion field_76443_y;

    @Shadow
    @Final
    public int id;

    @Override
    public boolean usesIndividualTexture() {
        return this.id == field_76443_y.id;
    }

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation("textures/gui/mob_effects/saturation.png");
    }
}
