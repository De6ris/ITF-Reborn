package net.oilcake.mitelros.api;

import net.minecraft.Entity;
import net.minecraft.Item;

public interface ITFRegistry {
    void registerItemWater(Item item, int water);

    void registerItemWaterChance(Item item, float chance);

    void registerMeatAnimal(Class<? extends Entity> clazz);
}
