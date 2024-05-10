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
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 9), true, Blocks.blockNickel);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 9), true, nickelIngot);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 9), true, Blocks.blockTungsten);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 9), true, tungstenIngot);
        register.registerShapelessRecipe(new ItemStack(frostPowder, 2), true, frostRod);
        RecipesArmor.registerArmorRecipe(register);
        register.registerShapedRecipe(new ItemStack(fishingRodNickel), true, "  S", " SG", "SAG", 'A', nickelNugget, 'S', Item.stick, 'G', Item.silk);
        register.registerShapedRecipe(new ItemStack(fishingRodTungsten), true, "  S", " SG", "SAG", 'A', tungstenNugget, 'S', Item.stick, 'G', Item.silk);
        register.registerShapedRecipe(new ItemStack(bowTungsten, 1), true, "#C ", "#EC", "#C ", '#', silk, 'E', tungstenIngot, 'C', stick);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW", 'S', silk, 'W', stick);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW", 'S', sinew, 'W', stick);
        register.registerShapedRecipe(new ItemStack(detectorEmerald, 1), true, "FAF", "ANA", "FAF", 'A', Item.goldNugget, 'F', Item.ancientMetalNugget, 'N', Item.emerald);
        register.registerShapedRecipe(new ItemStack(detectorDiamond, 1), true, "FAF", "ANA", "FAF", 'A', Item.goldNugget, 'F', Item.ancientMetalNugget, 'N', Item.diamond);
        register.registerShapedRecipe(new ItemStack(woodBucket, 1), true, "W W", " W ", 'W', Block.wood);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "SP", "S ", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "S ", "SP", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", " S", "PS", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "PS", " S", 'F', Item.flint, 'S', Item.stick, 'P', sinew);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "PS", " S", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", " S", "PS", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "SP", "S ", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);
        register.registerShapedRecipe(new ItemStack(hoeFlint, 1), true, "FF", "S ", "SP", 'F', Item.flint, 'S', Item.stick, 'P', Item.silk);
        register.registerShapedRecipe(new ItemStack(stickKnife, 1), true, "S", "S", 'S', Item.stick);
        register.registerShapelessRecipe(new ItemStack(totemOfUnknown, 1), true, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon, Block.melon);
        register.registerShapelessRecipe(new ItemStack(peeledSugarcane, 2), false, Item.reed, Item.reed);
        register.registerShapelessRecipe(new ItemStack(sulphur, 9), true, new ItemStack(Blocks.blockSulphur, 1));
        register.registerShapelessRecipe(new ItemStack(Item.gunpowder, 5), true, new ItemStack(Items.sulphur, 8), new ItemStack(Item.coal, 1, 1));
        register.registerShapelessRecipe(new ItemStack(helmetAncientMetalSacred, 1), true, forgingnote, Item.ingotGold, Item.helmetAncientMetal);
        register.registerShapelessRecipe(new ItemStack(chestplateAncientMetalSacred, 1), true, forgingnote, Item.ingotGold, Item.plateAncientMetal);
        register.registerShapelessRecipe(new ItemStack(leggingsAncientMetalSacred, 1), true, forgingnote, Item.ingotGold, Item.legsAncientMetal);
        register.registerShapelessRecipe(new ItemStack(bootsAncientMetalSacred, 1), true, forgingnote, Item.ingotGold, Item.bootsAncientMetal);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 1), false, arrowTungsten);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 1), false, arrowNickel);
        register.registerShapelessRecipe(new ItemStack(mashedCactus, 1), true, Block.cactus);
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 1), true, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 1), true, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget);
        register.registerShapelessRecipe(new ItemStack(uruIngot, 1), true, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget, uruNugget);
        register.registerShapelessRecipe(new ItemStack(Item.leather, 1), true, wolf_fur, wolf_fur, wolf_fur, wolf_fur);
        register.registerShapelessRecipe(new ItemStack(Block.ice, 1), true, iceChunk, iceChunk, iceChunk, iceChunk);
        register.registerShapelessRecipe(new ItemStack(seedsBeetroot, 1), false, beetroot, beetroot);
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), false, beetroot);
        register.registerShapelessRecipe(new ItemStack(pulque, 1), true, Item.sugar, agave, new ItemStack(Item.potion, 1, 0));//.resetDifficulty(3200);
        register.registerShapelessRecipe(new ItemStack(ale, 1), true, Item.sugar, Item.wheat, new ItemStack(Item.potion, 1, 0));//.resetDifficulty(3200);
        register.registerShapelessRecipe(new ItemStack(clayBowlRaw, 1), false, Item.clay);
        register.registerShapelessRecipe(new ItemStack(tungstenBucket, 1), false, tungstenBucketStone);//.resetDifficulty(100);
        register.registerShapelessRecipe(new ItemStack(nickelBucket, 1), false, nickelBucketStone);//.resetDifficulty(100);

        RecipesFood.registerFoodRecipe(register);

        register.registerShapelessRecipe(new ItemStack(carrotOnAStickNickel, 1), false, Item.carrot, fishingRodNickel);
        register.registerShapelessRecipe(new ItemStack(carrotOnAStickTungsten, 1), false, Item.carrot, fishingRodTungsten);

        ItemCoin[] coins = {nickelCoin, tungstenCoin};
        for (ItemCoin coin : coins) {
            for (int plank_subtype = 1; plank_subtype <= 9; plank_subtype++) {
                register.registerShapelessRecipe(new ItemStack(coin.getNuggetPeer(), plank_subtype), true, new ItemStack(coin, plank_subtype));//.resetDifficulty(25); TODO what is resetDifficulty
            }
            register.registerShapelessRecipe(new ItemStack(coin), true, new ItemStack(coin.getNuggetPeer()));
        }
        RecipesFurnaceExtend.registerFurnaceRecipes();
    }
}
