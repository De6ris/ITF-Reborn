package net.oilcake.mitelros.mixins.entity.mob;

import net.minecraft.EntityWitch;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityWitch.class)
public class EntityWitchMixin {
    @Mutable
    @Shadow
    @Final
    private static int[] witchDrops;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addWitchLoot(CallbackInfo ci) {
        int[] original = witchDrops;
        int[] expanded = new int[original.length + 1];
        System.arraycopy(original, 0, expanded, 0, original.length);
        expanded[original.length] = Items.seedsBeetroot.itemID;
        witchDrops = expanded;
    }
}
