package net.oilcake.mitelros.mixins.world.structure;

import net.minecraft.ComponentNetherBridgePiece;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.util.ITFLootTables;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ComponentNetherBridgePiece.class)
public class ComponentNetherBridgePieceMixin {
    @Mutable
    @Shadow
    @Final
    protected static WeightedRandomChestContent[] field_111019_a;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addITFLoot(CallbackInfo ci) {
        field_111019_a = WeightedRandomChestContent.func_92080_a(field_111019_a, ITFLootTables.fortressExtra.get());
    }
}
