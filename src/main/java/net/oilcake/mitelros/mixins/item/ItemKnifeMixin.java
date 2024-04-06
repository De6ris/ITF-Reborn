package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ItemKnife.class})
public class ItemKnifeMixin extends ItemDagger {
  protected ItemKnifeMixin(int par1, Material material) {
    super(par1, material);
  }
  
  public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
    RaycastCollision rc = player.getSelectedObject(partial_tick, false);
    if (rc == null)
      return false; 
    if (!rc.isBlock())
      return false; 
    if (rc.getBlockHit() == Block.wood && getToolMaterial() == Material.flint && player.getHeldItemStack().getItemDamage() == 0) {
      if (player.onServer())
        rc.world.setBlock(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, Block.workbench.blockID, rc.world.getBlockMetadata(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z) & 0x3, 2); 
      if (player.onClient()) {
        player.swingArm();
      } else {
        rc.world.playSoundAtEntity((Entity)player, "dig.wood", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        player.setHeldItemStack(null);
      } 
      return true;
    } 
    if (rc.getBlockHit() == Block.wood && getToolMaterial() == Material.obsidian && player.getHeldItemStack().getItemDamage() == 0) {
      if (player.onServer())
        rc.world.setBlock(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, Block.workbench.blockID, (rc.world.getBlockMetadata(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z) & 0x3) + 13, 2); 
      if (player.onClient()) {
        player.swingArm();
      } else {
        rc.world.playSoundAtEntity((Entity)player, "dig.wood", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
        player.setHeldItemStack(null);
      } 
      return true;
    } 
    if (!rc.isNeighborAirBlock() || !rc.canPlayerEditNeighborOfBlockHit(player, player.getHeldItemStack()))
      return false; 
    return false;
  }
}
