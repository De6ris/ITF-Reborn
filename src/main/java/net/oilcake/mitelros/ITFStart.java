package net.oilcake.mitelros;

import fi.dy.masa.malilib.gui.screen.ModsScreen;
import net.fabricmc.api.ModInitializer;
import net.oilcake.mitelros.client.ITFEvent;
import net.oilcake.mitelros.util.ITFConfig;
import net.xiaoyu233.fml.config.ConfigRegistry;
import net.xiaoyu233.fml.reload.event.MITEEvents;

import java.util.Optional;

public class ITFStart implements ModInitializer {
    public static final String MOD_ID = "ITF-Reborn";
    public static final String Version = "H15";

    @Override
    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new ITFEvent());
        ITFConfig.init();
        ITFConfig.getInstance().load();
        ModsScreen.getInstance().addConfig(ITFConfig.getInstance());
    }
}
