package net.oilcake.mitelros.item.register;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ItemMorningStar;

public class FurnaceRecipesExtend extends Items {
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
        FurnaceRecipes.smelting().addSmelting(clayBowlWaterSuspicious.itemID, new ItemStack(clayBowlWater));
        FurnaceRecipes.smelting().addSmelting(clayBowlWaterSwampland.itemID, new ItemStack(clayBowlWater));
        FurnaceRecipes.smelting().addSmelting(bowlWaterSuspicious.itemID, new ItemStack(bowlWater));
        FurnaceRecipes.smelting().addSmelting(bowlWaterSwampland.itemID, new ItemStack(bowlWater));
        FurnaceRecipes.smelting().addSmelting(suspiciousPotion.itemID, new ItemStack(potion, 1, 0));
        FurnaceRecipes.smelting().addSmelting(horse_meat.itemID, new ItemStack(horse_meat_cooked));
        FurnaceRecipes.smelting().addSmelting(clayBowlRaw.itemID, new ItemStack(clayBowlEmpty));
        FurnaceRecipes.smelting().addSmelting(leatherKettleSuspicious.itemID, new ItemStack(leatherKettle));
        FurnaceRecipes.smelting().addSmelting(leatherKettleSwampland.itemID, new ItemStack(leatherKettle));
        FurnaceRecipes.smelting().addSmelting(hardenedClayJugSuspicious.itemID, new ItemStack(hardenedClayJug));
        FurnaceRecipes.smelting().addSmelting(hardenedClayJugSwampland.itemID, new ItemStack(hardenedClayJug));
        FurnaceRecipes.smelting().addSmelting(clayJug.itemID, new ItemStack(hardenedClayJug).setItemDamage(hardenedClayJug.maxDamage - 1));
        ItemFood.setCookingResult((ItemFood) horse_meat, (ItemFood) horse_meat_cooked, 6);
        registerBlastFurnaceRecipes();
    }

    private static void registerBlastFurnaceRecipes() {
        Class<?>[] tools = {
                ItemSword.class, ItemAxe.class, ItemPickaxe.class, ItemHoe.class, ItemShovel.class,
                ItemWarHammer.class, ItemBattleAxe.class, ItemScythe.class, ItemDagger.class, ItemKnife.class,
                ItemMorningStar.class, ItemHatchet.class, ItemShears.class, ItemMattock.class};
        Class<?>[] armors = {
                ItemHelmet.class, ItemCuirass.class, ItemLeggings.class, ItemBoots.class
        };
        Material[] available_material = {Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Materials.tungsten, Material.ancient_metal, Material.rusted_iron};

        for (Material material : available_material) {
            for (Class<?> tool : tools) {
                registerRecipeSafe(Item.getMatchingItem(tool, material), new ItemStack(appleRed));
            }
            for (Class<?> armor : armors) {
                registerRecipeSafe(ItemArmor.getMatchingArmor(armor, material, false), new ItemStack(appleRed));
                registerRecipeSafe(ItemArmor.getMatchingArmor(armor, material, true), new ItemStack(appleRed));
            }
        }
    }

    private static void registerRecipeSafe(Item item, ItemStack itemStack) {
        if (item != null && itemStack != null) {
            FurnaceRecipes.smelting().addSmelting(item.itemID, itemStack);
        }
    }

    public static <T extends Item & IDamageableItem> ItemStack recycleArmors(ItemStack input_item_stack, T armor) {
        float ingotToNugget = input_item_stack.getItem().isChainMail() ? 4.0F : 9.0F;
        float durabilityRatio = (input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / (float) input_item_stack.getMaxDamage();
        float component = armor.getNumComponentsForDurability();
        int quantity = (int) (durabilityRatio * component * ingotToNugget / 3.0F);
        return armorItemStack(armor.getHardestMetalMaterial(), quantity, armor.getMaterialForRepairs());
    }

    private static ItemStack armorItemStack(Material hardestMaterial, int quantity, Material materialForRepair) {
        ItemStack output;
        if (hardestMaterial == Material.rusted_iron) {
            quantity /= 3;
            output = new ItemStack(Item.getMatchingItem(ItemNugget.class, Material.iron), quantity);
        } else {
            output = new ItemStack(Item.getMatchingItem(ItemNugget.class, materialForRepair), quantity);
        }
        if (quantity == 0) {
            output.setStackSize(1);
        }
        return output;
    }
}
