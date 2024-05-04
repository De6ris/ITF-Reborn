package net.oilcake.mitelros.mixins.enchantment;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentProtection.class)
public class EnchantmentProtectionMixin {
    @WrapOperation(method = "getFireTimeForEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/EnchantmentHelper;getMaxEnchantmentLevel(I[Lnet/minecraft/ItemStack;)I"))
    private static int iceArmor(int var6, ItemStack[] var7, Operation<Integer> original) {
        if (var7 == null) {
            return 0;
        }
        for (ItemStack itemStack : var7) {
            if (itemStack != null && itemStack.hasMaterial(Materials.ice_chunk)) {
                return 4;
            }
        }
        return original.call(var6, var7);
    }

    @ModifyReturnValue(method = "getTotalProtectionOfEnchantments", at = @At(value = "RETURN", ordinal = 1))
    private static float iceArmor(float original, @Local(argsOnly = true) ItemStack[] armors, @Local(argsOnly = true) DamageSource damageSource) {
        if (damageSource == null || !damageSource.isFireDamage()) return original;
        int iceCount = 0;
        for (ItemStack item_stack : armors) {
            if (item_stack == null) continue;
            Item item = item_stack.getItem();
            if (item instanceof ItemArmor) {
                ItemArmor armor = item_stack.getItemAsArmor();
                if (armor.hasMaterial(Materials.ice_chunk)) {
                    iceCount += 1;
                }
                continue;
            }
            Minecraft.setErrorMessage("getTotalProtectionOfEnchantments: don't know how to handle enchanted items that aren't armor");
        }
        return original + iceCount * 2.5f;
    }
}
