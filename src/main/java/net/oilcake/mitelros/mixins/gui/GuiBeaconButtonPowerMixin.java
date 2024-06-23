package net.oilcake.mitelros.mixins.gui;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.GuiBeaconButtonPower;
import net.minecraft.Potion;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiBeaconButtonPower.class)
public class GuiBeaconButtonPowerMixin {
    // this is for killing "saturation II"
    @ModifyExpressionValue(method = "func_82251_b", at = @At(value = "FIELD", target = "Lnet/minecraft/GuiBeaconButtonPower;field_82261_l:I", opcode = Opcodes.GETFIELD, ordinal = 1))
    private int fixBug(int original) {
        if (original == Potion.field_76443_y.id) {
            return Potion.regeneration.id;
        }
        return original;
    }
}
