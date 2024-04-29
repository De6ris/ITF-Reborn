package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.status.TemperatureManager;

import java.util.Random;

import static net.oilcake.mitelros.ITFStart.MOD_ID_Simple;

public class GuiInGameInfoHandler {
    public static void drawInfo(Gui gui, Minecraft mc) {
        EntityPlayer player = mc.thePlayer.getAsPlayer();
        if (GuiIngame.server_load >= 0) {
            ScaledResolution sr = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            String text = GuiIngame.server_load + "%";
            gui.drawString(mc.fontRenderer, text, sr.getScaledWidth() - mc.fontRenderer.getStringWidth(text) - 2, 2, 14737632);
        }
        StringBuilder firstRow = new StringBuilder();

        if (ITFConfig.MODText.getBooleanValue()) {
            firstRow.append(MOD_ID_Simple).append(" ");
        }

        if (ITFConfig.DifficultyInfo.getBooleanValue()) {
            String difficultyText = GuiInGameInfoHandler.getDifficultyText();
            if (!difficultyText.isEmpty()) {
                firstRow.append(difficultyText).append(" ");
            }
        }
        if (ITFConfig.SeasonText.getBooleanValue()) {
            firstRow.append(GuiInGameInfoHandler.season(mc.theWorld.getDayOfWorld())).append(" ");
        }
        if (ITFConfig.WeatherText.getBooleanValue()) {
            firstRow.append(GuiInGameInfoHandler.weather(mc.theWorld, mc.thePlayer.getBiome().isFreezing())).append(" ");
        }

        StringBuilder secondRow = new StringBuilder();
        if (ITFConfig.CoordinateText.getBooleanValue() && player.getHeldItemStack() != null && player.getHeldItemStack().getItem() == Item.compass) {
            String pos = "平面坐标: (" + MathHelper.floor_double(mc.thePlayer.posX) + ", " + MathHelper.floor_double(mc.thePlayer.posZ) + ")";
            secondRow.append(pos).append(" ");
        }
        if (ITFConfig.TimeText.getBooleanValue() && player.getHeldItemStack() != null && player.getHeldItemStack().getItem() == Item.pocketSundial) {
            String time = "时间: (" + mc.thePlayer.getWorld().getHourOfDay() + ":" + (mc.thePlayer.getWorld().getTotalWorldTime() % 1000L * 60L / 1000L) + ")";
            secondRow.append(time).append(" ");
        }
        if (ITFConfig.TemperatureText.getBooleanValue()) {
            secondRow.append(GuiInGameInfoHandler.getTemperatureText(((ITFPlayer) mc.thePlayer).getTemperatureManager())).append(" ");
        }
        int baseYLevel = ITFConfig.InfoYLevel.getIntegerValue();
        gui.drawString(mc.fontRenderer, firstRow.toString(), 2, 2 + baseYLevel * 10, 14737632);
        gui.drawString(mc.fontRenderer, secondRow.toString(), 2, 12 + baseYLevel * 10, 14737632);

    }

    public static String getPlainDifficultyText() {
        int difficulty = Constant.calculateCurrentDifficulty();
        if (difficulty < 0) {
            return "休闲难度";
        }
        if (difficulty == 0) {
            return "";
        }
        if (ITFConfig.FinalChallenge.get() && difficulty == Constant.ultimateDifficulty) {
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
        if (ITFConfig.FinalChallenge.get() && difficulty == Constant.ultimateDifficulty) {
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
