package net.oilcake.mitelros.mixins.gui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.block.api.ITFWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiCrafting.class)
public abstract class GuiCraftingMixin extends InventoryEffectRenderer {
    public GuiCraftingMixin(Container par1Container) {
        super(par1Container);
    }

    @WrapOperation(method = "drawGuiContainerForegroundLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockWorkbench;getToolMaterial(I)Lnet/minecraft/Material;"))
    private Material addITFWorkbench(int metadata, Operation<Material> original) {
        ContainerWorkbench workbench = (ContainerWorkbench) this.inventorySlots;
        if (workbench.world.getBlockId(workbench.x, workbench.y, workbench.z) == Blocks.itfWorkBench.blockID) {
            return ITFWorkbench.getToolMaterial(metadata);
        } else {
            return original.call(metadata);
        }
    }
}
