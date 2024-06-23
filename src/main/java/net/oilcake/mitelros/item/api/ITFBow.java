package net.oilcake.mitelros.item.api;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;

public class ITFBow extends ItemBow {
    public ITFBow(int id, Material reinforcement_material) {
        super(id, reinforcement_material);
        if (reinforcement_material == Materials.tungsten) {
            this.setMaxDamage(256);
        } else if (reinforcement_material == Materials.uru) {
            this.setMaxDamage(512);
        }
    }

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
        return (int) (ticksPull * (1.0F - 0.5F * EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.quickness, item_stack)));
    }

    public static int getArrowSpeedBonus(Material material) {
        if (material == Materials.tungsten) {
            return 35;
        } else if (material == Materials.uru) {
            return 45;
        }
        return 0;
    }

    public static double getDamageModifier(Material material) {
        if (material == Materials.tungsten) return 1.15D;
        if (material == Materials.uru) return 0.9D;
        if (material == Materials.mithril) return 1.1D;
        if (material == Materials.ancient_metal) return 1.05D;
        return 0.75D;
    }
}
