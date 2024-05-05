package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.QualityHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract EnumQuality getQuality();

    @Shadow
    public int itemID;


    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemTool;getToolMaterial()Lnet/minecraft/Material;"))
    private void nickelInfo(EntityPlayer par1EntityPlayer, boolean par2, Slot slot, CallbackInfoReturnable<List> cir, @Local ArrayList<String> var3) {
        Item var4 = Item.itemsList[this.itemID];
        if (((ItemTool) var4).getToolMaterial() == Materials.nickel) {
            var3.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
        }
    }

    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/Item;addInformationBeforeEnchantments(Lnet/minecraft/ItemStack;Lnet/minecraft/EntityPlayer;Ljava/util/List;ZLnet/minecraft/Slot;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void inject(EntityPlayer par1EntityPlayer, boolean par2, Slot slot, CallbackInfoReturnable<List> cir, ArrayList var3, Item var4, String var5, boolean is_map) {
        if (par2 && var4.hasQuality()) {
            String description = QualityHandler.getQualityInfo(var4, this.getQuality());
            if (description != null) {
                var3.add(EnumChatFormatting.GRAY + description);
            }
        }
    }

    @ModifyArg(method = "tryDamageItem(Lnet/minecraft/DamageSource;ILnet/minecraft/EntityLivingBase;)Lnet/minecraft/ItemDamageResult;", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;tryDamageItem(Lnet/minecraft/World;IZ)Lnet/minecraft/ItemDamageResult;", ordinal = 0), index = 1)
    private int corrosion(int damage) {
        return (int) ((ITFConfig.TagCorrosion.getIntegerValue() * 0.3f + 1.0f) * damage);
    }
}
