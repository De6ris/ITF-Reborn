package net.oilcake.mitelros.item.register;

import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class ITFRecipesFood extends Items {

    public static Item getWaterBowlForCooking() {
        return Items.bowlWaterPure;
    }

    public static Item getClayBowlWaterForCooking() {
        return Items.clayBowlWaterPure;
    }

    public static void registerFoodRecipe(CraftingRecipeRegisterEvent register) {
        emptyVesselRecipes(register);
        clayBowlMilkRecipes(register);
        woodBowlFeastRecipes(register);
        clayBowlFeastRecipes(register);
        bucketExtendRecipes(register);
        register.registerShapelessRecipe(new ItemStack(peeledSugarcane, 2), true, Item.reed, Item.reed);
        register.registerShapelessRecipe(new ItemStack(mashedCactus, 1), true, Block.cactus);
        register.registerShapelessRecipe(new ItemStack(seedsBeetroot, 1), true, beetroot, beetroot);
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), true, beetroot);
        register.registerShapelessRecipe(new ItemStack(pulque, 1), true, Item.sugar, agave, new ItemStack(Item.potion, 1, 0)).difficulty(3200);
        register.registerShapelessRecipe(new ItemStack(ale, 1), true, Item.sugar, Item.wheat, new ItemStack(Item.potion, 1, 0)).difficulty(3200);
        register.registerShapelessRecipe(new ItemStack(clayBowlRaw, 1), true, Item.clay);
        register.registerShapelessRecipe(new ItemStack(lemonPie), true, Item.sugar, Item.egg, Item.flour, lemon);
        register.registerShapelessRecipe(new ItemStack(experimentalPotion, 1), true, Item.blazePowder, Item.netherStalkSeeds, new ItemStack(Item.potion, 1, 0), new ItemStack(Item.appleGold, 1, 0));
        register.registerShapelessRecipe(new ItemStack(Item.dough, 1), false, Item.flour, getClayBowlWaterForCooking());
        register.registerShapedRecipe(new ItemStack(ice_sucker), true, "AB", "BC", 'A', Item.stick, 'B', Item.snowball, 'C', Item.sugar);
        register.registerShapedRecipe(new ItemStack(melon_ice), true, "AB", "BC", 'A', Item.stick, 'B', Item.snowball, 'C', Item.melon);
        register.registerShapedRecipe(new ItemStack(chocolate_smoothie), true, "AAA", "BBB", 'A', iceChunk, 'B', new ItemStack(Item.dyePowder, 1, 3));
    }

    public static void emptyVesselRecipes(CraftingRecipeRegisterEvent register) {
        int i;
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(Item.glassBottle, i), false, new ItemStack(suspiciousPotion, i));
        }
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty, i), false, new ItemStack(bowlWaterPure, i));
        }
        for (i = 1; i <= 9; i++) {
            register.registerShapelessRecipe(new ItemStack(clayBowlEmpty, i), false, new ItemStack(clayBowlWaterPure, i)).difficulty(0);
        }
        register.registerShapelessRecipe(new ItemStack(leatherKettle).setItemDamage(leatherKettle.maxDamage - 1), false, new ItemStack(leatherKettle)).difficulty(0).allowDamaged();
        register.registerShapelessRecipe(new ItemStack(leatherKettle).setItemDamage(leatherKettle.maxDamage - 1), false, new ItemStack(leatherKettlePure)).difficulty(0).allowDamaged();
        register.registerShapelessRecipe(new ItemStack(hardenedClayJug).setItemDamage(hardenedClayJug.maxDamage - 1), false, new ItemStack(hardenedClayJug)).difficulty(0).allowDamaged();
        register.registerShapelessRecipe(new ItemStack(hardenedClayJug).setItemDamage(hardenedClayJug.maxDamage - 1), false, new ItemStack(hardenedClayJugPure)).difficulty(0).allowDamaged();
    }

    public static void clayBowlMilkRecipes(CraftingRecipeRegisterEvent register) {
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 1), false, new ItemStack(clayBowlMilk, 4)).difficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 2), false, new ItemStack(clayBowlMilk, 8)).difficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, clayBowlMilk);
    }

    public static void woodBowlFeastRecipes(CraftingRecipeRegisterEvent register) {
        register.registerShapelessRecipe(new ItemStack(bowlBeetrootSoup, 1, 0), true, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, getWaterBowlForCooking());
        register.registerShapelessRecipe(new ItemStack(bowlPorkchopStew, 1), true, getWaterBowlForCooking(), Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
        register.registerShapelessRecipe(new ItemStack(bowlLampchopStew, 1), true, getWaterBowlForCooking(), Item.lambchopCooked, Item.onion, Item.potato);
        register.registerShapelessRecipe(new ItemStack(bowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, getWaterBowlForCooking());
        register.registerShapelessRecipe(new ItemStack(bowlLemonade, 1), true, Item.sugar, lemon, getWaterBowlForCooking());
    }

    public static void clayBowlFeastRecipes(CraftingRecipeRegisterEvent register) {
        register.registerShapelessRecipe(new ItemStack(clayBowlBeefStew), true, Item.beefCooked, Block.mushroomBrown, Item.potato, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlChickenSoup), true, Item.chickenCooked, Item.carrot, Item.onion, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlVegetableSoup), true, Item.potato, Item.carrot, Item.onion, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlIceCream), true, Item.chocolate, clayBowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(clayBowlIceCream), true, new ItemStack(Item.dyePowder, 1, 3), Item.sugar, clayBowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(clayBowlSalad), true, Block.plantYellow, Block.plantYellow, Block.plantYellow, clayBowlEmpty);
        register.registerShapelessRecipe(new ItemStack(clayBowlCreamOfMushroomSoup), true, Block.mushroomBrown, Block.mushroomBrown, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlCreamOfVegetableSoup), true, Item.potato, Item.carrot, Item.onion, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlPumpkinSoup), true, Block.pumpkin, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlMashedPotato), true, Item.bakedPotato, Item.cheese, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlSorbet), true, Item.orange, Item.sugar, Item.snowball, clayBowlEmpty);
        register.registerShapelessRecipe(new ItemStack(clayBowlPorridge), true, Item.seeds, Item.blueberries, Item.sugar, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlCereal), true, Item.wheat, Item.sugar, clayBowlMilk);
        register.registerShapelessRecipe(new ItemStack(clayBowlMushroomStew), true, Block.mushroomBrown, Block.mushroomRed, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlBeetrootSoup, 1, 0), true, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlPorkchopStew, 1), true, getClayBowlWaterForCooking(), Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
        register.registerShapelessRecipe(new ItemStack(clayBowlLampchopSoup, 1), true, getClayBowlWaterForCooking(), Item.lambchopCooked, Item.onion, Item.potato);
        register.registerShapelessRecipe(new ItemStack(clayBowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, getClayBowlWaterForCooking());
        register.registerShapelessRecipe(new ItemStack(clayBowlLemonade, 1), true, Item.sugar, lemon, getClayBowlWaterForCooking());
    }

    public static void bucketExtendRecipes(CraftingRecipeRegisterEvent register) {
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

        ItemBucket[] pure_water_buckets = {copperBucketWaterPure, silverBucketWaterPure, goldBucketWaterPure, ironBucketWaterPure, ancientmetalBucketWaterPure, mithrilBucketWaterPure, adamantiumBucketWaterPure, nickelBucketWaterPure, tungstenBucketWaterPure};
        for (ItemBucket pureWaterBucket : pure_water_buckets) {
            int i1;
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.dough, i1), false, pureWaterBucket, new ItemStack(Item.flour, i1));
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, pureWaterBucket, new ItemStack(Item.flour, i1), new ItemStack(Item.chocolate, i1));
                register.registerShapelessRecipe(new ItemStack(bowlWaterPure, i1), true, pureWaterBucket, new ItemStack(Item.bowlEmpty, i1)).difficulty(25);
                register.registerShapelessRecipe(new ItemStack(clayBowlWaterPure, i1), true, pureWaterBucket, new ItemStack(clayBowlEmpty, i1)).difficulty(25);
            }
            for (i1 = 1; i1 <= 2; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i1 * 4), false, pureWaterBucket, new ItemStack(Item.flour, i1), new ItemStack(Item.dyePowder, i1, 3), new ItemStack(Item.sugar, i1));
            }
            register.registerShapelessRecipe(new ItemStack(pureWaterBucket), true, pureWaterBucket.getEmptyVessel(), new ItemStack(bowlWaterPure, 4)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(pureWaterBucket), true, pureWaterBucket.getEmptyVessel(), new ItemStack(clayBowlWaterPure, 4)).difficulty(25);
        }

        // convert between bucket and bowl
        ItemBucket[] water_buckets = {nickelBucketWater, tungstenBucketWater};
        for (ItemBucket waterBucket : water_buckets) {
            int i1;
            for (i1 = 1; i1 <= 4; i1++) {
                register.registerShapelessRecipe(new ItemStack(Item.bowlWater, i1), true, waterBucket, new ItemStack(Item.bowlEmpty, i1)).difficulty(25);
                register.registerShapelessRecipe(new ItemStack(clayBowlWater, i1), true, waterBucket, new ItemStack(clayBowlEmpty, i1)).difficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(waterBucket), true, waterBucket.getEmptyVessel(), new ItemStack(bowlWater, 4)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(waterBucket), true, waterBucket.getEmptyVessel(), new ItemStack(clayBowlWater, 4)).difficulty(25);
        }
    }

}
