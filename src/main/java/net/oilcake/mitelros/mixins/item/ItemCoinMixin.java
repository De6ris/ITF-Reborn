package net.oilcake.mitelros.mixins.item;

import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemCoin.class)
public class ItemCoinMixin extends Item {
    @Inject(method = "getExperienceValue", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Integer> cir) {
        Material material = this.getExclusiveMaterial();
        if (material == Materials.nickel)
            cir.setReturnValue(50);
        if (material == Materials.tungsten)
            cir.setReturnValue(5000);
    }

    @Inject(method = "getForMaterial", at = @At("HEAD"), cancellable = true)
    private static void inject(Material material, CallbackInfoReturnable<ItemCoin> cir) {
        if (material == Materials.nickel)
            cir.setReturnValue(Items.nickelCoin);
        if (material == Materials.tungsten)
            cir.setReturnValue(Items.tungstenCoin);
    }

    @Inject(method = "getNuggetPeer", at = @At("HEAD"), cancellable = true)
    private void inject_1(CallbackInfoReturnable<Item> cir) {
        Material material = getExclusiveMaterial();
        if (material == Materials.nickel)
            cir.setReturnValue(Items.nickelNugget);
        if (material == Materials.tungsten)
            cir.setReturnValue(Items.tungstenNugget);
    }
}
