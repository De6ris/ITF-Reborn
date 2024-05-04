package net.oilcake.mitelros.mixins.gui;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.GuiBeacon;
import net.minecraft.Potion;
import net.minecraft.TileEntityBeacon;
import net.oilcake.mitelros.api.ITFTileEntityBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiBeacon.class)
public class GuiBeaconMixin {
    @Shadow
    private TileEntityBeacon beacon;

    @ModifyExpressionValue(method = "updateScreen", at = @At(value = "FIELD", target = "Lnet/minecraft/TileEntityBeacon;effectsList:[[Lnet/minecraft/Potion;"))
    private Potion[][] itfEffectList(Potion[][] original) {
        return ((ITFTileEntityBeacon) this.beacon).getIsAdvanced() ? ((ITFTileEntityBeacon) this.beacon).getITFEffectList() : original;
    }
}
