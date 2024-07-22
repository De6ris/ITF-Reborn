package net.oilcake.mitelros.util;

import net.minecraft.Block;
import net.minecraft.BlockLog;
import net.minecraft.Item;
import net.minecraft.ItemStack;

import java.util.Optional;

public class FireCookHandler {
    private static final FireCookHandler Instance = new FireCookHandler();

    public static FireCookHandler getInstance() {
        return Instance;
    }

    public boolean isCookResult(ItemStack input) {
        return input.itemID == Item.coal.itemID && input.getItemSubtype() == 1;
//        return input.itemID == Items.bowlHotWater.itemID || input.itemID == Items.clayBowlHotWater.itemID;
    }

    public Optional<ItemStack> getCookResult(ItemStack input) {
        if (isBlockLog(input)) {
            return Optional.of(new ItemStack(Item.coal, input.stackSize, 1));
        }
//        else if (input.itemID == Item.bowlWater.itemID) {
//            return new ItemStack(Items.bowlHotWater, input.stackSize);
//        } else if (input.itemID == Items.clayBowlWater.itemID) {
//            return new ItemStack(Items.clayBowlHotWater, input.stackSize);
//        }
        return Optional.empty();
    }

    private static boolean isBlockLog(ItemStack input) {
        return input.itemID < 4096 && Block.blocksList[input.itemID] instanceof BlockLog;
    }
}
