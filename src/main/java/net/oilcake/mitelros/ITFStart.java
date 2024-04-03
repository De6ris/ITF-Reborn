package net.oilcake.mitelros;

import net.fabricmc.api.ModInitializer;
import net.oilcake.mitelros.client.ITFEvent;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class ITFStart implements ModInitializer {
    public static final String Version = "H14";

    public static final String ExperimentalPath = "configs\\ExperimentalOption.cfg";
    public static final String StuckPath = "configs\\StuckChallenge.cfg";

    @Override
    public void onInitialize() {
        StuckTagConfig.loadConfigs(StuckPath);
        ExperimentalConfig.loadConfigs(ExperimentalPath);
        MITEEvents.MITE_EVENT_BUS.register(new ITFEvent());
    }
}
