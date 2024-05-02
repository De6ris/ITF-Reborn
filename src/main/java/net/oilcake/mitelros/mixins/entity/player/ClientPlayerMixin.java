package net.oilcake.mitelros.mixins.entity.player;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
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

    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        this.mc.displayGuiScreen(new GuiEnchantReserver(this, x, y, z, slots));
    }

    @Inject(method = "getBenchAndToolsModifier", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/BlockWorkbench;getToolMaterial(I)Lnet/minecraft/Material;", shift = At.Shift.AFTER), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectModifyWorkbenchModifier(Container container, CallbackInfoReturnable<Float> cir, ContainerWorkbench container_workbench, SlotCrafting slot_crafting, ItemStack item_stack, Item item, IRecipe recipe, Material material_to_check_tool_bench_hardness_against, Material benchMaterial) {
        if (benchMaterial.getMinHarvestLevel() < material_to_check_tool_bench_hardness_against.getMinHarvestLevel()) {
            cir.setReturnValue(0.0F);
        } else if (benchMaterial == Material.flint || benchMaterial == Material.obsidian) {
            cir.setReturnValue(0.25F);
        } else if (benchMaterial == Material.copper || benchMaterial == Material.silver || benchMaterial == Material.gold) {
            cir.setReturnValue(0.4F);
        } else if (benchMaterial == Material.iron || benchMaterial == Materials.nickel) {
            cir.setReturnValue(0.5F);
        } else if (benchMaterial == Material.ancient_metal) {
            cir.setReturnValue(0.75F);
        } else if (benchMaterial == Material.mithril) {
            cir.setReturnValue(1.0F);
        } else if (benchMaterial == Materials.tungsten) {
            cir.setReturnValue(1.5F);
        } else if (benchMaterial == Material.adamantium) {
            cir.setReturnValue(2.5F);
        } else {
            Minecraft.setErrorMessage("getBenchAndToolsModifier: unrecognized tool material " + benchMaterial);
            cir.setReturnValue(0.0F);
        }
    }

    @ModifyExpressionValue(method = "setSprinting", at = @At(value = "INVOKE", target = "Lnet/minecraft/ClientPlayer;hasCurse(Lnet/minecraft/Curse;Z)Z"))
    private boolean cantRun(boolean original) {
        return original || (getHealth() / 5.0F < 1.0F && ITFConfig.Realistic.get());
    }
}
