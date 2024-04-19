package net.oilcake.mitelros.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.xiaoyu233.fml.reload.event.EnchantmentRegistryEvent;
import net.xiaoyu233.fml.util.IdUtil;

public class Enchantments {
    public static final Enchantment enchantmentMelting = new EnchantmentMelting(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentAbsorb = new EnchantmentAbsorb(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentVanishing = new EnchantmentVanishing(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 15);

    public static final Enchantment enchantmentCallOfNether = new EnchantmentTemperature(IdUtil.getNextEnchantmentID(), EnumRarity.uncommon, 10);

    public static final Enchantment enchantmentDestroying = new EnchantmentDestroying(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 20);

    public static final Enchantment enchantmentInfinity = new EnchantmentInfinity(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 20);

    public static final Enchantment enchantmentArrogance = new EnchantmentArrogance(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 15);

    public static final Enchantment enchantmentThresher = new EnchantmentThresher(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentSweeping = new EnchantmentSweeping(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentMending = new EnchantmentMending(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 20);// TODO deal with ultimate
    public static final Enchantment enchantmentSunlightMending = new EnchantmentMending(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 20);
    public static final Enchantment enchantmentMoonlightMending = new EnchantmentMending(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 20);
    public static final Enchantment enchantmentCallOfPolar = new EnchantmentTemperature(IdUtil.getNextEnchantmentID(), EnumRarity.uncommon, 10);

    public static void registerEnchantments(EnchantmentRegistryEvent event) {// TODO enchantments still work
//        event.registerEnchantment(enchantmentMelting, enchantmentAbsorb, enchantmentVanishing, enchantmentDestroying, enchantmentCallofNether, enchantmentInfinity, enchantmentArrogance, enchantmentThresher, enchantmentSweeping, enchantmentMending);
    }
}
