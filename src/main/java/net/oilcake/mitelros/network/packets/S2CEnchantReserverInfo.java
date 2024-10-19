package net.oilcake.mitelros.network.packets;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiScreen;
import net.minecraft.Minecraft;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.network.ITFNetwork;

public class S2CEnchantReserverInfo implements Packet {
    private final int EXP;

    public S2CEnchantReserverInfo(PacketByteBuf packetByteBuf) {
        this(packetByteBuf.readInt());
    }

    public S2CEnchantReserverInfo(int exp) {
        this.EXP = exp;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(this.EXP);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        GuiScreen openingGUI = Minecraft.getMinecraft().currentScreen;
        if (openingGUI instanceof GuiEnchantReserver)
            ((GuiEnchantReserver) openingGUI).setEnchantInfo(this.EXP);
    }

    @Override
    public ResourceLocation getChannel() {
        return ITFNetwork.EnchantmentReserverInfo;
    }
}
