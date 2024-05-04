package net.oilcake.mitelros.item.api;

import net.minecraft.CreativeTabs;
import net.minecraft.Item;
import net.minecraft.Material;

public class ItemBrewingMisc extends Item {
    public ItemBrewingMisc(int id, Material material, String texture) {
        super(id, material, texture);
        setMaxStackSize(16);
        setCraftingDifficultyAsComponent(100.0F);
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    public ItemBrewingMisc setPotionEffectExtend(String par1Str) {
        setPotionEffect(par1Str);
        return this;
    }
}
