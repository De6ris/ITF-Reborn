package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.Debug;
import net.minecraft.WeatherEvent;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(WeatherEvent.class)
public class WeatherEventMixin {
    @Shadow
    public long start;

    @WrapOperation(method = "addStorm", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 1))
    private int season(Random instance, int i, Operation<Integer> original) {
        return (int) (original.call(instance, i / 2) * getStormDurationModify(this.getSeasonOfStormStart()));
    }

    @ModifyConstant(method = "addStorm", constant = @Constant(intValue = 4, ordinal = 0))
    private int season1(int constant) {
        return (this.getSeasonOfStormStart() == 1) ? 2 : 4;
    }

    @ModifyConstant(method = "addStorm", constant = @Constant(intValue = 4, ordinal = 1))
    private int season2(int constant) {
        return this.getSeasonOfStormStart() == 2 ? 2 : 3;
    }

    @Unique
    private int getSeasonOfStormStart() {
        int day = World.getDayOfWorld(this.start);
        return day % 128 / 32;
    }

    @Unique
    private float getStormDurationModify(int Season) {
        switch (Season) {
            case 0:
                return 1.0F;
            case 1:
                return 0.5F;
            case 2:
                return 2.5F;
            case 3:
                return 1.0F;
        }
        Debug.setErrorMessage("getStormDurationModify: called for num " + Season + " for calculating. Use the default.");
        return 1.0F;
    }
}
