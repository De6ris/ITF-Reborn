package net.oilcake.mitelros.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.xiaoyu233.fml.reload.event.EnchantmentRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Enchantments {
    private static int getNextEnchantmentID() {
        return IdUtil.getNextEnchantmentID();
    }
    
    public static final Enchantment enchantmentMelting = new EnchantmentMelting(getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentAbsorb = new EnchantmentAbsorb(getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentVanishing = new EnchantmentVanishing(getNextEnchantmentID(), EnumRarity.rare, 15);

    public static final Enchantment enchantmentFrostResistance = new EnchantmentTemperature(getNextEnchantmentID(), EnumRarity.uncommon, 10);

    public static final Enchantment enchantmentDestroying = new EnchantmentDestroying(getNextEnchantmentID(), EnumRarity.epic, 20);

    public static final Enchantment enchantmentInfinity = new EnchantmentInfinity(getNextEnchantmentID(), EnumRarity.rare, 20);

    public static final Enchantment enchantmentArrogance = new EnchantmentArrogance(getNextEnchantmentID(), EnumRarity.rare, 15);

    public static final Enchantment enchantmentThresher = new EnchantmentThresher(getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentSweeping = new EnchantmentSweeping(getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentMending = new EnchantmentMending(getNextEnchantmentID(), EnumRarity.epic, 20);
    public static final Enchantment enchantmentSunlightMending = new EnchantmentMending(getNextEnchantmentID(), EnumRarity.epic, 20);
    public static final Enchantment enchantmentMoonlightMending = new EnchantmentMending(getNextEnchantmentID(), EnumRarity.epic, 20);
    public static final Enchantment enchantmentHeatResistance = new EnchantmentTemperature(getNextEnchantmentID(), EnumRarity.uncommon, 10);

    public static void registerEnchantments(EnchantmentRegistryEvent event) {
//        event.registerEnchantment(enchantmentMelting, enchantmentAbsorb, enchantmentVanishing, enchantmentDestroying, enchantmentCallofNether, enchantmentInfinity, enchantmentArrogance, enchantmentThresher, enchantmentSweeping, enchantmentMending);
    }
}
