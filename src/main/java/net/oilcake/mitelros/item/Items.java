package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.api.ItemRockExtend;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.potion.ItemPotionExperimental;
import net.oilcake.mitelros.item.potion.ItemPotionSuspicious;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.util.IdUtil;
import net.xiaoyu233.fml.util.ReflectHelper;

import static net.xiaoyu233.fml.util.ReflectHelper.createInstance;

public class Items extends Item {
    public static final ItemArmor nickelHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.nickel, false);

    public static final ItemArmor nickelHelmetChain = new ItemHelmet(IdUtil.getNextItemID(), Materials.nickel, true);

    public static final ItemArmor nickelChestplateChain = new ItemCuirass(IdUtil.getNextItemID(), Materials.nickel, true);

    public static final ItemArmor nickelLeggingsChain = new ItemLeggings(IdUtil.getNextItemID(), Materials.nickel, true);

    public static final ItemArmor nickelBootsChain = new ItemBoots(IdUtil.getNextItemID(), Materials.nickel, true);

    public static final ItemNugget nickelNugget = ReflectHelper.createInstance(ItemNugget.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemAxe nickelAxe = ReflectHelper.createInstance(ItemAxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemBattleAxe nickelBattleAxe = ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemDagger nickelDagger = ReflectHelper.createInstance(ItemDagger.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final Item nickelIngot = ReflectHelper.createInstance(ItemIngot.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel).setXPReward(15);

    public static final ItemPickaxe nickelPickaxe = ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemShovel nickelShovel = ReflectHelper.createInstance(ItemShovel.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemSword nickelSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemWarHammer nickelWarHammer = ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemHatchet nickelHatchet = ReflectHelper.createInstance(ItemHatchet.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemHoe nickelHoe = ReflectHelper.createInstance(ItemHoe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemKnife nickelKnife = ReflectHelper.createInstance(ItemKnife.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemMattock nickelMattock = ReflectHelper.createInstance(ItemMattock.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemScythe nickelScythe = ReflectHelper.createInstance(ItemScythe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemShears nickelShears = ReflectHelper.createInstance(ItemShears.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final Item doorNickel = new ItemDoor(IdUtil.getNextItemID(), Materials.nickel);

    public static final ItemChain nickelChain = ReflectHelper.createInstance(ItemChain.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemCoin nickelCoin = ReflectHelper.createInstance(ItemCoin.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.nickel);

    public static final ItemArrow arrowNickel = new ItemArrow(IdUtil.getNextItemID(), Materials.nickel);

    public static final ItemArmor tungstenHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.tungsten, false);

    public static final ItemArmor tungstenHelmetChain = new ItemHelmet(IdUtil.getNextItemID(), Materials.tungsten, true);

    public static final ItemArmor tungstenChestplateChain = new ItemCuirass(IdUtil.getNextItemID(), Materials.tungsten, true);

    public static final ItemArmor tungstenLeggingsChain = new ItemLeggings(IdUtil.getNextItemID(), Materials.tungsten, true);

    public static final ItemArmor tungstenBootsChain = new ItemBoots(IdUtil.getNextItemID(), Materials.tungsten, true);

    public static final ItemNugget tungstenNugget = ReflectHelper.createInstance(ItemNugget.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemAxe tungstenAxe = ReflectHelper.createInstance(ItemAxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemBattleAxe tungstenBattleAxe = ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemDagger tungstenDagger = ReflectHelper.createInstance(ItemDagger.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final Item tungstenIngot = ReflectHelper.createInstance(ItemIngot.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten).setXPReward(75);

    public static final ItemPickaxe tungstenPickaxe = ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemShovel tungstenShovel = ReflectHelper.createInstance(ItemShovel.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemSword tungstenSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemWarHammer tungstenWarHammer = ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemHatchet tungstenHatchet = ReflectHelper.createInstance(ItemHatchet.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemHoe tungstenHoe = ReflectHelper.createInstance(ItemHoe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemKnife tungstenKnife = ReflectHelper.createInstance(ItemKnife.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemMattock tungstenMattock = ReflectHelper.createInstance(ItemMattock.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemScythe tungstenScythe = ReflectHelper.createInstance(ItemScythe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemShears tungstenShears = ReflectHelper.createInstance(ItemShears.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final Item doorTungsten = new ItemDoor(IdUtil.getNextItemID(), Materials.tungsten);

    public static final ItemChain tungstenChain = ReflectHelper.createInstance(ItemChain.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemCoin tungstenCoin = ReflectHelper.createInstance(ItemCoin.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.tungsten);

    public static final ItemArrow arrowTungsten = new ItemArrow(IdUtil.getNextItemID(), Materials.tungsten);

    public static final ItemBowl bowlPorkchopStew = (ItemBowl) (new ItemBowl(IdUtil.getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");

    public static final ItemBowl bowlChestnutSoup = (ItemBowl) (new ItemBowl(IdUtil.getNextItemID(), Materials.chestnut_soup, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");

    public static final ItemBowl bowlWaterSuspicious = (ItemBowl) (new ItemBowl(IdUtil.getNextItemID(), Materials.unsafe_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");

    public static final ItemBowl bowlWaterSwampland = (ItemBowl) (new ItemBowl(IdUtil.getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");

    public static final ItemPieces pieceCopper = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceCopper");

    public static final ItemPieces pieceSilver = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceSilver");

    public static final ItemPieces pieceGold = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceGold");

    public static final ItemPieces pieceGoldNether = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceGoldNether");

    public static final ItemPieces pieceIron = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceIron");

    public static final ItemPieces pieceNickel = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceNickel");

    public static final ItemPieces pieceMithril = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceMithril");

    public static final ItemPieces pieceTungsten = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceTungsten");

    public static final ItemPieces pieceAdamantium = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceAdamantium");

    public static final ItemFood mashedCactus = (ItemFood) ((ITFItem) (new ItemFood(IdUtil.getNextItemID(), Materials.mashedCactus, 2, 0, 250, false, true, false, "mashedCactus"))).setWater(1).setMaxStackSize(8);

    public static final ItemFood lemon = (new ItemFood(IdUtil.getNextItemID(), Material.fruit, 2, 1, 1000, false, false, true, "lemon")).setPlantProduct();

    public static final Item lemonPie = (new ItemFood(IdUtil.getNextItemID(), Material.pie, 10, 6, 1000, true, true, true, "lemon_pie")).setMaxStackSize(8).setPlantProduct().setAnimalProduct();

    public static final ItemBucket nickelBucket = new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, null);

    public static final ItemBucket nickelBucketWater = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Material.water)).setContainerItem(nickelBucket);

    public static final ItemBucket nickelBucketLava = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Material.lava)).setContainerItem(nickelBucket);

    public static final ItemBucket nickelBucketStone = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Material.stone)).setContainerItem(nickelBucket);

    public static final ItemBucketMilk nickelBucketMilk = (ItemBucketMilk) (new ItemBucketMilk(IdUtil.getNextItemID(), Materials.nickel)).setContainerItem(nickelBucket);

    public static final ItemBucket tungstenBucket = new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, null);

    public static final ItemBucket tungstenBucketWater = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Material.water)).setContainerItem(tungstenBucket);

    public static final ItemBucket tungstenBucketLava = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Material.lava)).setContainerItem(tungstenBucket);

    public static final ItemBucket tungstenBucketStone = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Material.stone)).setContainerItem(tungstenBucket);

    public static final ItemBucketMilk tungstenBucketMilk = (ItemBucketMilk) (new ItemBucketMilk(IdUtil.getNextItemID(), Materials.tungsten)).setContainerItem(tungstenBucket);

    public static final ItemBucket copperBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.copper, Materials.unsafe_water)).setContainerItem(bucketCopperEmpty);

    public static final ItemBucket silverBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.silver, Materials.unsafe_water)).setContainerItem(bucketSilverEmpty);

    public static final ItemBucket goldBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.gold, Materials.unsafe_water)).setContainerItem(bucketGoldEmpty);

    public static final ItemBucket ironBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.iron, Materials.unsafe_water)).setContainerItem(bucketIronEmpty);

    public static final ItemBucket nickelBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Materials.unsafe_water)).setContainerItem(nickelBucket);

    public static final ItemBucket ancientmetalBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.ancient_metal, Materials.unsafe_water)).setContainerItem(bucketAncientMetalEmpty);

    public static final ItemBucket mithrilBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.mithril, Materials.unsafe_water)).setContainerItem(bucketMithrilEmpty);

    public static final ItemBucket tungstenBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Materials.unsafe_water)).setContainerItem(tungstenBucket);

    public static final ItemBucket adamantiumBucketWaterSuspicious = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.adamantium, Materials.unsafe_water)).setContainerItem(bucketAdamantiumEmpty);

    public static final ItemBucket copperBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.copper, Materials.dangerous_water)).setContainerItem(bucketCopperEmpty);

    public static final ItemBucket silverBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.silver, Materials.dangerous_water)).setContainerItem(bucketSilverEmpty);

    public static final ItemBucket goldBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.gold, Materials.dangerous_water)).setContainerItem(bucketGoldEmpty);

    public static final ItemBucket ironBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.iron, Materials.dangerous_water)).setContainerItem(bucketIronEmpty);

    public static final ItemBucket nickelBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Materials.dangerous_water)).setContainerItem(nickelBucket);

    public static final ItemBucket ancientmetalBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.ancient_metal, Materials.dangerous_water)).setContainerItem(bucketAncientMetalEmpty);

    public static final ItemBucket mithrilBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.mithril, Materials.dangerous_water)).setContainerItem(bucketMithrilEmpty);

    public static final ItemBucket tungstenBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Materials.dangerous_water)).setContainerItem(tungstenBucket);

    public static final ItemBucket adamantiumBucketWaterDangerous = (ItemBucket) (new ItemBucket(IdUtil.getNextItemID(), Material.adamantium, Materials.dangerous_water)).setContainerItem(bucketAdamantiumEmpty);

    public static final Item wolf_fur = ReflectHelper.createInstance(ItemNugget.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.wolf_fur).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("small_leather").setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);

    public static final Item horse_meat = new ItemMeat(IdUtil.getNextItemID(), 6, 6, true, false, "horse_meat");

    public static final Item horse_meat_cooked = new ItemMeat(IdUtil.getNextItemID(), 12, 12, true, true, "horse_meat_cooked");

    public static final ItemArmor wolfHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.wolf_fur, false);

    public static final ItemArmor wolfChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.wolf_fur, false);

    public static final ItemArmor wolfLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.wolf_fur, false);

    public static final ItemArmor wolfBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.wolf_fur, false);

    public static final ItemAppleGold goldenApple = (ItemAppleGold) (new ItemAppleGold(66, 2, 1, "VANILLA")).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 30, 0, 1.0F).setUnlocalizedName("appleGold").useVanillaTexture("apple_golden");

    public static final Item goldenAppleLegend = (new ItemGoldenAppleLegend(IdUtil.getNextItemID(), 2, 1, "goldapple")).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 30, 4, 1.0F).setUnlocalizedName("wtfk").useVanillaTexture("apple_golden_legend");

    public static final ItemBowl bowlLemonade = (ItemBowl) (new ItemBowl(IdUtil.getNextItemID(), Materials.lemonade, "lemonade")).setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");

    public static final ItemBowl bowlEmpty = (ItemBowl) (new ItemBowl(25, null, "VANILLA")).setUnlocalizedName("bowl").useVanillaTexture("bowl").setMaxStackSize(4);

    public static final ItemMorningStar morningStarCopper = new ItemMorningStar(IdUtil.getNextItemID(), Material.copper);

    public static final ItemMorningStar morningStarSilver = new ItemMorningStar(IdUtil.getNextItemID(), Material.silver);

    public static final ItemMorningStar morningStarGold = new ItemMorningStar(IdUtil.getNextItemID(), Material.gold);

    public static final ItemMorningStar morningStarIron = new ItemMorningStar(IdUtil.getNextItemID(), Material.iron);

    public static final ItemMorningStar morningStarNickel = new ItemMorningStar(IdUtil.getNextItemID(), Materials.nickel);

    public static final ItemMorningStar morningStarAncientMetal = new ItemMorningStar(IdUtil.getNextItemID(), Material.ancient_metal);

    public static final ItemMorningStar morningStarMithril = new ItemMorningStar(IdUtil.getNextItemID(), Material.mithril);

    public static final ItemMorningStar morningStarTungsten = new ItemMorningStar(IdUtil.getNextItemID(), Materials.tungsten);

    public static final ItemMorningStar morningStarAdamantium = new ItemMorningStar(IdUtil.getNextItemID(), Material.adamantium);

    public static final Item fragStalkerCreeper = new ItemStandard(IdUtil.getNextItemID(), Material.frags, "fragStalkerCreeper");

    public static final ItemFood glowberries = (ItemFood) (new ItemFood(IdUtil.getNextItemID(), Materials.glowberries, 1, 0, false, false, false, "glow_berries")).setMaxStackSize(16).setAlwaysEdible();

    public static final ItemArrow arrowMagical = new ItemArrow(IdUtil.getNextItemID(), Materials.magical);

    public static final ItemWand lavaWand = new ItemWand(IdUtil.getNextItemID(), Materials.tungsten);

    public static final ItemWand freezeWand = new ItemWand(IdUtil.getNextItemID(), Materials.nickel);

    public static final ItemWand shockWand = new ItemWand(IdUtil.getNextItemID(), Material.ancient_metal);

    public static final Item experimentalPotion = (new ItemPotionExperimental(IdUtil.getNextItemID())).setUnlocalizedName("experimentalPotion").setCreativeTab(CreativeTabs.tabMisc);

    public static final ItemShardAT shardDiamond = (ItemShardAT) (new ItemShardAT(862, Material.diamond)).setUnlocalizedName("shardDiamond").setXPReward(4);

    public static final ItemShardAT shardEmerald = (ItemShardAT) (new ItemShardAT(861, Material.emerald)).setUnlocalizedName("shardEmerald").setXPReward(3);

    public static final ItemShardAT shardNetherQuartz = (ItemShardAT) (new ItemShardAT(863, Material.quartz)).setUnlocalizedName("shardNetherQuartz").setXPReward(2);

    public static final ItemRecordExtend recordDamnation = (ItemRecordExtend) (new ItemRecordExtend(2024, "imported.damnation", "record_damnation", "Damnation", "Mwk feat. Hatsune Miku")).setUnlocalizedName("record");

    public static final ItemRecordExtend recordConnected = (ItemRecordExtend) (new ItemRecordExtend(2025, "imported.connected", "record_connected", "Connected", "Mwk feat. Hatsune Miku")).setUnlocalizedName("record");

    public static final ItemArmor VibraniumHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.vibranium, false);

    public static final ItemArmor VibraniumChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.vibranium, false);

    public static final ItemArmor VibraniumLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.vibranium, false);

    public static final ItemArmor VibraniumBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.vibranium, false);

    public static final ItemSword VibraniumSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.vibranium);

    public static final ItemArmor helmetCustom_a = new ItemHelmet(IdUtil.getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor chestplateCustom_a = new ItemCuirass(IdUtil.getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor leggingsCustom_a = new ItemLeggings(IdUtil.getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor bootsCustom_a = new ItemBoots(IdUtil.getNextItemID(), Materials.custom_a, false);

    public static final ItemArmor HelmetAncientmetalsacred = new ItemHelmet(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final ItemArmor ChestplateAncientmetalsacred = new ItemCuirass(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final ItemArmor LeggingsAncientmetalsacred = new ItemLeggings(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final ItemArmor BootsAncientmetalsacred = new ItemBoots(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);

    public static final Item AncientmetalArmorPiece = ReflectHelper.createInstance(ItemNugget.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.ancient_metal_sacred).setCraftingDifficultyAsComponent(800.0F).setUnlocalizedName("ancient_metal_sacred_piece").setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);

    public static final ItemFood Agave = (ItemFood) (new ItemFood(IdUtil.getNextItemID(), Materials.agave, 1, 0, false, false, false, "agave")).setMaxStackSize(16).setAlwaysEdible();

    public static final Item Pulque = (new ItemWine(IdUtil.getNextItemID())).setUnlocalizedName("pulque").setCreativeTab(CreativeTabs.tabFood);

    public static final Item Ale = (new ItemWine(IdUtil.getNextItemID())).setUnlocalizedName("ale").setCreativeTab(CreativeTabs.tabFood);

    public static final ItemBow bowTungsten = new ItemBow(IdUtil.getNextItemID(), Materials.tungsten);

    public static final ItemArmor UruHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.uru, false);

    public static final ItemArmor UruChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.uru, false);

    public static final ItemArmor UruLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.uru, false);

    public static final ItemArmor UruBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.uru, false);

    public static final ItemNugget UruNugget = ReflectHelper.createInstance(ItemNugget.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final ItemBattleAxe UruBattleAxe = ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final Item UruIngot = ReflectHelper.createInstance(ItemIngot.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru).setXPReward(150);

    public static final ItemSword UruSword = ReflectHelper.createInstance(ItemSword.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final ItemWarHammer UruWarHammer = ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final ItemMattock UruMattock = ReflectHelper.createInstance(ItemMattock.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final ItemScythe UruScythe = ReflectHelper.createInstance(ItemScythe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final ItemPieces pieceUru = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceAdamantium");

    public static final Item forgingnote = new ItemStandard(IdUtil.getNextItemID(), Materials.paper, "forging_note");

    public static final ItemSeeds seedsBeetroot = new ItemSeeds(IdUtil.getNextItemID(), 1, 1, false, false, false, Blocks.beetroots.blockID, Block.tilledField.blockID, "Beetrootseeds");

    public static final ItemFood beetroot = (new ItemFood(IdUtil.getNextItemID(), Materials.beetroot, 2, 1, 1000, false, false, true, "Beetroot")).setPlantProduct();

    public static final ItemBowl bowlSalmonSoup = (ItemBowl) (new ItemBowl(IdUtil.getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");

    public static final ItemBowl bowlBeetrootSoup = (ItemBowl) (new ItemBowl(IdUtil.getNextItemID(), Materials.beetroot, "beetroot_soup")).setFoodValue(15, 6, 6000, false, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");

    public static final ItemStandard claybowlRaw = (ItemStandard) (new ItemStandard(IdUtil.getNextItemID(), Material.clay, "bowlclayRaw")).setMaxStackSize(4);

    public static final ItemBowlClay claybowlEmpty = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), null, "VANILLA")).setUnlocalizedName("bowlclay").useVanillaTexture("bowlclay").setMaxStackSize(4);

    public static final ItemBowlClay claybowlMushroomStew = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.mushroom_stew, "mushroom_stew")).setFoodValue(2, 4, false, false, false).setPlantProduct().setUnlocalizedName("mushroomStew");

    public static final ItemBowlClay claybowlMilk = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.milk, "bowl_milk")).setFoodValue(0, 1, true, false, false).setAnimalProduct().setContainerItem(claybowlEmpty).setAlwaysEdible().setUnlocalizedName("bowlMilk");

    public static final ItemBowlClay claybowlWater = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.water, "bowl_water")).setContainerItem(claybowlEmpty).setUnlocalizedName("bowlWater");

    public static final ItemBowlClay claybowlBeefStew = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.beef_stew, "beef_stew")).setFoodValue(16, 16, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beefStew");

    public static final ItemBowlClay claybowlChickenSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.chicken_soup, "chicken_soup")).setFoodValue(10, 10, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("chickenSoup");

    public static final ItemBowlClay claybowlVegetableSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.vegetable_soup, "vegetable_soup")).setFoodValue(6, 6, false, false, true).setPlantProduct().setUnlocalizedName("vegetableSoup");

    public static final ItemBowlClay claybowlIceCream = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.ice_cream, "ice_cream")).setFoodValue(5, 4, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("iceCream");

    public static final ItemBowlClay claybowlSalad = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.salad, "bowl_salad")).setFoodValue(1, 1, false, false, true).setPlantProduct().setUnlocalizedName("salad");

    public static final ItemBowlClay claybowlCreamOfMushroomSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.cream_of_mushroom_soup, "cream_of_mushroom_soup")).setFoodValue(3, 5, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfMushroomSoup");

    public static final ItemBowlClay claybowlCreamOfVegetableSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.cream_of_vegetable_soup, "cream_of_vegetable_soup")).setFoodValue(7, 7, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfVegetableSoup");

    public static final ItemBowlClay claybowlPumpkinSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.pumpkin_soup, "pumpkin_soup")).setFoodValue(1, 2, false, false, true).setPlantProduct().setUnlocalizedName("pumpkinSoup");

    public static final ItemBowlClay claybowlMashedPotato = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.mashed_potato, "mashed_potato")).setFoodValue(12, 8, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("mashedPotato");

    public static final ItemBowlClay claybowlSorbet = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.sorbet, "sorbet")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("sorbet");

    public static final ItemBowlClay claybowlPorridge = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.porridge, "porridge")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("porridge");

    public static final ItemBowlClay claybowlCereal = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Material.cereal, "cereal")).setFoodValue(4, 2, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("cereal");

    public static final ItemBowlClay claybowlLemonade = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Materials.lemonade, "lemonade")).setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");

    public static final ItemBowlClay claybowlPorkchopStew = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");

    public static final ItemBowlClay claybowlChestnutSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Materials.chestnut_soup, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");

    public static final ItemBowlClay claybowlWaterSuspicious = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Materials.unsafe_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");

    public static final ItemBowlClay claybowlWaterSwampland = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");

    public static final ItemBowlClay claybowlSalmonSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");

    public static final ItemBowlClay claybowlBeetrootSoup = (ItemBowlClay) (new ItemBowlClay(IdUtil.getNextItemID(), Materials.beetroot, "beetroot_soup")).setFoodValue(15, 6, 6000, false, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");

    public static final Item totemOfFecund = (new ItemTotem(IdUtil.getNextItemID(), Material.gold, "totem")).setMaxStackSize(1);

    public static final ItemArmor helmetCustom_b = new ItemHelmet(IdUtil.getNextItemID(), Materials.custom_b, false);

    public static final ItemArmor chestplateCustom_b = new ItemCuirass(IdUtil.getNextItemID(), Materials.custom_b, false);

    public static final ItemArmor leggingsCustom_b = new ItemLeggings(IdUtil.getNextItemID(), Materials.custom_b, false);

    public static final ItemArmor bootsCustom_b = new ItemBoots(IdUtil.getNextItemID(), Materials.custom_b, false);

    public static final ItemFishingRod fishingRodNickel = (ItemFishingRod) (new ItemFishingRod(IdUtil.getNextItemID(), Materials.nickel)).setUnlocalizedName("fishingRod");

    public static final ItemFishingRod fishingRodTungsten = (ItemFishingRod) (new ItemFishingRod(IdUtil.getNextItemID(), Materials.tungsten)).setUnlocalizedName("fishingRod");

    public static final ItemCarrotOnAStick carrotOnAStickNickel = (ItemCarrotOnAStick) (new ItemCarrotOnAStick(IdUtil.getNextItemID(), Materials.nickel)).setUnlocalizedName("carrotOnAStick");

    public static final ItemCarrotOnAStick carrotOnAStickTungsten = (ItemCarrotOnAStick) (new ItemCarrotOnAStick(IdUtil.getNextItemID(), Materials.tungsten)).setUnlocalizedName("carrotOnAStick");

    public static final ItemPotionSuspicious suspiciousPotion = (ItemPotionSuspicious) (new ItemPotionSuspicious(IdUtil.getNextItemID())).setUnlocalizedName("suspiciousPotion").setCreativeTab(CreativeTabs.tabMisc);

    public static final Item totemOfDestroy = (new ItemTotem(IdUtil.getNextItemID(), Materials.tungsten, "totem")).setMaxStackSize(1);

    public static final Item totemOfPreserve = (new ItemTotem(IdUtil.getNextItemID(), Material.iron, "totem")).setMaxStackSize(1);

    public static final Item totemOfKnowledge = ((ITFItem) (new ItemTotem(IdUtil.getNextItemID(), Material.ancient_metal, "totem")).setMaxStackSize(1)).setExtraInfo("右键提升20%经验");

    public static final ItemIgnition ignitionCopper = new ItemIgnition(IdUtil.getNextItemID(), Material.copper);

    public static final ItemIgnition ignitionSilver = new ItemIgnition(IdUtil.getNextItemID(), Material.silver);

    public static final ItemIgnition ignitionGold = new ItemIgnition(IdUtil.getNextItemID(), Material.gold);

    public static final ItemIgnition ignitionIron = new ItemIgnition(3, Material.iron);

    public static final ItemIgnition ignitionNickel = new ItemIgnition(IdUtil.getNextItemID(), Materials.nickel);

    public static final ItemIgnition ignitionTungsten = new ItemIgnition(IdUtil.getNextItemID(), Materials.tungsten);

    public static final ItemIgnition ignitionMithril = new ItemIgnition(IdUtil.getNextItemID(), Material.mithril);

    public static final ItemIgnition ignitionAncientMetal = new ItemIgnition(IdUtil.getNextItemID(), Material.ancient_metal);

    public static final ItemIgnition ignitionAdamantium = new ItemIgnition(IdUtil.getNextItemID(), Material.adamantium);

    public static final ItemIgnition ignitionWood = new ItemIgnition(IdUtil.getNextItemID(), Material.wood);

    public static final ItemBrewingMisc wither_branch = (new ItemBrewingMisc(IdUtil.getNextItemID(), Material.wood, "wither_wood")).setPotionEffectExtend("+0-1+2+3+13&4-4");

    public static final ItemGuideBook guide = new ItemGuideBook(IdUtil.getNextItemID());

    public static final Item totemOfHunting = (new ItemTotem(IdUtil.getNextItemID(), Materials.nickel, "totem")).setMaxStackSize(1);

    public static final ItemClub UruMorningStar = ReflectHelper.createInstance(ItemClub.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final ItemPickaxe UruPickaxe = ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.uru);

    public static final ItemRockExtend shardAzurite = (ItemRockExtend) (new ItemRockExtend(IdUtil.getNextItemID(), Materials.crystal, "azurite")).setXPReward(1);

    public static final Item detectorEmerald = new ItemDetector(IdUtil.getNextItemID(), Material.emerald, "emerald").setUnlocalizedName("detector");

    public static final Item detectorDiamond = new ItemDetector(IdUtil.getNextItemID(), Material.diamond, "diamond").setUnlocalizedName("detector");

    public static final Item sulphur = new ItemStandard(IdUtil.getNextItemID(), Materials.sulphur, "sulphur_sphere");

    public static final ItemBow bowUru = new ItemBow(IdUtil.getNextItemID(), Materials.uru);

    public static final Item enderRod = ((ITFItem) (new ItemBrewingMisc(IdUtil.getNextItemID(), Material.ender_pearl, "enderRod")).setPotionEffectExtend("+0+1-2+3+13&4-4").setReachBonus(0.5F)).setExtraInfo("似乎能提升触及距离, 还能酿药?");

    public static final ItemMorningStar morningStarRustedIron = new ItemMorningStar(IdUtil.getNextItemID(), Material.rusted_iron);

    public static final ItemBucket woodBucket = (ItemBucket) new ItemBucket(IdUtil.getNextItemID(), Materials.wood, null);

    public static final ItemBucket woodBucketWater = (ItemBucket) new ItemBucket(IdUtil.getNextItemID(), Materials.wood, Materials.water).setContainerItem(woodBucket);

    public static final ItemBucket woodBucketWaterSuspicious = (ItemBucket) new ItemBucket(IdUtil.getNextItemID(), Materials.wood, Materials.unsafe_water).setContainerItem(woodBucket);

    public static final ItemBucket woodBucketWaterDangerous = (ItemBucket) new ItemBucket(IdUtil.getNextItemID(), Materials.wood, Materials.dangerous_water).setContainerItem(woodBucket);

    public static final ItemBucketMilk woodBucketMilk = (ItemBucketMilk) new ItemBucketMilk(IdUtil.getNextItemID(), Materials.wood).setContainerItem(woodBucket);

    public static final ItemHoe hoeFlint = createInstance(ItemHoe.class, new Class[]{int.class, Material.class}, IdUtil.getNextItemID(), Materials.flint);

    public static final ItemFood peeledSugarcane = (new ItemFood(IdUtil.getNextItemID(), Materials.peeledSugarcane, 0, 1, 1000, false, false, true, "peeledSugarcane")).setPlantProduct();

    public static final Item totemOfSentry = (new ItemTotem(IdUtil.getNextItemID(), Material.adamantium, "totem")).setMaxStackSize(1);

    public static final Item totemOfUnknown = (new ItemTotem(IdUtil.getNextItemID(), Material.rusted_iron, "totem")).setMaxStackSize(1);

    public static final ItemIgnition ignitionRustedIron = new ItemIgnition(IdUtil.getNextItemID(), Material.rusted_iron);

    public static final ItemKnife stickKnife = ReflectHelper.createInstance(ItemKnife.class, new Class[]{int.class, Material.class}, Integer.valueOf(IdUtil.getNextItemID()), Materials.wood);

    public static final ItemWand slimeWand = new ItemWand(IdUtil.getNextItemID(), Material.copper);


    public static void registerItems(ItemRegistryEvent event) {
        event.register("armor/nickel_helmet", nickelHelmet);
        event.register("armor/nickel_chestplate", nickelChestplate);
        event.register("armor/nickel_leggings", nickelLeggings);
        event.register("armor/nickel_boots", nickelBoots);
        event.register("armor/nickel_chainmail_helmet", nickelHelmetChain);
        event.register("armor/nickel_chainmail_chestplate", nickelChestplateChain);
        event.register("armor/nickel_chainmail_leggings", nickelLeggingsChain);
        event.register("armor/nickel_chainmail_boots", nickelBootsChain);
        event.register("ingots/nickel", nickelIngot);
        event.register("nuggets/nickel", nickelNugget);
        event.register("tools/nickel_axe", nickelAxe);
        event.register("tools/nickel_battle_axe", nickelBattleAxe);
        event.register("tools/nickel_dagger", nickelDagger);
        event.register("tools/nickel_hatchet", nickelHatchet);
        event.register("tools/nickel_hoe", nickelHoe);
        event.register("tools/nickel_knife", nickelKnife);
        event.register("tools/nickel_pickaxe", nickelPickaxe);
        event.register("tools/nickel_mattock", nickelMattock);
        event.register("tools/nickel_scythe", nickelScythe);
        event.register("tools/nickel_shears", nickelShears);
        event.register("tools/nickel_shovel", nickelShovel);
        event.register("tools/nickel_sword", nickelSword);
        event.register("tools/nickel_war_hammer", nickelWarHammer);
        event.register("doors/nickel", doorNickel);
        event.register("chain/nickel", nickelChain);
        event.register("coins/nickel", nickelCoin);
        event.register("arrows/nickel_arrow", arrowNickel);
        event.register("armor/tungsten_helmet", tungstenHelmet);
        event.register("armor/tungsten_chestplate", tungstenChestplate);
        event.register("armor/tungsten_leggings", tungstenLeggings);
        event.register("armor/tungsten_boots", tungstenBoots);
        event.register("armor/tungsten_chainmail_helmet", tungstenHelmetChain);
        event.register("armor/tungsten_chainmail_chestplate", tungstenChestplateChain);
        event.register("armor/tungsten_chainmail_leggings", tungstenLeggingsChain);
        event.register("armor/tungsten_chainmail_boots", tungstenBootsChain);
        event.register("ingots/tungsten", tungstenIngot);
        event.register("nuggets/tungsten", tungstenNugget);
        event.register("tools/tungsten_axe", tungstenAxe);
        event.register("tools/tungsten_battle_axe", tungstenBattleAxe);
        event.register("tools/tungsten_dagger", tungstenDagger);
        event.register("tools/tungsten_hatchet", tungstenHatchet);
        event.register("tools/tungsten_hoe", tungstenHoe);
        event.register("tools/tungsten_knife", tungstenKnife);
        event.register("tools/tungsten_pickaxe", tungstenPickaxe);
        event.register("tools/tungsten_mattock", tungstenMattock);
        event.register("tools/tungsten_scythe", tungstenScythe);
        event.register("tools/tungsten_shears", tungstenShears);
        event.register("tools/tungsten_shovel", tungstenShovel);
        event.register("tools/tungsten_sword", tungstenSword);
        event.register("tools/tungsten_war_hammer", tungstenWarHammer);
        event.register("doors/tungsten", doorTungsten);
        event.register("chain/tungsten", tungstenChain);
        event.register("coins/tungsten", tungstenCoin);
        event.register("arrows/tungsten_arrow", arrowTungsten);
        event.register("bowls/porkchop_stew", bowlPorkchopStew);
        event.register("bowls/lampchop_stew", bowlChestnutSoup);
        event.register("bowls/salmon_soup", bowlSalmonSoup);
        event.register("bowls/beetroot_soup", bowlBeetrootSoup);
        event.register("pieces/copper", pieceCopper);
        event.register("pieces/silver", pieceSilver);
        event.register("pieces/gold", pieceGold);
        event.register("pieces/gold_nether", pieceGoldNether);
        event.register("pieces/iron", pieceIron);
        event.register("pieces/nickel", pieceNickel);
        event.register("pieces/tungsten", pieceTungsten);
        event.register("pieces/mithril", pieceMithril);
        event.register("pieces/adamantium", pieceAdamantium);
        event.register("food/mashed_cactus", mashedCactus);
        event.register("food/lemon", lemon);
        event.register("food/lemon_pie", lemonPie);
        event.register("bowls/lemonade", bowlLemonade);
        event.register("buckets/nickel/empty", nickelBucket);
        event.register("buckets/nickel/lava", nickelBucketLava);
        event.register("buckets/nickel/milk", nickelBucketMilk);
        event.register("buckets/nickel/stone", nickelBucketStone);
        event.register("buckets/nickel/water", nickelBucketWater);
        event.register("buckets/tungsten/empty", tungstenBucket);
        event.register("buckets/tungsten/lava", tungstenBucketLava);
        event.register("buckets/tungsten/milk", tungstenBucketMilk);
        event.register("buckets/tungsten/stone", tungstenBucketStone);
        event.register("buckets/tungsten/water", tungstenBucketWater);
        event.register("bowls/bowl_water_suspicious", bowlWaterSuspicious);
        event.register("bowls/bowl_water_swampland", bowlWaterSwampland);
        event.register("buckets/copper/water_suspicious", copperBucketWaterSuspicious);
        event.register("buckets/silver/water_suspicious", silverBucketWaterSuspicious);
        event.register("buckets/gold/water_suspicious", goldBucketWaterSuspicious);
        event.register("buckets/iron/water_suspicious", ironBucketWaterSuspicious);
        event.register("buckets/nickel/water_suspicious", nickelBucketWaterSuspicious);
        event.register("buckets/mithril/water_suspicious", mithrilBucketWaterSuspicious);
        event.register("buckets/tungsten/water_suspicious", tungstenBucketWaterSuspicious);
        event.register("buckets/adamantium/water_suspicious", adamantiumBucketWaterSuspicious);
        event.register("buckets/ancient_metal/water_suspicious", ancientmetalBucketWaterSuspicious);
        event.register("buckets/copper/water_swampland", copperBucketWaterDangerous);
        event.register("buckets/silver/water_swampland", silverBucketWaterDangerous);
        event.register("buckets/gold/water_swampland", goldBucketWaterDangerous);
        event.register("buckets/iron/water_swampland", ironBucketWaterDangerous);
        event.register("buckets/nickel/water_swampland", nickelBucketWaterDangerous);
        event.register("buckets/mithril/water_swampland", mithrilBucketWaterDangerous);
        event.register("buckets/tungsten/water_swampland", tungstenBucketWaterDangerous);
        event.register("buckets/adamantium/water_swampland", adamantiumBucketWaterDangerous);
        event.register("buckets/ancient_metal/water_swampland", ancientmetalBucketWaterDangerous);
        event.register("wolf_fur", wolf_fur);
        event.register("food/horse_meat", horse_meat);
        event.register("food/horse_meat_cooked", horse_meat_cooked);
        event.register("armor/wolf_helmet", wolfHelmet);
        event.register("armor/wolf_jacket", wolfChestplate);
        event.register("armor/wolf_leggings", wolfLeggings);
        event.register("armor/wolf_boots", wolfBoots);
        event.register("apple_golden", goldenApple);
        event.register("apple_golden", goldenAppleLegend);
        event.register("bowl", bowlEmpty);
        event.register("tools/copper_club", morningStarCopper);
        event.register("tools/silver_club", morningStarSilver);
        event.register("tools/gold_club", morningStarGold);
        event.register("tools/iron_club", morningStarIron);
        event.register("tools/nickel_club", morningStarNickel);
        event.register("tools/ancient_metal_club", morningStarAncientMetal);
        event.register("tools/mithril_club", morningStarMithril);
        event.register("tools/tungsten_club", morningStarTungsten);
        event.register("tools/adamantium_club", morningStarAdamantium);
        event.register("frag/stalker_creeper", fragStalkerCreeper);
        event.register("food/glow_berries", glowberries);
        event.register("arrows/magical_arrow", arrowMagical);
        event.register("wand/lava", lavaWand);
        event.register("wand/ice", freezeWand);
        event.register("wand/thunder", shockWand);
        event.register("potion/suspicious_potion", suspiciousPotion);
        event.register("potion/experimental_potion", experimentalPotion);
        event.register("shards/diamond", shardDiamond);
        event.register("shards/emerald", shardEmerald);
        event.register("shards/quartz", shardNetherQuartz);
        event.register("azurite", shardAzurite);
        event.register("records/record_damnation", recordDamnation);
        event.register("records/record_connected", recordConnected);
        event.register("tools/vibranium_sword", VibraniumSword);
        event.register("armor/vibranium_helmet", VibraniumHelmet);
        event.register("armor/vibranium_chestplate", VibraniumChestplate);
        event.register("armor/vibranium_leggings", VibraniumLeggings);
        event.register("armor/vibranium_boots", VibraniumBoots);
        event.register("armor/null_helmet", helmetCustom_a);
        event.register("armor/null_chestplate", chestplateCustom_a);
        event.register("armor/null_leggings", leggingsCustom_a);
        event.register("armor/null_boots", bootsCustom_a);
        event.register("armor/null_helmet", helmetCustom_b);
        event.register("armor/null_chestplate", chestplateCustom_b);
        event.register("armor/null_leggings", leggingsCustom_b);
        event.register("armor/null_boots", bootsCustom_b);
        event.register("armor/ancient_metal_sacred_helmet", HelmetAncientmetalsacred);
        event.register("armor/ancient_metal_sacred_chestplate", ChestplateAncientmetalsacred);
        event.register("armor/ancient_metal_sacred_leggings", LeggingsAncientmetalsacred);
        event.register("armor/ancient_metal_sacred_boots", BootsAncientmetalsacred);
        event.register("ancient_metal_armor_piece", AncientmetalArmorPiece);
        event.register("food/agave", Agave);
        event.register("pulque", Pulque);
        event.register("ale", Ale);
        event.register("armor/uru_helmet", UruHelmet);
        event.register("armor/uru_chestplate", UruChestplate);
        event.register("armor/uru_leggings", UruLeggings);
        event.register("armor/uru_boots", UruBoots);
        event.register("forging_note", forgingnote);
        event.register("ingots/uru", UruIngot);
        event.register("nuggets/uru", UruNugget);
        event.register("tools/uru_battle_axe", UruBattleAxe);
        event.register("tools/uru_mattock", UruMattock);
        event.register("tools/uru_scythe", UruScythe);
        event.register("tools/uru_sword", UruSword);
        event.register("tools/uru_war_hammer", UruWarHammer);
        event.register("tools/uru_club", UruMorningStar);
        event.register("tools/uru_pickaxe", UruPickaxe);
        event.register("pieces/uru", pieceUru);
        event.register("bows/tungsten/", bowTungsten).setUnlocalizedName("tungsten_bow");
        event.register("food/beetroot", beetroot);
        event.register("food/beetroot_seeds", seedsBeetroot);
        event.register("hardened_clay_bowls/raw", claybowlRaw);
        event.register("hardened_clay_bowls/beef_stew", claybowlBeefStew);
        event.register("hardened_clay_bowls/beetroot_soup", claybowlBeetrootSoup);
        event.register("hardened_clay_bowls/bowl_milk", claybowlMilk);
        event.register("hardened_clay_bowls/bowl_salad", claybowlSalad);
        event.register("hardened_clay_bowls/bowl_water", claybowlWater);
        event.register("hardened_clay_bowls/bowl_water_suspicious", claybowlWaterSuspicious);
        event.register("hardened_clay_bowls/bowl_water_swampland", claybowlWaterSwampland);
        event.register("hardened_clay_bowls/cereal", claybowlCereal);
        event.register("hardened_clay_bowls/chicken_soup", claybowlChickenSoup);
        event.register("hardened_clay_bowls/cream_of_mushroom_soup", claybowlCreamOfMushroomSoup);
        event.register("hardened_clay_bowls/cream_of_vegetable_soup", claybowlCreamOfVegetableSoup);
        event.register("hardened_clay_bowls/empty", claybowlEmpty);
        event.register("hardened_clay_bowls/ice_cream", claybowlIceCream);
        event.register("hardened_clay_bowls/lampchop_stew", claybowlChestnutSoup);
        event.register("hardened_clay_bowls/lemonade", claybowlLemonade);
        event.register("hardened_clay_bowls/mashed_potato", claybowlMashedPotato);
        event.register("hardened_clay_bowls/mushroom_stew", claybowlMushroomStew);
        event.register("hardened_clay_bowls/porkchop_stew", claybowlPorkchopStew);
        event.register("hardened_clay_bowls/porridge", claybowlPorridge);
        event.register("hardened_clay_bowls/pumpkin_soup", claybowlPumpkinSoup);
        event.register("hardened_clay_bowls/salmon_soup", claybowlSalmonSoup);
        event.register("hardened_clay_bowls/sorbet", claybowlSorbet);
        event.register("hardened_clay_bowls/vegetable_soup", claybowlVegetableSoup);
        event.register("totem/totem_of_fecund", totemOfFecund);
        event.register("totem/totem_of_destroy", totemOfDestroy);
        event.register("totem/totem_of_knowledge", totemOfKnowledge);
        event.register("totem/totem_of_preserve", totemOfPreserve);
        event.register("totem/totem_of_hunting", totemOfHunting);
        event.register("ignition/wood", ignitionWood);
        event.register("ignition/copper", ignitionCopper);
        event.register("ignition/silver", ignitionSilver);
        event.register("ignition/gold", ignitionGold);
        event.register("ignition/iron", ignitionIron);
        event.register("ignition/nickel", ignitionNickel);
        event.register("ignition/ancient_metal", ignitionAncientMetal);
        event.register("ignition/mithril", ignitionMithril);
        event.register("ignition/tungsten", ignitionTungsten);
        event.register("ignition/adamantium", ignitionAdamantium);
        event.register("wither_branch", wither_branch);
        event.register("tools/detector", detectorDiamond);
        event.register("tools/detector_emerald", detectorEmerald);
        event.register("sulphur_sphere", sulphur);
        event.register("bows/uru/", bowUru).setUnlocalizedName("uru_bow");
        event.register("enderRod", enderRod).setUnlocalizedName("enderRod");
        event.register("tools/rusted_iron_club", morningStarRustedIron);
        event.register("buckets/wood/empty", woodBucket);
        event.register("buckets/wood/water", woodBucketWater);
        event.register("buckets/wood/water_suspicious", woodBucketWaterSuspicious);
        event.register("buckets/wood/water_swampland", woodBucketWaterDangerous);
        event.register("buckets/wood/milk", woodBucketMilk);
        event.register("tools/flint_hoe", hoeFlint);
        event.register("food/peeled_sugarcane", peeledSugarcane);
        event.register("totem/totem_of_sentry", totemOfSentry);
        event.register("totem/totem_of_unknown", totemOfUnknown);
        event.register("ignition/rusted_iron", ignitionRustedIron);
        event.register("tools/flint_hoe", hoeFlint);
        event.register("tools/stick_knife", stickKnife);
        event.register("wand/slime", slimeWand);
        Constant.initItemArray();
    }

    public static void registerBasicToolRecipes(RecipeRegistryEvent register, Material material) {
        Item item = Item.getMatchingItem(ItemIngot.class, material);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemSword.class, material)), true, "A", "A", "S",

                Character.valueOf('A'), item,
                Character.valueOf('S'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHoe.class, material)), true, "AA", "S ", "S ",

                Character.valueOf('A'), item,
                Character.valueOf('S'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemAxe.class, material)), true, "AA", "SA", "S ",

                Character.valueOf('A'), item,
                Character.valueOf('S'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShovel.class, material)), true, "A", "S", "S",

                Character.valueOf('A'), item,
                Character.valueOf('S'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemPickaxe.class, material)), true, "AAA", " S ", " S ",

                Character.valueOf('A'), item,
                Character.valueOf('S'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemFishingRod.class, material)), true, "  S", " SG", "SAG",

                Character.valueOf('A'), getMatchingItem(ItemNugget.class, item.getExclusiveMaterial()),
                Character.valueOf('S'), Item.stick,
                Character.valueOf('G'), Item.silk);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShears.class, material)), true, "A ", " A",

                Character.valueOf('A'), item);
        registerArmorRecipe(register, item, material);
    }

    public static void registerMITEToolRecipe(RecipeRegistryEvent register, Material material) {
        Item item = Item.getMatchingItem(ItemIngot.class, material);
        Item item_nugget = getMatchingItem(ItemNugget.class, item.getExclusiveMaterial());
        Item item_chain = Item.getMatchingItem(ItemChain.class, material);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemArrow.class, material)), true, "C", "B", "A",

                Character.valueOf('A'), Item.feather,
                Character.valueOf('B'), Item.stick,
                Character.valueOf('C'), item_nugget);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemChain.class, material)), true, " A ", "A A", " A ",

                Character.valueOf('A'), item_nugget);
        registerArmorRecipe(register, item_chain, material);
        register.registerShapedRecipe(new ItemStack(ItemBucket.getPeer(material, null)), true, "A A", " A ",

                Character.valueOf('A'), item);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemScythe.class, material)), true, "SA ", "S A", "S  ",

                Character.valueOf('A'), item,
                Character.valueOf('S'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemMattock.class, material)), true, "AAA", " SA", " S ",

                Character.valueOf('A'), item,
                Character.valueOf('S'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHatchet.class, material)), true, " BA", " B ",

                Character.valueOf('A'), item,
                Character.valueOf('B'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemWarHammer.class, material)), true, "AAA", "ABA", " B ",

                Character.valueOf('A'), item,
                Character.valueOf('B'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDagger.class, material)), true, " A ", " B ",

                Character.valueOf('A'), item,
                Character.valueOf('B'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemBattleAxe.class, material)), true, "A A", "ABA", " B ",

                Character.valueOf('A'), item,
                Character.valueOf('B'), Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDoor.class, material)), true, "AA", "AA", "AA",

                Character.valueOf('A'), item);
    }

    public static void registerITFToolRecipe(RecipeRegistryEvent register) {
        Material[] materials = {Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Material.ancient_metal, Material.mithril, Materials.tungsten, Material.adamantium};
        for (int i = 0; i < materials.length; i++) {
            Item item = Item.getMatchingItem(ItemIngot.class, materials[i]);
            Item item_nugget = getMatchingItem(ItemNugget.class, materials[i]);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemMorningStar.class, materials[i]), 1), true, "###", "#*#", " # ",

                    Character.valueOf('#'), item_nugget,
                    Character.valueOf('*'), item);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemIgnition.class, materials[i])), true, "C ", " F",

                    Character.valueOf('C'), item_nugget,
                    Character.valueOf('F'), flint);
        }
    }

    public static void registerArmorRecipe(RecipeRegistryEvent register, Item item, Material material) {
        if (item instanceof ItemChain) {
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class, material, true)), true, "AAA", "A A",

                    Character.valueOf('A'), item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class, material, true)), true, "A A", "AAA", "AAA",

                    Character.valueOf('A'), item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class, material, true)), true, "AAA", "A A", "A A",

                    Character.valueOf('A'), item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class, material, true)), true, "A A", "A A",

                    Character.valueOf('A'), item);
        } else {
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class, material, false)), true, "AAA", "A A",

                    Character.valueOf('A'), item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class, material, false)), true, "A A", "AAA", "AAA",

                    Character.valueOf('A'), item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class, material, false)), true, "AAA", "A A", "A A",

                    Character.valueOf('A'), item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class, material, false)), true, "A A", "A A",

                    Character.valueOf('A'), item);
        }
    }

    public static void registerFullMetalToolRecipe(RecipeRegistryEvent register, Material material) {
        registerBasicToolRecipes(register, material);
        registerMITEToolRecipe(register, material);
    }

    public static void registerRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(lemonPie), true, Item.sugar, Item.egg, Item.flour, lemon);
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 9), true, Blocks.blockNickel);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 9), true, nickelIngot);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 9), true, Blocks.blockTungsten);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 9), true, tungstenIngot);
        registerArmorRecipe(register, wolf_fur, Materials.wolf_fur);
        registerITFToolRecipe(register);
        registerFullMetalToolRecipe(register, Materials.nickel);
        registerFullMetalToolRecipe(register, Materials.tungsten);
        register.registerShapedRecipe(new ItemStack(bowTungsten, 1), true, "#C ", "#EC", "#C ",

                Character.valueOf('#'), silk,
                Character.valueOf('E'), tungstenIngot,
                Character.valueOf('C'), stick);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW",

                Character.valueOf('S'), silk,
                Character.valueOf('W'), stick);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW",

                Character.valueOf('S'), sinew,
                Character.valueOf('W'), stick);
        register.registerShapedRecipe(new ItemStack(detectorEmerald, 1), true, "FAF", "ANA", "FAF",

                Character.valueOf('A'), Item.goldNugget,
                Character.valueOf('F'), Item.ancientMetalNugget,
                Character.valueOf('N'), Item.emerald);
        register.registerShapedRecipe(new ItemStack(detectorDiamond, 1), true, "FAF", "ANA", "FAF",

                Character.valueOf('A'), Item.goldNugget,
                Character.valueOf('F'), Item.ancientMetalNugget,
                Character.valueOf('N'), Item.diamond);

        register.registerShapedRecipe(new ItemStack(woodBucket, 1), true, "W W", " W ",

                Character.valueOf('W'), Block.wood);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "SP", "S ",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Items.sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "S ", "SP",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Items.sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", " S", "PS",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Items.sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "PS", " S",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Items.sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "PS", " S",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", " S", "PS",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "SP", "S ",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "S ", "SP",

                Character.valueOf('F'), Item.flint,
                Character.valueOf('S'), Item.stick,
                Character.valueOf('P'), Item.silk);
        register.registerShapedRecipe(new ItemStack(stickKnife, 1), true, "S", "S",

                Character.valueOf('S'), Item.stick);

        register.registerShapelessRecipe(new ItemStack(totemOfUnknown, 1), true, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon);
        register.registerShapelessRecipe(new ItemStack(peeledSugarcane, 2), false, Item.reed, Item.reed);
        register.registerShapelessRecipe(new ItemStack(sulphur, 9), true, new ItemStack(Blocks.blockSulphur, 1));
        register.registerShapelessRecipe(new ItemStack(Item.gunpowder, 5), true, new ItemStack(Items.sulphur, 8), new ItemStack(Item.coal, 1, 1));
        register.registerShapelessRecipe(new ItemStack(forgingnote, 2), false, forgingnote, Item.writableBook);
        register.registerShapelessRecipe(new ItemStack(UruHelmet, 1), true, forgingnote, UruIngot, Item.helmetMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruChestplate, 1), true, forgingnote, UruIngot, Item.plateMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruLeggings, 1), true, forgingnote, UruIngot, Item.legsMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruBoots, 1), true, forgingnote, UruIngot, Item.bootsMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruSword, 1), true, forgingnote, UruIngot, Item.swordMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruScythe, 1), true, forgingnote, UruIngot, Item.scytheMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruBattleAxe, 1), true, forgingnote, UruIngot, Item.battleAxeMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruWarHammer, 1), true, forgingnote, UruIngot, Item.warHammerMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruMattock, 1), true, forgingnote, UruIngot, Item.mattockMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruMorningStar, 1), true, forgingnote, UruIngot, morningStarMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(bowUru, 1), true, forgingnote, UruIngot, Item.bowMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruPickaxe, 1), true, forgingnote, UruIngot, Item.pickaxeMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(UruNugget, 9), true, UruIngot);
        register.registerShapelessRecipe(new ItemStack(HelmetAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.helmetAncientMetal);
        register.registerShapelessRecipe(new ItemStack(ChestplateAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.plateAncientMetal);
        register.registerShapelessRecipe(new ItemStack(LeggingsAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.legsAncientMetal);
        register.registerShapelessRecipe(new ItemStack(BootsAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.bootsAncientMetal);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 1), false, arrowTungsten);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 1), false, arrowNickel);
        register.registerShapelessRecipe(new ItemStack(mashedCactus, 1), true, Block.cactus);
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 1), true, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 1), true, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget);
        register.registerShapelessRecipe(new ItemStack(UruIngot, 1), true, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget);
        register.registerShapelessRecipe(new ItemStack(Item.leather, 1), true, wolf_fur, wolf_fur, wolf_fur, wolf_fur);
        register.registerShapelessRecipe(new ItemStack(seedsBeetroot, 1), false, beetroot, beetroot);
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), false, beetroot);
        register.registerShapelessRecipe(new ItemStack(Pulque, 1), true, new Object[]{Item.sugar, Agave, new ItemStack(Item.potion, 1, 0)});//.resetDifficulty(3200);
        register.registerShapelessRecipe(new ItemStack(Ale, 1), true, new Object[]{Item.sugar, Item.wheat, new ItemStack(Item.potion, 1, 0)});//.resetDifficulty(3200);
        register.registerShapelessRecipe(new ItemStack(claybowlRaw, 1), false, Item.clay);
        register.registerShapelessRecipe(new ItemStack(tungstenBucket, 1), false, new Object[]{tungstenBucketStone});//.resetDifficulty(100);
        register.registerShapelessRecipe(new ItemStack(nickelBucket, 1), false, new Object[]{nickelBucketStone});//.resetDifficulty(100);
        int i;
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(Item.glassBottle, i), false, new ItemStack(suspiciousPotion, i));
        }
        register.registerShapelessRecipe(new ItemStack(bowlBeetrootSoup, 1, 0), false, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, Item.bowlWater);
        register.registerShapelessRecipe(new ItemStack(bowlPorkchopStew, 1), true, Item.bowlWater, Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
        register.registerShapelessRecipe(new ItemStack(bowlChestnutSoup, 1), true, Item.bowlWater, Item.lambchopCooked, Item.onion, Item.potato);
        register.registerShapelessRecipe(new ItemStack(bowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, Item.bowlWater);
        register.registerShapelessRecipe(new ItemStack(bowlLemonade, 1), true, Item.sugar, lemon, Item.bowlWater);
        register.registerShapelessRecipe(new ItemStack(carrotOnAStickNickel, 1), false, Item.carrot, fishingRodNickel);
        register.registerShapelessRecipe(new ItemStack(carrotOnAStickTungsten, 1), false, Item.carrot, fishingRodTungsten);
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty, i), false, new ItemStack(bowlWaterSuspicious, i));
            register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty, i), false, new ItemStack(bowlWaterSwampland, i));
        }
        register.registerShapelessRecipe(new ItemStack(claybowlBeefStew), false, Item.beefCooked, Block.mushroomBrown, Item.potato, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlChickenSoup), false, Item.chickenCooked, Item.carrot, Item.onion, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlVegetableSoup), false, Item.potato, Item.carrot, Item.onion, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlIceCream), false, Item.chocolate, claybowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(claybowlIceCream), false, new ItemStack(Item.dyePowder, 1, 3), Item.sugar, claybowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(claybowlSalad), false, Block.plantYellow, Block.plantYellow, Block.plantYellow, claybowlEmpty);
        register.registerShapelessRecipe(new ItemStack(claybowlCreamOfMushroomSoup), false, Block.mushroomBrown, Block.mushroomBrown, claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(claybowlCreamOfVegetableSoup), false, Item.potato, Item.carrot, Item.onion, claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(claybowlPumpkinSoup), false, Block.pumpkin, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlMashedPotato), false, Item.bakedPotato, Item.cheese, claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(claybowlSorbet), false, Item.orange, Item.sugar, Item.snowball, claybowlEmpty);
        register.registerShapelessRecipe(new ItemStack(claybowlPorridge), false, Item.seeds, Item.blueberries, Item.sugar, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlCereal), false, Item.wheat, Item.sugar, claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(claybowlMushroomStew), false, Block.mushroomBrown, Block.mushroomRed, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlBeetrootSoup, 1, 0), false, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlPorkchopStew, 1), true, claybowlWater, Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
        register.registerShapelessRecipe(new ItemStack(claybowlChestnutSoup, 1), true, claybowlWater, Item.lambchopCooked, Item.onion, Item.potato);
        register.registerShapelessRecipe(new ItemStack(claybowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(claybowlLemonade, 1), true, Item.sugar, lemon, claybowlWater);
        register.registerShapelessRecipe(new ItemStack(experimentalPotion, 1), true, Item.blazePowder, Item.netherStalkSeeds, new ItemStack(Item.potion, 1, 0), new ItemStack(Item.appleGold, 1, 0));
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 1), false, new Object[]{new ItemStack(claybowlMilk, 4)});//.resetDifficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 2), false, new Object[]{new ItemStack(claybowlMilk, 8)});//.resetDifficulty(6400);
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(claybowlEmpty, i), false, new ItemStack(claybowlWaterSuspicious, i));
            register.registerShapelessRecipe(new ItemStack(claybowlEmpty, i), false, new ItemStack(claybowlWaterSwampland, i));
        }
        ItemBucketMilk[] milk_buckets = {Item.bucketCopperMilk, Item.bucketSilverMilk, Item.bucketGoldMilk, Item.bucketIronMilk, Item.bucketAncientMetalMilk, Item.bucketMithrilMilk, Item.bucketAdamantiumMilk, tungstenBucketMilk, nickelBucketMilk};
        for (int n = 0; n < milk_buckets.length; n++) {
            register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, milk_buckets[n]);
            int i1;
            for (i1 = 1; i1 <= 9; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.cheese, i1), false, new Object[]{new ItemStack(milk_buckets[n], i1)});//.resetDifficulty(6400);
            }
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.bowlMilk, i1), true, new Object[]{milk_buckets[n], new ItemStack(Item.bowlEmpty, i1)});//.resetDifficulty(25);
            }
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(claybowlMilk, i1), true, new Object[]{milk_buckets[n], new ItemStack(claybowlEmpty, i1)});//.resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(milk_buckets[n]), true, new Object[]{milk_buckets[n]
                    .getEmptyVessel(), Item.bowlMilk, Item.bowlMilk, Item.bowlMilk, Item.bowlMilk});//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(milk_buckets[n]), true, new Object[]{milk_buckets[n]
                    .getEmptyVessel(), claybowlMilk, claybowlMilk, claybowlMilk, claybowlMilk});//.resetDifficulty(25);
        }
        register.registerShapelessRecipe(new ItemStack(Item.dough, 1), false, Item.flour, Item.bowlWater);
        register.registerShapelessRecipe(new ItemStack(Item.dough, 1), false, Item.flour, claybowlWater);
        ItemBucket[] water_buckets = {Item.bucketCopperWater, Item.bucketSilverWater, Item.bucketGoldWater, Item.bucketIronWater, Item.bucketAncientMetalWater, Item.bucketMithrilWater, Item.bucketAdamantiumWater, nickelBucketWater, tungstenBucketWater};
        for (int j = 0; j < water_buckets.length; j++) {
            int i1;
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.dough, i1), false, water_buckets[j], new ItemStack(Item.flour, i1));
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, water_buckets[j], new ItemStack(Item.flour, i1), new ItemStack(Item.chocolate, i1));
                register.registerShapelessRecipe(new ItemStack(Item.bowlWater, i1), true, new Object[]{water_buckets[j], new ItemStack(Item.bowlEmpty, i1)});//.resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(claybowlWater, i1), true, new Object[]{water_buckets[j], new ItemStack(claybowlEmpty, i1)});//.resetDifficulty(25);
            }
            for (i1 = 1; i1 <= 2; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, water_buckets[j], new ItemStack(Item.flour, i1), new ItemStack(Item.dyePowder, i1, 3), new ItemStack(Item.sugar, i1));
            }
            register.registerShapelessRecipe(new ItemStack(water_buckets[j]), true, new Object[]{water_buckets[j].getEmptyVessel(), new ItemStack(Item.bowlWater, 4)});//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(water_buckets[j]), true, new Object[]{water_buckets[j].getEmptyVessel(), new ItemStack(claybowlWater, 4)});//.resetDifficulty(25);
        }
        ItemBucket[] sus_water_buckets = {copperBucketWaterSuspicious, silverBucketWaterSuspicious, goldBucketWaterSuspicious, ironBucketWaterSuspicious, ancientmetalBucketWaterSuspicious, mithrilBucketWaterSuspicious, adamantiumBucketWaterSuspicious, nickelBucketWaterSuspicious, tungstenBucketWaterSuspicious};
        for (int k = 0; k < sus_water_buckets.length; k++) {
            for (int i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(bowlWaterSuspicious, i1), true, new Object[]{sus_water_buckets[k], new ItemStack(Item.bowlEmpty, i1)});//.resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(claybowlWaterSuspicious, i1), true, new Object[]{sus_water_buckets[k], new ItemStack(claybowlEmpty, i1)});//.resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(sus_water_buckets[k]), true, new Object[]{sus_water_buckets[k].getEmptyVessel(), new ItemStack(bowlWaterSuspicious, 4)});//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(sus_water_buckets[k]), true, new Object[]{sus_water_buckets[k].getEmptyVessel(), new ItemStack(claybowlWaterSuspicious, 4)});//.resetDifficulty(25);
        }
        ItemBucket[] smp_water_buckets = {copperBucketWaterDangerous, silverBucketWaterDangerous, goldBucketWaterDangerous, ironBucketWaterDangerous, ancientmetalBucketWaterDangerous, mithrilBucketWaterDangerous, adamantiumBucketWaterDangerous, nickelBucketWaterDangerous, tungstenBucketWaterDangerous};
        for (int m = 0; m < smp_water_buckets.length; m++) {
            for (int i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(bowlWaterSwampland, i1), true, new Object[]{smp_water_buckets[m], new ItemStack(Item.bowlEmpty, i1)});//.resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(claybowlWaterSwampland, i1), true, new Object[]{smp_water_buckets[m], new ItemStack(claybowlEmpty, i1)});//.resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(smp_water_buckets[m]), true, new Object[]{smp_water_buckets[m].getEmptyVessel(), new ItemStack(bowlWaterSwampland, 4)});//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(smp_water_buckets[m]), true, new Object[]{smp_water_buckets[m].getEmptyVessel(), new ItemStack(claybowlWaterSwampland, 4)});//.resetDifficulty(25);
        }
        register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, claybowlMilk);
        ItemCoin[] coins = {nickelCoin, tungstenCoin};
        for (ItemCoin coin : coins) {
            for (int plank_subtype = 1; plank_subtype <= 9; plank_subtype++) {
                register.registerShapelessRecipe(new ItemStack(coin.getNuggetPeer(), plank_subtype), true, new Object[]{new ItemStack(coin, plank_subtype)});//.resetDifficulty(25); TODO what is resetDifficulty
            }
            register.registerShapelessRecipe(new ItemStack(coin), true, new ItemStack(coin.getNuggetPeer()));
        }
        FurnaceRecipes.smelting().addSmelting(pieceAdamantium.itemID, new ItemStack(adamantiumNugget));
        FurnaceRecipes.smelting().addSmelting(pieceCopper.itemID, new ItemStack(copperNugget));
        FurnaceRecipes.smelting().addSmelting(pieceGold.itemID, new ItemStack(goldNugget));
        FurnaceRecipes.smelting().addSmelting(pieceGoldNether.itemID, new ItemStack(goldNugget));
        FurnaceRecipes.smelting().addSmelting(pieceSilver.itemID, new ItemStack(silverNugget));
        FurnaceRecipes.smelting().addSmelting(pieceIron.itemID, new ItemStack(ironNugget));
        FurnaceRecipes.smelting().addSmelting(pieceNickel.itemID, new ItemStack(nickelNugget));
        FurnaceRecipes.smelting().addSmelting(pieceMithril.itemID, new ItemStack(mithrilNugget));
        FurnaceRecipes.smelting().addSmelting(pieceTungsten.itemID, new ItemStack(tungstenNugget));
        FurnaceRecipes.smelting().addSmelting(pieceUru.itemID, new ItemStack(UruNugget));
        FurnaceRecipes.smelting().addSmelting(AncientmetalArmorPiece.itemID, new ItemStack(ancientMetalNugget));
        FurnaceRecipes.smelting().addSmelting(claybowlWaterSuspicious.itemID, new ItemStack(claybowlWater));
        FurnaceRecipes.smelting().addSmelting(claybowlWaterSwampland.itemID, new ItemStack(claybowlWater));
        FurnaceRecipes.smelting().addSmelting(suspiciousPotion.itemID, new ItemStack(potion, 1, 0));
        FurnaceRecipes.smelting().addSmelting(horse_meat.itemID, new ItemStack(horse_meat_cooked));
        FurnaceRecipes.smelting().addSmelting(claybowlRaw.itemID, new ItemStack(claybowlEmpty));
        Class[] tools = {
                ItemSword.class, ItemAxe.class, ItemPickaxe.class, ItemHoe.class, ItemShovel.class, ItemWarHammer.class, ItemBattleAxe.class, ItemScythe.class, ItemDagger.class, ItemKnife.class,
                ItemMorningStar.class, ItemHatchet.class, ItemShears.class, ItemMattock.class, ItemHelmet.class, ItemBoots.class, ItemLeggings.class, ItemCuirass.class};
        Material[] available_material = {Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Materials.tungsten, Material.ancient_metal, Material.rusted_iron};
        for (Class tool : tools) {
            for (Material material : available_material) {
                Item matchingitem = Item.getMatchingItem(tool, material);
                if (matchingitem != null) {
                    if (matchingitem instanceof ItemArmor) {
                        matchingitem = ItemArmor.getMatchingArmor(tool, material, false);
                        FurnaceRecipes.smelting().addSmelting(matchingitem.itemID, new ItemStack(appleRed));
                        matchingitem = ItemArmor.getMatchingArmor(tool, material, true);
                        FurnaceRecipes.smelting().addSmelting(matchingitem.itemID, new ItemStack(appleRed));
                    } else {
                        FurnaceRecipes.smelting().addSmelting(matchingitem.itemID, new ItemStack(appleRed));
                    }
                }
            }
        }
        ItemFood.setCookingResult((ItemFood) horse_meat, (ItemFood) horse_meat_cooked, 6);
    }
}
