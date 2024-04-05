package net.oilcake.mitelros.mixins.item;

import net.minecraft.IDamageableItem;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Item;
import net.minecraft.ItemFishingRod;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemFishingRod.class)
public abstract class ItemFishingRodMixin extends Item implements IDamageableItem {
    private Icon[] uncastIcons = new Icon[11];

    private Material hook_material;

    @Shadow
    public int getNumComponentsForDurability() {
        return 0;
    }

    @Inject(method = "getMaterialOrdinal", at = @At("HEAD"), cancellable = true)
    private void getMaterialOrdinal(CallbackInfoReturnable<Integer> cir) {
        if (this.hook_material == Materials.nickel)
            cir.setReturnValue(9);
        if (this.hook_material == Materials.tungsten)
            cir.setReturnValue(10);
    }

    @Inject(method = "getMaterialByOrdinal", at = @At("HEAD"), cancellable = true)
    private void getMaterialByOrdinal(int ordinal, CallbackInfoReturnable<Material> cir) {
        switch (ordinal) {
            case 9:
                cir.setReturnValue(Materials.nickel);
            case 10:
                cir.setReturnValue(Materials.tungsten);
        }
    }
}
