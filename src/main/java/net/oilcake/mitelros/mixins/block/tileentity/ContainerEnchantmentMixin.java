package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.ItemGoldenAppleLegend;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ContainerEnchantment.class)
public class ContainerEnchantmentMixin extends Container {
    @Shadow
    public IInventory tableInventory;
    @Shadow
    private int posX;

    @Shadow
    private int posY;

    @Shadow
    private int posZ;

    public ContainerEnchantmentMixin(EntityPlayer player) {
        super(player);
    }

    @Shadow
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }

    @Inject(method = "enchantItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemAppleGold;isUnenchantedGoldenApple(Lnet/minecraft/ItemStack;)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void itfApple(EntityPlayer par1EntityPlayer, int par2, CallbackInfoReturnable<Boolean> cir, ItemStack var3, int experience_cost) {
        if (ItemGoldenAppleLegend.isUnenchantedGoldenApple(var3)) {
            par1EntityPlayer.addExperience(-experience_cost);
            this.tableInventory.setInventorySlotContents(0, new ItemStack(Items.goldenAppleLegend, 1, 1));
            par1EntityPlayer.triggerAchievement(AchievementExtend.decimator);
            cir.setReturnValue(true);
        }
    }

    @ModifyArg(method = "calcEnchantmentLevelsForSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/EnchantmentHelper;getEnchantmentLevelsAlteredByItemEnchantability(ILnet/minecraft/Item;)I"))
    private int enhance(int enchantment_levels) {
        boolean enhanced = (this.world.getBlock(this.posX, this.posY - 1, this.posZ) == Blocks.blockEnchantEnhancer);
        return enchantment_levels * (enhanced ? 2 : 1);
    }
}
