package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFEnchantment;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.QualityHandler;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    public abstract EnumQuality getQuality();

    @Shadow
    public abstract NBTTagList getEnchantmentTagList();

    @Shadow
    public abstract boolean hasTagCompound();

    @Shadow
    public int itemID;

    public int getWater() {
        return ((ITFItem) getItem()).getWater();
    }

    @Redirect(method = "getTooltip(Lnet/minecraft/EntityPlayer;ZLnet/minecraft/Slot;)Ljava/util/List;", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemStack;getEnchantmentTagList()Lnet/minecraft/NBTTagList;"))
    private NBTTagList removeOriginallyEnchantmentInfo0(ItemStack instance) {
        return null;
    }

    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemTool;getToolMaterial()Lnet/minecraft/Material;"))
    private void nickelInfo(EntityPlayer par1EntityPlayer, boolean par2, Slot slot, CallbackInfoReturnable<List> cir, @Local ArrayList<String> var3) {
        Item var4 = Item.itemsList[this.itemID];
        if (((ItemTool) var4).getToolMaterial() == Materials.nickel) {
            var3.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
        }
    }

    @Inject(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/Item;addInformation(Lnet/minecraft/ItemStack;Lnet/minecraft/EntityPlayer;Ljava/util/List;ZLnet/minecraft/Slot;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void addEnchantmentsInformation(EntityPlayer par1EntityPlayer, boolean par2, Slot slot, CallbackInfoReturnable<List> cir, ArrayList var3, Item var4) {
        NBTTagList tagList;
        if (this.hasTagCompound() && (tagList = this.getEnchantmentTagList()) != null) {
            if (tagList.tagCount() > 0) {
                var3.add("");
            }
            for (int i = 0; i < tagList.tagCount(); i++) {
                short id = ((NBTTagCompound) (tagList.tagAt(i))).getShort("id");
                short lvl = ((NBTTagCompound) (tagList.tagAt(i))).getShort("lvl");
                Enchantment enchantment = Enchantment.enchantmentsList[id];
                if (enchantment != null) {
                    if (((ITFEnchantment) enchantment).isCurse()) {
                        var3.add(EnumChatFormatting.RED + enchantment.getTranslatedName(lvl, ReflectHelper.dyCast(this)));
                    } else if (((ITFEnchantment) enchantment).isTreasure()) {
                        var3.add(EnumChatFormatting.getByChar('6') + enchantment.getTranslatedName(lvl, ReflectHelper.dyCast(this)));
                    } else {
                        var3.add(EnumChatFormatting.AQUA + enchantment.getTranslatedName(lvl, ReflectHelper.dyCast(this)));
                    }
                }
            }
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

    @Shadow
    public Item getItem() {
        return null;
    }
}
