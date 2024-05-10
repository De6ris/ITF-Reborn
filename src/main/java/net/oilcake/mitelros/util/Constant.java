package net.oilcake.mitelros.util;

import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import net.oilcake.mitelros.config.ConfigBooleanChallenge;
import net.oilcake.mitelros.config.ITFConfig;

public class Constant {
    public static int nextPotionID = 24;
    public static int nextBiomeID = 27;
    public static int nextCurseID = 17;
    public static int ultimateDifficulty;
    public static int nextItemID;
    public static int nextBlockID;

    public static int getNextBiomeID() {
        return nextBiomeID++;
    }

    public static int getNextCurseID() {
        return nextCurseID++;
    }

    public static int calculateCurrentDifficulty() {
        int difficulty = 0;
        for (ConfigBase configBase : ITFConfig.challenge) {
            if (configBase instanceof ConfigBooleanChallenge challenge && challenge.getBooleanValue()) {
                difficulty += challenge.getLevel();
            }
            if (configBase instanceof ConfigInteger configInteger) {
                difficulty += configInteger.getIntegerValue();
            }
        }
        return difficulty;
    }
}
