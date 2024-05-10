package net.oilcake.mitelros.enchantment;

import net.minecraft.*;

public class EnchantmentSweeping extends Enchantment {
    protected EnchantmentSweeping(int id, EnumRarity rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 3;
    }

    @Override
    public String getNameSuffix() {
        return "sweeping";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemScythe;
    }

    @Override
    public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
        return (creativeModeTab == CreativeTabs.tabCombat);
    }
}
