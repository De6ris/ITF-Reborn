package net.oilcake.mitelros.status;

import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Items;

public class MiscManager {

    private EntityPlayer player;

    public MiscManager(EntityPlayer player) {
        this.player = player;
    }

    public boolean UnderArrogance() {
        boolean Hel_Arro = false;
        boolean Cst_Arro = false;
        boolean Lgs_Arro = false;
        boolean Bts_Arro = false;
        boolean Hnd_Arro = false;
        ItemStack Helmet = player.getHelmet();
        ItemStack Cuirass = player.getCuirass();
        ItemStack Leggings = player.getLeggings();
        ItemStack Boots = player.getBoots();
        ItemStack Holding = player.getHeldItemStack();
        if (Helmet != null)
            Hel_Arro = EnchantmentHelper.hasEnchantment(Helmet, Enchantments.enchantmentArrogance);
        if (Cuirass != null)
            Cst_Arro = EnchantmentHelper.hasEnchantment(Cuirass, Enchantments.enchantmentArrogance);
        if (Leggings != null)
            Lgs_Arro = EnchantmentHelper.hasEnchantment(Leggings, Enchantments.enchantmentArrogance);
        if (Boots != null)
            Bts_Arro = EnchantmentHelper.hasEnchantment(Boots, Enchantments.enchantmentArrogance);
        if (Holding != null)
            Hnd_Arro = EnchantmentHelper.hasEnchantment(Holding, Enchantments.enchantmentArrogance);
        boolean Arro = (Hel_Arro || Cst_Arro || Lgs_Arro || Bts_Arro || Hnd_Arro);
        return (this.player.experience < 2300 && Arro);
    }

    public int getWeight(EntityPlayer player) {
        int weight = 0;
        ItemStack helmet = player.getHelmet();
        ItemStack cuirass = player.getCuirass();
        ItemStack leggings = player.getLeggings();
        ItemStack boots = player.getBoots();
        if (helmet != null) {
            weight += 2;
            if (helmet.itemID == Items.wolfHelmet.itemID) weight += 2;
            else if (helmet.itemID == Item.helmetLeather.itemID) weight += 1;
        }
        if (cuirass != null) {
            weight += 2;
            if (cuirass.itemID == Items.wolfChestplate.itemID) weight += 2;
            else if (cuirass.itemID == Item.plateLeather.itemID) weight += 1;
        }
        if (leggings != null) {
            weight += 2;
            if (leggings.itemID == Items.wolfLeggings.itemID) weight += 2;
            else if (leggings.itemID == Item.legsLeather.itemID) weight += 1;
        }
        if (boots != null) {
            weight += 2;
            if (boots.itemID == Items.wolfBoots.itemID) weight += 2;
            else if (boots.itemID == Item.bootsLeather.itemID) weight += 1;
        }
        return weight;
    }
}
