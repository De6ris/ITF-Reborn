package net.oilcake.mitelros.util;

import net.minecraft.EnumQuality;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ItemTool;
import net.oilcake.mitelros.config.ITFConfig;

public class QualityHandler {
    public static int getQualityAmplifier(EnumQuality quality) {
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
        String effect;
        boolean isTool = true;
        if (item instanceof ItemTool) {
            effect = "%挖掘速度";
        } else if (item instanceof ItemArmor) {
            effect = "%护甲值";
            isTool = false;
        } else {
            return null;
        }
        int amplifier = getQualityAmplifier(quality);
        if (isTool) amplifier *= 3;
        if (amplifier == 0) return null;
        return "品质效果: " +
                (amplifier > 0 ? "提升" : "降低") +
                amplifier +
                effect;
    }
}
