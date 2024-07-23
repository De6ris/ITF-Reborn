package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.WontFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.*;

@Mixin(ItemEnchantedBook.class)
public abstract class ItemEnchantedBookMixin extends Item {

    @Shadow
    public abstract void addEnchantment(ItemStack par1ItemStack, EnchantmentData par2EnchantmentData);

    /**
     * @author Debris
     * @reason rewrite
     */
    @WontFix
    @Overwrite
    public WeightedRandomChestContent func_92112_a(Random par1Random, int par2, int par3, int par4) {
        ItemStack var6 = new ItemStack(this.itemID, 1, 0);
        List<EnchantmentData> var4 = buildEnchantmentList(par1Random, 75);
        if (var4 != null) {
            for (EnchantmentData var8 : var4) {
                this.addEnchantment(var6, var8);
            }
        }
        return new WeightedRandomChestContent(var6, par2, par3, par4 * 2);
    }


    @Unique
    private static List<EnchantmentData> buildEnchantmentList(Random random, int enchantment_levels) {
        float randomness = 1.0F + (random.nextFloat() - 0.5F) * 0.5F;
        int adjusted_enchantment_levels = (int) (enchantment_levels * randomness);
        if (adjusted_enchantment_levels < 1)
            adjusted_enchantment_levels = 1;
        ArrayList<EnchantmentData> enchantments_for_item = new ArrayList<>();
        while (true) {
            Map<Integer, EnchantmentData> all_possible_enchantments = mapEnchantmentData(adjusted_enchantment_levels);
            if (all_possible_enchantments == null)
                break;
            removeEnchantmentsFromMapThatConflict(all_possible_enchantments, enchantments_for_item);
            if (all_possible_enchantments.isEmpty())
                break;
            EnchantmentData enchantment_data = (EnchantmentData) WeightedRandom.getRandomItem(random, all_possible_enchantments.values());
            if (enchantment_data == null)
                break;
            Enchantment enchantment = enchantment_data.enchantmentobj;
            if (enchantments_for_item.size() < 2 && all_possible_enchantments.size() > 1 && enchantment.hasLevels() && random.nextInt(2) == 0)
                enchantment_data.enchantmentLevel = random.nextInt(enchantment_data.enchantmentLevel) + 1;
            enchantments_for_item.add(enchantment_data);
            adjusted_enchantment_levels -= enchantment.hasLevels() ? enchantment.getMinEnchantmentLevelsCost(enchantment_data.enchantmentLevel) : enchantment.getMinEnchantmentLevelsCost();
            adjusted_enchantment_levels -= 5;
            if (adjusted_enchantment_levels < 5 || enchantments_for_item.size() > 2)
                break;
        }
        ArrayList<EnchantmentData> enchantments_for_item_shuffled = new ArrayList<>();
        int n = enchantments_for_item.size();
        while (n > 0) {
            int index = random.nextInt(enchantments_for_item.size());
            EnchantmentData enchantment_data = enchantments_for_item.get(index);
            if (enchantment_data != null) {
                enchantments_for_item_shuffled.add(enchantment_data);
                enchantments_for_item.set(index, null);
                n--;
            }
        }
        return (enchantments_for_item_shuffled.isEmpty()) ? null : enchantments_for_item_shuffled;
    }

    @Unique
    private static void removeEnchantmentsFromMapThatConflict(Map<Integer, EnchantmentData> map, ArrayList<EnchantmentData> enchantments) {
        for (EnchantmentData enchantment_data : enchantments) {
            Enchantment enchantment = enchantment_data.enchantmentobj;
            map.keySet().removeIf(id -> !enchantment.canApplyTogether(Enchantment.get(id)));
        }
    }

    @Unique
    private static Map<Integer, EnchantmentData> mapEnchantmentData(int enchantment_levels) {
        HashMap<Integer, EnchantmentData> map = new HashMap<>();
        for (int i = 0; i < Enchantment.enchantmentsList.length; ++i) {
            Enchantment enchantment = Enchantment.get(i);
            if (enchantment != null) {
                if (enchantment.hasLevels()) {
                    for (int level = enchantment.getNumLevels(); level > 0; --level) {
                        if (enchantment.getMinEnchantmentLevelsCost(level) <= enchantment_levels) {
                            map.put(enchantment.effectId, new EnchantmentData(enchantment, level));
                            break;
                        }
                    }
                } else if (enchantment.getMinEnchantmentLevelsCost() <= enchantment_levels) {
                    map.put(enchantment.effectId, new EnchantmentData(enchantment, 1));
                }
            }
        }
        return map.isEmpty() ? null : map;
    }

}
