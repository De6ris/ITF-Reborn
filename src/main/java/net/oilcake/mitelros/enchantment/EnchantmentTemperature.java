package net.oilcake.mitelros.enchantment;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFEnchantment;

public class EnchantmentTemperature extends Enchantment implements ITFEnchantment {
    protected EnchantmentTemperature(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    public int getNumLevels() {
        return 1;
    }

    public String getNameSuffix() {
        if (this == Enchantments.enchantmentFrostResistance) {
            return "temperature.frost_immunity";
        } else if (this == Enchantments.enchantmentHeatResistance) {
            return "temperature.heat_immunity";
        } else {
            Minecraft.setErrorMessage("getNameSuffix: no handler for " + this);
            return null;
        }
    }

    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return !(par1Enchantment instanceof EnchantmentTemperature);
    }

    public boolean canEnchantItem(Item item) {
        return item instanceof net.minecraft.ItemCuirass;
    }

    public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
        return (creativeModeTab == CreativeTabs.tabCombat);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}
