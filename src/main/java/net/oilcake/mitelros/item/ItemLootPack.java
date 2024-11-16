package net.oilcake.mitelros.item;

import net.minecraft.*;

import java.util.Random;
import java.util.function.Supplier;

public class ItemLootPack extends Item {
    private final Supplier<WeightedRandomChestContent[]> lootTableSupplier;

    private final int lootRolls;

    public ItemLootPack(int id, Material material, String texture, Supplier<WeightedRandomChestContent[]> lootTableSupplier, int lootRolls) {
        super(id, material, texture);
        this.lootTableSupplier = lootTableSupplier;
        this.lootRolls = lootRolls;
        this.setMaxStackSize(4);
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        if (player.onServer()) {
            if (!player.isPlayerInCreative()) {
                player.convertOneOfHeldItem(null);
            }
            Random random = player.rand;
            sendLootToPlayer(player, random, this.lootTableSupplier.get(), this.lootRolls);
            player.makeSound("random.orb", 0.1f, 0.5f * ((random.nextFloat() - random.nextFloat()) * 0.7f + 1.8f));
        }
        return true;
    }

    private static void sendLootToPlayer(EntityPlayer player, Random random, WeightedRandomChestContent[] lootTable, int lootRolls) {
        for (int i = 0; i < lootRolls; i++) {
            WeightedRandomChestContent var5 = (WeightedRandomChestContent) WeightedRandom.getRandomItem(random, lootTable);
            int var6 = var5.min_quantity + random.nextInt(var5.max_quantity - var5.min_quantity + 1);
            ItemStack item_stack = var5.theItemId;
            if (item_stack.getMaxStackSize() >= var6) {
                ItemStack var7 = item_stack.copy();
                var7.stackSize = var6;
                var7.applyRandomItemStackDamageForChest();
                player.inventory.addItemStackToInventoryOrDropIt(var7);
                continue;
            }
            for (int var9 = 0; var9 < var6; ++var9) {
                ItemStack var8 = item_stack.copy();
                var8.stackSize = 1;
                var8.applyRandomItemStackDamageForChest();
                player.inventory.addItemStackToInventoryOrDropIt(var8);
            }
        }
    }
}
