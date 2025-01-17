package net.oilcake.mitelros.registry;

import net.minecraft.Block;
import net.minecraft.Entity;
import net.minecraft.Item;
import net.minecraft.ItemStack;
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

    public static final Map<Block, Integer> PIECE_MAP = new HashMap<>();

    @Override
    public void registerOrePiece(Block blockOre, int dropItemID) {
        PIECE_MAP.put(blockOre, dropItemID);
    }

    public static final Map<Block, Integer> MELTING_MAP = new HashMap<>();

    @Override
    public void registerOreMelting(Block blockOre, int dropItemID) {
        MELTING_MAP.put(blockOre, dropItemID);
    }

    public static final Map<Block, ItemStack> ABSORBING_MAP = new HashMap<>();

    @Override
    public void registerOreAbsorbing(Block blockOre, ItemStack output) {
        ABSORBING_MAP.put(blockOre, output);
    }
}
