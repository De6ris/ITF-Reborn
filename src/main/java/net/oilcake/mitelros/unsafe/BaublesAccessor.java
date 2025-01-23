package net.oilcake.mitelros.unsafe;

import baubles.api.BaublesApi;
import net.minecraft.EntityPlayer;
import net.minecraft.IInventory;
import net.minecraft.ItemStack;

public class BaublesAccessor {
    public static ItemStack getStackInAmulet(EntityPlayer player) {
        IInventory baubles = BaublesApi.getBaubles(player);
        return baubles.getStackInSlot(0);
    }

    public static void clearAmulet(EntityPlayer player) {
        IInventory baubles = BaublesApi.getBaubles(player);
        baubles.setInventorySlotContents(0, null);
    }
}
