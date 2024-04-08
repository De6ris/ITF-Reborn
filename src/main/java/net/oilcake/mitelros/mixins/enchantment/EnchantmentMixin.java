package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.Enchantment;
import net.oilcake.mitelros.api.ITFEnchantment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements ITFEnchantment {
    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
