package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ItemAppleGold;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemAppleGold.class)
public class ItemAppleGoldMixin {
    @ModifyReturnValue(method = "isGoldenApple", at = @At("RETURN"))
    private static boolean addITFGoldenApple(boolean original, @Local(argsOnly = true) ItemStack itemStack) {
        if (original) return true;
        return itemStack != null && itemStack.getItem() == Items.goldenAppleLegend;
    }
}
