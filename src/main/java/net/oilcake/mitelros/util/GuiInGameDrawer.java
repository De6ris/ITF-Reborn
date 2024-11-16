package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.item.potion.PotionExtend;

public class GuiInGameDrawer {
    public static final ResourceLocation icons_itf = new ResourceLocation("textures/gui/itf_icons.png");

    public static void drawWater(Gui gui, Minecraft mc, int par1, int par2) {
        int var12 = par1 / 2 + 91;
        FoodStats foodStats = mc.thePlayer.getFoodStats();
        mc.getTextureManager().bindTexture(icons_itf);
        mc.mcProfiler.endStartSection("water");
        int waterLimit = ((ITFFoodStats) mc.thePlayer.getFoodStats()).itf$GetWaterLimit();
        int water = ((ITFFoodStats) foodStats).itf$GetWater();
        int displayY = par2 - 39 - 9;
        for (int temp = 0; temp < 10; temp++) {
            int var25 = 16;
            int var36 = 0;
            int displayX = var12 - temp * 8 - 9;
            if (mc.thePlayer.isPotionActive(PotionExtend.dehydration)) {
                var25 += 27;
                var36 = 3;
            }
            if (temp < waterLimit / 2)
                gui.drawTexturedModalRect(displayX, displayY, 16 + var36 * 9, 54, 9, 9);
            if (temp * 2 + 1 < water)
                gui.drawTexturedModalRect(displayX, displayY, var25 + 9, 54, 9, 9);
            if (temp * 2 + 1 == water)
                gui.drawTexturedModalRect(displayX, displayY, var25 + 18, 54, 9, 9);
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
