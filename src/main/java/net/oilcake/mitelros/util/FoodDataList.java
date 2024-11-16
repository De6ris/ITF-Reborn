package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.api.IWateryMaterial;
import net.oilcake.mitelros.item.potion.PotionExtend;

import java.util.Random;

public class FoodDataList {
    public static int bowlFoodWater(Material material) {
        if (material instanceof IWateryMaterial iWateryMaterial) {
            return iWateryMaterial.getWater();
        }
        if (material == Material.water) {
            return 1;// changed
        }
        if (material == (Material.cereal) || material == (Material.ice_cream) || material == (Material.milk)) {
            return 2;
        } else if (!(material == null || material == (Material.mashed_potato) || material == (Materials.salad))) {
            return 4;
        }
        return 0;
    }

    public static int foodWater(int id, Material material) {
        if (material == Material.fruit)
            return ITFConfig.TagDryDilemma.getBooleanValue() ? 1 : 2;
        if (material == Material.desert)
            return -1;
        return 0;
    }

    public static void onWaterDrunk(Item item, EntityPlayer player) {
        if (item.hasMaterial(Material.water)) {
            float randomFloat = player.rand.nextFloat();
            if (randomFloat > 0.8D)
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
            player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int) (160.0D * (1.0D + randomFloat)), 0));
        }
    }

    public static void onFoodEaten(ItemStack item_stack, EntityPlayer player) {
        Random rand = player.rand;
        Item item = item_stack.getItem();
        if (item == Item.rottenFlesh)
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 600, 0));
        if (item_stack.hasMaterial(Material.bread) || item_stack.hasMaterial(Material.desert))
            player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
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
}
