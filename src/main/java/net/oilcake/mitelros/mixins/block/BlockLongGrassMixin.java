package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.BlockTallGrass;
import net.minecraft.IBlockAccess;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockTallGrass.class)
public class BlockLongGrassMixin {
    @ModifyExpressionValue(method = "colorMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/BiomeGenBase;getBiomeGrassColor()I"))
    private int itfSeasonColor(int original, @Local(argsOnly = true) IBlockAccess par1IBlockAccess) {
        if (!ITFConfig.SeasonColor.get()) {
            return original;
        }
        int mixedColor = SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(), original >> 16);
        int finalColor = 0xFFFF & original;
        finalColor += mixedColor << 16;
        return finalColor;
    }
}
