package net.oilcake.mitelros.mixins.world;

import net.minecraft.WorldChunkManagerHell;
import net.minecraft.WorldProvider;
import net.minecraft.WorldProviderUnderworld;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldProviderUnderworld.class)
public class WorldProviderUnderworldMixin extends WorldProvider {
    public WorldProviderUnderworldMixin(int dimension_id, String name) {
        super(dimension_id, name);
    }

    @Inject(method = "registerWorldChunkManager", at = @At("HEAD"), cancellable = true)
    public void registerWorldChunkManager(CallbackInfo ci) {
        if (ITFConfig.TagDeadGeothermy.get()) {
            this.worldChunkMgr = new WorldChunkManagerHell(BiomeBases.BIOME_UNDERWORLD_IN_FREEZE, 1.0F, 0.0F);
            ci.cancel();
        }
    }
}
