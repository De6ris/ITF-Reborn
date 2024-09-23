package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFlintAndSteel.class)
public class ItemFlintAndSteelMixin extends Item {

    @Inject(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/RaycastCollision;getBlockHit()Lnet/minecraft/Block;"))
    private void activateFurnace(EntityPlayer player, float partial_tick, boolean ctrl_is_down, CallbackInfoReturnable<Boolean> cir, @Local RaycastCollision rc) {
        if (rc.getBlockHit() instanceof BlockFurnace) {
            TileEntityFurnace furnace = (TileEntityFurnace) rc.world.getBlockTileEntity(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
            ((ITFFurnace) furnace).itf$setActive(true);
            if (player.onClient()) {
                player.swingArm();
            } else {
                rc.world.playSoundAtEntity(player, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                player.tryDamageHeldItem(DamageSource.generic, 1);
            }
        }
    }
}
