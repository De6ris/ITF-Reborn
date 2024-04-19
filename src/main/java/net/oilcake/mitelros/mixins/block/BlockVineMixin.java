package net.oilcake.mitelros.mixins.block;

import net.minecraft.BlockVine;
import net.minecraft.IBlockAccess;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockVine.class)
public class BlockVineMixin {
    @Inject(method = "colorMultiplier", at = @At("RETURN"), cancellable = true)
    private void seasonColor(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, CallbackInfoReturnable<Integer> cir) {
        if (!ITFConfig.SeasonColor.get()) {
            return;
        }
        int original = cir.getReturnValue();
        int mixedColor = SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(), original >> 16);
        cir.setReturnValue(0xFFFF & original + (mixedColor << 16));
    }
}
