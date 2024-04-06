package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.Enchantment;
import net.oilcake.mitelros.api.ITFEnchantment;
import net.oilcake.mitelros.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Enchantment.class)
public class EnchantmentMixin implements ITFEnchantment {
    @Unique
    public boolean isReverse() {
        return false;
    }

    @Unique
    public boolean isTreasure() {
        return false;
    }
}
