package net.oilcake.mitelros.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.Minecraft;

public enum EnumSeason {
    SPRING(0),
    SUMMER(1),
    AUTUMN(2),
    WINTER(3),
    ;

    public final int code;

    EnumSeason(int code) {
        this.code = code;
    }

    private static final ImmutableList<EnumSeason> SEASONS = ImmutableList.copyOf(values());

    public static EnumSeason getForCode(int code) {
        if (code > WINTER.code) {
            Minecraft.setErrorMessage("why code too large");
            return SPRING;
        }
        return SEASONS.get(code);
    }
}
