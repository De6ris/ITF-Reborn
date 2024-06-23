package net.oilcake.mitelros.mixins.world;

import net.minecraft.WorldChunkManager;
import net.oilcake.mitelros.world.ITFBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(WorldChunkManager.class)
public class WorldChunkManagerMixin {
    @Shadow
    private List biomesToSpawnIn;

    @Inject(method = {"<init>()V"}, at = {@At("RETURN")})
    private void playerSpawnable(CallbackInfo callbackInfo) {
        this.biomesToSpawnIn.add(ITFBiomes.BIOME_SAVANNA);
        this.biomesToSpawnIn.add(ITFBiomes.BIOME_SAVANNA_PLEATU);
    }
}
