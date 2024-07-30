package net.oilcake.mitelros.item.register;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class RecipeRegister extends Items {
    public static void registerRecipes(RecipeRegistryEvent register) {
        registerMetalRecipes(register);
        registerMiscRecipes(register);
        RecipesArmor.registerArmorRecipe(register);
        RecipesFood.registerFoodRecipe(register);
        FurnaceRecipesExtend.registerFurnaceRecipes();
    }

    private static void registerMetalRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 9), true, Blocks.blockNickel);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 9), true, nickelIngot);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 9), true, Blocks.blockTungsten);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 9), true, tungstenIngot);
        register.registerShapedRecipe(new ItemStack(fishingRodNickel), true, "  S", " SG", "SAG", 'A', nickelNugget, 'S', Item.stick, 'G', Item.silk);
        register.registerShapedRecipe(new ItemStack(fishingRodTungsten), true, "  S", " SG", "SAG", 'A', tungstenNugget, 'S', Item.stick, 'G', Item.silk);
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 1), true, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 1), true, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget);
        register.registerShapelessRecipe(new ItemStack(uruIngot, 1), true, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget);
        register.registerShapelessRecipe(new ItemStack(tungstenBucket, 1), false, tungstenBucketStone).difficulty(100);
        register.registerShapelessRecipe(new ItemStack(nickelBucket, 1), false, nickelBucketStone).difficulty(100);
        register.registerShapelessRecipe(new ItemStack(helmetAncientMetalSacred, 1), true, forgingNote, Item.ingotGold, Item.helmetAncientMetal);
        register.registerShapelessRecipe(new ItemStack(chestplateAncientMetalSacred, 1), true, forgingNote, Item.ingotGold, Item.plateAncientMetal);
        register.registerShapelessRecipe(new ItemStack(leggingsAncientMetalSacred, 1), true, forgingNote, Item.ingotGold, Item.legsAncientMetal);
        register.registerShapelessRecipe(new ItemStack(bootsAncientMetalSacred, 1), true, forgingNote, Item.ingotGold, Item.bootsAncientMetal);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 1), false, arrowTungsten);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 1), false, arrowNickel);
        register.registerShapedRecipe(new ItemStack(bowTungsten, 1), true, "#C ", "#EC", "#C ", '#', silk, 'E', tungstenIngot, 'C', stick);
        register.registerShapelessRecipe(new ItemStack(carrotOnAStickNickel, 1), false, Item.carrot, fishingRodNickel);
        register.registerShapelessRecipe(new ItemStack(carrotOnAStickTungsten, 1), false, Item.carrot, fishingRodTungsten);
        register.registerShapedRecipe(new ItemStack(detectorEmerald, 1), true, "FAF", "ANA", "FAF", 'A', Item.goldNugget, 'F', Item.ancientMetalNugget, 'N', Item.emerald);
        register.registerShapedRecipe(new ItemStack(detectorDiamond, 1), true, "FAF", "ANA", "FAF", 'A', Item.goldNugget, 'F', Item.ancientMetalNugget, 'N', Item.diamond);
        ItemCoin[] coins = {nickelCoin, tungstenCoin};
        for (ItemCoin coin : coins) {
            for (int num = 1; num <= 9; num++) {
                register.registerShapelessRecipe(new ItemStack(coin.getNuggetPeer(), num), true, new ItemStack(coin, num)).difficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(coin), true, new ItemStack(coin.getNuggetPeer()));
        }
    }

    private static void registerMiscRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(frostPowder, 2), true, frostRod);
        register.registerShapelessRecipe(new ItemStack(Item.leather, 1), true, wolf_fur, wolf_fur, wolf_fur, wolf_fur);
        register.registerShapelessRecipe(new ItemStack(Block.ice, 1), true, iceChunk, iceChunk, iceChunk, iceChunk);
        register.registerShapelessRecipe(new ItemStack(sulphur, 9), true, new ItemStack(Blocks.blockSulphur, 1));
        register.registerShapelessRecipe(new ItemStack(Item.gunpowder, 5), true, new ItemStack(Items.sulphur, 8), new ItemStack(Item.coal, 1, 1));
        register.registerShapedRecipe(new ItemStack(stickKnife, 1), true, "S", "S", 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(minePocket, 1), true, "SWS", "W W", "WWW", 'S', Item.silk, 'W', Items.wolf_fur);
        register.registerShapelessRecipe(new ItemStack(totemOfUnknown, 1), true, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon);
        register.registerShapedRecipe(new ItemStack(clayJug, 1), true, " C ", "C C", " C ", 'C', clay);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW", 'S', silk, 'W', stick);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW", 'S', sinew, 'W', stick);
        register.registerShapedRecipe(new ItemStack(woodBucket, 1), true, "W W", " W ", 'W', Block.wood);
        registerHoeFlintRecipes(register);
    }

    private static void registerHoeFlintRecipes(RecipeRegistryEvent register) {
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "SP", "S ", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "S ", "SP", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", " S", "PS", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "PS", " S", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "PS", " S", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", " S", "PS", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "SP", "S ", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "S ", "SP", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);

    }
}
