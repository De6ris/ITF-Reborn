package net.oilcake.mitelros.mixins;

import net.minecraft.Item;
import net.minecraft.ItemGlassBottle;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemGlassBottle.class)
public class ItemGlassBottleMixin extends Item {
    @ModifyArg(method = "onItemRightClick", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;convertOneOfHeldItem(Lnet/minecraft/ItemStack;)V"), index = 0)
    private ItemStack inject(ItemStack created_item_stack) {
        return new ItemStack(Items.SuspiciousPotion, 1, 0);
    }
}
