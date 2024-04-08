package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({ItemFlintAndSteel.class})
public class ItemFlintAndSteelMixin extends Item {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        System.out.println("clicked");
        RaycastCollision rc = player.getSelectedObject(partial_tick, false);
        if (rc == null)
            return false;
        if (!rc.isBlock())
            return false;
        if (rc.getBlockHit() == Block.tnt) {
            if (player.onServer())
                BlockTNT.ignite(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, player);
        } else if (rc.getBlockHit() instanceof BlockFurnace) {
            TileEntityFurnace furnace = (TileEntityFurnace) rc.world.getBlockTileEntity(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
            ((ITFFurnace) furnace).activateFurnace();
        } else {
            if (!rc.isNeighborAirBlock() || !rc.canPlayerEditNeighborOfBlockHit(player, player.getHeldItemStack()))
                return false;
            if (player.onServer())
                rc.setNeighborBlock(Block.spark);
        }
        if (player.onClient()) {
            player.swingArm();
        } else {
            rc.world.playSoundAtEntity(player, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            player.tryDamageHeldItem(DamageSource.generic, 1);
        }
        return true;
    }
}
