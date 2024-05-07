package net.oilcake.mitelros.util;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemStack;

public class FireCookHandler {
    public static boolean isCookResult(ItemStack input) {
        if (input.itemID == Item.coal.itemID && input.getItemSubtype() == 1) return true;
//        return input.itemID == Items.bowlHotWater.itemID || input.itemID == Items.clayBowlHotWater.itemID;
        return false;
    }

    public static ItemStack getCookResult(ItemStack input) {
        if (input.itemID == Block.wood.blockID) {
            return new ItemStack(Item.coal, input.stackSize, 1);
        }
//        else if (input.itemID == Item.bowlWater.itemID) {
//            return new ItemStack(Items.bowlHotWater, input.stackSize);
//        } else if (input.itemID == Items.clayBowlWater.itemID) {
//            return new ItemStack(Items.clayBowlHotWater, input.stackSize);
//        }
        else {
            return null;
        }
    }
}
