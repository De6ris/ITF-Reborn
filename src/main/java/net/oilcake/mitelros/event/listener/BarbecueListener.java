package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IBarbecueListener;
import net.minecraft.BlockLog;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Items;

public class BarbecueListener implements IBarbecueListener {
    @Override
    public ItemStack getCookResult(ItemStack input) {
        Item item = input.getItem();
        if (item.isBlock() && item.getAsItemBlock().getBlock() instanceof BlockLog) {
            return new ItemStack(Item.coal, input.stackSize, 1);
        }
        if (item == Items.clayBowlWater) {
            return new ItemStack(Items.clayBowlWaterPure, input.stackSize);
        }
        return null;
    }

    @Override
    public boolean isCookResult(ItemStack itemStack) {
        if (itemStack.itemID == Item.coal.itemID && itemStack.getItemSubtype() == 1) {
            return true;
        }
        if (itemStack.itemID == Items.clayBowlWaterPure.itemID){
            return true;
        }
        return false;
    }
}
