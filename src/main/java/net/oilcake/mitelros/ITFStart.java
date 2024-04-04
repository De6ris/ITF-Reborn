package net.oilcake.mitelros;

import net.fabricmc.api.ModInitializer;
import net.minecraft.ChatMessageComponent;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.client.ITFEvent;
import net.oilcake.mitelros.util.Config;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.config.ConfigRegistry;
import net.xiaoyu233.fml.reload.event.MITEEvents;

import java.util.Optional;

public class ITFStart implements ModInitializer {
    private transient final ConfigRegistry configRegistry = new ConfigRegistry(Config.StuckTags, Config.CONFIG_FILE);

    public static final String Version = "H15";

    @Override
    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new ITFEvent());
    }

    @Override
    public Optional<ConfigRegistry> createConfig() {
        return Optional.of(configRegistry);
    }
}
