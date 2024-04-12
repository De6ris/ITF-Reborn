package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnchantmentArrowRecovery;
import net.minecraft.EnumRarity;
import net.oilcake.mitelros.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentArrowRecovery.class)
public abstract class EnchantmentArrowRecoveryMixin extends Enchantment {
    protected EnchantmentArrowRecoveryMixin(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return (super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != Enchantments.enchantmentInfinity.effectId);
    }
}
