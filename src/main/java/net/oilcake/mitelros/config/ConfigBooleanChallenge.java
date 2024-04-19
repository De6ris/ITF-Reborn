package net.oilcake.mitelros.config;

import fi.dy.masa.malilib.config.options.ConfigBoolean;

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
        return String.format("(LVL%d)%s: %s", this.level, this.getName(), this.getBooleanValue() ? "开" : "关");
    }
}
