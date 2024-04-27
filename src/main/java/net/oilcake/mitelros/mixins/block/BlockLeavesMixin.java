package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @ModifyConstant(method = "dropBlockAsEntityItem", constant = @Constant(intValue = 3))
    private int itfLeafKind(int constant) {
        return 4;
    }

    @ModifyArg(method = "dropBlockAsEntityItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockLeaves;dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;IIIF)I"), index = 1)
    private int addLemon(int par2) {
        if (par2 != Item.banana.itemID) return par2;
        return (new Random()).nextInt(2) == 0 ? par2 : Items.lemon.itemID;
    }

    @Inject(method = "colorMultiplier", at = @At("HEAD"), cancellable = true)
    public void colorMultiplierITF(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, CallbackInfoReturnable<Integer> cir) {
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;

        if (ITFConfig.SeasonColor.get()) {
            for (int var9 = -1; var9 <= 1; ++var9) {
                for (int var10 = -1; var10 <= 1; ++var10) {
                    int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
                    var6 += SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(), (var11 & 16711680) >> 16);
                    var7 += (var11 & '\uff00') >> 8;
                    var8 += var11 & 255;
                }
            }
        } else {
            for (int var9 = -1; var9 <= 1; ++var9) {
                for (int var10 = -1; var10 <= 1; ++var10) {
                    int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
                    var6 += (var11 & 16711680) >> 16;
                    var7 += (var11 & 65280) >> 8;
                    var8 += var11 & 255;
                }
            }
        }
        cir.setReturnValue((var6 / 9 & 0xFF) << 16 | (var7 / 9 & 0xFF) << 8 | var8 / 9 & 0xFF);
    }
}
