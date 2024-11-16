package net.oilcake.mitelros.event;

import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.handler.GravelDropHandler;
import moddedmite.rustedironcore.api.event.listener.IArrowRegisterListener;
import net.minecraft.Material;
import net.oilcake.mitelros.event.listener.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;

import java.util.function.Consumer;

public class ITFEventRIC extends Handlers {
    public static void register() {
        FurnaceUpdate.register(new FurnaceListener());

        Enchanting.register(new EnchantingListener());

        BiomeGenerate.register(new BiomeGenerateListener());

        GravelDrop.registerGravelLootEntry(new GravelDropHandler.GravelLootEntry(GravelDropHandler.ObsidianEntry.weight(), info -> Items.nickelNugget.itemID));
        GravelDrop.registerGravelLootEntry(new GravelDropHandler.GravelLootEntry(GravelDropHandler.MithrilEntry.weight() * 3 / 4, info -> Items.tungstenNugget.itemID));

        BeaconUpdate.register(new BeaconListener());

        SpawnCondition.register(new SpawnConditionsRegistry());

        EntityMobMixin.register(new EntityMobListener());

        PlayerEvent.register(new PlayerListener());

        ArrowRegister.register(new IArrowRegisterListener() {
            @Override
            public void onRegister(Consumer<Material> registry) {
                registry.accept(Materials.nickel);
                registry.accept(Materials.tungsten);
            }
        });

        PropertiesRegistry.register(new PropertyRegistry());

        Smelting.register(new SmeltingRegistry());

        Crafting.register(new CraftingRegistry());

        Trading.register(new TradingListener());

        Combat.register(new CombatListener());

        LootTable.register(new LootTableRegistry());

        EntityTracker.register(new EntityTrackerRegistry());

        PlayerAttribute.register(new PlayerAttributeListener());

        Achievement.register(new AchievementListener());

        Barbecue.register(new BarbecueListener());
    }
}
