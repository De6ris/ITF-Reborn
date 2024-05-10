package net.oilcake.mitelros.item.register;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ItemMorningStar;

public class RecipesFurnaceExtend extends Items {
    public static void registerFurnaceRecipes() {
        FurnaceRecipes.smelting().addSmelting(pieceAdamantium.itemID, new ItemStack(adamantiumNugget));
        FurnaceRecipes.smelting().addSmelting(pieceCopper.itemID, new ItemStack(copperNugget));
        FurnaceRecipes.smelting().addSmelting(pieceGold.itemID, new ItemStack(goldNugget));
        FurnaceRecipes.smelting().addSmelting(pieceGoldNether.itemID, new ItemStack(goldNugget));
        FurnaceRecipes.smelting().addSmelting(pieceSilver.itemID, new ItemStack(silverNugget));
        FurnaceRecipes.smelting().addSmelting(pieceIron.itemID, new ItemStack(ironNugget));
        FurnaceRecipes.smelting().addSmelting(pieceNickel.itemID, new ItemStack(nickelNugget));
        FurnaceRecipes.smelting().addSmelting(pieceMithril.itemID, new ItemStack(mithrilNugget));
        FurnaceRecipes.smelting().addSmelting(pieceTungsten.itemID, new ItemStack(tungstenNugget));
        FurnaceRecipes.smelting().addSmelting(pieceUru.itemID, new ItemStack(uruNugget));
        FurnaceRecipes.smelting().addSmelting(ancientMetalArmorPiece.itemID, new ItemStack(ancientMetalNugget));
        FurnaceRecipes.smelting().addSmelting(clayBowlWaterSuspicious.itemID, new ItemStack(clayBowlHotWater));
        FurnaceRecipes.smelting().addSmelting(clayBowlWaterSwampland.itemID, new ItemStack(clayBowlHotWater));
        FurnaceRecipes.smelting().addSmelting(clayBowlWater.itemID, new ItemStack(clayBowlHotWater));
        FurnaceRecipes.smelting().addSmelting(bowlWaterSuspicious.itemID, new ItemStack(bowlHotWater));
        FurnaceRecipes.smelting().addSmelting(bowlWaterSwampland.itemID, new ItemStack(bowlHotWater));
        FurnaceRecipes.smelting().addSmelting(bowlWater.itemID, new ItemStack(bowlHotWater));
        FurnaceRecipes.smelting().addSmelting(suspiciousPotion.itemID, new ItemStack(potion, 1, 0));
        FurnaceRecipes.smelting().addSmelting(horse_meat.itemID, new ItemStack(horse_meat_cooked));
        FurnaceRecipes.smelting().addSmelting(clayBowlRaw.itemID, new ItemStack(clayBowlEmpty));
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
