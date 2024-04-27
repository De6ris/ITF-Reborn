package net.oilcake.mitelros.mixins.util;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Item;
import net.oilcake.mitelros.api.ITFEnchantment;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @ModifyExpressionValue(method = "mapEnchantmentData", at = @At(value = "INVOKE", target = "Lnet/minecraft/Enchantment;canEnchantItem(Lnet/minecraft/Item;)Z"))
    private static boolean noTreasureAndCurse(boolean original, @Local Enchantment enchantment) {
        return original && !((ITFEnchantment) enchantment).isCurse() && !((ITFEnchantment) enchantment).isTreasure();
    }

    @ModifyConstant(method = "buildEnchantmentList", constant = @Constant(intValue = 2, ordinal = 0))
    private static int uruEnchantMore1(int constant, @Local Item item) {
        return (item.getMaterialForRepairs() == Materials.uru) ? 4 : 2;
    }

    @ModifyConstant(method = "buildEnchantmentList", constant = @Constant(intValue = 2, ordinal = 2))
    private static int uruEnchantMore2(int constant, @Local Item item) {
        return (item.getMaterialForRepairs() == Materials.uru) ? 4 : 2;
    }
}
