package net.oilcake.mitelros.mixins.render;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.Item;
import net.minecraft.ItemRenderer;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.wand.ItemWand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow
    private ItemStack itemToRender;

    @ModifyExpressionValue(method = "renderItemInFirstPerson", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBow;getTicksForMaxPull(Lnet/minecraft/ItemStack;)I"))
    private int addITFWand(int original) {
        Item item = this.itemToRender.getItem();
        if (item instanceof ItemWand itemWand) {
            return itemWand.getTicksForMaxPull();
        } else {
            return original;
        }
    }
}
