package net.oilcake.mitelros.mixins.world.structure;

import net.minecraft.ComponentScatteredFeatureDesertPyramid;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ComponentScatteredFeatureDesertPyramid.class)
public class ComponentDesertPyramidMixin {
    @ModifyArg(method = "addComponentParts", at = @At(value = "INVOKE", target = "Lnet/minecraft/WeightedRandomChestContent;func_92080_a([Lnet/minecraft/WeightedRandomChestContent;[Lnet/minecraft/WeightedRandomChestContent;)[Lnet/minecraft/WeightedRandomChestContent;"), index = 0)
    private WeightedRandomChestContent[] addTotem(WeightedRandomChestContent[] par0ArrayOfWeightedRandomChestContent) {
        return WeightedRandomChestContent.func_92080_a(par0ArrayOfWeightedRandomChestContent, new WeightedRandomChestContent(Items.totemOfPreserve.itemID, 0, 1, 1, 1));
    }
}
