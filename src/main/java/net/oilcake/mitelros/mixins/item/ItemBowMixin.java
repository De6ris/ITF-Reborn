package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.api.ITFBow;
import net.oilcake.mitelros.util.quality.EnumEffectEntry;
import net.oilcake.mitelros.util.quality.EnumToolType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemBow.class)
public abstract class ItemBowMixin extends Item {
    @Inject(method = "getTicksForMaxPull", at = @At("HEAD"), cancellable = true)
    private static void setITFPullSpeed(ItemStack item_stack, CallbackInfoReturnable<Integer> cir) {
        int i = ITFBow.overridePullSpeed(item_stack);
        if (i != -1) {
            cir.setReturnValue(i);
        }
    }

    @ModifyExpressionValue(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;inCreativeMode()Z"))
    private boolean infinity(boolean original, @Local(argsOnly = true) EntityPlayer player) {
        return original || EnchantmentHelper.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity);
    }

    @ModifyExpressionValue(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;inCreativeMode()Z"))
    private boolean infinity_1(boolean original, @Local(argsOnly = true) EntityPlayer player) {
        return original || EnchantmentHelper.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity);
    }

    @Inject(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;tryDamageHeldItem(Lnet/minecraft/DamageSource;I)Lnet/minecraft/ItemDamageResult;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void modifyDamage(ItemStack item_stack, World world, EntityPlayer player, int item_in_use_count, CallbackInfo ci, ItemArrow arrow, float fraction_pulled, EntityArrow entity_arrow, int power, int punch) {
        double damageAfterPower = entity_arrow.getDamage();
        if (power > 0) {
            damageAfterPower -= ((double) ((float) power * 0.5F) + 0.5);
        }
        Material material = item_stack.getMaterialForRepairs();
        damageAfterPower *= ITFBow.getDamageModifier(material);
        if (power > 0) {
            damageAfterPower += (double) ((float) power * 0.5F) + 0.5;
        }
        damageAfterPower *= EnumToolType.getMultiplierForEntry(item_stack, EnumEffectEntry.ArrowDamage);
        entity_arrow.setDamage(damageAfterPower);
    }// TODO compatibility
}
