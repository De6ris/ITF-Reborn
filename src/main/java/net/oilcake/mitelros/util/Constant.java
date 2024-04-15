package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.item.Items;

public class Constant {

    public static final ResourceLocation icons_itf = new ResourceLocation("textures/gui/icons_mitf.png");

    public static final int CONFIG_VERSION = 2;

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
        if (ITFConfig.TagFallenInMineLVL1.get() && !ITFConfig.TagFallenInMineLVL2.get())
            Diff++;
        if (ITFConfig.TagBattleSufferLVL1.get() && !ITFConfig.TagBattleSufferLVL2.get())
            Diff++;
        if (ITFConfig.TagFallenInMineLVL2.get())
            Diff += 2;
        if (ITFConfig.TagBattleSufferLVL2.get())
            Diff += 2;
        if (ITFConfig.TagHeatStroke.get())
            Diff++;
        if (ITFConfig.TagInstinctSurvival.get())
            Diff++;
        if (ITFConfig.TagInvisibleFollower.get())
            Diff++;
        if (ITFConfig.TagLegendFreeze.get())
            Diff++;
        if (ITFConfig.TagEternalRaining.get())
            Diff += 2;
        if (ITFConfig.TagUnstableConvection.get())
            Diff++;
        if (ITFConfig.TagDryDilemma.get())
            Diff++;
        if (ITFConfig.TagDeadGeothermy.get())
            Diff += 2;
        if (ITFConfig.TagHeatStorm.get())
            Diff++;
        if (ITFConfig.TagApocalypse.get())
            Diff += 3;
        if (ITFConfig.TagWorshipDark.get())
            Diff += 2;
        if (ITFConfig.TagMiracleDisaster.get())
            Diff++;
        if (ITFConfig.TagPseudoVision.get())
            Diff++;
        if (ITFConfig.TagRejection.get())
            Diff += 2;
        if (ITFConfig.TagUnderAlliance.get())
            Diff++;
        if (ITFConfig.TagDimensionInvade.get())
            Diff += 4;
        if (ITFConfig.TagDemonDescend.get())
            Diff += 2;

        if (ITFConfig.TagArmament.get())
            Diff -= 2;
        if (ITFConfig.TagDistortion.get())
            Diff -= 2;
        if (ITFConfig.TagDigest.get())
            Diff -= 2;

        return Diff;
    }
}
