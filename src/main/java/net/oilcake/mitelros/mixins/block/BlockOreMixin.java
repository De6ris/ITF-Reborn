package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockOre.class)
public abstract class BlockOreMixin extends Block {
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
            if (this.isVulnerableToExplosion()) {
                id_dropped.set(0);
            } else {
                int pieceID = this.oreToPieceID(info.getMetadata());
                if (pieceID != 0) {
                    id_dropped.set(pieceID);
                    quantity_dropped.set(1 + info.world.rand.nextInt(2));
                }
            }
            return;
        }
        boolean hasAbsorb = EnchantmentHelper.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb);
        if (hasAbsorb && this.canAbsorb()) {
            id_dropped.set(0);
            if (this == Block.oreEmerald) {
                info.getResponsiblePlayer().triggerAchievement(AchievementList.emeralds);
            } else if (this == Block.oreDiamond) {
                info.getResponsiblePlayer().triggerAchievement(AchievementList.diamonds);
            }
        } else {
            int pieceID = this.oreToPieceID(info.getMetadata());
            if (pieceID != 0) {
                id_dropped.set(pieceID);
                quantity_dropped.set(4 + info.world.rand.nextInt(4));
            }
        }
    }

    @Unique
    private boolean isVulnerableToExplosion() {
        return this == Blocks.blockAzurite;
    }

    @Unique
    private int oreToPieceID(int metadata) {
        if (this == Block.oreCopper) return Items.pieceCopper.itemID;
        if (this == Block.oreSilver) return Items.pieceSilver.itemID;
        if (this == Block.oreIron) return Items.pieceIron.itemID;
        if (this == Block.oreGold) return metadata == 2 ? Items.pieceGoldNether.itemID : Items.pieceGold.itemID;
        if (this == Block.oreMithril) return Items.pieceMithril.itemID;
        if (this == Block.oreAdamantium) return Items.pieceAdamantium.itemID;
        if (this == Block.oreNetherQuartz) return Item.shardNetherQuartz.itemID;
        if (this == Block.oreDiamond) return Item.shardDiamond.itemID;
        if (this == Block.oreEmerald) return Item.shardEmerald.itemID;
        if (this == Blocks.oreNickel) return Items.pieceNickel.itemID;
        if (this == Blocks.oreTungsten) return Items.pieceTungsten.itemID;
        if (this == Blocks.oreUru) return Items.pieceUru.itemID;
        if (this == Blocks.blockSulphur) return Items.sulphur.itemID;
        if (this == Blocks.blockAzurite) return Items.shardAzurite.itemID;
        return 0;
    }

    @Unique
    private int oreToMeltPiece() {
        if (this == Block.oreCopper) return Item.copperNugget.itemID;
        if (this == Block.oreSilver) return Item.silverNugget.itemID;
        if (this == Block.oreGold) return Item.goldNugget.itemID;
        if (this == Block.oreIron) return Item.ironNugget.itemID;
        if (this == Block.oreMithril) return Item.mithrilNugget.itemID;
        if (this == Block.oreAdamantium) return Item.adamantiumNugget.itemID;
        if (this == Blocks.oreNickel) return Items.nickelNugget.itemID;
        if (this == Blocks.oreTungsten) return Items.tungstenNugget.itemID;
        if (this == Blocks.oreUru) return Items.uruNugget.itemID;
        return 0;
    }

    @ModifyConstant(method = "dropBlockAsEntityItem", constant = @Constant(floatValue = 0.1f))
    private float moreFortune(float constant) {
        return constant * 2.0f;
    }

    @ModifyArg(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/Block;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;IIIF)I"), index = 1)
    private int smelt(int id_dropped, @Local(argsOnly = true) BlockBreakInfo info, @Local boolean suppress_fortune) {
        float chance = suppress_fortune ? 1.0F : (1.0F + info.getHarvesterFortune() * 0.2F);
        if (this.canAbsorb() && EnchantmentHelper.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb)) {
            int xp = this.calcAbsorbXP(chance);
            this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, xp);
        }
        if (EnchantmentHelper.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentMelting)) {
            float melting_chance = EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentMelting, info.responsible_item_stack);
            melting_chance *= (info.responsible_item_stack.getItemAsTool().getMaterialHarvestLevel() - getMinHarvestLevel(0));
            if (info.world.rand.nextFloat() < melting_chance && this.oreToMeltPiece() != 0) {
                return this.oreToMeltPiece();
            }
        }
        return id_dropped;
    }

    @Unique
    private boolean canAbsorb() {
        return this.calcAbsorbXP(1.0F) != 0;
//        return this == Block.oreDiamond || this == Block.oreEmerald || this == Blocks.blockAzurite || this == Block.oreNetherQuartz || this == Block.oreLapis;
    }

    @Unique
    private int calcAbsorbXP(float chance) {
        if (this == Block.oreDiamond) return (int) (530.0F * chance);
        if (this == Block.oreEmerald) return (int) (270.0F * chance);
        if (this == Blocks.blockAzurite) return (int) (((3 + new Random().nextInt(5)) * 6) * chance);
        if (this == Block.oreNetherQuartz) return (int) (60.0F * chance);
        if (this == Block.oreLapis) return (int) (((3 + new Random().nextInt(3)) * 30) * chance);
        return 0;
    }
}
