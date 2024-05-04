package net.oilcake.mitelros.mixins.gui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.ContainerWorkbench;
import net.minecraft.GuiContainer;
import net.minecraft.GuiScreen;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.block.api.ITFWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiContainer.class)
public abstract class GuiContainerMixin extends GuiScreen {
    @WrapOperation(method = "drawItemStackTooltip(Lnet/minecraft/ItemStack;IILnet/minecraft/Slot;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockWorkbench;getToolMaterial(I)Lnet/minecraft/Material;"))
    private Material addITFWorkbench(int metadata, Operation<Material> original, @Local ContainerWorkbench workbench) {
        if (workbench.world.getBlockId(workbench.x, workbench.y, workbench.z) == Blocks.itfWorkBench.blockID) {
            return ITFWorkbench.getToolMaterial(metadata);
        } else {
            return original.call(metadata);
        }
    }
}
