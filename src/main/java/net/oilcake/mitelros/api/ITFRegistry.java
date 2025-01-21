package net.oilcake.mitelros.api;

import net.minecraft.Block;
import net.minecraft.Entity;
import net.minecraft.Item;
import net.minecraft.ItemStack;

public interface ITFRegistry {
    void registerItemWater(Item item, int water);

    void registerItemWaterChance(Item item, float chance);

    /**
     * If you enable the config {@code Apocalypse}, the animals registered here won't spawn naturally.
     */
    void registerMeatAnimal(Class<? extends Entity> clazz);


    /**
     * If registered, the block will drop 4 to 7 pieces.
     */
    void registerOrePiece(Block blockOre, int dropItemID);

    void registerOreMelting(Block blockOre, int dropItemID);

    /**
     * @param output used to look up the exp equivalent.
     */
    void registerOreAbsorbing(Block blockOre, ItemStack output);
}
