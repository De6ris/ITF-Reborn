package net.oilcake.mitelros.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.reload.event.EnchantmentRegistryEvent;

public class Enchantments {
    public static final Enchantment enchantmentMelting = new EnchantmentMelting(Constant.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentAbsorb = new EnchantmentAbsorb(Constant.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentVanishing = new EnchantmentVanishing(Constant.getNextEnchantmentID(), EnumRarity.rare, 15);

    public static final Enchantment enchantmentCallofNether = new EnchantmentCallofNether(Constant.getNextEnchantmentID(), EnumRarity.uncommon, 10);

    public static final Enchantment enchantmentDestroying = new EnchantmentDestroying(Constant.getNextEnchantmentID(), EnumRarity.epic, 20);

    public static final Enchantment enchantmentInfinity = new EnchantmentInfinity(Constant.getNextEnchantmentID(), EnumRarity.epic, 20);

    public static final Enchantment enchantmentArrogance = new EnchantmentArrogance(Constant.getNextEnchantmentID(), EnumRarity.rare, 15);

    public static final Enchantment enchantmentThresher = new EnchantmentThresher(Constant.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentSweeping = new EnchantmentSweeping(Constant.getNextEnchantmentID(), EnumRarity.rare, 10);

    public static final Enchantment enchantmentMending = new EnchantmentMending(Constant.getNextEnchantmentID(), EnumRarity.epic, 20);// TODO deal with ultimate

    public static void registerEnchantments(EnchantmentRegistryEvent event) {
        event.registerEnchantment(enchantmentMelting, enchantmentAbsorb, enchantmentVanishing, enchantmentDestroying, enchantmentCallofNether, enchantmentInfinity, enchantmentArrogance, enchantmentThresher, enchantmentSweeping, enchantmentMending);

//        for (Enchantment enchantment : Enchantment.enchantmentsList) {
//            if (enchantment != null && !Enchantments.individualEnchantments.contains(enchantment)){
//                if (enchantment.enchantIndividually()){
//                    Enchantments.individualEnchantments.add(enchantment);
//                }
//            }
//        }// TODO

//        registerEnchantmentsUnsafe(new Enchantment[]{enchantmentMelting, enchantmentAbsorb, enchantmentVanishing, enchantmentDestroying, enchantmentCallofNether, enchantmentInfinity, enchantmentArrogance, enchantmentThresher, enchantmentSweeping, enchantmentMending});
    }

//    public static void registerEnchantmentsUnsafe(Enchantment... enchantments) {
//        for (int i = 80, bLength = Enchantment.enchantmentsList.length; i < bLength; i++) {
//            if (Enchantment.enchantmentsList[i] == null) {
//                for (int j = 0, enchantmentsLength = enchantments.length; j < enchantmentsLength; j++)
//                    Enchantment.enchantmentsList[i + j] = enchantments[j];
//                break;
//            }
//        }
//        ArrayList<Enchantment> var0 = new ArrayList<>();
//        for (Enchantment enchantment : Enchantment.enchantmentsList) {
//            if (enchantment != null)
//                var0.add(enchantment);
//        }
//        try {
//            Field enchantmentsBookList = Enchantment.class.getDeclaredField("enchantmentsBookList");
//            ReflectHelper.updateFinalModifiers(enchantmentsBookList);
//            enchantmentsBookList.setAccessible(true);
//            enchantmentsBookList.set(null, var0.toArray(new Enchantment[0]));
//        } catch (IllegalAccessException | NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//    }
}
