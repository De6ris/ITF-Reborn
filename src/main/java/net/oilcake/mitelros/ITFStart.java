package net.oilcake.mitelros;

import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.oilcake.mitelros.config.ITFConfig;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class ITFStart implements ModInitializer {
    public static final String MOD_ID = "MITE-ITF-Reborn";
    public static final String MOD_Version = "17.2.5";
    public static final String NameSpace = "ITF Reborn";

    @Override
    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new ITFEvent());
        ConfigManager.getInstance().registerConfig(ITFConfig.getInstance());
    }
}
