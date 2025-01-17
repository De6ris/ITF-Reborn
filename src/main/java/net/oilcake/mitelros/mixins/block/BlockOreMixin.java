package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.*;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.util.OreDropHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockOre.class)
public abstract class BlockOreMixin extends Block {
    @Unique
    private final BlockOre instance = (BlockOre) (Object) this;

    protected BlockOreMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    @WrapOperation(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
    private int preventLapisLazuiDroppingIfAbsorbed(Random instance, int i, Operation<Integer> original, @Local(argsOnly = true) BlockBreakInfo info) {
        if (EnchantmentHelper.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb)) {
            return -3;
        }
        return original.call(instance, i);
    }

    @Inject(method = "dropBlockAsEntityItem", at = @At(value = "FIELD", target = "Lnet/minecraft/BlockOre;blockID:I", ordinal = 2))
    private void changeDrop1(BlockBreakInfo info, CallbackInfoReturnable<Integer> cir, @Local(ordinal = 0) LocalIntRef id_dropped, @Local(ordinal = 2) LocalIntRef quantity_dropped) {
        if (info.wasExploded()) {
            if (OreDropHelper.isVulnerableToExplosion(instance)) {
                id_dropped.set(0);
            } else {
                int pieceID = OreDropHelper.getRawPieceItemID(instance, info.getMetadata());
                if (pieceID != 0) {
                    id_dropped.set(pieceID);
                    quantity_dropped.set(1 + info.world.rand.nextInt(2));
                }
            }
        } else {
            boolean hasAbsorb = EnchantmentHelper.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb);
            if (hasAbsorb && OreDropHelper.canAbsorb(instance)) {
                id_dropped.set(0);
                if (this == Block.oreEmerald) {
                    info.getResponsiblePlayer().triggerAchievement(AchievementList.emeralds);
                } else if (this == Block.oreDiamond) {
                    info.getResponsiblePlayer().triggerAchievement(AchievementList.diamonds);
                }
            } else {
                int pieceID = OreDropHelper.getRawPieceItemID(instance, info.getMetadata());
                if (pieceID != 0) {
                    id_dropped.set(pieceID);
                    quantity_dropped.set(4 + info.world.rand.nextInt(4));
                }
            }
        }
    }

    @ModifyExpressionValue(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockBreakInfo;getHarvesterFortune()I"))
    private int moreFortune(int original) {
        return original * 2;
    }

    @ModifyArg(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/Block;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;IIIF)I"), index = 1)
    private int smelt(int id_dropped, @Local(argsOnly = true) BlockBreakInfo info, @Local boolean suppress_fortune) {
        float chance = suppress_fortune ? 1.0F : (1.0F + info.getHarvesterFortune() * 0.2F);
        if (OreDropHelper.canAbsorb(instance) && EnchantmentHelper.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb)) {
            int xp = OreDropHelper.calcAbsorbXP(instance, chance);
            this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, xp);
        }
        if (EnchantmentHelper.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentMelting)) {
            float melting_chance = EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentMelting, info.responsible_item_stack);
            melting_chance *= (info.responsible_item_stack.getItemAsTool().getMaterialHarvestLevel() - getMinHarvestLevel(0));
            if (info.world.rand.nextFloat() < melting_chance) {
                int itemID = OreDropHelper.getMeltPieceItemID(instance);
                if (itemID != 0) return itemID;
            }
        }
        return id_dropped;
    }

}
