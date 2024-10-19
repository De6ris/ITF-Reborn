package net.oilcake.mitelros.registry;

import fi.dy.masa.malilib.ManyLib;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlugin;
import net.oilcake.mitelros.api.ITFRegistry;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.xiaoyu233.fml.FishModLoader;

public class VanillaPlugin implements ITFPlugin {
    @SuppressWarnings("unchecked")
    @Override
    public void register(ITFRegistry registry) {
        registry.registerItemWater(Item.carrot, ITFConfig.TagDryDilemma.getBooleanValue() ? 1 : 2);
        registry.registerItemWater(Items.ice_sucker, ITFConfig.TagDryDilemma.getBooleanValue() ? 1 : 2);
        registry.registerItemWater(Items.melon_ice, ITFConfig.TagDryDilemma.getBooleanValue() ? 1 : 2);
        registry.registerItemWater(Items.chocolate_smoothie, ITFConfig.TagDryDilemma.getBooleanValue() ? 1 : 2);
        registry.registerItemWater(Items.peeledSugarcane, 1);
        registry.registerItemWater(Items.glowberries, 1);
        registry.registerItemWater(Items.mashedCactus, 1);
        registry.registerItemWater(Item.bread, -1);
        registry.registerItemWater(Item.cheese, -1);

        registry.registerItemWaterChance(Items.agave, ITFConfig.TagDryDilemma.getBooleanValue() ? 0.2F : 0.4F);
        registry.registerItemWaterChance(Items.glowberries, ITFConfig.TagDryDilemma.getBooleanValue() ? 0.5F : 1.0F);

        registry.registerMeatAnimal(EntityCow.class);
        registry.registerMeatAnimal(EntityChicken.class);
        registry.registerMeatAnimal(EntitySheep.class);
        registry.registerMeatAnimal(EntityPig.class);
        registry.registerMeatAnimal(EntityHorse.class);

        if (FishModLoader.hasMod("bettermite")) {
            try {
                registry.registerMeatAnimal((Class<? extends Entity>) Class.forName("com.github.FlyBird.BetterMite.entity.EntityRabbit"));
            } catch (ClassNotFoundException | ClassCastException e) {
                ManyLib.logger.warn("itf reborn compat: failed to register rabbit for meat animals");
                e.printStackTrace();
            }
        }
    }
}
