package net.oilcake.mitelros;

import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.event.ITFEventFML;
import net.oilcake.mitelros.event.ITFEventRIC;
import net.oilcake.mitelros.network.ITFNetwork;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class ITFStart implements ModInitializer {
    public static final String MOD_ID = "MITE-ITF-Reborn";
    public static final String MOD_Version = "17.2.11";
    public static final String NameSpace = "ITF Reborn";
    public static final String NameSpaceCompact = "ITF";
    public static final String NameSpaceCompactWithColon = "ITF:";

    @Override
    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new ITFEventFML());
        ITFEventRIC.register();
        ConfigManager.getInstance().registerConfig(ITFConfig.getInstance());
        ITFNetwork.init();
    }
}
