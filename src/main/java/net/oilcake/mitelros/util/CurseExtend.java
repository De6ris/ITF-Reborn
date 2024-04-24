package net.oilcake.mitelros.util;

import net.minecraft.Curse;

public class CurseExtend extends Curse {
    public CurseExtend(int id, String key) {
        super(id, key);
    }

    public static final Curse fear_of_darkness = new Curse(Constant.getNextCurseID(), "fearOfDarkness");

    public static final Curse fear_of_light = new Curse(Constant.getNextCurseID(), "fearOfLight");
}
