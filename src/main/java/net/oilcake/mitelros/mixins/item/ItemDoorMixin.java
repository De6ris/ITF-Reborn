package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.ItemDoor;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemDoor.class})
public class ItemDoorMixin {
  @Shadow
  private Material door_material;
  
  @Overwrite
  public Block getBlock() {
    if (this.door_material == Material.wood)
      return Block.doorWood; 
    if (this.door_material == Material.copper)
      return Block.doorCopper; 
    if (this.door_material == Material.silver)
      return Block.doorSilver; 
    if (this.door_material == Material.gold)
      return Block.doorGold; 
    if (this.door_material == Material.iron)
      return Block.doorIron; 
    if (this.door_material == Material.mithril)
      return Block.doorMithril; 
    if (this.door_material == Material.adamantium)
      return Block.doorAdamantium; 
    if (this.door_material == Materials.nickel)
      return Blocks.doorNickel; 
    if (this.door_material == Materials.tungsten)
      return Blocks.doorTungsten; 
    return (this.door_material == Material.ancient_metal) ? Block.doorAncientMetal : null;
  }
}
