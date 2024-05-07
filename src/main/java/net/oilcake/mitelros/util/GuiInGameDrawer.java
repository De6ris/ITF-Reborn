package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.status.TemperatureManager;
import org.lwjgl.opengl.GL11;

public class GuiInGameDrawer {
    public static final ResourceLocation icons_itf = new ResourceLocation("textures/gui/itf_icons.png");

    public static void drawNutrientsBar(Gui gui, Minecraft mc, int var12, int var13) {
        int protein = Math.max(mc.thePlayer.getProtein() - 800000, 0);
        int phytonutrients = Math.max(mc.thePlayer.getPhytonutrients() - 800000, 0);
        int var26 = var12 - 90;
        int var25 = var13 + 32;
        if (protein > phytonutrients) {
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(icons_itf);
            gui.drawTexturedModalRect(var26 - 205, var25, 0, 106, 182, 6);
            gui.drawTexturedModalRect(var26 - 205, var25, 0, 100, (int) (182.0F * getRateNutrient(protein)), 6);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(icons_itf);
            gui.drawTexturedModalRect(var26 - 205, var25, 0, 94, (int) (182.0F * getRateNutrient(phytonutrients)), 6);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(icons_itf);
            gui.drawTexturedModalRect(var26 - 205, var25, 0, 106, 182, 6);
            gui.drawTexturedModalRect(var26 - 205, var25, 0, 94, (int) (182.0F * getRateNutrient(phytonutrients)), 6);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(icons_itf);
            gui.drawTexturedModalRect(var26 - 205, var25, 0, 100, (int) (182.0F * getRateNutrient(protein)), 6);
            GL11.glPopMatrix();
        }
    }

    private static float getRateNutrient(long par1) {
        par1 *= par1;
        par1 /= 160000L;
        return (float) par1 / 160000.0F;
    }

    public static void drawTemperatureBar(Gui gui, Minecraft mc, int var12, int var13) {
        int var26 = var12 - 90;
        int var25 = var13 + 24;
        GL11.glPushMatrix();
        GL11.glScalef(0.6F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(icons_itf);
        gui.drawTexturedModalRect(var26 - 205, var25, 0, 106, 182, 6);
        double temperature = ((ITFPlayer) mc.thePlayer).getTemperatureManager().getBodyTemperature();
        int length = (int) (91.0D * (((temperature - TemperatureManager.normalTemperature) * (temperature - TemperatureManager.normalTemperature) * (temperature > TemperatureManager.normalTemperature ? 1 : -1) / 9.0D) + 1.0D));
        if (length > 182) length = 182;
        if (length < 0) length = 1;
        gui.drawTexturedModalRect(var26 - 205, var25, 0, 118, length, 6);
        GL11.glPopMatrix();
    }

    public static void drawWater(Gui gui, Minecraft mc, int par1, int par2) {
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        FoodStats foodStats = mc.thePlayer.getFoodStats();
        mc.getTextureManager().bindTexture(icons_itf);
        mc.mcProfiler.endStartSection("water");
        int water = ((ITFFoodStats) foodStats).getWater();
        for (int temp = 0; temp < 10; temp++) {
            int var28 = var13 - 9;
            int var25 = 16;
            int var36 = 0;
            int var27 = var12 - temp * 8 - 9;
            if (mc.thePlayer.isPotionActive(PotionExtend.dehydration)) {
                var25 += 27;
                var36 = 3;
            }
            if (temp < ((ITFFoodStats) mc.thePlayer.getFoodStats()).getWaterLimit() / 2)
                gui.drawTexturedModalRect(var27, var28, 16 + var36 * 9, 54, 9, 9);
            if (temp * 2 + 1 < water)
                gui.drawTexturedModalRect(var27, var28, var25 + 9, 54, 9, 9);
            if (temp * 2 + 1 == water)
                gui.drawTexturedModalRect(var27, var28, var25 + 18, 54, 9, 9);
        }
    }

    public static void drawAir(Gui gui, Minecraft mc, int par1, int par2) {
        AttributeInstance var10 = mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = mc.thePlayer.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int displayY = var13 - (var16 - 1) * var17 - 20;
        int var23;
        int var25;
        int var26;
        int var28;
        mc.mcProfiler.endStartSection("air");
        if (mc.thePlayer.isInsideOfMaterial(Material.water)) {
            var23 = mc.thePlayer.getAir();
            var28 = MathHelper.ceiling_double_int((double) (var23 - 2) * 10.0 / 300.0);
            var25 = (byte) (MathHelper.ceiling_double_int((double) var23 * 10.0 / 300.0) - var28);

            for (var26 = 0; var26 < var28 + var25; ++var26) {
                if (var26 < var28) {
                    gui.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 16, 18, 9, 9);
                } else {
                    gui.drawTexturedModalRect(var12 - var26 * 8 - 9, displayY, 25, 18, 9, 9);
                }
            }
        }
    }
}
