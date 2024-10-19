package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.ILootTableRegisterListener;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.ITFLootTables;

import java.util.Arrays;
import java.util.List;

public class LootTableRegistry implements ILootTableRegisterListener {
    @Override
    public void onDesertPyramidRegister(List<WeightedRandomChestContent> original) {
        original.add(new WeightedRandomChestContent(Items.totemOfPreserve.itemID, 0, 1, 1, 1));
    }

    @Override
    public void onJunglePyramidRegister(List<WeightedRandomChestContent> original) {
        original.add(new WeightedRandomChestContent(Items.totemOfPreserve.itemID, 0, 1, 1, 1));
    }

    @Override
    public void onFortressRegister(List<WeightedRandomChestContent> original) {
        original.addAll(Arrays.asList(ITFLootTables.fortressExtra.get()));
    }

    @Override
    public void onMineshaftRegister(List<WeightedRandomChestContent> original) {
        original.add(new WeightedRandomChestContent(Items.ignitionCopper.itemID, 0, 1, 1, 2));
        original.add(new WeightedRandomChestContent(Items.ignitionSilver.itemID, 0, 1, 1, 2));
        original.add(new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 2, 2));
    }

    @Override
    public void onStrongholdCorridorRegister(List<WeightedRandomChestContent> original) {
        original.add(new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 1, 5));
        original.add(new WeightedRandomChestContent(Items.totemOfPreserve.itemID, 0, 1, 1, 5));
    }

    @Override
    public void onStrongholdCrossingRegister(List<WeightedRandomChestContent> original) {
        ILootTableRegisterListener.super.onStrongholdCrossingRegister(original);
    }

    @Override
    public void onStrongholdLibraryRegister(List<WeightedRandomChestContent> original) {
        ILootTableRegisterListener.super.onStrongholdLibraryRegister(original);
    }

    @Override
    public void onSwampHutRegister(List<WeightedRandomChestContent> original) {
        original.add(new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 2, 3));
    }
}
