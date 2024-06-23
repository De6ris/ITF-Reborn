package net.oilcake.mitelros.mixins.gui;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.util.GuiInGameDrawer;
import net.xiaoyu233.fml.FishModLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

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

    @Inject(locals = LocalCapture.CAPTURE_FAILHARD, method = "func_110327_a(II)V", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/Profiler;startSection(Ljava/lang/String;)V", args = "ldc=armor", shift = At.Shift.BEFORE))
    private void nutritionBar(int par1, int par2, CallbackInfo ci, boolean var3, int var4, int var5, FoodStats var7, int var8, AttributeInstance var10, int var11, int var12, int var13, float var14, float var15) {
        if (ITFConfig.TemperatureBar.getBooleanValue()) {
            GuiInGameDrawer.drawTemperatureBar(this, this.mc, var12, var13);
        }
    }
}
