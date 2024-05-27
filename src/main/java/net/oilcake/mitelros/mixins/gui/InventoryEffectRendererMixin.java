package net.oilcake.mitelros.mixins.gui;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.Container;
import net.minecraft.GuiContainer;
import net.minecraft.InventoryEffectRenderer;
import net.minecraft.Potion;
import net.oilcake.mitelros.api.ITFPotion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryEffectRenderer.class)
public abstract class InventoryEffectRendererMixin extends GuiContainer {
    public InventoryEffectRendererMixin(Container par1Container) {
        super(par1Container);
    }

    @Inject(method = "displayDebuffEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/Potion;hasStatusIcon()Z"))
    private void drawITFIcon(CallbackInfo ci, @Local Potion var8, @Local(ordinal = 0) int var1, @Local(ordinal = 1) int var2) {
        if (((ITFPotion) var8).usesIndividualTexture()) {
            this.mc.getTextureManager().bindTexture(((ITFPotion) var8).getTexture());
            this.drawTexturedModalRect2(var1 + 6, var2 + 7, 18, 18);
        }
    }
}
