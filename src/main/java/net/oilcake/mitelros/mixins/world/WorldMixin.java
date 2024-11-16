package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(World.class)
public abstract class WorldMixin implements ITFWorld {

    @Shadow
    public abstract World getWorld();

    public Explosion itf$ExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities, boolean b) {
        Explosion explosion = new Explosion(this.getWorld(), exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
        explosion.doExplosionA();
        explosion.affectedBlockPositions.clear();
        explosion.doExplosionB(false);
        return explosion;
    }

    @WrapOperation(method = "generateWeatherEvents(I)Ljava/util/List;", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 2))
    private int itfRain(Random instance, int i, Operation<Integer> original) {
        int duration_static = 6000 * (ITFConfig.TagEternalRaining.get() ? 6 : 1);
        int duration_random = original.call(instance, i) * (ITFConfig.TagEternalRaining.get() ? 2 : 1);
        int duration = duration_random + duration_static;
        duration = (int) (duration * getRainDurationModify(itf$GetWorldSeason()));
        return duration - 6000;
    }

    @Shadow
    @Final
    public int getDayOfWorld() {
        return 0;
    }

    @Unique
    public int itf$GetWorldSeason() {
        return (this.getDayOfWorld() % 128) / 32;
    }

    @Unique
    public float getRainDurationModify(int Season) {
        switch (Season) {
            case 0:
                return 1.0F;
            case 1:
                return 2.25F;
            case 2:
                return 0.75F;
            case 3:
                return 0.5F;
        }
        Debug.setErrorMessage("getRainDurationModify: called for num " + Season + " for calculating. Use the default.");
        return 1.0F;
    }

    @Unique
    public float itf$GetSeasonGrowthModifier() {
        return (float) Math.sin(0.0490873852123 * (this.getDayOfWorld() - 16));
    }

    @ModifyConstant(method = "canSnowAt", constant = @Constant(floatValue = 0.15F))
    private float itfSnow(float constant) {
        return (this.itf$GetWorldSeason() == 3) ? 1.0F : 0.15F;
    }

    @Inject(method = "isFreezing", at = @At("HEAD"), cancellable = true)
    private void itfFreezing(int x, int z, CallbackInfoReturnable<Boolean> cir) {
        if (getBiomeGenForCoords(x, z).temperature <= ((itf$GetWorldSeason() == 3) ? 1.0F : 0.15F))
            cir.setReturnValue(true);
    }

    @Shadow
    public BiomeGenBase getBiomeGenForCoords(int par1, int par3) {
        return null;
    }
}
