package net.oilcake.mitelros.mixins.gui;

import net.minecraft.GuiContainer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiContainer.class)
public class GuiContainerMixin {
//    @Inject(method = "drawItemStackTooltip(Lnet/minecraft/ItemStack;IILnet/minecraft/Slot;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityClientPlayerMP;isUpperBodyInWeb()Z"), locals = LocalCapture.CAPTURE_FAILSOFT)
//    private void temp(ItemStack par1ItemStack, int par2, int par3, Slot slot, CallbackInfo ci, List var4, Item item, IRecipe recipe, Material material_to_check_tool_bench_hardness_against) {
//        System.out.println(material_to_check_tool_bench_hardness_against);
//        System.out.println(item.getHardestMetalMaterial());
//        System.out.println("------------------");
//    }
}
