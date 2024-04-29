package net.oilcake.mitelros.mixins.item;

import net.minecraft.ItemBucketMilk;
import net.minecraft.ItemVessel;
import net.minecraft.Material;
import net.oilcake.mitelros.api.ITFItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemBucketMilk.class)
public abstract class ItemBucketMilkMixin extends ItemVessel {
    @Inject(method = "<init>(ILnet/minecraft/Material;)V", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo) {
        ((ITFItem) this).setFoodWater(8);
    }

    public ItemBucketMilkMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }
}
