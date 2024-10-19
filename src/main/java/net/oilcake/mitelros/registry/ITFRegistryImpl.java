package net.oilcake.mitelros.registry;

import net.minecraft.Entity;
import net.minecraft.Item;
import net.oilcake.mitelros.api.ITFRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ITFRegistryImpl implements ITFRegistry {
    public static final Map<Item, Integer> waterMap = new HashMap<>();
    public static final Map<Item, Float> waterChanceMap = new HashMap<>();
    @Override
    public void registerItemWater(Item item, int water) {
        waterMap.put(item, water);
    }

    @Override
    public void registerItemWaterChance(Item item, float chance) {
        waterChanceMap.put(item, chance);
    }

    public static final List<Class<? extends Entity>> meatAnimals = new ArrayList<>();

    @Override
    public void registerMeatAnimal(Class<? extends Entity> clazz) {
        meatAnimals.add(clazz);
    }
}
