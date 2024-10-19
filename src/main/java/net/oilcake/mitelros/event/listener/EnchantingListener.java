package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IEnchantingListener;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Materials;

import java.util.Random;

public class EnchantingListener implements IEnchantingListener {
    @Override
    public int onMaxEnchantNumModify(Random random, ItemStack item_stack, int enchantment_levels, int original) {
        return item_stack.getItem().getHardestMetalMaterial() == Materials.uru ? original + 2 : original;
    }
}
