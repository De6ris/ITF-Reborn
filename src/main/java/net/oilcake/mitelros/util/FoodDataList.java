package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;

import java.util.Random;

public class FoodDataList {
    public static int bowlFoodWater(Material material) {
        if (material == (Material.water) || material == (Material.cereal) || material == (Material.ice_cream) || material == (Material.milk) || material == Materials.hot_water) {
            return 2;
        } else if (material == (Materials.dangerous_water) || material == (Materials.suspicious_water)) {
            return 1;
        } else if (material == (Materials.beetroot_soup)) {
            return 6;
        } else if (!(material == null || material == (Material.mashed_potato) || material == (Materials.salad))) {
            return 4;
        }
        return 0;
    }

    public static int foodWater(int id, Material material) {// 137 carrot; it is called when initialize
        if (material == Material.fruit | id == 137  || material == Materials.ice_sucker || material == Materials.melon_ice || material == Materials.chocolate_smoothie)
            return ITFConfig.TagDryDilemma.get() ? 1 : 2;
        if (material == (Materials.glowberries) || material == Materials.peeledSugarcane || material == Materials.agave || material == Materials.mashedCactus)
            return 1;
        if (material == Material.cheese || id == 88 || material == Material.bread || material == Material.desert)
            return -1;
        return 0;
    }

    public static int foodTemperature(Material material) {
        if (material == Material.fruit) return -1;
        if (material == Materials.ice_sucker || material == Materials.melon_ice) return -2;
        if (material == Materials.chocolate_smoothie) return -3;
        return 0;
    }

    public static int bowlFoodTemperature(Material material) {
        if (material == (Material.water) || material == (Materials.dangerous_water)
                || material == (Materials.suspicious_water) || material == (Material.milk)
                || material == (Materials.lemonade)) {
            return -1;
        } else if (material == (Materials.ice_cream) || material == (Materials.sorbet)) {
            return -2;
        } else if (material == (Materials.porkchop_stew) || material == (Materials.lampchop_stew)
                || material == (Material.beef_stew) || material == (Material.chicken_soup)
                || material == (Materials.beetroot_soup) || material == (Material.vegetable_soup)
                || material == (Materials.fish_soup) || material == (Material.mushroom_stew)
                || material == (Material.porridge) || material == (Materials.cereal)) {
            return 3;
        } else if (material == (Materials.hot_water) || material == (Material.cream_of_mushroom_soup) || material == (Material.cream_of_vegetable_soup)) {
            return 2;
        }
        return 0;
    }

    public static void onFoodEaten(ItemStack item_stack, EntityPlayer player) {
        Random rand = player.rand;
        if (item_stack.itemID == Item.rottenFlesh.itemID)
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 600, 0));
        if (item_stack.hasMaterial(Material.bread) || item_stack.hasMaterial(Material.desert))
            player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
        if (rand.nextFloat() < chanceOfDecreaseWater(item_stack.itemID)) {
            player.addWater(-1);
        }
        if (item_stack.itemID == Item.egg.itemID) {
            if (rand.nextDouble() > (ITFConfig.TagDryDilemma.get() ? 0.5D : 0.25D))
                player.addWater(1);
        }
        if (item_stack.getItem() instanceof ItemMeat meat) {
            if (meat.is_cooked) {
                player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
                player.addPotionEffect(new PotionEffect(PotionExtend.warm.id, 3000, 0));
            } else {
                if (ITFConfig.TagDigest.get()) return;
                if (ITFConfig.Realistic.get() || rand.nextInt(4) == 0) {
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (120.0D * (1.0D + rand.nextDouble())), 0));
                }
            }
        }
    }

    public static float chanceOfDecreaseWater(int itemID) {
        if (itemID == Items.agave.itemID) return ITFConfig.TagDryDilemma.get() ? 0.8F : 0.6F;
        if (itemID == Items.glowberries.itemID) return ITFConfig.TagDryDilemma.get() ? 0.5F : 0.0F;
        return 0.0F;
    }
}
