package net.oilcake.mitelros.item.register;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.ItemMorningStar;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;

public class RecipesArmor extends Items {
    public static void registerArmorRecipe(RecipeRegistryEvent register) {
        registerArmorRecipe(register, wolf_fur, Materials.wolf_fur, false);
        registerArmorRecipe(register, iceChunk, Materials.ice_chunk, false);
        registerITFToolRecipe(register);
        registerFullMetalToolRecipe(register, Materials.nickel);
        registerFullMetalToolRecipe(register, Materials.tungsten);
        registerUruMetalRecipes(register);
    }

    private static void registerArmorRecipe(RecipeRegistryEvent register, Item item, Material material, boolean isChainMail) {
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class, material, isChainMail)), true, "AAA", "A A", 'A', item);
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class, material, isChainMail)), true, "A A", "AAA", "AAA", 'A', item);
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class, material, isChainMail)), true, "AAA", "A A", "A A", 'A', item);
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class, material, isChainMail)), true, "A A", "A A", 'A', item);
    }

    private static void registerITFToolRecipe(RecipeRegistryEvent register) {
        Material[] materials = new Material[]{Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Material.ancient_metal, Material.mithril, Materials.tungsten, Material.adamantium};
        for (Material material : materials) {
            Item item = Item.getMatchingItem(ItemIngot.class, material);
            Item item_nugget = getMatchingItem(ItemNugget.class, material);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemMorningStar.class, material), 1), true, "###", "#*#", " # ", '#', item_nugget, '*', item);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemFlintAndSteel.class, material)), true, "C ", " F", 'C', item_nugget, 'F', flint);
            register.registerShapedRecipe(new ItemStack(leatherKettle, 1).setItemDamage(leatherKettle.maxDamage - 1), false, "#N", "JL", 'J', Item.sinew, '#', Item.silk, 'N', item_nugget, 'L', Item.leather).difficulty(2000);
        }
    }

    private static void registerFullMetalToolRecipe(RecipeRegistryEvent register, Material material) {
        Item item_ingot = Item.getMatchingItem(ItemIngot.class, material);
        Item item_nugget = getMatchingItem(ItemNugget.class, item_ingot.getExclusiveMaterial());
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemArrow.class, material)), true, "C", "B", "A", 'A', Item.feather, 'B', Item.stick, 'C', item_nugget);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemChain.class, material)), true, " A ", "A A", " A ", 'A', item_nugget);
        Item item_chain = Item.getMatchingItem(ItemChain.class, material);
        registerArmorRecipe(register, item_chain, material, true);
        registerArmorRecipe(register, item_ingot, material, false);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemSword.class, material)), true, "A", "A", "S", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHoe.class, material)), true, "AA", "S ", "S ", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemAxe.class, material)), true, "AA", "SA", "S ", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShovel.class, material)), true, "A", "S", "S", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemPickaxe.class, material)), true, "AAA", " S ", " S ", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShears.class, material)), true, "A ", " A", 'A', item_ingot);
        register.registerShapedRecipe(new ItemStack(ItemBucket.getPeer(material, null)), true, "A A", " A ", 'A', item_ingot);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemScythe.class, material)), true, "SA ", "S A", "S  ", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemMattock.class, material)), true, "AAA", " SA", " S ", 'A', item_ingot, 'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHatchet.class, material)), true, " BA", " B ", 'A', item_ingot, 'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemWarHammer.class, material)), true, "AAA", "ABA", " B ", 'A', item_ingot, 'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDagger.class, material)), true, " A ", " B ", 'A', item_ingot, 'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemBattleAxe.class, material)), true, "A A", "ABA", " B ", 'A', item_ingot, 'B', Item.stick);

    }

    private static void registerUruMetalRecipes(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(forgingNote, 2), false, forgingNote, Item.writableBook);

        registerUruItem(register, uruHelmet, helmetMithril);
        registerUruItem(register, uruChestplate, plateMithril);
        registerUruItem(register, uruLeggings, legsMithril);
        registerUruItem(register, uruBoots, bootsMithril);
        registerUruItem(register, uruSword, swordMithril);
        registerUruItem(register, uruScythe, scytheMithril);
        registerUruItem(register, uruBattleAxe, battleAxeMithril);
        registerUruItem(register, uruWarHammer, warHammerMithril);
        registerUruItem(register, uruMattock, mattockMithril);
        registerUruItem(register, uruMorningStar, morningStarMithril);
        registerUruItem(register, bowUru, bowMithril);
        registerUruItem(register, uruPickaxe, pickaxeMithril);

        register.registerShapelessRecipe(new ItemStack(uruNugget, 9), true, uruIngot);
    }

    // 25600 is the difficulty of adamantium ingot
    private static <T extends Item & IDamageableItem> void registerUruItem(RecipeRegistryEvent register, T uruItem, Item mithrilItem) {
        register.registerShapelessRecipe(new ItemStack(uruItem),
                        true,
                        forgingNote, uruIngot, mithrilItem, Item.ingotMithril)
                .difficulty(uruItem.getNumComponentsForDurability() * 25600.0F);
    }
}
