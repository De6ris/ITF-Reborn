package net.oilcake.mitelros.item.register;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class RecipesFood extends Items {
    public static void registerFoodRecipe(RecipeRegistryEvent register) {
        emptyVesselRecipes(register);
        clayBowlMilkRecipes(register);
        woodBowlFeastRecipes(register);
        clayBowlFeastRecipes(register);
        bucketExtendRecipes(register);
        register.registerShapelessRecipe(new ItemStack(lemonPie), true, Item.sugar, Item.egg, Item.flour, lemon);
        register.registerShapelessRecipe(new ItemStack(experimentalPotion, 1), true, Item.blazePowder, Item.netherStalkSeeds, new ItemStack(Item.potion, 1, 0), new ItemStack(Item.appleGold, 1, 0));
        register.registerShapelessRecipe(new ItemStack(Item.dough, 1), false, Item.flour, claybowlWater);
        register.registerShapedRecipe(new ItemStack(ice_sucker), true, "AB", "BC", 'A', Item.stick, 'B', Item.snowball, 'C', Item.sugar);
        register.registerShapedRecipe(new ItemStack(melon_ice), true, "AB", "BC", 'A', Item.stick, 'B', Item.snowball, 'C', Item.melon);
        register.registerShapedRecipe(new ItemStack(chocolate_smoothie), true, "AAA", "BBB", 'A', iceChunk, 'B', new ItemStack(Item.dyePowder, 1, 3));
    }

    public static void emptyVesselRecipes(RecipeRegistryEvent register) {
        int i;
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(Item.glassBottle, i), false, new ItemStack(suspiciousPotion, i));
        }
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty, i), false, new ItemStack(bowlWaterSuspicious, i));
            register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty, i), false, new ItemStack(bowlWaterSwampland, i));
        }
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(claybowlEmpty, i), false, new ItemStack(claybowlWaterSuspicious, i));
            register.registerShapelessRecipe(new ItemStack(claybowlEmpty, i), false, new ItemStack(claybowlWaterSwampland, i));
        }
    }

    public static void clayBowlMilkRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 1), false, new ItemStack(claybowlMilk, 4));//.resetDifficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 2), false, new ItemStack(claybowlMilk, 8));//.resetDifficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, claybowlMilk);
    }

    public static void woodBowlFeastRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(bowlBeetrootSoup, 1, 0), false, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, Item.bowlWater);
        register.registerShapelessRecipe(new ItemStack(bowlPorkchopStew, 1), true, Item.bowlWater, Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
        register.registerShapelessRecipe(new ItemStack(bowlChestnutSoup, 1), true, Item.bowlWater, Item.lambchopCooked, Item.onion, Item.potato);
        register.registerShapelessRecipe(new ItemStack(bowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, Item.bowlWater);
        register.registerShapelessRecipe(new ItemStack(bowlLemonade, 1), true, Item.sugar, lemon, Item.bowlWater);
    }

    public static void clayBowlFeastRecipes(RecipeRegistryEvent register) {
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
    }

    public static void bucketExtendRecipes(RecipeRegistryEvent register) {
        ItemBucketMilk[] milk_buckets = {Item.bucketCopperMilk, Item.bucketSilverMilk, Item.bucketGoldMilk, Item.bucketIronMilk, Item.bucketAncientMetalMilk, Item.bucketMithrilMilk, Item.bucketAdamantiumMilk, tungstenBucketMilk, nickelBucketMilk};
        for (ItemBucketMilk milkBucket : milk_buckets) {
            register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, milkBucket);
            int i1;
            for (i1 = 1; i1 <= 9; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.cheese, i1), false, new ItemStack(milkBucket, i1));//.resetDifficulty(6400);
            }
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.bowlMilk, i1), true, milkBucket, new ItemStack(Item.bowlEmpty, i1));//.resetDifficulty(25);
            }
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(claybowlMilk, i1), true, milkBucket, new ItemStack(claybowlEmpty, i1));//.resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(milkBucket), true, milkBucket
                    .getEmptyVessel(), Item.bowlMilk, Item.bowlMilk, Item.bowlMilk, Item.bowlMilk);//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(milkBucket), true, milkBucket
                    .getEmptyVessel(), claybowlMilk, claybowlMilk, claybowlMilk, claybowlMilk);//.resetDifficulty(25);
        }
        ItemBucket[] water_buckets = {Item.bucketCopperWater, Item.bucketSilverWater, Item.bucketGoldWater, Item.bucketIronWater, Item.bucketAncientMetalWater, Item.bucketMithrilWater, Item.bucketAdamantiumWater, nickelBucketWater, tungstenBucketWater};
        for (ItemBucket waterBucket : water_buckets) {
            int i1;
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.dough, i1), false, waterBucket, new ItemStack(Item.flour, i1));
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, waterBucket, new ItemStack(Item.flour, i1), new ItemStack(Item.chocolate, i1));
                register.registerShapelessRecipe(new ItemStack(Item.bowlWater, i1), true, waterBucket, new ItemStack(Item.bowlEmpty, i1));//.resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(claybowlWater, i1), true, waterBucket, new ItemStack(claybowlEmpty, i1));//.resetDifficulty(25);
            }
            for (i1 = 1; i1 <= 2; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, waterBucket, new ItemStack(Item.flour, i1), new ItemStack(Item.dyePowder, i1, 3), new ItemStack(Item.sugar, i1));
            }
            register.registerShapelessRecipe(new ItemStack(waterBucket), true, waterBucket.getEmptyVessel(), new ItemStack(Item.bowlWater, 4));//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(waterBucket), true, waterBucket.getEmptyVessel(), new ItemStack(claybowlWater, 4));//.resetDifficulty(25);
        }
        ItemBucket[] suspicious_water_buckets = {copperBucketWaterSuspicious, silverBucketWaterSuspicious, goldBucketWaterSuspicious, ironBucketWaterSuspicious, ancientmetalBucketWaterSuspicious, mithrilBucketWaterSuspicious, adamantiumBucketWaterSuspicious, nickelBucketWaterSuspicious, tungstenBucketWaterSuspicious};
        for (ItemBucket susWaterBucket : suspicious_water_buckets) {
            for (int i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(bowlWaterSuspicious, i1), true, susWaterBucket, new ItemStack(Item.bowlEmpty, i1));//.resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(claybowlWaterSuspicious, i1), true, susWaterBucket, new ItemStack(claybowlEmpty, i1));//.resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(susWaterBucket), true, susWaterBucket.getEmptyVessel(), new ItemStack(bowlWaterSuspicious, 4));//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(susWaterBucket), true, susWaterBucket.getEmptyVessel(), new ItemStack(claybowlWaterSuspicious, 4));//.resetDifficulty(25);
        }
        ItemBucket[] dangerous_water_buckets = {copperBucketWaterDangerous, silverBucketWaterDangerous, goldBucketWaterDangerous, ironBucketWaterDangerous, ancientmetalBucketWaterDangerous, mithrilBucketWaterDangerous, adamantiumBucketWaterDangerous, nickelBucketWaterDangerous, tungstenBucketWaterDangerous};
        for (ItemBucket smpWaterBucket : dangerous_water_buckets) {
            for (int i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(bowlWaterSwampland, i1), true, smpWaterBucket, new ItemStack(Item.bowlEmpty, i1));//.resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(claybowlWaterSwampland, i1), true, smpWaterBucket, new ItemStack(claybowlEmpty, i1));//.resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(smpWaterBucket), true, smpWaterBucket.getEmptyVessel(), new ItemStack(bowlWaterSwampland, 4));//.resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(smpWaterBucket), true, smpWaterBucket.getEmptyVessel(), new ItemStack(claybowlWaterSwampland, 4));//.resetDifficulty(25);
        }
    }

}
