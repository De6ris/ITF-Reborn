package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenPlains;
import net.minecraft.EntityHorse;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeGenPlains.class)
public abstract class BiomePlainsMixin extends BiomeGenBase {
    protected BiomePlainsMixin(int par1) {
        super(par1);
    }

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        if (ITFConfig.TagApocalypse.get()) {
            removeEntityFromSpawnableLists(EntityHorse.class);
        }
    }
}
