package net.oilcake.mitelros.network.modern;

import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.Packet;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.ITFStart;

public class ITFNetwork {
    public static final ResourceLocation BossInfo = new ResourceLocation(ITFStart.MOD_ID, "BossInfo");

    public static void sendToClient(ServerPlayer player, Packet packet) {
        Network.sendToClient(player, packet);
    }

    public static void sendToServer(Packet packet) {
        Network.sendToServer(packet);
    }
}
