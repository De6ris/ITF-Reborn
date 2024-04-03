package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.Item;
import net.minecraft.EnumRarity;

public class EnchantmentCallofNether extends Enchantment {
  protected EnchantmentCallofNether(int id, EnumRarity rarity, int difficulty) {
    super(id, rarity, difficulty);
  }
  
  public int getNumLevels() {
    return 1;
  }
  
  public String getNameSuffix() {
    return "callofnether";
  }
  
  public boolean canEnchantItem(Item item) {
    return item instanceof net.minecraft.ItemCuirass;
  }
  
  public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
    return (creativeModeTab == CreativeTabs.tabCombat);
  }
}
