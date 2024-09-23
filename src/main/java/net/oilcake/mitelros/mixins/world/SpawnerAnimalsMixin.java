package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.SpawnerAnimals;
import net.minecraft.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SpawnerAnimals.class)
public class SpawnerAnimalsMixin {
    @ModifyConstant(method = "trySpawningHostileMobs", constant = @Constant(intValue = 256), require = 0)
    private int moreMobs(int constant, @Local(argsOnly = true) WorldServer world) {
        int day = world.getDayOfWorld();
        if (day > 4096) return 4;
        return (64 - (int) (0.0146484375 * day));
    }
}
