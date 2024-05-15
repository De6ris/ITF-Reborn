package net.oilcake.mitelros.mixins.world;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WorldGenDungeons.class)
public abstract class WorldGenDungeonsMixin extends WorldGenerator {
    @Mutable
    @Final
    @Shadow
    private static WeightedRandomChestContent[] field_111189_a;
    @Mutable
    @Final
    @Shadow
    private static WeightedRandomChestContent[] chest_contents_for_underworld;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addITFLoot(CallbackInfo ci) {
        field_111189_a = WeightedRandomChestContent.func_92080_a(field_111189_a,
                new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Items.morningStarCopper.itemID, 0, 1, 1, 1),
                new WeightedRandomChestContent(Items.warHammerRustedIron.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.morningStarRustedIron.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.ignitionRustedIron.itemID, 0, 1, 1, 1)
        );
        chest_contents_for_underworld = WeightedRandomChestContent.func_92080_a(chest_contents_for_underworld,
                new WeightedRandomChestContent(Items.ancientMetalArmorPiece.itemID, 0, 1, 2, 10),
                new WeightedRandomChestContent(Items.morningStarAncientMetal.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.ignitionAncientMetal.itemID, 0, 1, 1, 2)
        );
    }

    @ModifyExpressionValue(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/WeightedRandomChestContent;func_92080_a([Lnet/minecraft/WeightedRandomChestContent;[Lnet/minecraft/WeightedRandomChestContent;)[Lnet/minecraft/WeightedRandomChestContent;"))
    private WeightedRandomChestContent[] addMoreBooks(WeightedRandomChestContent[] original, @Local(argsOnly = true) Random par2Random) {
        return WeightedRandomChestContent.func_92080_a(original, Item.enchantedBook.func_92114_b(par2Random));
    }

    @Inject(method = "pickMobSpawner", at = @At("HEAD"), cancellable = true)
    private void pickMobSpawnerITF(World world, Random par1Random, int y, CallbackInfoReturnable<String> cir) {
        if ((ITFConfig.TagMiracleDisaster.get())) {
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
