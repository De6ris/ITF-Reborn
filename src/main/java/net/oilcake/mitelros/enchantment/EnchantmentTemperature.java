package net.oilcake.mitelros.enchantment;

import net.minecraft.*;

public class EnchantmentTemperature extends Enchantment {
    protected EnchantmentTemperature(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    public int getNumLevels() {
        return 1;
    }

    public String getNameSuffix() {
        if (this == Enchantments.enchantmentCallOfNether) {
            return "temperature.callofnether";
        } else if (this == Enchantments.enchantmentCallOfPolar) {
            return "temperature.callofpolar";
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
