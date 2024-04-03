package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.Item;
import net.minecraft.EnumRarity;

public class EnchantmentSweeping extends Enchantment {
  protected EnchantmentSweeping(int id, EnumRarity rarity, int difficulty) {
    super(id, rarity, difficulty);
  }
  
  public int getNumLevels() {
    return 5;
  }
  
  public String getNameSuffix() {
    return "sweeping";
  }
  
  public boolean canEnchantItem(Item item) {
    return item instanceof net.minecraft.ItemScythe;
  }
  
  public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
    return (creativeModeTab == CreativeTabs.tabCombat);
  }
}
