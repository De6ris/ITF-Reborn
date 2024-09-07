package net.oilcake.mitelros.mixins.block;

import net.minecraft.BlockLeaves;
import net.minecraft.BlockLeavesBase;
import net.minecraft.Item;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(BlockLeaves.class)
public class BlockLeavesMixin extends BlockLeavesBase {

    protected BlockLeavesMixin(int par1, Material par2Material, boolean par3) {
        super(par1, par2Material, par3);
    }

    @ModifyArg(method = "updateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockLeaves;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;Lnet/minecraft/Item;)I"), index = 1)
    private Item addLemon(Item par2) {
        if (par2 != Item.banana) return par2;
        return (new Random()).nextInt(2) == 0 ? par2 : Items.lemon;
    }

    @ModifyArg(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockLeaves;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;IIIF)I", ordinal = 3), index = 1)
    private int addLemon(int par2) {
        return (new Random()).nextInt(2) == 0 ? par2 : Items.lemon.itemID;
    }
}
