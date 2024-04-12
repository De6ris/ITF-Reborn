package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnchantmentUntouching;
import net.minecraft.EnumRarity;
import net.oilcake.mitelros.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentUntouching.class)
public abstract class EnchantmentSilkTouchMixin extends Enchantment {
    protected EnchantmentSilkTouchMixin(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Inject(method = "canApplyTogether", at = @At("HEAD"), cancellable = true)
    private void inject(Enchantment par1Enchantment, CallbackInfoReturnable<Boolean> cir) {
        if (par1Enchantment.effectId == Enchantments.enchantmentMelting.effectId || par1Enchantment.effectId == Enchantments.enchantmentAbsorb.effectId) {
            cir.setReturnValue(false);
        }
    }
}
