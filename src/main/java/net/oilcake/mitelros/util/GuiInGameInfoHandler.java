package net.oilcake.mitelros.util;

import net.minecraft.BiomeGenBase;
import net.minecraft.Minecraft;
import net.minecraft.WeatherEvent;
import net.minecraft.World;
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
        BiomeGenBase biome = mc.thePlayer.getBiome();
        String biomeName = StringUtils.substringBefore(biome.toString(), "@").substring(19) + " ";
        return direction + " " + biomeName + " " + weather(mc.theWorld, biome.isFreezing());
    }

    public static String getPlainDifficultyText() {
        int difficulty = Constant.calculateCurrentDifficulty();
        if (difficulty < 0) {
            return "休闲难度";
        }
        if (difficulty == 0) {
            return "";
        }
        if (ITFConfig.FinalChallenge.get() && difficulty == ITFConfig.ultimateDifficulty) {
            return "终极难度";
        }
        return ("挑战难度: " + difficulty);
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
        return difficulty >= 16 ? ("§c挑战难度: §4" + difficulty + "§f") : (difficulty >= 12 ? ("挑战难度: §c" + difficulty + "§f") : (difficulty >= 8 ? ("挑战难度: §6" + difficulty + "§f") : (difficulty >= 4 ? ("挑战难度: §a" + difficulty + "§f") : ("挑战难度: " + difficulty + "§f"))));
    }

    public static String getTemperatureText(TemperatureManager temperatureManager) {
        float temperature = temperatureManager.bodyTemperature;
        double unit = temperatureManager.getUnit();
        return String.format("体温: %.3f°C (你感到: %s%.2f)", temperature, unit > 0 ? "+" : "", unit);
    }

    public static String season(int day) {
        day %= 128;
        if (day < 10) return "上春";
        if (day < 21) return "花月";
        if (day < 32) return "晚春";
        if (day < 42) return "孟夏";
        if (day < 53) return "仲夏";
        if (day < 64) return "荷月";
        if (day < 74) return "初秋";
        if (day < 85) return "正秋";
        if (day < 96) return "深秋";
        if (day < 106) return "开冬";
        if (day < 117) return "冬月";
        return "严冬";
    }

    public static String weather(World world, boolean freezing) {
        String rainSnow;
        if (!freezing || ((ITFWorld) world).getWorldSeason() != 3) {
            rainSnow = "雨";
        } else {
            rainSnow = "雪";
        }
        String weather;
        WeatherEvent event = world.getCurrentWeatherEvent();
        Random R = new Random(world.getDayOfWorld());
        if (event != null) {
            if (world.getDayOfWorld() % 32 == 0) {
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
            event = world.getNextWeatherEvent(false);
            if (event != null) {
                if (event.start - world.getAdjustedTimeOfDay() < 2000L) {
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
