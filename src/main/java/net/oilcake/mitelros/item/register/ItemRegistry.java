package net.oilcake.mitelros.item.register;

import huix.glacier.api.extension.creativetab.GlacierCreativeTabs;
import net.minecraft.CreativeTabs;
import net.minecraft.Item;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;

import static net.oilcake.mitelros.ITFStart.NameSpace;

public class ItemRegistry extends Items implements Runnable {
    public static final CreativeTabs ITFItem = new GlacierCreativeTabs("itf_reborn.item") {
        @Override
        public int getTabIconItemIndex() {
            return Items.enderWand.itemID;
        }
    };
    public static final CreativeTabs ITFBlock = new GlacierCreativeTabs("itf_reborn.block") {
        @Override
        public int getTabIconItemIndex() {
            return Blocks.magicTable.blockID;
        }
    };

    private final ItemRegistryEvent event;

    public ItemRegistry(ItemRegistryEvent event) {
        this.event = event;
    }

    @Override
    public void run() {
        register("armor/nickel_helmet", nickelHelmet);
        register("armor/nickel_chestplate", nickelChestplate);
        register("armor/nickel_leggings", nickelLeggings);
        register("armor/nickel_boots", nickelBoots);
        register("armor/nickel_chainmail_helmet", nickelHelmetChain);
        register("armor/nickel_chainmail_chestplate", nickelChestplateChain);
        register("armor/nickel_chainmail_leggings", nickelLeggingsChain);
        register("armor/nickel_chainmail_boots", nickelBootsChain);
        register("ingots/nickel", nickelIngot);
        register("nuggets/nickel", nickelNugget);
        register("tools/nickel_axe", nickelAxe);
        register("tools/nickel_battle_axe", nickelBattleAxe);
        register("tools/nickel_dagger", nickelDagger);
        register("tools/nickel_hatchet", nickelHatchet);
        register("tools/nickel_hoe", nickelHoe);
        register("tools/nickel_knife", nickelKnife);
        register("tools/nickel_pickaxe", nickelPickaxe);
        register("tools/nickel_mattock", nickelMattock);
        register("tools/nickel_scythe", nickelScythe);
        register("tools/nickel_shears", nickelShears);
        register("tools/nickel_shovel", nickelShovel);
        register("tools/nickel_sword", nickelSword);
        register("tools/nickel_war_hammer", nickelWarHammer);
        register("doors/nickel", doorNickel);
        register("chain/nickel", nickelChain);
        register("coins/nickel", nickelCoin);
        register("arrows/nickel_arrow", arrowNickel);
        register("armor/tungsten_helmet", tungstenHelmet);
        register("armor/tungsten_chestplate", tungstenChestplate);
        register("armor/tungsten_leggings", tungstenLeggings);
        register("armor/tungsten_boots", tungstenBoots);
        register("armor/tungsten_chainmail_helmet", tungstenHelmetChain);
        register("armor/tungsten_chainmail_chestplate", tungstenChestplateChain);
        register("armor/tungsten_chainmail_leggings", tungstenLeggingsChain);
        register("armor/tungsten_chainmail_boots", tungstenBootsChain);
        register("ingots/tungsten", tungstenIngot);
        register("nuggets/tungsten", tungstenNugget);
        register("tools/tungsten_axe", tungstenAxe);
        register("tools/tungsten_battle_axe", tungstenBattleAxe);
        register("tools/tungsten_dagger", tungstenDagger);
        register("tools/tungsten_hatchet", tungstenHatchet);
        register("tools/tungsten_hoe", tungstenHoe);
        register("tools/tungsten_knife", tungstenKnife);
        register("tools/tungsten_pickaxe", tungstenPickaxe);
        register("tools/tungsten_mattock", tungstenMattock);
        register("tools/tungsten_scythe", tungstenScythe);
        register("tools/tungsten_shears", tungstenShears);
        register("tools/tungsten_shovel", tungstenShovel);
        register("tools/tungsten_sword", tungstenSword);
        register("tools/tungsten_war_hammer", tungstenWarHammer);
        register("doors/tungsten", doorTungsten);
        register("chain/tungsten", tungstenChain);
        register("coins/tungsten", tungstenCoin);
        register("arrows/tungsten_arrow", arrowTungsten);
        register("bowls/porkchop_stew", bowlPorkchopStew);
        register("bowls/lampchop_stew", bowlLampchopStew);
        register("bowls/salmon_soup", bowlSalmonSoup);
        register("bowls/beetroot_soup", bowlBeetrootSoup);
        register("pieces/copper", pieceCopper);
        register("pieces/silver", pieceSilver);
        register("pieces/gold", pieceGold);
        register("pieces/gold_nether", pieceGoldNether);
        register("pieces/iron", pieceIron);
        register("pieces/nickel", pieceNickel);
        register("pieces/tungsten", pieceTungsten);
        register("pieces/mithril", pieceMithril);
        register("pieces/adamantium", pieceAdamantium);
        register("food/mashed_cactus", mashedCactus);
        register("food/lemon", lemon);
        register("food/lemon_pie", lemonPie);
        register("bowls/lemonade", bowlLemonade);
        register("buckets/nickel/empty", nickelBucket);
        register("buckets/nickel/lava", nickelBucketLava);
        register("buckets/nickel/milk", nickelBucketMilk);
        register("buckets/nickel/stone", nickelBucketStone);
        register("buckets/nickel/water", nickelBucketWater);
        register("buckets/tungsten/empty", tungstenBucket);
        register("buckets/tungsten/lava", tungstenBucketLava);
        register("buckets/tungsten/milk", tungstenBucketMilk);
        register("buckets/tungsten/stone", tungstenBucketStone);
        register("buckets/tungsten/water", tungstenBucketWater);
        register("bowls/bowl_water_pure", bowlWaterPure);
        register("buckets/copper/water_pure", copperBucketWaterPure);
        register("buckets/silver/water_pure", silverBucketWaterPure);
        register("buckets/gold/water_pure", goldBucketWaterPure);
        register("buckets/iron/water_pure", ironBucketWaterPure);
        register("buckets/nickel/water_pure", nickelBucketWaterPure);
        register("buckets/mithril/water_pure", mithrilBucketWaterPure);
        register("buckets/tungsten/water_pure", tungstenBucketWaterPure);
        register("buckets/adamantium/water_pure", adamantiumBucketWaterPure);
        register("buckets/ancient_metal/water_pure", ancientmetalBucketWaterPure);
        register("misc/wolf_fur", wolf_fur);
        register("food/horse_meat", horse_meat);
        register("food/horse_meat_cooked", horse_meat_cooked);
        register("armor/wolf_helmet", wolfHelmet);
        register("armor/wolf_jacket", wolfChestplate);
        register("armor/wolf_leggings", wolfLeggings);
        register("armor/wolf_boots", wolfBoots);
        register("apple_golden", goldenAppleLegend, false);
        register("tools/copper_club", morningStarCopper);
        register("tools/silver_club", morningStarSilver);
        register("tools/gold_club", morningStarGold);
        register("tools/iron_club", morningStarIron);
        register("tools/nickel_club", morningStarNickel);
        register("tools/ancient_metal_club", morningStarAncientMetal);
        register("tools/mithril_club", morningStarMithril);
        register("tools/tungsten_club", morningStarTungsten);
        register("tools/adamantium_club", morningStarAdamantium);
        register("frag/stalker_creeper", fragStalkerCreeper);
        register("food/glow_berries", glowberries);
        register("arrows/magical_arrow", arrowMagical);
        register("wand/lava", lavaWand);
        register("wand/ice", freezeWand);
        register("wand/thunder", shockWand);
        register("potion/suspicious_potion", suspiciousPotion);
        register("potion/experimental_potion", experimentalPotion);
        register("misc/azurite", shardAzurite);
        register("records/record_damnation", recordDamnation);
        register("records/record_connected", recordConnected);
        register("tools/vibranium_sword", vibraniumSword);
        register("armor/vibranium_helmet", vibraniumHelmet);
        register("armor/vibranium_chestplate", vibraniumChestplate);
        register("armor/vibranium_leggings", vibraniumLeggings);
        register("armor/vibranium_boots", vibraniumBoots);
        register("armor/null_helmet", helmetCustom_a);
        register("armor/null_chestplate", chestplateCustom_a);
        register("armor/null_leggings", leggingsCustom_a);
        register("armor/null_boots", bootsCustom_a);
        register("armor/null_helmet", helmetCustom_b);
        register("armor/null_chestplate", chestplateCustom_b);
        register("armor/null_leggings", leggingsCustom_b);
        register("armor/null_boots", bootsCustom_b);
        register("armor/ancient_metal_sacred_helmet", helmetAncientMetalSacred);
        register("armor/ancient_metal_sacred_chestplate", chestplateAncientMetalSacred);
        register("armor/ancient_metal_sacred_leggings", leggingsAncientMetalSacred);
        register("armor/ancient_metal_sacred_boots", bootsAncientMetalSacred);
        register("misc/ancient_metal_armor_piece", ancientMetalArmorPiece);
        register("food/agave", agave);
        register("misc/pulque", pulque);
        register("misc/ale", ale);
        register("armor/uru_helmet", uruHelmet);
        register("armor/uru_chestplate", uruChestplate);
        register("armor/uru_leggings", uruLeggings);
        register("armor/uru_boots", uruBoots);
        register("misc/forging_note", forgingNote);
        register("ingots/uru", uruIngot);
        register("nuggets/uru", uruNugget);
        register("tools/uru_battle_axe", uruBattleAxe);
        register("tools/uru_mattock", uruMattock);
        register("tools/uru_scythe", uruScythe);
        register("tools/uru_sword", uruSword);
        register("tools/uru_war_hammer", uruWarHammer);
        register("tools/uru_club", uruMorningStar);
        register("tools/uru_pickaxe", uruPickaxe);
        register("pieces/uru", pieceUru);
        register("bows/tungsten/", bowTungsten).setUnlocalizedName("tungsten_bow");
        register("food/beetroot", beetroot);
        register("food/beetroot_seeds", seedsBeetroot);
        register("hardened_clay_bowls/raw", clayBowlRaw);
        register("hardened_clay_bowls/beef_stew", clayBowlBeefStew);
        register("hardened_clay_bowls/beetroot_soup", clayBowlBeetrootSoup);
        register("hardened_clay_bowls/bowl_milk", clayBowlMilk);
        register("hardened_clay_bowls/bowl_salad", clayBowlSalad);
        register("hardened_clay_bowls/bowl_water", clayBowlWater);
        register("hardened_clay_bowls/bowl_water_pure", clayBowlWaterPure);
        register("hardened_clay_bowls/cereal", clayBowlCereal);
        register("hardened_clay_bowls/chicken_soup", clayBowlChickenSoup);
        register("hardened_clay_bowls/cream_of_mushroom_soup", clayBowlCreamOfMushroomSoup);
        register("hardened_clay_bowls/cream_of_vegetable_soup", clayBowlCreamOfVegetableSoup);
        register("hardened_clay_bowls/empty", clayBowlEmpty);
        register("hardened_clay_bowls/ice_cream", clayBowlIceCream);
        register("hardened_clay_bowls/lampchop_stew", clayBowlLampchopSoup);
        register("hardened_clay_bowls/lemonade", clayBowlLemonade);
        register("hardened_clay_bowls/mashed_potato", clayBowlMashedPotato);
        register("hardened_clay_bowls/mushroom_stew", clayBowlMushroomStew);
        register("hardened_clay_bowls/porkchop_stew", clayBowlPorkchopStew);
        register("hardened_clay_bowls/porridge", clayBowlPorridge);
        register("hardened_clay_bowls/pumpkin_soup", clayBowlPumpkinSoup);
        register("hardened_clay_bowls/salmon_soup", clayBowlSalmonSoup);
        register("hardened_clay_bowls/sorbet", clayBowlSorbet);
        register("hardened_clay_bowls/vegetable_soup", clayBowlVegetableSoup);
        register("totem/totem_of_fecund", totemOfFecund);
        register("totem/totem_of_destroy", totemOfDestroy);
        register("totem/totem_of_knowledge", totemOfKnowledge);
        register("totem/totem_of_preserve", totemOfPreserve);
        register("totem/totem_of_hunting", totemOfHunting);
        register("ignition/wood", ignitionWood);
        register("ignition/copper", ignitionCopper);
        register("ignition/silver", ignitionSilver);
        register("ignition/gold", ignitionGold);
        register("ignition/nickel", ignitionNickel);
        register("ignition/ancient_metal", ignitionAncientMetal);
        register("ignition/mithril", ignitionMithril);
        register("ignition/tungsten", ignitionTungsten);
        register("ignition/adamantium", ignitionAdamantium);
        register("misc/wither_branch", wither_branch);
        register("tools/detector", detectorDiamond);
        register("tools/detector_emerald", detectorEmerald);
        register("misc/sulphur_sphere", sulphur);
        register("bows/uru/", bowUru).setUnlocalizedName("uru_bow");
        register("misc/ender_rod", enderRod);
        register("tools/rusted_iron_club", morningStarRustedIron);
        register("buckets/wood/empty", woodBucket);
        register("buckets/wood/water", woodBucketWater);
        register("buckets/wood/water_pure", woodBucketWaterPure);
        register("buckets/wood/milk", woodBucketMilk);
        register("tools/flint_hoe", hoeFlint);
        register("food/peeled_sugarcane", peeledSugarcane);
        register("totem/totem_of_sentry", totemOfSentry);
        register("totem/totem_of_unknown", totemOfUnknown);
        register("ignition/rusted_iron", ignitionRustedIron);
        register("tools/stick_knife", stickKnife);
        register("wand/slime", slimeWand);
        register("misc/ice_chunk", iceChunk);
        register("armor/ice_helmet", iceHelmet);
        register("armor/ice_chestplate", iceChestplate);
        register("armor/ice_leggings", iceLeggings);
        register("armor/ice_boots", iceBoots);
        register("food/ice_sucker", ice_sucker);
        register("food/melon_ice", melon_ice);
        register("food/chocolate_smoothie", chocolate_smoothie);
        register("misc/frost_rod", frostRod);
        register("misc/frost_powder", frostPowder);
        register("totem/totem_of_flattening", totemOfFlattening);
        register("loot_pack/lich", lootPackLich);
        register("pocket/mine", minePocket);
        register("kettle/leather", leatherKettle).setUnlocalizedName("leather_kettle");
        register("kettle/leather", leatherKettlePure).setUnlocalizedName("leather_kettle_pure");
        register("jug/clay", clayJug).setUnlocalizedName("clay_jug");
        register("jug/hardened_clay", hardenedClayJug).setUnlocalizedName("hardened_clay_jug");
        register("jug/hardened_clay", hardenedClayJugPure).setUnlocalizedName("hardened_clay_jug_pure");
        register("wand/ender", enderWand);
        register("misc/boss_detector", bossDetector);
        register("kettle/leather", uruKettle).setUnlocalizedName("uru_kettle");

        fishingRodNickel.setCreativeTab(ITFItem);
        fishingRodTungsten.setCreativeTab(ITFItem);
    }

    private Item register(String texture, Item item) {
        return register(texture, item, true);
    }

    private Item register(String texture, Item item, boolean withDomain) {
        String translationKey = texture;
        if (withDomain) texture = ITFStart.ResourceDomainColon + texture;
        return event.register(NameSpace, texture, translationKey, item, ITFItem);
    }

}
