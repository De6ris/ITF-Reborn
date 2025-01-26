package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.property.ItemProperties;
import net.minecraft.Item;
import net.oilcake.mitelros.api.ITFPlugin;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.registry.ITFRegistryImpl;
import net.oilcake.mitelros.registry.VanillaPlugin;
import net.xiaoyu233.fml.FishModLoader;

public class PropertyRegistry implements Runnable {
    @Override
    public void run() {
        ItemProperties.HeatLevelRequired.register(Item.getItem(Blocks.oreNickel), 2);
        ItemProperties.HeatLevelRequired.register(Item.getItem(Blocks.oreTungsten), 3);
        ItemProperties.HeatLevelRequired.register(Item.getItem(Blocks.oreUru), 4);

        ItemProperties.HeatLevelRequired.register(Items.pieceCopper, 2);
        ItemProperties.HeatLevelRequired.register(Items.pieceCopper, 2);
        ItemProperties.HeatLevelRequired.register(Items.pieceGold, 2);
        ItemProperties.HeatLevelRequired.register(Items.pieceGoldNether, 2);
        ItemProperties.HeatLevelRequired.register(Items.pieceIron, 2);
        ItemProperties.HeatLevelRequired.register(Items.pieceNickel, 2);
        ItemProperties.HeatLevelRequired.register(Items.pieceMithril, 3);
        ItemProperties.HeatLevelRequired.register(Items.pieceTungsten, 3);
        ItemProperties.HeatLevelRequired.register(Items.pieceAdamantium, 4);
        ItemProperties.HeatLevelRequired.register(Items.pieceUru, 4);

        ItemProperties.HeatLevelRequired.register(Items.ancientMetalArmorPiece, 3);

        ItemProperties.RockExperience.register(Items.shardAzurite, 5);


        ITFRegistryImpl itfRegistry = new ITFRegistryImpl();
        new VanillaPlugin().register(itfRegistry);
        FishModLoader.getEntrypointContainers("itf-reborn", ITFPlugin.class)
                .forEach(x -> x.getEntrypoint().register(itfRegistry));
    }
}
