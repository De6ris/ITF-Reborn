package net.oilcake.mitelros.mixins.world.structure;

import net.minecraft.ComponentScatteredFeatureDesertPyramid;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComponentScatteredFeatureDesertPyramid.class)
public class ComponentDesertPyramidMixin {
    @Mutable
    @Shadow
    @Final
    private static WeightedRandomChestContent[] itemsToGenerateInTemple;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addITFLoot(CallbackInfo ci) {
        itemsToGenerateInTemple = WeightedRandomChestContent.func_92080_a(itemsToGenerateInTemple,
                new WeightedRandomChestContent(Items.totemOfPreserve.itemID, 0, 1, 1, 1)
        );
    }
}
