package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockFlowerExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Inject(method = "<clinit>()V", at = @At("TAIL"))
    private static void injectClinit(CallbackInfo callback) {
        Item.itemsList[Blocks.flowerextend.blockID] = (new ItemMultiTextureTile(Blocks.flowerextend, BlockFlowerExtend.types)).setUnlocalizedName("flowers");
        Block.pumpkinLantern.setLightValue(0.9375f);
    }

    @Redirect(method = "dropBlockAsItself", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void noInfo(String text) {
        if (text.startsWith("dropBlockAsEntityItem: info.block!=this")) {
            return;
        }
        Minecraft.setErrorMessage(text);
    }


    @Redirect(method = "dropBlockAsEntityItem(Lnet/minecraft/BlockBreakInfo;Lnet/minecraft/ItemStack;)I", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void noInfo_1(String text) {
        if (text.startsWith("dropBlockAsEntityItem: info.block!=this")) {
            return;
        }
        Minecraft.setErrorMessage(text);
    }

    @Redirect(method = "validate", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void noError(String text) {
        if (text.startsWith("No creative tab for [")) return;
        if (text.startsWith("Creative tab for [")) return;
        Minecraft.setErrorMessage(text);
    }

}
