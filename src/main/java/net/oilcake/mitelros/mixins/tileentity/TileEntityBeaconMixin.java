package net.oilcake.mitelros.mixins.tileentity;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityBeacon.class)
public abstract class TileEntityBeaconMixin extends TileEntity {
    @ModifyExpressionValue(method = "updateState", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;getBlockId(III)I"))
    private int addITFMetals(int original) {
        if (original == Block.blockAncientMetal.blockID || original == Blocks.blockNickel.blockID || original == Blocks.blockTungsten.blockID)
            return Block.blockEmerald.blockID;
        return original;
    }

    @Inject(method = "isItemValidForSlot", at = @At("HEAD"), cancellable = true)
    private void addITFMetal(int par1, ItemStack par2ItemStack, CallbackInfoReturnable<Boolean> cir) {
        if (par2ItemStack.itemID == Item.ingotMithril.itemID || par2ItemStack.itemID == Item.ingotAdamantium.itemID || par2ItemStack.itemID == Item.ingotAncientMetal.itemID || par2ItemStack.itemID == Item.ingotCopper.itemID || par2ItemStack.itemID == Item.ingotSilver.itemID || par2ItemStack.itemID == Items.nickelIngot.itemID || par2ItemStack.itemID == Items.tungstenIngot.itemID || par2ItemStack.itemID == Items.uruIngot.itemID)
            cir.setReturnValue(true);
    }
}
