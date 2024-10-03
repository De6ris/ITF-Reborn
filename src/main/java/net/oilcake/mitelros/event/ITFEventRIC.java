package net.oilcake.mitelros.event;

import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.handler.GravelDropHandler;
import moddedmite.rustedironcore.api.event.listener.IEnchantingListener;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.event.listener.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;

import java.util.Random;

public class ITFEventRIC extends Handlers {
    public static void register() {
        FurnaceUpdate.register(new FurnaceListener());

        Enchanting.register(new IEnchantingListener() {
            @Override
            public int onMaxEnchantNumModify(Random random, ItemStack item_stack, int enchantment_levels, int original) {
                return item_stack.getItem().getHardestMetalMaterial() == Materials.uru ? original + 2 : original;
            }
        });

        BiomeGenerate.register(new BiomeGenerateListener());

        GravelDrop.registerGravelLootEntry(new GravelDropHandler.GravelLootEntry(GravelDropHandler.ObsidianEntry.weight(), info -> Items.nickelNugget.itemID));
        GravelDrop.registerGravelLootEntry(new GravelDropHandler.GravelLootEntry(GravelDropHandler.MithrilEntry.weight() * 3 / 4, info -> Items.tungstenNugget.itemID));

        BeaconUpdate.register(new BeaconListener());

        SpawnCondition.register(new ITFSpawnConditions());

        EntityMobMixin.register(new EntityMobListener());

        PlayerEvent.register(new PlayerListener());

        ArrowRegister.register(floatProperty -> {
            floatProperty.register(Materials.nickel, 0.7F);
            floatProperty.register(Materials.tungsten, 0.9F);
        });

        PropertiesRegistry.register(new PropertyRegistry());

        Smelting.register(new SmeltingRegistry());

    }
}
