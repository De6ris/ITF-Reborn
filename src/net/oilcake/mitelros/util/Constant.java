package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.bjo;
import net.oilcake.mitelros.item.Items;

public class Constant {
    public static final String VERSION = " ITF Aleph-0x00000156 ";
    public static final bjo icons_lros = new bjo("textures/gui/icons_lros.png");
    public static final bjo MITE_icons = new bjo("textures/gui/MITE_icons.png");
//    public static final bjo inventory = new bjo("textures/gui/inventory.png");
    public static final int VER_NUM = 114514;
    public static int nextItemID = 1284;
    public static int nextBlockID = 174;
    public static int nextPotionID = 24;
    public static int nextEnchantmentID = 96;
    public static int nextAchievementID = 136;
    public static int nextBiomeID = 27;
    public static int nextPacketID = 109;
    public static int getNextItemID(){
        return nextItemID++;
    }
    public static int getNextBiomeID(){
        return nextBiomeID++;
    }
    public static int getNextBlockID() {return nextBlockID++;}
    public static int getNextEnchantmentID(){
        return nextEnchantmentID++;
    }

    public static int getNextAchievementID() {return nextAchievementID++;}
    public static int getNextPacketID() {return nextPacketID++;}


    public static ItemArmor[] HELMETS = null;
    public static ItemArmor[] CHESTPLATES = null;
    public static ItemArmor[] LEGGINGS = null;
    public static ItemArmor[] BOOTS = null;
    public static Item[] SWORDS = null;
    public static ItemArmor[][] ARMORS = null;
    public static void initItemArray() {
        HELMETS = new ItemArmor[]{Item.helmetLeather, Item.helmetChainCopper, Item.helmetCopper, Item.helmetRustedIron, Item.helmetChainIron, Item.helmetIron, Item.helmetChainAncientMetal, Item.helmetAncientMetal, Item.helmetChainMithril, Item.helmetMithril, Item.helmetAdamantium,
                Items.nickelHelmet, Items.nickelHelmetChain,Items.tungstenHelmet,Items.tungstenHelmetChain,Items.WolfHelmet,Items.VibraniumHelmet,Items.MaidHelmet,Items.HelmetAncientmetalsacred};
        CHESTPLATES = new ItemArmor[]{Item.plateLeather, Item.plateChainCopper, Item.plateCopper, Item.plateRustedIron, Item.plateChainIron, Item.plateIron, Item.plateChainAncientMetal, Item.plateAncientMetal, Item.plateChainMithril, Item.plateMithril, Item.plateAdamantium,
                Items.nickelChestplate, Items.nickelChestplateChain,Items.tungstenChestplate,Items.tungstenChestplateChain,Items.WolfChestplate,Items.VibraniumChestplate,Items.MaidChestplate,Items.ChestplateAncientmetalsacred};
        LEGGINGS = new ItemArmor[]{Item.legsLeather, Item.legsChainCopper, Item.legsCopper, Item.legsRustedIron, Item.legsChainIron, Item.legsIron, Item.legsChainAncientMetal, Item.legsAncientMetal, Item.legsChainMithril, Item.legsMithril, Item.legsAdamantium,
                Items.nickelLeggings, Items.nickelLeggingsChain,Items.tungstenLeggings,Items.tungstenLeggings,Items.WolfLeggings,Items.VibraniumLeggings,Items.MaidLeggings,Items.LeggingsAncientmetalsacred};
        BOOTS = new ItemArmor[]{Item.bootsLeather, Item.bootsChainCopper, Item.bootsCopper, Item.bootsRustedIron, Item.bootsChainIron, Item.bootsIron, Item.bootsChainAncientMetal, Item.bootsAncientMetal, Item.bootsChainMithril, Item.bootsMithril, Item.bootsAdamantium,
                Items.nickelBoots, Items.nickelBootsChain,Items.tungstenBoots,Items.tungstenBootsChain,Items.WolfBoots,Items.VibraniumBoots,Items.MaidBoots,Items.BootsAncientmetalsacred};
        ARMORS = new ItemArmor[][]{HELMETS, CHESTPLATES, LEGGINGS, BOOTS};
        SWORDS = new Item[]{Item.swordRustedIron, Item.swordIron, Item.swordAncientMetal,Item.swordMithril,Item.swordAdamantium,Items.nickelSword,Items.tungstenSword,Items.VibraniumSword
        };
    }
    public static int CalculateCurrentDiff(){
        int Diff = 0;
        if(StuckTagConfig.TagConfig.TagFallenInMine.ConfigValue) Diff += 2;
        if(StuckTagConfig.TagConfig.TagBattleSuffer.ConfigValue) Diff += 2;
        if(StuckTagConfig.TagConfig.TagHeatStroke.ConfigValue) Diff += 1;
        if(StuckTagConfig.TagConfig.TagNoWeatherPredict.ConfigValue) Diff += 1;
        if(StuckTagConfig.TagConfig.TagInstinctSurvival.ConfigValue) Diff += 2;
        if(StuckTagConfig.TagConfig.TagInvisibleFollower.ConfigValue) Diff += 1;
        if(StuckTagConfig.TagConfig.TagLegendFreeze.ConfigValue) Diff += 1;
        if(StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue) Diff += 1;
        if(StuckTagConfig.TagConfig.TagUnstableConvection.ConfigValue) Diff += 1;
        return Diff;
    }
}