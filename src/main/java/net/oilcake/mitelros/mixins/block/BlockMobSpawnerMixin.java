package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.entity.mob.EntityCastleGuard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockMobSpawner.class)
public abstract class BlockMobSpawnerMixin extends BlockContainer {
    protected BlockMobSpawnerMixin(int par1, Material par2Material, BlockConstants block_constants) {
        super(par1, par2Material, block_constants);
    }

    @Inject(method = "dropBlockAsEntityItem", at = @At("RETURN"))
    private void castleGuard(BlockBreakInfo info, CallbackInfoReturnable<Integer> cir) {
        if (info.world.isUnderworld()) {
            EntityCastleGuard castleGuard = new EntityCastleGuard(info.world);
            castleGuard.setPosition(info.x, info.y, info.z);
            castleGuard.refreshDespawnCounter(-9600);
            castleGuard.entityFX(EnumEntityFX.summoned);
            castleGuard.onSpawnWithEgg(null);
            info.world.spawnEntityInWorld(castleGuard);
        }
    }
}
