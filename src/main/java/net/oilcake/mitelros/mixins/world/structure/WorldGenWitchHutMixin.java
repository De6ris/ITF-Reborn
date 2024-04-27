package net.oilcake.mitelros.mixins.world.structure;

import net.minecraft.ComponentScatteredFeatureSwampHut;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComponentScatteredFeatureSwampHut.class)
public class WorldGenWitchHutMixin {
    @Mutable
    @Shadow
    @Final
    protected static WeightedRandomChestContent[] chest_contents;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addITFLoot(CallbackInfo ci) {
        chest_contents = WeightedRandomChestContent.func_92080_a(chest_contents,
                new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 2, 3)
        );
    }
}
