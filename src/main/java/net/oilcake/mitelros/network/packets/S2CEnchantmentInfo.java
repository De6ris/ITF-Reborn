package net.oilcake.mitelros.network.packets;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFGuiEnchantment;
import net.oilcake.mitelros.network.ITFNetwork;

public class S2CEnchantmentInfo implements Packet {
    private int[] info = new int[12];

    public S2CEnchantmentInfo(PacketByteBuf packetByteBuf) {
        for (int i = 0; i < 12; i++) {
            this.info[i] = packetByteBuf.readByte();
        }
    }

    public S2CEnchantmentInfo(int[] info) {
        this.info = info;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        for (int i = 0; i < 12; i++) {
            packetByteBuf.writeByte(this.info[i]);
        }
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        GuiScreen openingGUI = Minecraft.getMinecraft().currentScreen;
        if (openingGUI instanceof GuiEnchantment guiEnchantment)
            ((ITFGuiEnchantment) guiEnchantment).itf$SetEnchantmentInfo(this.info);
    }

    @Override
    public ResourceLocation getChannel() {
        return ITFNetwork.EnchantmentInfo;
    }
}
