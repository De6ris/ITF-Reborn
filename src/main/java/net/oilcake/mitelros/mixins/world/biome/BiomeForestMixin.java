package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenForest;
import net.minecraft.EntityWolf;
import net.minecraft.SpawnListEntry;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeGenForest.class)
public class BiomeForestMixin extends BiomeGenBase {
    protected BiomeForestMixin(int par1) {
        super(par1);
    }

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        if (ITFConfig.TagCreaturesV2.get())
            RegenHostileAnimals();
    }

    private void RegenHostileAnimals() {
        removeEntityFromSpawnableLists(EntityWolf.class);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 8));
    }
}
