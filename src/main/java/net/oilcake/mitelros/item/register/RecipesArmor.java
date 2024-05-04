package net.oilcake.mitelros.item.register;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ItemMorningStar;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class RecipesArmor extends Items {
    public static void registerArmorRecipe(RecipeRegistryEvent register) {
        registerArmorRecipe(register, wolf_fur, Materials.wolf_fur);
        registerArmorRecipe(register, iceChunk, Materials.ice_chunk);
        registerITFToolRecipe(register);
        registerFullMetalToolRecipe(register, Materials.nickel);
        registerFullMetalToolRecipe(register, Materials.tungsten);
        uruRecipes(register);
    }

    public static void registerArmorRecipe(RecipeRegistryEvent register, Item item, Material material) {
        if (item instanceof ItemChain) {
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class, material, true)), true, "AAA", "A A", 'A', item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class, material, true)), true, "A A", "AAA", "AAA", 'A', item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class, material, true)), true, "AAA", "A A", "A A", 'A', item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class, material, true)), true, "A A", "A A", 'A', item);
        } else {
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class, material, false)), true, "AAA", "A A", 'A', item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class, material, false)), true, "A A", "AAA", "AAA", 'A', item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class, material, false)), true, "AAA", "A A", "A A", 'A', item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class, material, false)), true, "A A", "A A", 'A', item);
        }
    }

    public static void registerFullMetalToolRecipe(RecipeRegistryEvent register, Material material) {
        registerBasicToolRecipes(register, material);
        registerMITEToolRecipeForITFMaterial(register, material);
    }

    public static void registerBasicToolRecipes(RecipeRegistryEvent register, Material material) {
        Item item = Item.getMatchingItem(ItemIngot.class, material);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemSword.class, material)), true, "A", "A", "S", 'A', item, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHoe.class, material)), true, "AA", "S ", "S ", 'A', item, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemAxe.class, material)), true, "AA", "SA", "S ", 'A', item, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShovel.class, material)), true, "A", "S", "S", 'A', item, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemPickaxe.class, material)), true, "AAA", " S ", " S ", 'A', item, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShears.class, material)), true, "A ", " A", 'A', item);
        registerArmorRecipe(register, item, material);
    }

    public static void registerMITEToolRecipeForITFMaterial(RecipeRegistryEvent register, Material material) {
        Item item_ingot = Item.getMatchingItem(ItemIngot.class, material);
        Item item_nugget = getMatchingItem(ItemNugget.class, item_ingot.getExclusiveMaterial());
        Item item_chain = Item.getMatchingItem(ItemChain.class, material);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemArrow.class, material)), true, "C", "B", "A", 'A', Item.feather, 'B', Item.stick, 'C', item_nugget);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemChain.class, material)), true, " A ", "A A", " A ", 'A', item_nugget);
        registerArmorRecipe(register, item_chain, material);
        register.registerShapedRecipe(new ItemStack(ItemBucket.getPeer(material, null)), true, "A A", " A ", 'A', item_ingot);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemScythe.class, material)), true, "SA ", "S A", "S  ", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemMattock.class, material)), true, "AAA", " SA", " S ", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHatchet.class, material)), true, " BA", " B ", 'A', item_ingot, 'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemWarHammer.class, material)), true, "AAA", "ABA", " B ", 'A', item_ingot, 'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDagger.class, material)), true, " A ", " B ", 'A', item_ingot, 'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemBattleAxe.class, material)), true, "A A", "ABA", " B ", 'A', item_ingot, 'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDoor.class, material)), true, "AA", "AA", "AA", 'A', item_ingot);
    }

    public static void registerITFToolRecipe(RecipeRegistryEvent register) {
        Material[] materials = new Material[]{Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Material.ancient_metal, Material.mithril, Materials.tungsten, Material.adamantium};

        for (Material material : materials) {
            Item item = Item.getMatchingItem(ItemIngot.class, material);
            Item item_nugget = getMatchingItem(ItemNugget.class, material);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemMorningStar.class, material), 1), true, "###", "#*#", " # ", '#', item_nugget, '*', item);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemFlintAndSteel.class, material)), true, "C ", " F", 'C', item_nugget, 'F', flint);
        }

    }

    public static void uruRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(forgingnote, 2), false, forgingnote, Item.writableBook);
        register.registerShapelessRecipe(new ItemStack(uruHelmet, 1), true, forgingnote, uruIngot, Item.helmetMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruChestplate, 1), true, forgingnote, uruIngot, Item.plateMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruLeggings, 1), true, forgingnote, uruIngot, Item.legsMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruBoots, 1), true, forgingnote, uruIngot, Item.bootsMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruSword, 1), true, forgingnote, uruIngot, Item.swordMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruScythe, 1), true, forgingnote, uruIngot, Item.scytheMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruBattleAxe, 1), true, forgingnote, uruIngot, Item.battleAxeMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruWarHammer, 1), true, forgingnote, uruIngot, Item.warHammerMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruMattock, 1), true, forgingnote, uruIngot, Item.mattockMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruMorningStar, 1), true, forgingnote, uruIngot, morningStarMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(bowUru, 1), true, forgingnote, uruIngot, Item.bowMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruPickaxe, 1), true, forgingnote, uruIngot, Item.pickaxeMithril, Item.ingotMithril);
        register.registerShapelessRecipe(new ItemStack(uruNugget, 9), true, uruIngot);
    }
}
