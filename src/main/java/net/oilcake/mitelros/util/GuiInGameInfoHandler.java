package net.oilcake.mitelros.util;

import net.minecraft.Minecraft;
import net.minecraft.WeatherEvent;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.status.TemperatureManager;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class GuiInGameInfoHandler {
    public static String GAInfo(Minecraft mc) {
        String direction = switch (mc.thePlayer.getDirectionFromYaw().toString()) {
            case "EAST" -> "东";
            case "WEST" -> "西";
            case "NORTH" -> "北";
            case "SOUTH" -> "南";
            default -> "null";
        };
        String Biome = StringUtils.substringBefore(mc.thePlayer.getBiome().toString(), "@").substring(19) + " ";
        return direction + " " + Biome + " " + weather(mc);
    }

    public static String getDifficultyText() {
        int difficulty = Constant.calculateCurrentDifficulty();
        if (difficulty < 0) {
            return "§a休闲难度§r";
        }
        if (difficulty == 0) {
            return "";
        }
        if (ITFConfig.FinalChallenge.get() && difficulty == ITFConfig.ultimateDifficulty) {
            return "§4终极难度§r";
        }
        return difficulty >= 16 ? ("§c挑战难度：§4" + difficulty + " §f") : (difficulty >= 12 ? (" 挑战难度：§c" + difficulty + " §f") : (difficulty >= 8 ? (" 挑战难度：§6" + difficulty + " §f") : (difficulty >= 4 ? (" 挑战难度：§a" + difficulty + " §f") : (" 挑战难度：" + difficulty + " §f"))));
    }

    public static String getTemperatureText(TemperatureManager temperatureManager) {
        float temperature = temperatureManager.bodyTemperature;
        int unit = temperatureManager.getUnit();
        return String.format("体温: %.3f°C (你感到: %s%d)", temperature, unit > 0 ? "+" : "", unit);
    }

    public static String weather(Minecraft mc) {
        String rainSnow;
        if (!mc.thePlayer.getBiome().isFreezing() || ((ITFWorld) mc.thePlayer.worldObj).getWorldSeason() != 3) {
            rainSnow = "雨";
        } else {
            rainSnow = "雪";
        }
        String weather;
        WeatherEvent event = mc.theWorld.getCurrentWeatherEvent();
        Random R = new Random(mc.theWorld.getDayOfWorld());
        if (event != null) {
            if (mc.theWorld.getDayOfWorld() % 32 == 0) {
                weather = "雷暴";
            } else {
                int ran2 = R.nextInt(3);
                if (ran2 == 0) {
                    weather = "小" + rainSnow;
                } else if (ran2 == 1) {
                    weather = "中" + rainSnow;
                } else {
                    weather = "大" + rainSnow;
                }
            }
        } else {
            event = mc.theWorld.getNextWeatherEvent(false);
            if (event != null) {
                if (event.start - mc.theWorld.getAdjustedTimeOfDay() < 2000L) {
                    weather = "有雨";
                } else {
                    weather = "阴";
                }
            } else {
                int ran2 = R.nextInt(3);
                if (ran2 == 0) {
                    weather = "晴";
                } else if (ran2 == 1) {
                    weather = "多云";
                } else {
                    weather = "晴转多云";
                }
            }
        }
        return weather;
    }
}
