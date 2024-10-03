package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.property.ItemProperties;
import moddedmite.rustedironcore.property.MaterialProperties;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;

public class PropertyRegistry implements Runnable {
    @Override
    public void run() {
        MaterialProperties.RepairItem.register(Materials.nickel, Items.nickelNugget);
        MaterialProperties.RepairItem.register(Materials.tungsten, Items.tungstenNugget);
        MaterialProperties.RepairItem.register(Materials.ancient_metal_sacred, Items.ancientMetalArmorPiece);
        MaterialProperties.RepairItem.register(Materials.uru, Items.uruNugget);

        MaterialProperties.HarvestEfficiency.register(Materials.uru, 4.0F);
        MaterialProperties.HarvestEfficiency.register(Materials.nickel, 2.0F);
        MaterialProperties.HarvestEfficiency.register(Materials.tungsten, 2.75F);

        MaterialProperties.PeerCoin.register(Materials.nickel, Items.nickelCoin);
        MaterialProperties.PeerCoinXP.register(Materials.nickel, 50);
        MaterialProperties.PeerCoin.register(Materials.tungsten, Items.tungstenCoin);
        MaterialProperties.PeerCoinXP.register(Materials.tungsten, 5000);

        MaterialProperties.BucketMeltingChance.register(Materials.tungsten, 0.0F);
        ItemProperties.RockExperience.register(Items.shardAzurite, 5);
    }
}
