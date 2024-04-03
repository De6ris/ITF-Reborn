package net.oilcake.mitelros.status;

import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Items;

public class WeightManager {
    public int getWeight(EntityPlayer player) {
        int weight = 0;
        ItemStack helmet = player.getHelmet();
        ItemStack cuirass = player.getCuirass();
        ItemStack leggings = player.getLeggings();
        ItemStack boots = player.getBoots();
        if (helmet != null) {
            weight += 2;
            if (helmet.itemID == Items.WolfHelmet.itemID) weight += 2;
            else if (helmet.itemID == Item.helmetLeather.itemID) weight += 1;
        }
        if (cuirass != null) {
            weight += 2;
            if (cuirass.itemID == Items.WolfChestplate.itemID) weight += 2;
            else if (cuirass.itemID == Item.plateLeather.itemID) weight += 1;
        }
        if (leggings != null) {
            weight += 2;
            if (leggings.itemID == Items.WolfLeggings.itemID) weight += 2;
            else if (leggings.itemID == Item.legsLeather.itemID) weight += 1;
        }
        if (boots != null) {
            weight += 2;
            if (boots.itemID == Items.WolfBoots.itemID) weight += 2;
            else if (boots.itemID == Item.bootsLeather.itemID) weight += 1;
        }
        return weight;
    }
}
