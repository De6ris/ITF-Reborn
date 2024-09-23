package net.oilcake.mitelros;

import fi.dy.masa.malilib.config.ConfigManager;
import moddedmite.rustedironcore.network.PacketReader;
import net.fabricmc.api.ModInitializer;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.event.ITFEventFML;
import net.oilcake.mitelros.event.ITFEventRIC;
import net.oilcake.mitelros.network.modern.ITFNetwork;
import net.oilcake.mitelros.network.modern.S2CBossInfo;
import net.xiaoyu233.fml.FishModLoader;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class ITFStart implements ModInitializer {
    public static final String MOD_ID = "MITE-ITF-Reborn";
    public static final String MOD_Version = "17.2.11";
    public static final String NameSpace = "ITF Reborn";

    @Override
    public void onInitialize() {
        MITEEvents.MITE_EVENT_BUS.register(new ITFEventFML());
        ITFEventRIC.register();
        ConfigManager.getInstance().registerConfig(ITFConfig.getInstance());
        if (!FishModLoader.isServer()) {
            this.initClient();
        }
        this.initServer();
    }

    private void initClient() {
        PacketReader.registerClientPacketReader(ITFNetwork.BossInfo, S2CBossInfo::new);
    }

    private void initServer() {
    }
}
