package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.BlockFire;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockFire.class)
public class BlockFireMixin {
    @Redirect(method = "tryExtinguishByItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;getItem()Lnet/minecraft/Item;"))
    private Item redirect(ItemStack instance) {
        if (instance.getItem().itemID == Block.wood.blockID) {
            return Item.porkRaw;
        }
        return instance.getItem();
    }
}
