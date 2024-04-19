package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.status.TemperatureManager;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.GuiInGameInfoHandler;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

import static net.oilcake.mitelros.ITFStart.MOD_ID;

@Mixin(GuiIngame.class)
public class GuiIngameMixin extends Gui {
    @Shadow
    @Final
    private Minecraft mc;

    @Inject(method = "func_110327_a", at = @At("HEAD"))
    private void injectITFWater(int par1, int par2, CallbackInfo ci) {
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        FoodStats foodStats = this.mc.thePlayer.getFoodStats();
        this.mc.getTextureManager().bindTexture(Constant.icons_itf);
        this.mc.mcProfiler.endStartSection("water");
        int water = ((ITFFoodStats) foodStats).getWater();
        for (int temp = 0; temp < 10; temp++) {
            int var28 = var13 - 9;
            int var25 = 16;
            int var36 = 0;
            int var27 = var12 - temp * 8 - 9;
            if (this.mc.thePlayer.isPotionActive(PotionExtend.dehydration)) {
                var25 += 27;
                var36 = 3;
            }
            if (temp < ((ITFFoodStats) this.mc.thePlayer.getFoodStats()).getWaterLimit() / 2)
                drawTexturedModalRect(var27, var28, 16 + var36 * 9, 54, 9, 9);
            if (temp * 2 + 1 < water)
                drawTexturedModalRect(var27, var28, var25 + 9, 54, 9, 9);
            if (temp * 2 + 1 == water)
                drawTexturedModalRect(var27, var28, var25 + 18, 54, 9, 9);
        }
    }

    @Inject(method = "func_110327_a", at = @At("HEAD"))
    private void injectITFAir(int par1, int par2, CallbackInfo ci) {
        AttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = this.mc.thePlayer.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 20;
        int var23;
        int var25;
        int var26;
        int var28;
        this.mc.mcProfiler.endStartSection("air");
        if (this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
            var23 = this.mc.thePlayer.getAir();
            var28 = MathHelper.ceiling_double_int((double) (var23 - 2) * 10.0 / 300.0);
            var25 = (byte) (MathHelper.ceiling_double_int((double) var23 * 10.0 / 300.0) - var28);

            for (var26 = 0; var26 < var28 + var25; ++var26) {
                if (var26 < var28) {
                    this.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 16, 18, 9, 9);
                } else {
                    this.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 25, 18, 9, 9);
                }
            }
        }
    }

    @Inject(method = "func_110327_a", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;isInsideOfMaterial(Lnet/minecraft/Material;)Z"), cancellable = true)
    private void injectITFCancelAir(int par1, int par2, CallbackInfo ci) {
        this.mc.mcProfiler.endSection();
        ci.cancel();
    }

    @Redirect(method = "renderGameOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;inDevMode()Z"))
    private boolean disableDevInfo() {
        return false;
    }

    @Inject(method = "renderGameOverlay(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;inDevMode()Z", shift = At.Shift.BEFORE))
    private void injectRenderPos(float par1, boolean par2, int par3, int par4, CallbackInfo ci) {
        if ((ITFConfig.DisplayHud.get() && (!this.mc.gameSettings.showDebugInfo || this.mc.gameSettings.gui_mode != 0)) && Minecraft.getErrorMessage() == null) {
            String pos = "平面坐标: (" + MathHelper.floor_double(this.mc.thePlayer.posX) + ", " + MathHelper.floor_double(this.mc.thePlayer.posZ) + ") ";
            String time = "时间: (" + this.mc.thePlayer.getWorld().getHourOfDay() + ":" + (this.mc.thePlayer.getWorld().getTotalWorldTime() % 1000L * 60L / 1000L) + ") ";
            EntityPlayer player = this.mc.thePlayer.getAsPlayer();
            if (GuiIngame.server_load >= 0) {
                ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
                String text = GuiIngame.server_load + "%";
                drawString(this.mc.fontRenderer, text, sr.getScaledWidth() - this.mc.fontRenderer.getStringWidth(text) - 2, 2, 14737632);
            }
            int difficulty = Constant.calculateCurrentDifficulty();
            String difficultyText = difficulty >= 16 ? "§c挑战难度：§4" + difficulty + " §f" : (difficulty >= 12 ? " 挑战难度：§c" + difficulty + " §f" : (difficulty >= 8 ? " 挑战难度：§6" + difficulty + " §f" : (difficulty >= 4 ? " 挑战难度：§a" + difficulty + " §f" : (difficulty >= 0 ? " 挑战难度：" + difficulty + " §f" : null))));
            StringBuilder var68 = (new StringBuilder()).append(MOD_ID);
            if (ITFConfig.FinalChallenge.get() && difficulty == ITFConfig.ultimateDifficulty)
                difficultyText = "§4终极难度§r ";
            if (difficulty < 0)
                difficultyText = "§a休闲难度§r ";
            if (player.getHeldItemStack() != null && player.getHeldItemStack().getItem() == Item.compass)
                var68.append(pos);
            if (player.getHeldItemStack() != null && player.getHeldItemStack().getItem() == Item.pocketSundial)
                var68.append(time);
            if (difficulty != 0)
                var68.append(difficultyText);
            var68.append(GuiInGameInfoHandler.weather(this.mc));
            TemperatureManager temperatureManager = ((ITFPlayer) this.mc.thePlayer).getTemperatureManager();
            float temperature = temperatureManager.bodyTemperature;
            int unit = temperatureManager.getUnit();
            var68.append(String.format(" 体温: %.3f°C (你感到: %s%d)", temperature, unit > 0 ? "+" : "", unit));
            drawString(this.mc.fontRenderer, var68.toString(), 2, 2, 14737632);
        }
    }

    @Inject(locals = LocalCapture.CAPTURE_FAILHARD, method = "func_110327_a(II)V", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/Profiler;endStartSection(Ljava/lang/String;)V", args = "ldc=air", shift = At.Shift.BEFORE))
    private void injectRenderNutrition(int par1, int par2, CallbackInfo ci, boolean var3, int var4, int var5, FoodStats var7, int var8, AttributeInstance var10, int var11, int var12, int var13, float var14, float var15) {
        int protein = Math.max(((ITFPlayer) this.mc.thePlayer).getProtein() - 800000, 0);
        int phytonutrients = Math.max(((ITFPlayer) this.mc.thePlayer).getPhytonutrients() - 800000, 0);
        int var26 = var12 - 90;
        int var25 = var13 + 32;
        if (getNutrientsPriority(protein, phytonutrients)) {
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(Constant.icons_itf);
            drawTexturedModalRect(var26 - 205, var25, 0, 106, 182, 6);
            drawTexturedModalRect(var26 - 205, var25, 0, 100, (int) (182.0F * getRateNutrient(protein)), 6);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(Constant.icons_itf);
            drawTexturedModalRect(var26 - 205, var25, 0, 94, (int) (182.0F * getRateNutrient(phytonutrients)), 6);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(Constant.icons_itf);
            drawTexturedModalRect(var26 - 205, var25, 0, 106, 182, 6);
            drawTexturedModalRect(var26 - 205, var25, 0, 94, (int) (182.0F * getRateNutrient(phytonutrients)), 6);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(Constant.icons_itf);
            drawTexturedModalRect(var26 - 205, var25, 0, 100, (int) (182.0F * getRateNutrient(protein)), 6);
            GL11.glPopMatrix();
        }
    }

    private boolean getNutrientsPriority(int protein, int phytonutrients) {
        return (protein > phytonutrients);
    }

    private float getRateNutrient(long par1) {
        par1 *= par1;
        par1 /= 160000L;
        return (float) par1 / 160000.0F;
    }

    @Redirect(method = {"func_110327_a(II)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/AttributeInstance;getAttributeValue()D"))
    private double redirectHealthLimit(AttributeInstance att) {
        return this.mc.thePlayer.getHealthLimit();
    }
}
