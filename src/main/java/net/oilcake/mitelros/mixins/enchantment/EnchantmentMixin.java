package net.oilcake.mitelros.mixins.enchantment;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.oilcake.mitelros.api.ITFEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements ITFEnchantment {
    @Shadow
    @Final
    public int effectId;

    @Shadow
    public EnumRarity rarity;

    @ModifyReturnValue(method = "getWeight", at = @At("RETURN"))
    private int resetWeight(int original) {
        return switch (this.rarity) {
            case common -> 27;
            case uncommon -> 9;
            case rare -> 3;
            case epic -> 1;
        };
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
}
