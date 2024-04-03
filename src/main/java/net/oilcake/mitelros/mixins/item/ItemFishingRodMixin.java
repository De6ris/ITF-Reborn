package net.oilcake.mitelros.mixins.item;

import net.minecraft.IDamageableItem;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Item;
import net.minecraft.ItemFishingRod;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemFishingRod.class})
public class ItemFishingRodMixin extends Item implements IDamageableItem {
  private Icon[] uncastIcons = new Icon[11];
  
  private Icon castIcon;
  
  private Material hook_material;
  
  @Shadow
  public int getNumComponentsForDurability() {
    return 0;
  }
  
  @Overwrite
  private int getMaterialOrdinal() {
    if (this.hook_material == Material.flint)
      return 0; 
    if (this.hook_material == Material.obsidian)
      return 1; 
    if (this.hook_material == Material.copper)
      return 2; 
    if (this.hook_material == Material.silver)
      return 3; 
    if (this.hook_material == Material.gold)
      return 4; 
    if (this.hook_material == Material.iron)
      return 5; 
    if (this.hook_material == Material.mithril)
      return 6; 
    if (this.hook_material == Material.adamantium)
      return 7; 
    if (this.hook_material == Material.ancient_metal)
      return 8; 
    if (this.hook_material == Materials.nickel)
      return 9; 
    if (this.hook_material == Materials.tungsten)
      return 10; 
    return -1;
  }
  
  @Overwrite
  private Material getMaterialByOrdinal(int ordinal) {
    switch (ordinal) {
      case 0:
        return Material.flint;
      case 1:
        return Material.obsidian;
      case 2:
        return Material.copper;
      case 3:
        return Material.silver;
      case 4:
        return Material.gold;
      case 5:
        return Material.iron;
      case 6:
        return Material.mithril;
      case 7:
        return Material.adamantium;
      case 8:
        return Material.ancient_metal;
      case 9:
        return (Material)Materials.nickel;
      case 10:
        return (Material)Materials.tungsten;
    } 
    return null;
  }
  
  @Overwrite
  public void registerIcons(IconRegister par1IconRegister) {
    this.castIcon = par1IconRegister.registerIcon(getIconString() + "_cast");
    for (int i = 0; i < this.uncastIcons.length; i++) {
      if (getMaterialByOrdinal(i) != null)
        this.uncastIcons[i] = par1IconRegister.registerIcon("fishing_rods/" + getMaterialByOrdinal(i).name + "_uncast");
    } 
  }
}
