package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemMultiTextureTile;
import net.minecraft.Minecraft;
import net.oilcake.mitelros.block.BlockFlowerExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Inject(method = "<clinit>()V", at = @At("TAIL"))
    private static void injectClinit(CallbackInfo callback) {
        Item.itemsList[Blocks.flowerextend.blockID] = (new ItemMultiTextureTile(Blocks.flowerextend, BlockFlowerExtend.types)).setUnlocalizedName("flowers");
        mySetLightValue(Blocks.blockAzurite, 0.75f);
        mySetLightValue(Blocks.azuriteCluster, 0.5f);
        mySetLightValue(Blocks.torchWoodIdle, 0.5f);
        mySetLightValue(Blocks.torchWoodExtinguished, 0.1f);
    }

    private static void mySetLightValue(Block block, float par1) {// TODO for temporary use
        Block.lightValue[block.blockID - 3840] = (int) (15.0f * par1);
    }
    @Redirect(method = "dropBlockAsItself", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void removeInfo(String text) {
        if (text.startsWith("dropBlockAsEntityItem: info.block!=this")) {
            return;
        }
        Minecraft.setErrorMessage(text);
    }


    @Redirect(method = "dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;Lnet/minecraft/ItemStack;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void removeInfo_1(String text) {
        if (text.startsWith("dropBlockAsEntityItem: info.block!=this")) {
            return;
        }
        Minecraft.setErrorMessage(text);
    }

    @Redirect(method = "validate", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void inject(String text) {
        if (text.startsWith("No creative tab for [")) return;
        if (text.startsWith("Creative tab for [")) return;
        Minecraft.setErrorMessage(text);
    }
}
