package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(GuiEnchantment.class)
public abstract class GuiEnchantmentMixin extends GuiContainer implements ITFGui {
    @Unique
    private int[] info = new int[6];

    public GuiEnchantmentMixin(Container par1Container) {
        super(par1Container);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer", at = @At("TAIL"))
    private void addInfo(float par1, int par2, int par3, CallbackInfo ci) {
        if (this.info[0] == 0) return;
        if (!this.inventorySlots.getSlot(0).getHasStack()) return;
        for (int line = 0; line < 3; line++) {
            int enchantmentId = this.info[2 * line];
            int enchantmentLevel = this.info[2 * line + 1];
            String var13 = Enchantment.get(enchantmentId).toString() + enchantmentLevel + "...?";
            int var4 = (this.width - this.xSize) / 2;
            int var5 = (this.height - this.ySize) / 2;
            int var18 = par2 - (var4 + 60);
            int var19 = par3 - (var5 + 14 + 19 * line);
            if (var18 >= 0 && var19 >= 0 && var18 < 108 && var19 < 19) {
                this.drawCreativeTabHoveringText(EnumChatFormatting.AQUA + var13, par2, par3);
            }
        }
    }

    @Override
    public void setEnchantmentInfo(int[] info) {
        this.info = info;
    }
}
