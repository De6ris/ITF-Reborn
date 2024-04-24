package net.oilcake.mitelros.util;

import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.config.ConfigBooleanChallenge;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;

public class Constant {

    public static final ResourceLocation icons_itf = new ResourceLocation("textures/gui/icons_mitf.png");

    public static int nextPotionID = 24;
    public static int nextBiomeID = 27;
    public static int nextCurseID = 17;

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

    public static int calculateCurrentDifficulty() {
        int difficulty = 0;
        for (ConfigBase configBase : ITFConfig.challenge) {
            if (configBase instanceof ConfigBooleanChallenge challenge && challenge.getBooleanValue()) {
                difficulty += challenge.getLevel();
            }
            if (configBase instanceof ConfigInteger configInteger) {
                difficulty += configInteger.getIntegerValue();
            }
        }
        return difficulty;
    }
}
