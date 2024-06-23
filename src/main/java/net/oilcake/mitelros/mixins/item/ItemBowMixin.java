package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ITFBow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemBow.class)
public abstract class ItemBowMixin extends Item {
    @Final
    @Shadow
    @Mutable
    private static Material[] possible_arrow_materials;

    @Mutable
    @Shadow
    @Final
    public static String[] bow_pull_icon_name_array;
    @Shadow
    private Material reinforcement_material;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addITFBowlMaterials(CallbackInfo ci) {
        Material[] original = possible_arrow_materials;
        Material[] expanded = new Material[original.length + 3];
        System.arraycopy(original, 0, expanded, 0, original.length);
        expanded[original.length] = Materials.nickel;
        expanded[original.length + 1] = Materials.tungsten;
        expanded[original.length + 2] = Materials.magical;
        possible_arrow_materials = expanded;

        bow_pull_icon_name_array = new String[possible_arrow_materials.length * 3];

        for (int arrow_index = 0; arrow_index < possible_arrow_materials.length; ++arrow_index) {
            Material material = possible_arrow_materials[arrow_index];
            for (int icon_index = 0; icon_index < 3; ++icon_index) {
                ItemBow.bow_pull_icon_name_array[arrow_index * 3 + icon_index] = material.name + "_arrow_" + icon_index;
            }
        }
    }// feels stupid

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
        if (power > 0) {
            entity_arrow.setDamage(entity_arrow.getDamage() - (double) ((float) power * 0.5F) - 0.5);
        }
        Material material = item_stack.getMaterialForRepairs();
        entity_arrow.setDamage(entity_arrow.getDamage() * ITFBow.getDamageModifier(material));
        if (power > 0) {
            entity_arrow.setDamage(entity_arrow.getDamage() + (double) ((float) power * 0.5F) + 0.5);
        }
    }// TODO compatibility

    @ModifyConstant(method = "addInformation", constant = @Constant(intValue = 10))
    private int itfBonus(int bonus) {
        int itfBonus = ITFBow.getArrowSpeedBonus(this.reinforcement_material);
        return itfBonus != 0 ? itfBonus : bonus;
    }
}
