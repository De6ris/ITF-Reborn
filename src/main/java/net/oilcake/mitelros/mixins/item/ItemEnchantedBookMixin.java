package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(ItemEnchantedBook.class)
public class ItemEnchantedBookMixin extends Item {
    @Inject(method = "func_92112_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/MathHelper;getRandomIntegerInRange(Ljava/util/Random;II)I"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void inject(Random par1Random, int par2, int par3, int par4, CallbackInfoReturnable<WeightedRandomChestContent> cir, Enchantment var5, ItemStack var6) {
        while (var5.rarity == EnumRarity.epic || var5.rarity == EnumRarity.rare) {
            var5 = Enchantment.enchantmentsBookList[par1Random.nextInt(Enchantment.enchantmentsBookList.length)];
        }
    }
}
