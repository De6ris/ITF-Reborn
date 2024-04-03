package net.oilcake.mitelros.item;

import net.minecraft.CreativeTabs;
import net.minecraft.ItemRock;
import net.minecraft.Material;

public class ItemShardAT extends ItemRock {
  protected ItemShardAT(int id, Material material) {
    super(id, material, "shards/" + material.name);
    setMaxStackSize(64);
    setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(material) / ((material == Material.flint) ? 4 : 9));
    setCreativeTab(CreativeTabs.tabMaterials);
  }
}
