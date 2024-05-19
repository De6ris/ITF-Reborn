package net.oilcake.mitelros.util;

import net.minecraft.EnumQuality;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ItemTool;
import net.oilcake.mitelros.config.ITFConfig;

public enum EnumQualityEffect {
    Digging("挖掘速度", 3),
    Protection("护甲值", 1),
    Blocking("格挡效果", 1);

    final String name;
    final int multiplier;

    EnumQualityEffect(String name, int multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public static int getQualityMultiplier(EnumQuality quality, EnumQualityEffect effect) {
        return getQualityMultiplier(quality) * effect.multiplier;
    }

    public static int getQualityMultiplier(EnumQuality quality) {
        return (ITFConfig.TagWorkOfHeaven.getBooleanValue() ? 2 : 1) * (switch (quality) {
            case wretched -> -3;
            case poor, average -> 0;
            case fine -> 3;
            case excellent -> 6;
            case superb -> 9;
            case masterwork -> 12;
            case legendary -> 15;
        });
    }

    public static String getQualityInfo(Item item, EnumQuality quality) {
        EnumQualityEffect effect;
        if (item instanceof ItemArmor) {
            effect = EnumQualityEffect.Protection;
        } else if (item instanceof ItemTool itemTool) {
            if (itemTool.getToolType().equals("sword")) {
                effect = EnumQualityEffect.Blocking;
            } else {
                effect = EnumQualityEffect.Digging;
            }
        } else {
            return null;
        }
        int amplifier = getQualityMultiplier(quality, effect);
        if (amplifier == 0) return null;
        return "品质效果: " +
                (amplifier > 0 ? "提升" : "降低") +
                amplifier + "%" +
                effect.name;
    }
}
