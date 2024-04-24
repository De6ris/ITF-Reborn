package net.oilcake.mitelros.config;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import net.minecraft.EnumChatFormatting;

public class ConfigBooleanChallenge extends ConfigBoolean {

    private final int level;

    public ConfigBooleanChallenge(String name, String comment, int level) {
        super(name, comment);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String getText() {
        String level = this.getColor() + String.format("(LVL%d)", this.level);
        String info = EnumChatFormatting.WHITE + this.getName() + ": " + (this.getBooleanValue() ? "开" : "关");
        return level + info;
    }

    public EnumChatFormatting getColor() {
        return switch (this.level) {
            case -3 -> EnumChatFormatting.GREEN;
            case -2 -> EnumChatFormatting.AQUA;
            case -1 -> EnumChatFormatting.DARK_AQUA;
            case 1 -> EnumChatFormatting.BLUE;
            case 2 -> EnumChatFormatting.YELLOW;
            case 3 -> EnumChatFormatting.GOLD;
            case 4 -> EnumChatFormatting.RED;
            case 5 -> EnumChatFormatting.DARK_RED;
            default -> EnumChatFormatting.WHITE;
        };
    }
}
