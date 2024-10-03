package net.oilcake.mitelros.network.modern;

import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketReader;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.ITFStart;
import net.xiaoyu233.fml.FishModLoader;

public class ITFNetwork {
    public static final ResourceLocation BossInfo = new ResourceLocation(ITFStart.MOD_ID, "BossInfo");
    public static final ResourceLocation SetGlowing = new ResourceLocation(ITFStart.MOD_ID, "SetGlowing");

    public static void sendToClient(ServerPlayer player, Packet packet) {
        Network.sendToClient(player, packet);
    }

    public static void sendToServer(Packet packet) {
        Network.sendToServer(packet);
    }

    public static void init() {
        if (!FishModLoader.isServer()) {
            initClient();
        }
        initServer();
    }

    private static void initClient() {
        PacketReader.registerClientPacketReader(BossInfo, S2CBossInfo::new);
        PacketReader.registerClientPacketReader(SetGlowing, S2CSetGlowing::new);
    }

    private static void initServer() {
    }
}
