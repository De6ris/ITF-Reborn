package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WorldGenDungeons.class)
public abstract class WorldGenDungeonsMixin extends WorldGenerator {
    @ModifyExpressionValue(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/WeightedRandomChestContent;func_92080_a([Lnet/minecraft/WeightedRandomChestContent;[Lnet/minecraft/WeightedRandomChestContent;)[Lnet/minecraft/WeightedRandomChestContent;"))
    private WeightedRandomChestContent[] addMoreBooks(WeightedRandomChestContent[] original, @Local(argsOnly = true) Random par2Random) {
        return WeightedRandomChestContent.func_92080_a(original, Item.enchantedBook.func_92114_b(par2Random));
    }

    @Inject(method = "pickMobSpawner", at = @At("HEAD"), cancellable = true)
    private void pickMobSpawnerITF(World world, Random par1Random, int y, CallbackInfoReturnable<String> cir) {
        if (ITFConfig.TagMiracleDisaster.getBooleanValue() && world.isOverworld()) {
            String mob = switch (par1Random.nextInt(7)) {
                case 0 -> "Zombie";
                case 1 -> "Ghoul";
                case 2 -> "Skeleton";
                case 3 -> "Spider";
                case 4 -> "Wight";
                case 5 -> "DemonSpider";
                default -> "Hellhound";
            };
            cir.setReturnValue(mob);
        }
    }
}
