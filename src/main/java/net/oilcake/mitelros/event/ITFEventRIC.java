package net.oilcake.mitelros.event;

import moddedmite.rustedironcore.api.event.EnchantingHandler;
import moddedmite.rustedironcore.api.event.FurnaceUpdateHandler;
import moddedmite.rustedironcore.api.interfaces.IEnchantingListener;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Materials;

import java.util.Random;

public class ITFEventRIC {
    public static void register() {
        FurnaceUpdateHandler.getInstance().registerFurnaceUpdateListener(new FurnaceListener());
        EnchantingHandler.getInstance().registerEnchantingListener(new IEnchantingListener() {
            @Override
            public int onMaxEnchantNumModify(Random random, ItemStack item_stack, int enchantment_levels, int original) {
                return item_stack.getItem().getHardestMetalMaterial() == Materials.uru ? original + 2 : original;
            }
        });
    }
}
