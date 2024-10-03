package net.oilcake.mitelros.item;

import moddedmite.rustedironcore.api.item.BowItem;
import moddedmite.rustedironcore.api.item.DoorItem;
import moddedmite.rustedironcore.api.item.FishingRodItem;
import moddedmite.rustedironcore.api.item.NuggetItem;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.api.*;
import net.oilcake.mitelros.item.minePocket.ItemMinePocket;
import net.oilcake.mitelros.item.potion.ItemPotionExperimental;
import net.oilcake.mitelros.item.potion.ItemPotionSuspicious;
import net.oilcake.mitelros.item.totem.*;
import net.oilcake.mitelros.item.wand.*;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.ITFLootTables;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.xiaoyu233.fml.util.ReflectHelper;

public class Items extends Item {
    public static final ItemArmor nickelHelmet = new ItemHelmet(getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelChestplate = new ItemCuirass(getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelLeggings = new ItemLeggings(getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelBoots = new ItemBoots(getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelHelmetChain = new ItemHelmet(getNextItemID(), Materials.nickel, true);

    public static final ItemArmor nickelChestplateChain = new ItemCuirass(getNextItemID(), Materials.nickel, true);

    public static final ItemArmor nickelLeggingsChain = new ItemLeggings(getNextItemID(), Materials.nickel, true);

    public static final ItemArmor nickelBootsChain = new ItemBoots(getNextItemID(), Materials.nickel, true);

    public static final ItemNugget nickelNugget = new NuggetItem(getNextItemID(), Materials.nickel);

    public static final ItemAxe nickelAxe = ReflectHelper.createInstance(ItemAxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemBattleAxe nickelBattleAxe = ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemDagger nickelDagger = ReflectHelper.createInstance(ItemDagger.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final Item nickelIngot = ReflectHelper.createInstance(ItemIngot.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel).setXPReward(15);

    public static final ItemPickaxe nickelPickaxe = ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemShovel nickelShovel = ReflectHelper.createInstance(ItemShovel.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemSword nickelSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemWarHammer nickelWarHammer = ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemHatchet nickelHatchet = ReflectHelper.createInstance(ItemHatchet.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemHoe nickelHoe = ReflectHelper.createInstance(ItemHoe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemKnife nickelKnife = ReflectHelper.createInstance(ItemKnife.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemMattock nickelMattock = ReflectHelper.createInstance(ItemMattock.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemScythe nickelScythe = ReflectHelper.createInstance(ItemScythe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemShears nickelShears = new ItemShears(getNextItemID(), Materials.nickel);

    public static final Item doorNickel = new DoorItem(getNextItemID(), Materials.nickel, () -> Blocks.doorNickel);

    public static final ItemChain nickelChain = ReflectHelper.createInstance(ItemChain.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.nickel);

    public static final ItemCoin nickelCoin = new ItemCoin(getNextItemID(), Materials.nickel);

    public static final ItemArrow arrowNickel = new ItemArrow(getNextItemID(), Materials.nickel);

    public static final ItemArmor tungstenHelmet = new ItemHelmet(getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenChestplate = new ItemCuirass(getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenLeggings = new ItemLeggings(getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenBoots = new ItemBoots(getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenHelmetChain = new ItemHelmet(getNextItemID(), Materials.tungsten, true);

    public static final ItemArmor tungstenChestplateChain = new ItemCuirass(getNextItemID(), Materials.tungsten, true);

    public static final ItemArmor tungstenLeggingsChain = new ItemLeggings(getNextItemID(), Materials.tungsten, true);

    public static final ItemArmor tungstenBootsChain = new ItemBoots(getNextItemID(), Materials.tungsten, true);

    public static final ItemNugget tungstenNugget = new NuggetItem(getNextItemID(), Materials.tungsten);

    public static final ItemAxe tungstenAxe = ReflectHelper.createInstance(ItemAxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemBattleAxe tungstenBattleAxe = ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemDagger tungstenDagger = ReflectHelper.createInstance(ItemDagger.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final Item tungstenIngot = ReflectHelper.createInstance(ItemIngot.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten).setXPReward(75);

    public static final ItemPickaxe tungstenPickaxe = ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemShovel tungstenShovel = ReflectHelper.createInstance(ItemShovel.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemSword tungstenSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemWarHammer tungstenWarHammer = ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemHatchet tungstenHatchet = ReflectHelper.createInstance(ItemHatchet.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemHoe tungstenHoe = ReflectHelper.createInstance(ItemHoe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemKnife tungstenKnife = ReflectHelper.createInstance(ItemKnife.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemMattock tungstenMattock = ReflectHelper.createInstance(ItemMattock.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemScythe tungstenScythe = ReflectHelper.createInstance(ItemScythe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemShears tungstenShears = new ItemShears(getNextItemID(), Materials.tungsten);

    public static final Item doorTungsten = new DoorItem(getNextItemID(), Materials.tungsten, () -> Blocks.doorTungsten);

    public static final ItemChain tungstenChain = ReflectHelper.createInstance(ItemChain.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.tungsten);

    public static final ItemCoin tungstenCoin = new ItemCoin(getNextItemID(), Materials.tungsten);

    public static final ItemArrow arrowTungsten = new ItemArrow(getNextItemID(), Materials.tungsten);

    public static final ItemBowl bowlPorkchopStew = (ItemBowl) (new ItemBowl(getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");

    public static final ItemBowl bowlLampchopStew = (ItemBowl) (new ItemBowl(getNextItemID(), Materials.lampchop_stew, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");

    public static final ItemBowl bowlWaterSuspicious = (ItemBowl) (new ItemBowl(getNextItemID(), Materials.suspicious_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");

    public static final ItemBowl bowlWaterSwampland = (ItemBowl) (new ItemBowl(getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");

    public static final ItemPieces pieceCopper = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceCopper");

    public static final ItemPieces pieceSilver = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceSilver");

    public static final ItemPieces pieceGold = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceGold");

    public static final ItemPieces pieceGoldNether = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceGoldNether");

    public static final ItemPieces pieceIron = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceIron");

    public static final ItemPieces pieceNickel = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceNickel");

    public static final ItemPieces pieceMithril = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceMithril");

    public static final ItemPieces pieceTungsten = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceTungsten");

    public static final ItemPieces pieceAdamantium = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceAdamantium");

    public static final ItemFood mashedCactus = (ItemFood) (new ItemFood(getNextItemID(), Materials.mashedCactus, 2, 0, 250, false, true, false, "mashedCactus")).setMaxStackSize(8);

    public static final ItemFood lemon = (new ItemFood(getNextItemID(), Material.fruit, 2, 1, 1000, false, false, true, "lemon")).setPlantProduct();

    public static final Item lemonPie = (new ItemFood(getNextItemID(), Material.pie, 10, 6, 1000, true, true, true, "lemon_pie")).setMaxStackSize(8).setPlantProduct().setAnimalProduct();

    public static final ItemBucket nickelBucket = new ItemBucket(getNextItemID(), Materials.nickel, null);

    public static final ItemBucket nickelBucketWater = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.nickel, Material.water)).setContainerItem(nickelBucket);

    public static final ItemBucket nickelBucketLava = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.nickel, Material.lava)).setContainerItem(nickelBucket);

    public static final ItemBucket nickelBucketStone = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.nickel, Material.stone)).setContainerItem(nickelBucket);

    public static final ItemBucketMilk nickelBucketMilk = (ItemBucketMilk) (new ItemBucketMilk(getNextItemID(), Materials.nickel)).setContainerItem(nickelBucket);

    public static final ItemBucket tungstenBucket = new ItemBucket(getNextItemID(), Materials.tungsten, null);

    public static final ItemBucket tungstenBucketWater = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.tungsten, Material.water)).setContainerItem(tungstenBucket);

    public static final ItemBucket tungstenBucketLava = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.tungsten, Material.lava)).setContainerItem(tungstenBucket);

    public static final ItemBucket tungstenBucketStone = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.tungsten, Material.stone)).setContainerItem(tungstenBucket);

    public static final ItemBucketMilk tungstenBucketMilk = (ItemBucketMilk) (new ItemBucketMilk(getNextItemID(), Materials.tungsten)).setContainerItem(tungstenBucket);

    public static final ItemBucket copperBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Material.copper, Materials.suspicious_water)).setContainerItem(bucketCopperEmpty);

    public static final ItemBucket silverBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Material.silver, Materials.suspicious_water)).setContainerItem(bucketSilverEmpty);

    public static final ItemBucket goldBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Material.gold, Materials.suspicious_water)).setContainerItem(bucketGoldEmpty);

    public static final ItemBucket ironBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Material.iron, Materials.suspicious_water)).setContainerItem(bucketIronEmpty);

    public static final ItemBucket nickelBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.nickel, Materials.suspicious_water)).setContainerItem(nickelBucket);

    public static final ItemBucket ancientmetalBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Material.ancient_metal, Materials.suspicious_water)).setContainerItem(bucketAncientMetalEmpty);

    public static final ItemBucket mithrilBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Material.mithril, Materials.suspicious_water)).setContainerItem(bucketMithrilEmpty);

    public static final ItemBucket tungstenBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.tungsten, Materials.suspicious_water)).setContainerItem(tungstenBucket);

    public static final ItemBucket adamantiumBucketWaterSuspicious = (ItemBucket) (new ItemBucket(getNextItemID(), Material.adamantium, Materials.suspicious_water)).setContainerItem(bucketAdamantiumEmpty);

    public static final ItemBucket copperBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Material.copper, Materials.dangerous_water)).setContainerItem(bucketCopperEmpty);

    public static final ItemBucket silverBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Material.silver, Materials.dangerous_water)).setContainerItem(bucketSilverEmpty);

    public static final ItemBucket goldBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Material.gold, Materials.dangerous_water)).setContainerItem(bucketGoldEmpty);

    public static final ItemBucket ironBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Material.iron, Materials.dangerous_water)).setContainerItem(bucketIronEmpty);

    public static final ItemBucket nickelBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.nickel, Materials.dangerous_water)).setContainerItem(nickelBucket);

    public static final ItemBucket ancientmetalBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Material.ancient_metal, Materials.dangerous_water)).setContainerItem(bucketAncientMetalEmpty);

    public static final ItemBucket mithrilBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Material.mithril, Materials.dangerous_water)).setContainerItem(bucketMithrilEmpty);

    public static final ItemBucket tungstenBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Materials.tungsten, Materials.dangerous_water)).setContainerItem(tungstenBucket);

    public static final ItemBucket adamantiumBucketWaterDangerous = (ItemBucket) (new ItemBucket(getNextItemID(), Material.adamantium, Materials.dangerous_water)).setContainerItem(bucketAdamantiumEmpty);

    public static final Item wolf_fur = new ItemStandard(getNextItemID(), Materials.wolf_fur, "wolf_fur");

    public static final Item horse_meat = new ItemMeat(getNextItemID(), 6, 6, true, false, "horse_meat");

    public static final Item horse_meat_cooked = new ItemMeat(getNextItemID(), 12, 12, true, true, "horse_meat_cooked");

    public static final ItemArmor wolfHelmet = new ItemHelmet(getNextItemID(), Materials.wolf_fur, false);

    public static final ItemArmor wolfChestplate = new ItemCuirass(getNextItemID(), Materials.wolf_fur, false);

    public static final ItemArmor wolfLeggings = new ItemLeggings(getNextItemID(), Materials.wolf_fur, false);

    public static final ItemArmor wolfBoots = new ItemBoots(getNextItemID(), Materials.wolf_fur, false);

    public static final Item goldenAppleLegend = (new ItemGoldenAppleLegend(getNextItemID(), 2, 1, "goldapple")).setAlwaysEdible().setUnlocalizedName("wtfk").useVanillaTexture("apple_golden_legend");

    public static final ItemBowl bowlLemonade = (ItemBowl) (new ItemBowl(getNextItemID(), Materials.lemonade, "lemonade")).setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");

    public static final ItemMorningStar morningStarCopper = new ItemMorningStar(getNextItemID(), Material.copper);

    public static final ItemMorningStar morningStarSilver = new ItemMorningStar(getNextItemID(), Material.silver);

    public static final ItemMorningStar morningStarGold = new ItemMorningStar(getNextItemID(), Material.gold);

    public static final ItemMorningStar morningStarIron = new ItemMorningStar(getNextItemID(), Material.iron);

    public static final ItemMorningStar morningStarNickel = new ItemMorningStar(getNextItemID(), Materials.nickel);

    public static final ItemMorningStar morningStarAncientMetal = new ItemMorningStar(getNextItemID(), Material.ancient_metal);

    public static final ItemMorningStar morningStarMithril = new ItemMorningStar(getNextItemID(), Material.mithril);

    public static final ItemMorningStar morningStarTungsten = new ItemMorningStar(getNextItemID(), Materials.tungsten);

    public static final ItemMorningStar morningStarAdamantium = new ItemMorningStar(getNextItemID(), Material.adamantium);

    public static final Item fragStalkerCreeper = new ItemStandard(getNextItemID(), Material.frags, "fragStalkerCreeper");

    public static final ItemFood glowberries = (ItemFood) (new ItemFood(getNextItemID(), Materials.glowberries, 1, 0, false, false, false, "glow_berries")).setMaxStackSize(16).setAlwaysEdible();

    public static final ItemArrow arrowMagical = new ItemArrow(getNextItemID(), Materials.magical);

    public static final ItemWand lavaWand = new ItemWandLava(getNextItemID());

    public static final ItemWand freezeWand = new ItemWandFreeze(getNextItemID());

    public static final ItemWand shockWand = new ItemWandShock(getNextItemID());

    public static final Item experimentalPotion = (new ItemPotionExperimental(getNextItemID())).setUnlocalizedName("experimentalPotion").setCreativeTab(CreativeTabs.tabMisc);

    public static final ITFRecord recordDamnation = (ITFRecord) (new ITFRecord(2024, "imported.damnation", "record_damnation", "Damnation", "Mwk feat. Hatsune Miku")).setUnlocalizedName("record");

    public static final ITFRecord recordConnected = (ITFRecord) (new ITFRecord(2025, "imported.connected", "record_connected", "Connected", "Mwk feat. Hatsune Miku")).setUnlocalizedName("record");

    public static final ItemArmor vibraniumHelmet = new ItemHelmet(getNextItemID(), Materials.vibranium, false);

    public static final ItemArmor vibraniumChestplate = new ItemCuirass(getNextItemID(), Materials.vibranium, false);

    public static final ItemArmor vibraniumLeggings = new ItemLeggings(getNextItemID(), Materials.vibranium, false);

    public static final ItemArmor vibraniumBoots = new ItemBoots(getNextItemID(), Materials.vibranium, false);

    public static final ItemSword vibraniumSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.vibranium);

    public static final ItemArmor helmetCustom_a = new ItemHelmet(getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor chestplateCustom_a = new ItemCuirass(getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor leggingsCustom_a = new ItemLeggings(getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor bootsCustom_a = new ItemBoots(getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor helmetAncientMetalSacred = new ItemHelmet(getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final ItemArmor chestplateAncientMetalSacred = new ItemCuirass(getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final ItemArmor leggingsAncientMetalSacred = new ItemLeggings(getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final ItemArmor bootsAncientMetalSacred = new ItemBoots(getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final Item ancientMetalArmorPiece = new NuggetItem(getNextItemID(), Materials.ancient_metal_sacred).setCraftingDifficultyAsComponent(800.0F).setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);

    public static final ItemFood agave = (ItemFood) (new ItemFood(getNextItemID(), Materials.agave, 1, 0, false, false, false, "agave")).setMaxStackSize(16).setAlwaysEdible();

    public static final Item pulque = (new ItemWine(getNextItemID())).setUnlocalizedName("pulque").setCreativeTab(CreativeTabs.tabFood);

    public static final Item ale = (new ItemWine(getNextItemID())).setUnlocalizedName("ale").setCreativeTab(CreativeTabs.tabFood);

    public static final ItemBow bowTungsten = new BowItem(getNextItemID(), Materials.tungsten, 256, 35);

    public static final ItemArmor uruHelmet = new ItemHelmet(getNextItemID(), Materials.uru, false);

    public static final ItemArmor uruChestplate = new ItemCuirass(getNextItemID(), Materials.uru, false);

    public static final ItemArmor uruLeggings = new ItemLeggings(getNextItemID(), Materials.uru, false);

    public static final ItemArmor uruBoots = new ItemBoots(getNextItemID(), Materials.uru, false);

    public static final ItemNugget uruNugget = new NuggetItem(getNextItemID(), Materials.uru);

    public static final ItemBattleAxe uruBattleAxe = ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru);

    public static final Item uruIngot = ReflectHelper.createInstance(ItemIngot.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru).setXPReward(150);

    public static final ItemSword uruSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru);

    public static final ItemWarHammer uruWarHammer = ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru);

    public static final ItemMattock uruMattock = ReflectHelper.createInstance(ItemMattock.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru);

    public static final ItemScythe uruScythe = ReflectHelper.createInstance(ItemScythe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru);

    public static final ItemPieces pieceUru = new ItemPieces(getNextItemID(), Materials.orePieces, "pieceAdamantium");

    public static final Item forgingNote = new ItemStandard(getNextItemID(), Materials.paper, "forging_note");

    public static final ItemSeeds seedsBeetroot = new ItemSeeds(getNextItemID(), 1, 1, false, false, false, Blocks.beetroots.blockID, Block.tilledField.blockID, "Beetrootseeds");

    public static final ItemFood beetroot = (new ItemFood(getNextItemID(), Materials.beetroot, 2, 1, 1000, false, false, true, "Beetroot")).setPlantProduct();

    public static final ItemBowl bowlSalmonSoup = (ItemBowl) (new ItemBowl(getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");

    public static final ItemBowl bowlBeetrootSoup = (ItemBowl) (new ItemBowl(getNextItemID(), Materials.beetroot_soup, "beetroot_soup")).setFoodValue(15, 6, 6000, false, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");

    public static final ItemStandard clayBowlRaw = (ItemStandard) (new ItemStandard(getNextItemID(), Material.clay, "bowlclayRaw")).setMaxStackSize(4);

    public static final ItemBowlClay clayBowlEmpty = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), null, "VANILLA")).setMaxStackSize(4);

    public static final ItemBowlClay clayBowlMushroomStew = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.mushroom_stew, "mushroom_stew")).setFoodValue(2, 4, false, false, false).setPlantProduct().setUnlocalizedName("mushroomStew");

    public static final ItemBowlClay clayBowlMilk = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.milk, "bowl_milk")).setFoodValue(0, 1, true, false, false).setAnimalProduct().setAlwaysEdible().setUnlocalizedName("bowlMilk");

    public static final ItemBowlClay clayBowlWater = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.water, "bowl_water")).setUnlocalizedName("bowlWater");

    public static final ItemBowlClay clayBowlBeefStew = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.beef_stew, "beef_stew")).setFoodValue(16, 16, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beefStew");

    public static final ItemBowlClay clayBowlChickenSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.chicken_soup, "chicken_soup")).setFoodValue(10, 10, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("chickenSoup");

    public static final ItemBowlClay clayBowlVegetableSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.vegetable_soup, "vegetable_soup")).setFoodValue(6, 6, false, false, true).setPlantProduct().setUnlocalizedName("vegetableSoup");

    public static final ItemBowlClay clayBowlIceCream = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.ice_cream, "ice_cream")).setFoodValue(5, 4, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("iceCream");

    public static final ItemBowlClay clayBowlSalad = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.salad, "bowl_salad")).setFoodValue(1, 1, false, false, true).setPlantProduct().setUnlocalizedName("salad");

    public static final ItemBowlClay clayBowlCreamOfMushroomSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.cream_of_mushroom_soup, "cream_of_mushroom_soup")).setFoodValue(3, 5, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfMushroomSoup");

    public static final ItemBowlClay clayBowlCreamOfVegetableSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.cream_of_vegetable_soup, "cream_of_vegetable_soup")).setFoodValue(7, 7, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfVegetableSoup");

    public static final ItemBowlClay clayBowlPumpkinSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.pumpkin_soup, "pumpkin_soup")).setFoodValue(1, 2, false, false, true).setPlantProduct().setUnlocalizedName("pumpkinSoup");

    public static final ItemBowlClay clayBowlMashedPotato = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.mashed_potato, "mashed_potato")).setFoodValue(12, 8, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("mashedPotato");

    public static final ItemBowlClay clayBowlSorbet = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.sorbet, "sorbet")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("sorbet");

    public static final ItemBowlClay clayBowlPorridge = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.porridge, "porridge")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("porridge");

    public static final ItemBowlClay clayBowlCereal = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Material.cereal, "cereal")).setFoodValue(4, 2, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("cereal");

    public static final ItemBowlClay clayBowlLemonade = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Materials.lemonade, "lemonade")).setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");

    public static final ItemBowlClay clayBowlPorkchopStew = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");

    public static final ItemBowlClay clayBowlLampchopSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Materials.lampchop_stew, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");

    public static final ItemBowlClay clayBowlWaterSuspicious = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Materials.suspicious_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");

    public static final ItemBowlClay clayBowlWaterSwampland = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");

    public static final ItemBowlClay clayBowlSalmonSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");

    public static final ItemBowlClay clayBowlBeetrootSoup = (ItemBowlClay) (new ItemBowlClay(getNextItemID(), Materials.beetroot_soup, "beetroot_soup")).setFoodValue(15, 6, 6000, false, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");

    public static final Item totemOfFecund = new ItemTotemFecund(getNextItemID());

    public static final ItemArmor helmetCustom_b = new ItemHelmet(getNextItemID(), Materials.custom_b, false);

    public static final ItemArmor chestplateCustom_b = new ItemCuirass(getNextItemID(), Materials.custom_b, false);

    public static final ItemArmor leggingsCustom_b = new ItemLeggings(getNextItemID(), Materials.custom_b, false);

    public static final ItemArmor bootsCustom_b = new ItemBoots(getNextItemID(), Materials.custom_b, false);

    public static final FishingRodItem fishingRodNickel = (FishingRodItem) (new FishingRodItem(getNextItemID(), Materials.nickel)).setUnlocalizedName("fishingRod");

    public static final FishingRodItem fishingRodTungsten = (FishingRodItem) (new FishingRodItem(getNextItemID(), Materials.tungsten)).setUnlocalizedName("fishingRod");

    public static final ItemCarrotOnAStick carrotOnAStickNickel = (ItemCarrotOnAStick) (new ItemCarrotOnAStick(getNextItemID(), Materials.nickel)).setUnlocalizedName("carrotOnAStick");

    public static final ItemCarrotOnAStick carrotOnAStickTungsten = (ItemCarrotOnAStick) (new ItemCarrotOnAStick(getNextItemID(), Materials.tungsten)).setUnlocalizedName("carrotOnAStick");

    public static final ItemPotionSuspicious suspiciousPotion = (ItemPotionSuspicious) (new ItemPotionSuspicious(getNextItemID())).setUnlocalizedName("suspiciousPotion").setCreativeTab(CreativeTabs.tabMisc);

    public static final Item totemOfDestroy = new ItemTotemDestroy(getNextItemID());

    public static final Item totemOfPreserve = new ItemTotemPreserve(getNextItemID());

    public static final Item totemOfKnowledge = new ItemTotemKnowledge(getNextItemID());

    public static final ItemFlintAndSteel ignitionCopper = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.copper).setMaxDamage((int) (2.0F * Material.copper.durability));

    public static final ItemFlintAndSteel ignitionSilver = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.silver).setMaxDamage((int) (2.0F * Material.silver.durability));

    public static final ItemFlintAndSteel ignitionGold = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.gold).setMaxDamage((int) (2.0F * Material.gold.durability));

    public static final ItemFlintAndSteel ignitionNickel = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Materials.nickel).setMaxDamage((int) (2.0F * Materials.nickel.durability));

    public static final ItemFlintAndSteel ignitionTungsten = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Materials.tungsten).setMaxDamage((int) (2.0F * Materials.tungsten.durability));

    public static final ItemFlintAndSteel ignitionMithril = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.mithril).setMaxDamage((int) (2.0F * Material.mithril.durability));

    public static final ItemFlintAndSteel ignitionAncientMetal = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.ancient_metal).setMaxDamage((int) (2.0F * Material.ancient_metal.durability));

    public static final ItemFlintAndSteel ignitionAdamantium = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.adamantium).setMaxDamage((int) (2.0F * Material.adamantium.durability));

    public static final ItemFlintAndSteel ignitionWood = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.wood).setMaxDamage(3 + (int) (2.0F * Material.wood.durability));

    public static final ItemBrewingMisc wither_branch = (new ItemBrewingMisc(getNextItemID(), Material.wood, "wither_wood")).setPotionEffectExtend("+0-1+2+3+13&4-4");

    public static final ItemGuideBook guide = new ItemGuideBook(getNextItemID());

    public static final Item totemOfHunting = new ItemTotemHunting(getNextItemID());

    public static final ItemClub uruMorningStar = ReflectHelper.createInstance(ItemClub.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru);

    public static final ItemPickaxe uruPickaxe = ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.uru);

    public static final ITFRock shardAzurite = (ITFRock) (new ITFRock(getNextItemID(), Materials.crystal, "azurite")).setXPReward(1);

    public static final Item detectorEmerald = new ItemDetector(getNextItemID(), Material.emerald, "emerald").setUnlocalizedName("detector");

    public static final Item detectorDiamond = new ItemDetector(getNextItemID(), Material.diamond, "diamond").setUnlocalizedName("detector");

    public static final Item sulphur = new ItemStandard(getNextItemID(), Materials.sulphur, "sulphur_sphere");

    public static final ItemBow bowUru = new BowItem(getNextItemID(), Materials.uru, 512, 45);

    public static final Item enderRod = new ItemBrewingMisc(getNextItemID(), Material.ender_pearl, "ender_rod").setPotionEffectExtend("+8+9+10+11&4-4+13").setReachBonus(0.5F);

    public static final ItemMorningStar morningStarRustedIron = new ItemMorningStar(getNextItemID(), Material.rusted_iron);

    public static final ItemBucket woodBucket = new ItemBucket(getNextItemID(), Materials.wood, null);

    public static final ItemBucket woodBucketWater = (ItemBucket) new ItemBucket(getNextItemID(), Materials.wood, Materials.water).setContainerItem(woodBucket);

    public static final ItemBucket woodBucketWaterSuspicious = (ItemBucket) new ItemBucket(getNextItemID(), Materials.wood, Materials.suspicious_water).setContainerItem(woodBucket);

    public static final ItemBucket woodBucketWaterDangerous = (ItemBucket) new ItemBucket(getNextItemID(), Materials.wood, Materials.dangerous_water).setContainerItem(woodBucket);

    public static final ItemBucketMilk woodBucketMilk = (ItemBucketMilk) new ItemBucketMilk(getNextItemID(), Materials.wood).setContainerItem(woodBucket);

    public static final ItemHoe hoeFlint = ReflectHelper.createInstance(ItemHoe.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.flint);

    public static final ItemFood peeledSugarcane = (new ItemFood(getNextItemID(), Materials.peeledSugarcane, 0, 1, 1000, false, false, true, "peeledSugarcane")).setPlantProduct();

    public static final Item totemOfSentry = new ItemTotemSentry(getNextItemID());

    public static final Item totemOfUnknown = new ItemTotemUnknown(getNextItemID());

    public static final ItemFlintAndSteel ignitionRustedIron = (ItemFlintAndSteel) new ItemFlintAndSteel(getNextItemID()).setMaterial(Material.flint, Material.rusted_iron).setMaxDamage((int) (2.0F * Material.rusted_iron.durability));

    public static final ItemKnife stickKnife = ReflectHelper.createInstance(ItemKnife.class, new Class[]{int.class, Material.class}, getNextItemID(), Materials.wood);

    public static final ItemWand slimeWand = new ItemWandSlime(getNextItemID());

    public static final Item iceChunk = new ItemStandard(getNextItemID(), Materials.ice_chunk, "ice_chunk").setCraftingDifficultyAsComponent(50.0F);

    public static final ItemArmor iceHelmet = new ItemHelmet(getNextItemID(), Materials.ice_chunk, false);

    public static final ItemArmor iceChestplate = new ItemCuirass(getNextItemID(), Materials.ice_chunk, false);

    public static final ItemArmor iceLeggings = new ItemLeggings(getNextItemID(), Materials.ice_chunk, false);

    public static final ItemArmor iceBoots = new ItemBoots(getNextItemID(), Materials.ice_chunk, false);

    public static final ItemFood ice_sucker = (ItemFood) (new ItemFood(getNextItemID(), Materials.ice_sucker, 1, 0, 1000, false, false, false, "ice_sucker")).setMaxStackSize(8).setAlwaysEdible();

    public static final ItemFood melon_ice = (ItemFood) (new ItemFood(getNextItemID(), Materials.melon_ice, 1, 1, 1000, false, false, true, "melon_ice")).setMaxStackSize(8);

    public static final ItemFood chocolate_smoothie = (ItemFood) (new ItemFood(getNextItemID(), Materials.chocolate_smoothie, 4, 2, 1000, false, false, true, "chocolate_smoothie")).setMaxStackSize(4);

    public static final Item frostRod = new ItemBrewingMisc(getNextItemID(), Materials.frost, "frost_rod").setPotionEffectExtend("+8+9+10+11&4-4+13").setReachBonus(0.5F);

    public static final Item frostPowder = new ItemBrewingMisc(getNextItemID(), Materials.frost, "frost_powder");//.setPotionEffectExtend("+8+9+10-11&4-4+13");

    public static final ItemBowlClay clayBowlHotWater = new ItemBowlClay(getNextItemID(), Materials.water, "clay_bowl_hot_water");

    public static final ItemBowl bowlHotWater = new ItemBowl(getNextItemID(), Materials.water, "bowl_water");

    public static final Item totemOfFlattening = new ItemTotemFlattening(getNextItemID());

    public static final Item lootPackLich = new ItemLootPack(getNextItemID(), Material.leather, "lootPack", ITFLootTables.lichEntity, 6);

    public static final Item minePocket = new ItemMinePocket(getNextItemID(), Material.leather, "pocket");
    public static final ItemKettle leatherKettle = new ItemKettle(getNextItemID(), 13, Material.water, Material.leather);
    public static final ItemKettle leatherKettleSuspicious = new ItemKettle(getNextItemID(), 13, Materials.suspicious_water, Material.leather);
    public static final ItemKettle leatherKettleSwampland = new ItemKettle(getNextItemID(), 13, Materials.dangerous_water, Material.leather);
    public static final ItemStandard clayJug = (ItemStandard) new ItemStandard(getNextItemID(), Material.clay, "clayJug").setMaxStackSize(1);
    public static final ItemKettle hardenedClayJug = new ItemKettle(getNextItemID(), 19, Material.water, Material.hardened_clay);
    public static final ItemKettle hardenedClayJugSuspicious = new ItemKettle(getNextItemID(), 19, Materials.suspicious_water, Material.hardened_clay);
    public static final ItemKettle hardenedClayJugSwampland = new ItemKettle(getNextItemID(), 19, Materials.dangerous_water, Material.hardened_clay);
    public static final ItemWand enderWand = new ItemWandEnder(getNextItemID());
    public static final ItemBossDetector bossDetector = new ItemBossDetector(getNextItemID(), Material.mithril, "boss_detector");

    private static int getNextItemID() {
        if (!ITFConfig.FixedID.getBooleanValue()) return IdUtil.getNextItemID();
        return Constant.nextItemID++;
    }

    static {
        ((ITFItem) totemOfKnowledge).itf$SetExtraInfo("itf.item.totem_of_knowledge.info");
        ((ITFItem) totemOfFlattening).itf$SetExtraInfo("itf.item.totem_of_flattening.info");
        ((ITFItem) frostRod).itf$SetExtraInfo("itf.item.frost_rod.info");
        ((ITFItem) enderRod).itf$SetExtraInfo("itf.item.ender_rod.info");
    }
}
