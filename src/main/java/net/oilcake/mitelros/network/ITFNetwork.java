package net.oilcake.mitelros.network;

import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketReader;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.network.packets.*;
import net.xiaoyu233.fml.FishModLoader;

public class ITFNetwork {
    public static final ResourceLocation BossInfo = new ResourceLocation(ITFStart.NameSpaceCompact, "BossInfo");
    public static final ResourceLocation SetGlowing = new ResourceLocation(ITFStart.NameSpaceCompact, "SetGlowing");
    public static final ResourceLocation DecreaseWater = new ResourceLocation(ITFStart.NameSpaceCompact, "DecreaseWater");
    public static final ResourceLocation EnchantmentInfo = new ResourceLocation(ITFStart.NameSpaceCompact, "EnchantmentInfo");
    public static final ResourceLocation EnchantmentReserverInfo = new ResourceLocation(ITFStart.NameSpaceCompact, "EnchantmentReserverInfo");
    public static final ResourceLocation UpdateITFStatus = new ResourceLocation(ITFStart.NameSpaceCompact, "UpdateITFStatus");

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
        PacketReader.registerClientPacketReader(EnchantmentInfo, S2CEnchantmentInfo::new);
        PacketReader.registerClientPacketReader(EnchantmentReserverInfo, S2CEnchantReserverInfo::new);
        PacketReader.registerClientPacketReader(UpdateITFStatus, S2CUpdateITFStatus::new);
    }

    private static void initServer() {
        PacketReader.registerServerPacketReader(DecreaseWater, C2SDecreaseWater::new);
    }
}
