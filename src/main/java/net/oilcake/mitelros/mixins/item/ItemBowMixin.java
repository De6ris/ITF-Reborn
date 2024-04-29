package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Materials;
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
public class ItemBowMixin extends Item {
    @Final
    @Shadow
    @Mutable
    private static Material[] possible_arrow_materials;

    @Shadow
    private Material reinforcement_material;

    public ItemBowMixin(int id, Material reinforcement_material) {
        super(id, Material.wood, "bows/" + reinforcement_material.toString() + "/");
    }

    @Inject(method = {"<clinit>()V"}, at = {@At("FIELD")})// TODO compatibility
    private static void injectClinit(CallbackInfo callback) {
        possible_arrow_materials = new Material[]{
                Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal,
                Materials.nickel, Materials.tungsten, Materials.magical};
    }

    @Inject(method = "getTicksForMaxPull", at = @At("HEAD"), cancellable = true)
    private static void getTicksForMaxPull(ItemStack item_stack, CallbackInfoReturnable<Integer> cir) {// TODO compatibility
        int TicksPull;
        Material material = item_stack.getMaterialForRepairs();
        if (material == Materials.tungsten) {
            TicksPull = 30;
        } else if (material == Materials.uru) {
            TicksPull = 18;
        } else if (material == Material.mithril) {
            TicksPull = 27;
        } else if (material == Material.ancient_metal) {
            TicksPull = 24;
        } else {
            TicksPull = 20;
        }
        cir.setReturnValue((int) (TicksPull * (1.0F - 0.5F * EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.quickness, item_stack))));
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
        if (material == Materials.tungsten) {
            entity_arrow.setDamage(entity_arrow.getDamage() * 1.15D);
        } else if (material == Materials.uru) {
            entity_arrow.setDamage(entity_arrow.getDamage() * 0.9D);
        } else if (material == Material.mithril) {
            entity_arrow.setDamage(entity_arrow.getDamage() * 1.1D);
        } else if (material == Material.ancient_metal) {
            entity_arrow.setDamage(entity_arrow.getDamage() * 1.05D);
        } else {
            entity_arrow.setDamage(entity_arrow.getDamage() * 0.75D);
        }
        if (power > 0) {
            entity_arrow.setDamage(entity_arrow.getDamage() + (double) ((float) power * 0.5F) + 0.5);
        }
    }

    @Inject(method = "<init>(ILnet/minecraft/Material;)V", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo) {
        this.setMaxDamage((this.reinforcement_material == Materials.tungsten) ? 256 : ((this.reinforcement_material == Materials.uru) ? 512 : ((this.reinforcement_material == Material.mithril) ? 128 : ((this.reinforcement_material == Material.ancient_metal) ? 64 : 32))));
    }


    @ModifyConstant(method = "addInformation", constant = @Constant(intValue = 10))
    private int itfBonus(int bonus) {
        if (this.reinforcement_material == Materials.tungsten) {
            bonus = 35;
        } else if (this.reinforcement_material == Materials.uru) {
            bonus = 45;
        }
        return bonus;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void itfMaxDamage(int id, Material reinforcement_material, CallbackInfo ci) {
        if (this.reinforcement_material == Materials.tungsten) {
            setMaxDamage(256);
        } else if (this.reinforcement_material == Materials.uru) {
            setMaxDamage(512);
        }
    }
}
