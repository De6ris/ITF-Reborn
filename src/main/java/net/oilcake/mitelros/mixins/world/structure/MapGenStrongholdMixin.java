package net.oilcake.mitelros.mixins.world.structure;

import net.minecraft.BiomeGenBase;
import net.minecraft.MapGenStronghold;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MapGenStronghold.class)
public class MapGenStrongholdMixin {
    @Shadow
    private BiomeGenBase[] allowedBiomeGenBases;


    @Inject(method = "<init>()V", at = @At("TAIL"))
    private void injectAddBiomes(CallbackInfo ci) {
        this.addBiomes();
    }

    @Inject(method = "<init>(Ljava/util/Map;)V", at = @At("TAIL"))
    private void injectAddBiomes(Map par1Map, CallbackInfo ci) {
        this.addBiomes();
    }

    @Unique
    private void addBiomes() {
        BiomeGenBase[] original = allowedBiomeGenBases;
        BiomeGenBase[] expanded = new BiomeGenBase[original.length + 3];
        System.arraycopy(original, 0, expanded, 0, original.length);
        expanded[original.length] = BiomeBases.BIOME_SAVANNA;
        expanded[original.length + 1] = BiomeBases.BIOME_SAVANNA_PLEATU;
        expanded[original.length + 2] = BiomeBases.BIOME_WINDSWEPT_PLEATU;
        allowedBiomeGenBases = expanded;
    }
}
