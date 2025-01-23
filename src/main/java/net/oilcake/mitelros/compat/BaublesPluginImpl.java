package net.oilcake.mitelros.compat;

import baubles.api.BaubleType;
import baubles.api.IBaublePlugin;
import net.minecraft.EntityLivingBase;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.totem.ItemTotem;

public class BaublesPluginImpl implements IBaublePlugin {
    @Override
    public boolean canPutBaubleSlot(ItemStack itemStack, BaubleType baubleType) {
        if (baubleType == BaubleType.AMULET) {
            if (itemStack.getItem() instanceof ItemTotem) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }
}
