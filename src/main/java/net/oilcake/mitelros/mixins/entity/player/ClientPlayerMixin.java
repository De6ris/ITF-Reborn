package net.oilcake.mitelros.mixins.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.block.api.ITFWorkbench;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayer.class)
public abstract class ClientPlayerMixin extends AbstractClientPlayer implements ITFPlayer {
    @Shadow
    protected Minecraft mc;

    public ClientPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Override
    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        this.mc.displayGuiScreen(new GuiEnchantReserver(this, x, y, z, slots));
    }

    @WrapOperation(method = "getBenchAndToolsModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockWorkbench;getToolMaterial(I)Lnet/minecraft/Material;"))
    private Material itfWorkBench(int metadata, Operation<Material> original, @Local ContainerWorkbench workbench) {
        if (workbench.world.getBlockId(workbench.x, workbench.y, workbench.z) == Blocks.itfWorkBench.blockID) {
            return ITFWorkbench.getToolMaterial(metadata);
        } else {
            return original.call(metadata);
        }
    }

    @Inject(method = "getBenchAndToolsModifier", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/BlockWorkbench;getToolMaterial(I)Lnet/minecraft/Material;", shift = At.Shift.AFTER), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectModifyWorkbenchModifier(Container container, CallbackInfoReturnable<Float> cir, ContainerWorkbench workbench, SlotCrafting slot_crafting, ItemStack item_stack, Item item, IRecipe recipe, Material material_to_check_tool_bench_hardness_against, Material benchMaterial) {
        if (benchMaterial.min_harvest_level < material_to_check_tool_bench_hardness_against.min_harvest_level) {
            cir.setReturnValue(0.0F);
        } else {
            float modifier = ITFWorkbench.getCraftingSpeedModifier(benchMaterial);
            if (modifier != 0.0f) cir.setReturnValue(modifier);
        }
    }

    @ModifyExpressionValue(method = "setSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/ClientPlayer;hasCurse(Lnet/minecraft/Curse;Z)Z"))
    private boolean cantRun(boolean original) {
        return original || (getHealth() / 5.0F < 1.0F && ITFConfig.Realistic.get());
    }
}
