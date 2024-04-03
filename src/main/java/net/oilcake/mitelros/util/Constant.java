package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.item.Items;
import net.xiaoyu233.fml.util.IdUtil;

public class Constant {

    public static final ResourceLocation icons_itf = new ResourceLocation("textures/gui/icons_mitf.png");

    public static int nextPotionID = 24;

    public static int nextEnchantmentID = 96;

    public static int nextAchievementID = 136;

    public static int nextBiomeID = 27;

    public static int nextPacketID = 109;
    public static int nextEntityID = 114;

    public static int getNextEntityID() {
        return nextEntityID++;
    }

    public static int getNextItemID() {
        return IdUtil.getNextItemID();
    }

    public static int getNextBiomeID() {
        return nextBiomeID++;
    }

    public static int getNextEnchantmentID() {
        return nextEnchantmentID++;
    }

    public static int getNextAchievementID() {
        return nextAchievementID++;
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
                Item.helmetAdamantium, Items.nickelHelmet, Items.nickelHelmetChain, Items.tungstenHelmet, Items.tungstenHelmetChain, Items.WolfHelmet, Items.VibraniumHelmet, Items.helmetCustom_a, Items.HelmetAncientmetalsacred, Items.UruHelmet,
                Items.helmetCustom_a};
        CHESTPLATES = new ItemArmor[]{
                Item.plateLeather, Item.plateChainCopper, Item.plateCopper, Item.plateRustedIron, Item.plateChainIron, Item.plateIron, Item.plateChainAncientMetal, Item.plateAncientMetal, Item.plateChainMithril, Item.plateMithril,
                Item.plateAdamantium, Items.nickelChestplate, Items.nickelChestplateChain, Items.tungstenChestplate, Items.tungstenChestplateChain, Items.WolfChestplate, Items.VibraniumChestplate, Items.chestplateCustom_a, Items.ChestplateAncientmetalsacred, Items.UruChestplate,
                Items.chestplateCustom_a};
        LEGGINGS = new ItemArmor[]{
                Item.legsLeather, Item.legsChainCopper, Item.legsCopper, Item.legsRustedIron, Item.legsChainIron, Item.legsIron, Item.legsChainAncientMetal, Item.legsAncientMetal, Item.legsChainMithril, Item.legsMithril,
                Item.legsAdamantium, Items.nickelLeggings, Items.nickelLeggingsChain, Items.tungstenLeggings, Items.tungstenLeggings, Items.WolfLeggings, Items.VibraniumLeggings, Items.leggingsCustom_a, Items.LeggingsAncientmetalsacred, Items.UruLeggings,
                Items.leggingsCustom_a};
        BOOTS = new ItemArmor[]{
                Item.bootsLeather, Item.bootsChainCopper, Item.bootsCopper, Item.bootsRustedIron, Item.bootsChainIron, Item.bootsIron, Item.bootsChainAncientMetal, Item.bootsAncientMetal, Item.bootsChainMithril, Item.bootsMithril,
                Item.bootsAdamantium, Items.nickelBoots, Items.nickelBootsChain, Items.tungstenBoots, Items.tungstenBootsChain, Items.WolfBoots, Items.VibraniumBoots, Items.bootsCustom_a, Items.BootsAncientmetalsacred, Items.UruBoots,
                Items.bootsCustom_a};
        ARMORS = new ItemArmor[][]{HELMETS, CHESTPLATES, LEGGINGS, BOOTS};
        SWORDS = new Item[]{Item.swordRustedIron, Item.swordIron, Item.swordAncientMetal, Item.swordMithril, Item.swordAdamantium, Items.nickelSword, Items.tungstenSword, Items.VibraniumSword, Items.UruSword};
    }

    public static int CalculateCurrentDiff() {
        int Diff = 0;
        if (StuckTagConfig.TagConfig.TagFallenInMineLVL1.ConfigValue.booleanValue() && !StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagBattleSufferLVL1.ConfigValue.booleanValue() && !StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue.booleanValue())
            Diff += 2;
        if (StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue.booleanValue())
            Diff += 2;
        if (StuckTagConfig.TagConfig.TagHeatStroke.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagNoWeatherPredict.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagInstinctSurvival.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagInvisibleFollower.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagLegendFreeze.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue.booleanValue())
            Diff += 2;
        if (StuckTagConfig.TagConfig.TagUnstableConvection.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagDeadgeothermy.ConfigValue.booleanValue())
            Diff += 2;
        if (StuckTagConfig.TagConfig.TagHeatStorm.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagApocalypse.ConfigValue.booleanValue())
            Diff += 3;
        if (StuckTagConfig.TagConfig.TagWorshipDark.ConfigValue.booleanValue())
            Diff += 2;
        if (StuckTagConfig.TagConfig.TagMiracleDisaster.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagAcousma.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagPseudovision.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagRejection.ConfigValue.booleanValue())
            Diff += 2;
        if (StuckTagConfig.TagConfig.TagUnderAlliance.ConfigValue.booleanValue())
            Diff++;
        if (StuckTagConfig.TagConfig.TagArmament.ConfigValue.booleanValue())
            Diff -= 2;
        if (StuckTagConfig.TagConfig.TagDistortion.ConfigValue.booleanValue())
            Diff -= 2;
        if (StuckTagConfig.TagConfig.TagDigest.ConfigValue.booleanValue())
            Diff -= 2;
        return Diff;
    }
}
