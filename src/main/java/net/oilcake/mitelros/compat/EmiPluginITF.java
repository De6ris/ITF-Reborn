package net.oilcake.mitelros.compat;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiInfoRecipe;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.ItemRock;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import shims.java.com.unascribed.retroemi.RetroEMI;
import shims.java.net.minecraft.text.Text;

import java.util.List;

public class EmiPluginITF implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(RecipeCategory.EnchantReserverIn);
        registry.addCategory(RecipeCategory.EnchantReserverOut);
        registry.addWorkstation(RecipeCategory.EnchantReserverIn, EmiStack.of(Blocks.blockEnchantReserver));
        registry.addWorkstation(RecipeCategory.EnchantReserverOut, EmiStack.of(Blocks.blockEnchantReserver));

        registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(new ItemStack(Blocks.nickelWorkBench)));
        registry.addWorkstation(VanillaEmiRecipeCategories.CRAFTING, EmiStack.of(new ItemStack(Blocks.tungstenWorkBench)));

        registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(Blocks.blastFurnaceStoneIdle));
        registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(Blocks.blastFurnaceObsidianIdle));
        registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(Blocks.blastFurnaceNetherrackIdle));
        registry.addWorkstation(VanillaEmiRecipeCategories.SMELTING, EmiStack.of(Blocks.blockSmokerIdle));

        this.addInfoRecipes(registry);
        this.addEnchantReserverRecipes(registry);
    }

    private void addInfoRecipes(EmiRegistry registry) {
        info(registry, Items.totemOfKnowledge, "itf.item.totem_of_knowledge.info");
        info(registry, Items.totemOfFlattening, "itf.item.totem_of_flattening.info");
        info(registry, Items.frostRod, "itf.item.frost_rod.info");
        info(registry, Items.enderRod, "itf.item.ender_rod.info");
        info(registry, Item.emerald, "itf.item.emerald.info");
    }

    private void addEnchantReserverRecipes(EmiRegistry registry) {
        enchantReserverIn(registry, Item.diamond);
        enchantReserverIn(registry, Item.emerald);
        enchantReserverIn(registry, Item.netherQuartz);
        enchantReserverIn(registry, Item.dyePowder, 4);
        enchantReserverIn(registry, Items.shardAzurite);

        enchantReserverOut(registry, Item.coinCopper);
        enchantReserverOut(registry, Item.coinSilver);
        enchantReserverOut(registry, Items.nickelCoin);
        enchantReserverOut(registry, Item.coinGold);
        enchantReserverOut(registry, Item.coinAncientMetal);
        enchantReserverOut(registry, Item.coinMithril);
        enchantReserverOut(registry, Items.tungstenCoin);
        enchantReserverOut(registry, Item.coinAdamantium);
        registry.addRecipe(new EnchantReserverOutRecipe(RetroEMI.wildcardIngredient(new ItemStack(Item.potion, 1, 32767)), EmiStack.of(Item.expBottle), 200));
    }

    private void enchantReserverIn(EmiRegistry registry, Item item) {
        registry.addRecipe(new EnchantReserverInRecipe(EmiStack.of(item), ItemRock.getExperienceValueWhenSacrificed(new ItemStack(item))));
    }

    private void enchantReserverIn(EmiRegistry registry, Item item, int metadata) {
        ItemStack itemStack = new ItemStack(item, 1, metadata);
        registry.addRecipe(new EnchantReserverInRecipe(EmiStack.of(itemStack), ItemRock.getExperienceValueWhenSacrificed(itemStack)));
    }

    private void enchantReserverOut(EmiRegistry registry, ItemCoin item) {
        registry.addRecipe(new EnchantReserverOutRecipe(EmiStack.of(item.getNuggetPeer()), EmiStack.of(item), item.getExperienceValue()));
    }

    private void info(EmiRegistry registry, Item item, String info) {
        registry.addRecipe(new EmiInfoRecipe(List.of(EmiStack.of(item)), List.of(Text.translatable(info)), null));
    }
}
