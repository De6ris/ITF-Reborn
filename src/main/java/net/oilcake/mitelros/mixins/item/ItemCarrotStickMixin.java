package net.oilcake.mitelros.mixins.item;

import net.minecraft.IDamageableItem;
import net.minecraft.Item;
import net.minecraft.ItemCarrotOnAStick;
import net.minecraft.ItemFishingRod;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemCarrotOnAStick.class})
public class ItemCarrotStickMixin extends Item implements IDamageableItem {
  protected Material hook_material;
  
  @Shadow
  public int getNumComponentsForDurability() {
    return 0;
  }
  
  @Overwrite
  public ItemFishingRod getFishingRodItem() {
    if (this.hook_material == Material.flint)
      return Item.fishingRodFlint; 
    if (this.hook_material == Material.obsidian)
      return Item.fishingRodObsidian; 
    if (this.hook_material == Material.copper)
      return Item.fishingRodCopper; 
    if (this.hook_material == Material.silver)
      return Item.fishingRodSilver; 
    if (this.hook_material == Material.gold)
      return Item.fishingRodGold; 
    if (this.hook_material == Material.iron)
      return Item.fishingRodIron; 
    if (this.hook_material == Material.mithril)
      return Item.fishingRodMithril; 
    if (this.hook_material == Material.adamantium)
      return Item.fishingRodAdamantium; 
    if (this.hook_material == Material.ancient_metal)
      return Item.fishingRodAncientMetal; 
    if (this.hook_material == Materials.nickel)
      return Items.fishingRodNickel; 
    if (this.hook_material == Materials.tungsten)
      return Items.fishingRodTungsten; 
    return null;
  }
}
