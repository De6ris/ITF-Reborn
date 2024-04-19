package net.oilcake.mitelros.enchantment;

import net.minecraft.*;

public class EnchantmentMending extends Enchantment {
    protected EnchantmentMending(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    public int getNumLevels() {
        return 1;
    }

    public String getNameSuffix() {
        if (this == Enchantments.enchantmentMending) {
            return "mending.all";
        } else if (this == Enchantments.enchantmentSunlightMending) {
            return "mending.sunlight";
        } else if (this == Enchantments.enchantmentMoonlightMending) {
            return "mending.moonlight";
        } else {
            Minecraft.setErrorMessage("getNameSuffix: no handler for " + this);
            return null;
        }
    }

    public boolean canEnchantItem(Item item) {
        return (item instanceof net.minecraft.ItemTool || item instanceof net.minecraft.ItemArmor);
    }

    public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
        return (creativeModeTab == CreativeTabs.tabTools);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}
