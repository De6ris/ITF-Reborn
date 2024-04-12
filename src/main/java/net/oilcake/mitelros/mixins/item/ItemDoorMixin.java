package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.ItemDoor;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemDoor.class)
public class ItemDoorMixin {
    @Shadow
    private Material door_material;

    @Inject(method = "getBlock", at = @At("HEAD"), cancellable = true)
    private void itfDoor(CallbackInfoReturnable<Block> cir) {
        if (this.door_material == Materials.nickel)
            cir.setReturnValue(Blocks.doorNickel);
        if (this.door_material == Materials.tungsten)
            cir.setReturnValue(Blocks.doorTungsten);
    }
}
