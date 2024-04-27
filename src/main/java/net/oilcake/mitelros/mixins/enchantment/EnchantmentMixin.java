package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.Enchantment;
import net.oilcake.mitelros.api.ITFEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements ITFEnchantment {
    @Shadow
    @Final
    public int effectId;

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        int id = this.effectId;
        return id == Enchantment.silkTouch.effectId || id == Enchantment.fortune.effectId || id == Enchantment.looting.effectId;
    }
}
