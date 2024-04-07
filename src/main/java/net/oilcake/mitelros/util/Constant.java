package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.item.Items;

public class Constant {

    public static final ResourceLocation icons_itf = new ResourceLocation("textures/gui/icons_mitf.png");

    public static final int CONFIG_VERSION = 1;

    public static int nextPotionID = 24;

    public static int nextBiomeID = 27;
    public static int nextCurseID = 16;

    public static int getNextBiomeID() {
        return nextBiomeID++;
    }

    public static int getNextCurseID() {
        return nextCurseID++;
    }

    public static ItemArmor[] HELMETS = null;

    public static ItemArmor[] CHESTPLATES = null;

    public static ItemArmor[] LEGGINGS = null;

    public static ItemArmor[] BOOTS = null;

    public static Item[] SWORDS = null;

    public static ItemArmor[][] ARMORS = null;

    public static void initItemArray() {
        HELMETS = new ItemArmor[]{
                Item.helmetLeather, Item.helmetChainCopper, Item.helmetCopper, Item.helmetRustedIron, Item.helmetChainIron, Item.helmetIron, Item.helmetChainAncientMetal, Item.helmetAncientMetal, Item.helmetChainMithril, Item.helmetMithril,
                Item.helmetAdamantium, Items.nickelHelmet, Items.nickelHelmetChain, Items.tungstenHelmet, Items.tungstenHelmetChain, Items.wolfHelmet, Items.VibraniumHelmet, Items.helmetCustom_a, Items.HelmetAncientmetalsacred, Items.UruHelmet,
                Items.helmetCustom_a};
        CHESTPLATES = new ItemArmor[]{
                Item.plateLeather, Item.plateChainCopper, Item.plateCopper, Item.plateRustedIron, Item.plateChainIron, Item.plateIron, Item.plateChainAncientMetal, Item.plateAncientMetal, Item.plateChainMithril, Item.plateMithril,
                Item.plateAdamantium, Items.nickelChestplate, Items.nickelChestplateChain, Items.tungstenChestplate, Items.tungstenChestplateChain, Items.wolfChestplate, Items.VibraniumChestplate, Items.chestplateCustom_a, Items.ChestplateAncientmetalsacred, Items.UruChestplate,
                Items.chestplateCustom_a};
        LEGGINGS = new ItemArmor[]{
                Item.legsLeather, Item.legsChainCopper, Item.legsCopper, Item.legsRustedIron, Item.legsChainIron, Item.legsIron, Item.legsChainAncientMetal, Item.legsAncientMetal, Item.legsChainMithril, Item.legsMithril,
                Item.legsAdamantium, Items.nickelLeggings, Items.nickelLeggingsChain, Items.tungstenLeggings, Items.tungstenLeggings, Items.wolfLeggings, Items.VibraniumLeggings, Items.leggingsCustom_a, Items.LeggingsAncientmetalsacred, Items.UruLeggings,
                Items.leggingsCustom_a};
        BOOTS = new ItemArmor[]{
                Item.bootsLeather, Item.bootsChainCopper, Item.bootsCopper, Item.bootsRustedIron, Item.bootsChainIron, Item.bootsIron, Item.bootsChainAncientMetal, Item.bootsAncientMetal, Item.bootsChainMithril, Item.bootsMithril,
                Item.bootsAdamantium, Items.nickelBoots, Items.nickelBootsChain, Items.tungstenBoots, Items.tungstenBootsChain, Items.wolfBoots, Items.VibraniumBoots, Items.bootsCustom_a, Items.BootsAncientmetalsacred, Items.UruBoots,
                Items.bootsCustom_a};
        ARMORS = new ItemArmor[][]{HELMETS, CHESTPLATES, LEGGINGS, BOOTS};
        SWORDS = new Item[]{Item.swordRustedIron, Item.swordIron, Item.swordAncientMetal, Item.swordMithril, Item.swordAdamantium, Items.nickelSword, Items.tungstenSword, Items.VibraniumSword, Items.UruSword};
    }

    public static int CalculateCurrentDiff() {
        int Diff = 0;
        if (Config.TagFallenInMineLVL1.get() && !Config.TagFallenInMineLVL2.get())
            Diff++;
        if (Config.TagBattleSufferLVL1.get() && !Config.TagBattleSufferLVL2.get())
            Diff++;
        if (Config.TagFallenInMineLVL2.get())
            Diff += 2;
        if (Config.TagBattleSufferLVL2.get())
            Diff += 2;
        if (Config.TagHeatStroke.get())
            Diff++;
        if (Config.TagNoWeatherPredict.get())
            Diff++;
        if (Config.TagInstinctSurvival.get())
            Diff++;
        if (Config.TagInvisibleFollower.get())
            Diff++;
        if (Config.TagLegendFreeze.get())
            Diff++;
        if (Config.TagEternalRaining.get())
            Diff += 2;
        if (Config.TagUnstableConvection.get())
            Diff++;
        if (Config.TagDryDilemma.get())
            Diff++;
        if (Config.TagDeadGeothermy.get())
            Diff += 2;
        if (Config.TagHeatStorm.get())
            Diff++;
        if (Config.TagApocalypse.get())
            Diff += 3;
        if (Config.TagWorshipDark.get())
            Diff += 2;
        if (Config.TagMiracleDisaster.get())
            Diff++;
        if (Config.TagAcousma.get())
            Diff++;
        if (Config.TagPseudoVision.get())
            Diff++;
        if (Config.TagRejection.get())
            Diff += 2;
        if (Config.TagUnderAlliance.get())
            Diff++;

        if (Config.TagArmament.get())
            Diff -= 2;
        if (Config.TagDistortion.get())
            Diff -= 2;
        if (Config.TagDigest.get())
            Diff -= 2;

        return Diff;
    }
}
