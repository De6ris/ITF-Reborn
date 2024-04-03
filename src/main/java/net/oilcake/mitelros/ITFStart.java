package net.oilcake.mitelros;

import net.fabricmc.api.ModInitializer;
import net.oilcake.mitelros.client.ITFEvent;
import net.oilcake.mitelros.util.Config;
import net.xiaoyu233.fml.config.ConfigRegistry;
import net.xiaoyu233.fml.reload.event.MITEEvents;

import java.util.Optional;

public class ITFStart implements ModInitializer {
    private transient final ConfigRegistry configRegistry = new ConfigRegistry(Config.StuckTags, Config.CONFIG_FILE);

    public static final String Version = "H15a";

//    public static final String ExperimentalPath = "configs\\ExperimentalOption.cfg";
//    public static final String StuckPath = "configs\\StuckChallenge.cfg";

    @Override
    public void onInitialize() {
//        StuckTagConfig.loadConfigs(StuckPath);
//        ExperimentalConfig.loadConfigs(ExperimentalPath);
        MITEEvents.MITE_EVENT_BUS.register(new ITFEvent());
    }

    @Override
    public Optional<ConfigRegistry> createConfig() {
        return Optional.of(configRegistry);
    }
}
