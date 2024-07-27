package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;

import java.util.function.Supplier;

public class ITFLootTables {
    public static final Supplier<WeightedRandomChestContent[]> lichEntity = () -> new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Item.diamond.itemID, 0, 2, 4, 20),
            new WeightedRandomChestContent(Item.coinGold.itemID, 0, 2, 4, 20),
            new WeightedRandomChestContent(Item.coinAncientMetal.itemID, 0, 2, 4, 20),
            new WeightedRandomChestContent(Items.freezeWand.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.lavaWand.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.coinMithril.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.chestplateAncientMetalSacred.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.helmetAncientMetalSacred.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.bootsAncientMetalSacred.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.leggingsAncientMetalSacred.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.totemOfKnowledge.itemID, 0, 1, 1, 4),
            new WeightedRandomChestContent(Items.totemOfSentry.itemID, 0, 1, 1, 4),
            new WeightedRandomChestContent(Items.totemOfFlattening.itemID, 0, 1, 1, 4),
    };

    public static final Supplier<WeightedRandomChestContent[]> lichCastle = () -> new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Item.ingotAncientMetal.itemID, 0, 3, 5, 10),
            new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 3, 5, 10),
            new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 3, 5),
            new WeightedRandomChestContent(Items.ancientMetalArmorPiece.itemID, 0, 6, 9, 20),
            new WeightedRandomChestContent(Item.coinAncientMetal.itemID, 0, 1, 3, 5),
            new WeightedRandomChestContent(Item.coinGold.itemID, 0, 1, 3, 5),
            new WeightedRandomChestContent(Item.horseArmorMithril.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.horseArmorAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.freezeWand.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.lavaWand.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Item.warHammerAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.battleAxeAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.swordAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.bootsAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.legsAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.plateAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Item.helmetAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.chestplateAncientMetalSacred.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.helmetAncientMetalSacred.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.bootsAncientMetalSacred.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.leggingsAncientMetalSacred.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.totemOfKnowledge.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.totemOfSentry.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.totemOfFlattening.itemID, 0, 1, 1, 1)
    };

    public static final Supplier<WeightedRandomChestContent[]> fortressExtra = () -> new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.tungstenIngot.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.tungstenNugget.itemID, 0, 3, 7, 10),
            new WeightedRandomChestContent(Items.tungstenSword.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.tungstenWarHammer.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.tungstenHelmetChain.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.tungstenChestplateChain.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.tungstenLeggingsChain.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.tungstenBootsChain.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.tungstenHelmet.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.tungstenChestplate.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.tungstenLeggings.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.tungstenBoots.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.ignitionGold.itemID, 0, 1, 1, 18),
            new WeightedRandomChestContent(Items.ignitionTungsten.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.totemOfDestroy.itemID, 0, 1, 1, 5)
    };

    public static final Supplier<WeightedRandomChestContent[]> dungeonOverworldExtra = () -> new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 1, 5),
            new WeightedRandomChestContent(Items.morningStarCopper.itemID, 0, 1, 1, 1),
            new WeightedRandomChestContent(Items.warHammerRustedIron.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.morningStarRustedIron.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.ignitionRustedIron.itemID, 0, 1, 1, 1)
    };

    public static final Supplier<WeightedRandomChestContent[]> dungeonUnderworldExtra = () -> new WeightedRandomChestContent[]{
            new WeightedRandomChestContent(Items.ancientMetalArmorPiece.itemID, 0, 1, 2, 10),
            new WeightedRandomChestContent(Items.morningStarAncientMetal.itemID, 0, 1, 1, 2),
            new WeightedRandomChestContent(Items.ignitionAncientMetal.itemID, 0, 1, 1, 2)
    };
}
