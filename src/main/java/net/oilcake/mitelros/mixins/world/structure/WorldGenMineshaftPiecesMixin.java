package net.oilcake.mitelros.mixins.world.structure;

import net.minecraft.StructureMineshaftPieces;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureMineshaftPieces.class)
public class WorldGenMineshaftPiecesMixin {
    @Mutable
    @Shadow
    @Final
    private static WeightedRandomChestContent[] mineshaftChestContents;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addITFLoot(CallbackInfo ci) {
        mineshaftChestContents = WeightedRandomChestContent.func_92080_a(mineshaftChestContents,
                new WeightedRandomChestContent(Items.ignitionCopper.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.ignitionSilver.itemID, 0, 1, 1, 2),
                new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 2, 2)
        );
    }
}
