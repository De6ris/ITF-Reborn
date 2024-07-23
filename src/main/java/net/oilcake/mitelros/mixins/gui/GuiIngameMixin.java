package net.oilcake.mitelros.mixins.gui;

import net.minecraft.Gui;
import net.minecraft.GuiIngame;
import net.minecraft.Minecraft;
import net.oilcake.mitelros.util.GuiInGameDrawer;
import net.xiaoyu233.fml.FishModLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class GuiIngameMixin extends Gui {
    @Shadow
    @Final
    private Minecraft mc;

    @Inject(method = "func_110327_a", at = @At("HEAD"))
    private void drawWaterAndAir(int par1, int par2, CallbackInfo ci) {
        GuiInGameDrawer.drawWater(this, this.mc, par1, par2);
        if (!FishModLoader.hasMod("bread_skin")) {
            GuiInGameDrawer.drawAir(this, this.mc, par1, par2);
        }
    }

    @Inject(method = "func_110327_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;isInsideOfMaterial(Lnet/minecraft/Material;)Z"), cancellable = true)
    private void injectITFCancelAir(int par1, int par2, CallbackInfo ci) {
        if (!FishModLoader.hasMod("bread_skin")) {
            this.mc.mcProfiler.endSection();
            ci.cancel();
        }
    }
}
