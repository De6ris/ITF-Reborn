package net.oilcake.mitelros.mixins.util;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.api.ITFEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "mapEnchantmentData", at = @At(value = "NEW", target = "()Ljava/util/HashMap;"))
    private static void setNotBook(int enchantment_levels, ItemStack item_stack, CallbackInfoReturnable<Map> cir, @Local LocalBooleanRef isBook) {
        isBook.set(false);
    }

    @WrapOperation(method = "mapEnchantmentData", at = @At(value = "INVOKE", target = "Lnet/minecraft/Enchantment;canEnchantItem(Lnet/minecraft/Item;)Z"))
    private static boolean removeTreasureAndCurse(Enchantment enchantment, Item item, Operation<Boolean> original) {
        if (((ITFEnchantment) enchantment).isCurse() || ((ITFEnchantment) enchantment).isTreasure()) {
            return false;
        }
        if (item == Item.book) {
            return true;
        } else {
            return original.call(enchantment, item);
        }
    }
}
