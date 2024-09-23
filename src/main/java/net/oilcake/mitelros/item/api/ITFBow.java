package net.oilcake.mitelros.item.api;

import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.quality.EnumEffectEntry;
import net.oilcake.mitelros.util.quality.EnumToolType;

public class ITFBow {
    public static int overridePullSpeed(ItemStack item_stack) {
        int ticksPull;
        Material material = item_stack.getMaterialForRepairs();
        if (material == Materials.tungsten) {
            ticksPull = 30;
        } else if (material == Materials.uru) {
            ticksPull = 18;
        } else if (material == Material.mithril) {
            ticksPull = 27;
        } else if (material == Material.ancient_metal) {
            ticksPull = 24;
        } else {
            return -1;
        }
        return (int) (ticksPull *
                (1.0F - 0.5F * EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.quickness, item_stack)) /
                EnumToolType.getMultiplierForEntry(item_stack, EnumEffectEntry.PullSpeed));
    }

    public static double getDamageModifier(Material material) {
        if (material == Materials.tungsten) return 1.15D;
        if (material == Materials.uru) return 0.9D;
        if (material == Materials.mithril) return 1.1D;
        if (material == Materials.ancient_metal) return 1.05D;
        return 0.75D;
    }
}
