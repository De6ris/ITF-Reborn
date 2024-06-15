package net.oilcake.mitelros.util;

import net.minecraft.Curse;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class CurseExtend extends Curse {
    private static int getNextCurseID() {
        return IdUtil.getNextCurseId();
    }
    public CurseExtend(int id, String key) {
        super(id, key);
    }

    public static final Curse fear_of_darkness = new Curse(getNextCurseID(), "fearOfDarkness");

    public static final Curse fear_of_light = new Curse(getNextCurseID(), "fearOfLight");
}
