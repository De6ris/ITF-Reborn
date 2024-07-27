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
        register.registerShapelessRecipe(new ItemStack(peeledSugarcane, 2), false, Item.reed, Item.reed);
        register.registerShapelessRecipe(new ItemStack(mashedCactus, 1), true, Block.cactus);
        register.registerShapelessRecipe(new ItemStack(seedsBeetroot, 1), false, beetroot, beetroot);
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), false, beetroot);
        register.registerShapelessRecipe(new ItemStack(pulque, 1), true, Item.sugar, agave, new ItemStack(Item.potion, 1, 0)).difficulty(3200);
        register.registerShapelessRecipe(new ItemStack(ale, 1), true, Item.sugar, Item.wheat, new ItemStack(Item.potion, 1, 0)).difficulty(3200);
        register.registerShapelessRecipe(new ItemStack(clayBowlRaw, 1), false, Item.clay);
        register.registerShapelessRecipe(new ItemStack(lemonPie), true, Item.sugar, Item.egg, Item.flour, lemon);
        register.registerShapelessRecipe(new ItemStack(experimentalPotion, 1), true, Item.blazePowder, Item.netherStalkSeeds, new ItemStack(Item.potion, 1, 0), new ItemStack(Item.appleGold, 1, 0));
        register.registerShapelessRecipe(new ItemStack(Item.dough, 1), false, Item.flour, clayBowlWater);
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
            register.registerShapelessRecipe(new ItemStack(clayBowlEmpty, i), false, new ItemStack(clayBowlWaterSuspicious, i));
            register.registerShapelessRecipe(new ItemStack(clayBowlEmpty, i), false, new ItemStack(clayBowlWaterSwampland, i));
        }
        register.registerShapelessRecipe(new ItemStack(leatherKettle).setItemDamage(18), false, new ItemStack(leatherKettleSwampland));
        register.registerShapelessRecipe(new ItemStack(leatherKettle).setItemDamage(18), false, new ItemStack(leatherKettleSuspicious));
        register.registerShapelessRecipe(new ItemStack(hardenedClayJug).setItemDamage(18), false, new ItemStack(hardenedClayJugSuspicious));
        register.registerShapelessRecipe(new ItemStack(hardenedClayJug).setItemDamage(18), false, new ItemStack(hardenedClayJugSwampland));
    }

    public static void clayBowlMilkRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 1), false, new ItemStack(clayBowlMilk, 4)).difficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 2), false, new ItemStack(clayBowlMilk, 8)).difficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, clayBowlMilk);
    }

    public static void woodBowlFeastRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(bowlBeetrootSoup, 1, 0), false, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, bowlWater);
        register.registerShapelessRecipe(new ItemStack(bowlPorkchopStew, 1), true, bowlWater, Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
        register.registerShapelessRecipe(new ItemStack(bowlLampchopStew, 1), true, bowlWater, Item.lambchopCooked, Item.onion, Item.potato);
        register.registerShapelessRecipe(new ItemStack(bowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, bowlWater);
        register.registerShapelessRecipe(new ItemStack(bowlLemonade, 1), true, Item.sugar, lemon, Item.bowlWater);
    }

    public static void clayBowlFeastRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(clayBowlBeefStew), false, Item.beefCooked, Block.mushroomBrown, Item.potato, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlChickenSoup), false, Item.chickenCooked, Item.carrot, Item.onion, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlVegetableSoup), false, Item.potato, Item.carrot, Item.onion, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlIceCream), false, Item.chocolate, clayBowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(clayBowlIceCream), false, new ItemStack(Item.dyePowder, 1, 3), Item.sugar, clayBowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(clayBowlSalad), false, Block.plantYellow, Block.plantYellow, Block.plantYellow, clayBowlEmpty);
        register.registerShapelessRecipe(new ItemStack(clayBowlCreamOfMushroomSoup), false, Block.mushroomBrown, Block.mushroomBrown, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlCreamOfVegetableSoup), false, Item.potato, Item.carrot, Item.onion, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlPumpkinSoup), false, Block.pumpkin, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlMashedPotato), false, Item.bakedPotato, Item.cheese, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlSorbet), false, Item.orange, Item.sugar, Item.snowball, clayBowlEmpty);
        register.registerShapelessRecipe(new ItemStack(clayBowlPorridge), false, Item.seeds, Item.blueberries, Item.sugar, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlCereal), false, Item.wheat, Item.sugar, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlMushroomStew), false, Block.mushroomBrown, Block.mushroomRed, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlBeetrootSoup, 1, 0), false, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlPorkchopStew, 1), true, clayBowlWater, Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
        register.registerShapelessRecipe(new ItemStack(clayBowlLampchopSoup, 1), true, clayBowlWater, Item.lambchopCooked, Item.onion, Item.potato);
        register.registerShapelessRecipe(new ItemStack(clayBowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, clayBowlWater);
        register.registerShapelessRecipe(new ItemStack(clayBowlLemonade, 1), true, Item.sugar, lemon, clayBowlWater);
    }

    public static void bucketExtendRecipes(RecipeRegistryEvent register) {
        ItemBucketMilk[] milk_buckets = {Item.bucketCopperMilk, Item.bucketSilverMilk, Item.bucketGoldMilk, Item.bucketIronMilk, Item.bucketAncientMetalMilk, Item.bucketMithrilMilk, Item.bucketAdamantiumMilk, tungstenBucketMilk, nickelBucketMilk};
        for (ItemBucketMilk milkBucket : milk_buckets) {
            register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, milkBucket);
            int i1;
            for (i1 = 1; i1 <= 9; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.cheese, i1), false, new ItemStack(milkBucket, i1)).difficulty(6400);
            }
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.bowlMilk, i1), true, milkBucket, new ItemStack(Item.bowlEmpty, i1)).difficulty(25);
            }
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(clayBowlMilk, i1), true, milkBucket, new ItemStack(clayBowlEmpty, i1)).difficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(milkBucket), true, milkBucket
                    .getEmptyVessel(), Item.bowlMilk, Item.bowlMilk, Item.bowlMilk, Item.bowlMilk).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(milkBucket), true, milkBucket
                    .getEmptyVessel(), clayBowlMilk, clayBowlMilk, clayBowlMilk, clayBowlMilk).difficulty(25);
        }
        ItemBucket[] water_buckets = {Item.bucketCopperWater, Item.bucketSilverWater, Item.bucketGoldWater, Item.bucketIronWater, Item.bucketAncientMetalWater, Item.bucketMithrilWater, Item.bucketAdamantiumWater, nickelBucketWater, tungstenBucketWater};
        for (ItemBucket waterBucket : water_buckets) {
            int i1;
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.dough, i1), false, waterBucket, new ItemStack(Item.flour, i1));
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, waterBucket, new ItemStack(Item.flour, i1), new ItemStack(Item.chocolate, i1));
                register.registerShapelessRecipe(new ItemStack(Item.bowlWater, i1), true, waterBucket, new ItemStack(Item.bowlEmpty, i1)).difficulty(25);
                register.registerShapelessRecipe(new ItemStack(clayBowlWater, i1), true, waterBucket, new ItemStack(clayBowlEmpty, i1)).difficulty(25);
            }
            for (i1 = 1; i1 <= 2; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, waterBucket, new ItemStack(Item.flour, i1), new ItemStack(Item.dyePowder, i1, 3), new ItemStack(Item.sugar, i1));
            }
            register.registerShapelessRecipe(new ItemStack(waterBucket), true, waterBucket.getEmptyVessel(), new ItemStack(Item.bowlWater, 4)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(waterBucket), true, waterBucket.getEmptyVessel(), new ItemStack(clayBowlWater, 4)).difficulty(25);
        }
        ItemBucket[] suspicious_water_buckets = {copperBucketWaterSuspicious, silverBucketWaterSuspicious, goldBucketWaterSuspicious, ironBucketWaterSuspicious, ancientmetalBucketWaterSuspicious, mithrilBucketWaterSuspicious, adamantiumBucketWaterSuspicious, nickelBucketWaterSuspicious, tungstenBucketWaterSuspicious};
        for (ItemBucket susWaterBucket : suspicious_water_buckets) {
            for (int i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(bowlWaterSuspicious, i1), true, susWaterBucket, new ItemStack(Item.bowlEmpty, i1)).difficulty(25);
                register.registerShapelessRecipe(new ItemStack(clayBowlWaterSuspicious, i1), true, susWaterBucket, new ItemStack(clayBowlEmpty, i1)).difficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(susWaterBucket), true, susWaterBucket.getEmptyVessel(), new ItemStack(bowlWaterSuspicious, 4)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(susWaterBucket), true, susWaterBucket.getEmptyVessel(), new ItemStack(clayBowlWaterSuspicious, 4)).difficulty(25);
        }
        ItemBucket[] dangerous_water_buckets = {copperBucketWaterDangerous, silverBucketWaterDangerous, goldBucketWaterDangerous, ironBucketWaterDangerous, ancientmetalBucketWaterDangerous, mithrilBucketWaterDangerous, adamantiumBucketWaterDangerous, nickelBucketWaterDangerous, tungstenBucketWaterDangerous};
        for (ItemBucket smpWaterBucket : dangerous_water_buckets) {
            for (int i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(bowlWaterSwampland, i1), true, smpWaterBucket, new ItemStack(Item.bowlEmpty, i1)).difficulty(25);
                register.registerShapelessRecipe(new ItemStack(clayBowlWaterSwampland, i1), true, smpWaterBucket, new ItemStack(clayBowlEmpty, i1)).difficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(smpWaterBucket), true, smpWaterBucket.getEmptyVessel(), new ItemStack(bowlWaterSwampland, 4)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(smpWaterBucket), true, smpWaterBucket.getEmptyVessel(), new ItemStack(clayBowlWaterSwampland, 4)).difficulty(25);
        }
    }

}
