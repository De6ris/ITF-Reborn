package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnchantmentPiercing;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.oilcake.mitelros.item.api.ItemMorningStar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentPiercing.class)
public abstract class EnchantmentPiercingMixin extends Enchantment {
    protected EnchantmentPiercingMixin(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Inject(method = "canEnchantItem", at = @At("HEAD"), cancellable = true)
    private void inject(Item item, CallbackInfoReturnable<Boolean> cir) {
        if (item.getClass() == ItemMorningStar.class) {
            cir.setReturnValue(true);
        }
    }
}
