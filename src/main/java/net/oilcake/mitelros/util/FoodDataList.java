package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;

import java.util.Random;

public class FoodDataList {
    public static int bowlFoodWater(Material material) {
        if (material == (Material.water) || material == (Material.cereal) || material == (Material.ice_cream) || material == (Material.milk)) {
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

    public static int foodWater(int id, Material material) {// 135 carrot; it is called at init
        if (material == Material.fruit | id == 135 || material == Materials.ice_sucker || material == Materials.melon_ice || material == Materials.chocolate_smoothie)
            return ITFConfig.TagDryDilemma.getBooleanValue() ? 1 : 2;
        if (material == (Materials.glowberries) || material == Materials.peeledSugarcane || material == Materials.agave || material == Materials.mashedCactus)
            return 1;
        if (material == Material.cheese || material == Material.bread || material == Material.desert)
            return -1;
        return 0;
    }

    public static void onWaterDrunk(Item item, EntityPlayer player) {
        if (item.hasMaterial(Materials.dangerous_water)) {
            double rand = Math.random();
            if (rand > 0.2D)
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
            player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
        }
        if (item.hasMaterial(Materials.suspicious_water)) {
            double rand = Math.random();
            if (rand > 0.8D)
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
            player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + rand)), 0));
        }
    }

    public static void onFoodEaten(ItemStack item_stack, EntityPlayer player) {
        Random rand = player.rand;
        Item item = item_stack.getItem();
        if (item == Item.rottenFlesh)
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 600, 0));
        if (item_stack.hasMaterial(Material.bread) || item_stack.hasMaterial(Material.desert))
            player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
        if (rand.nextFloat() < chanceOfDecreaseWater(item)) {
            player.itf$AddWater(-1);
        }
        if (item instanceof ItemMeat meat) {
            if (meat.is_cooked) {
                player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
            } else {
                if (ITFConfig.TagDigest.getBooleanValue()) return;
                if (rand.nextInt(4) == 0) {
                    player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (120.0D * (1.0D + rand.nextDouble())), 0));
                }
            }
        }
    }

    public static float chanceOfDecreaseWater(Item item) {
        if (item == Items.agave) return ITFConfig.TagDryDilemma.getBooleanValue() ? 0.8F : 0.6F;
        if (item == Items.glowberries) return ITFConfig.TagDryDilemma.getBooleanValue() ? 0.5F : 0.0F;
        return 0.0F;
    }
}
