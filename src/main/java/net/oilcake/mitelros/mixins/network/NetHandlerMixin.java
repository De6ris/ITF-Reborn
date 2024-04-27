package net.oilcake.mitelros.mixins.network;

import net.minecraft.NetHandler;
import net.oilcake.mitelros.api.ITFNetHandler;
import net.oilcake.mitelros.network.C2SDecreaseWater;
import net.oilcake.mitelros.network.S2CEnchantReserverInfo;
import net.oilcake.mitelros.network.S2CEnchantmentInfo;
import net.oilcake.mitelros.network.S2CUpdateNutrition;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NetHandler.class)
public class NetHandlerMixin implements ITFNetHandler {

    public void processEnchantReserverInfo(S2CEnchantReserverInfo packet) {
    }

    public void handleDecreaseWater(C2SDecreaseWater packet) {
    }

    public void handleUpdateNutrition(S2CUpdateNutrition packet) {
    }

    public void handleEnchantmentInfo(S2CEnchantmentInfo packet) {
    }
}
