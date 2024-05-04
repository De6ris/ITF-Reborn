package net.oilcake.mitelros.mixins.gui;

import net.minecraft.GuiBeacon;
import net.minecraft.GuiBeaconButtonPower;
import net.minecraft.I18n;
import net.minecraft.Potion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiBeaconButtonPower.class)
public class GuiBeaconButtonPowerMixin {

    @Shadow
    @Final
    private int field_82262_m;

    @Shadow
    @Final
    private int field_82261_l;

    @Shadow
    @Final
    private GuiBeacon beaconGui;

    /**
     * @author
     * @reason
     */
    @Overwrite// TODO feel lazy now
    public void func_82251_b(int i, int j) {
        String string = I18n.getString(Potion.potionTypes[this.field_82261_l].getName());
        if (this.field_82262_m >= 3 && this.field_82261_l != Potion.regeneration.id && this.field_82261_l != Potion.field_76443_y.id) {
            string = string + " II";
        }
        this.beaconGui.drawCreativeTabHoveringText(string, i, j);
    }
}
