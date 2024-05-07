package net.oilcake.mitelros.mixins.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.BlockFire;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.util.FireCookHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockFire.class)
public class BlockFireMixin {
    @WrapOperation(method = "tryExtinguishByItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;getItem()Lnet/minecraft/Item;"))
    private Item redirect(ItemStack instance, Operation<Item> original) {
        if (FireCookHandler.getCookResult(instance) != null) {
            return Item.porkRaw;
        }
        return original.call(instance);
    }
}
