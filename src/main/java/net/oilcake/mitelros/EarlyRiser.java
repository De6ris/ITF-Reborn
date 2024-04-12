package net.oilcake.mitelros;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.EnumQuality;
import net.xiaoyu233.fml.util.EnumExtends;

public class EarlyRiser implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("NICKEL", () -> new Object[]{8.0F, 30, EnumQuality.masterwork, "nickel"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("TUNGSTEN", () -> new Object[]{128.0F, 50, EnumQuality.legendary, "tungsten"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("URU", () -> new Object[]{192.0F, 100, EnumQuality.legendary, "uru"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("WOLF_FUR", () -> new Object[]{2.0F, 20, EnumQuality.excellent, "wolf_fur"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("CUSTOM_A", () -> new Object[]{0.0625F, 0, EnumQuality.average, "custom_a"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("CUSTOM_B", () -> new Object[]{0.0625F, 0, EnumQuality.average, "custom_b"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("VIBRANIUM", () -> new Object[]{4.0F, 0, EnumQuality.poor, "vibranium"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("MAGICAL", () -> new Object[]{0.125F, 100, EnumQuality.wretched, "magical"});
        EnumExtends.EQUIPMENT_MATERIAL.addEnum("ANCIENT_METAL_SACRED", () -> new Object[]{16.0F, 60, EnumQuality.masterwork, "ancient_metal_sacred"});
    }

}
