package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFEnchantment;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.PrintStream;
import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin implements ITFItem {
    @Shadow
    private float reach_bonus;

    @Shadow
    public abstract Material getMaterialForRepairs();

    @Shadow
    protected List materials;
    private int water;

    public Item item;

    public int itemID;

    @Redirect(method = {"<init>(ILjava/lang/String;I)V"}, at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"))
    public void removePrint(PrintStream printStream, String messsage) {
    }

    @Redirect(method = {"doesSubtypeMatterForProduct(Lnet/minecraft/ItemStack;)Z"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    public void removeErrorInfo(String text) {
    }

    @Inject(method = "getReachBonus(Lnet/minecraft/Block;I)F", at = @At("HEAD"), cancellable = true)
    private void alwaysBonus(Block block, int metadata, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(this.reach_bonus);
    }

    @Inject(method = "addInformation", at = @At(value = "INVOKE", target = "Lnet/minecraft/Item;getNutrition()I"))
    private void waterInfo(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        int water = this.getWater();
        if (this.water == 0) return;
        if (this.water < 0) {
            info.add(EnumChatFormatting.YELLOW + Translator.getFormatted("item.tooltip.water.minus", new Object[]{water}));
        } else {
            info.add(EnumChatFormatting.AQUA + Translator.getFormatted("item.tooltip.water.add", new Object[]{water}));
        }
    }

    public int getWater() {
        return this.water;
    }

    public Item setWater(int water) {
        this.water = water;
        return this.item;
    }

    public void addInformationBeforeEnchantments(ItemStack item_stack, EntityPlayer player, List list, boolean extended_info, Slot slot) {
        NBTTagList tagList;
        if (item_stack.hasTagCompound() && (tagList = item_stack.getEnchantmentTagList()) != null) {
            if (tagList.tagCount() > 0) {
                list.add("");
            }
            for (int i = 0; i < tagList.tagCount(); i++) {
                short id = ((NBTTagCompound) (tagList.tagAt(i))).getShort("id");
                short lvl = ((NBTTagCompound) (tagList.tagAt(i))).getShort("lvl");
                Enchantment enchantment = Enchantment.enchantmentsList[id];
                if (enchantment != null) {
                    if (((ITFEnchantment) (Enchantment.enchantmentsList[id])).isReverse()) {
                        list.add(EnumChatFormatting.RED + Enchantment.enchantmentsList[id].getTranslatedName(lvl, item_stack));
                    } else if (((ITFEnchantment) Enchantment.enchantmentsList[id]).isTreasure()) {
                        list.add(EnumChatFormatting.getByChar('6') + Enchantment.enchantmentsList[id].getTranslatedName(lvl, item_stack));
                    }
                }
            }
        }
    }

    @Inject(method = {"addInformation(Lnet/minecraft/ItemStack;Lnet/minecraft/EntityPlayer;Ljava/util/List;ZLnet/minecraft/Slot;)V"}, at = {@At("RETURN")})
    public void addInformationFishingRodBeforeEnchantments(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        NBTTagList tagList;
        Item item = Item.itemsList[this.itemID];
        if (item_stack.hasTagCompound() && (tagList = item_stack.getEnchantmentTagList()) != null) {
            if (tagList.tagCount() > 0 && !(item instanceof ItemTool)) {
                info.add("");
            }
            for (int i = 0; i < tagList.tagCount(); i++) {
                short id = ((NBTTagCompound) (tagList.tagAt(i))).getShort("id");
                short lvl = ((NBTTagCompound) (tagList.tagAt(i))).getShort("lvl");
                Enchantment enchantment = Enchantment.enchantmentsList[id];
                if (enchantment != null && !(item instanceof ItemTool)) {
                    if (((ITFEnchantment) (Enchantment.enchantmentsList[id])).isReverse()) {
                        info.add(EnumChatFormatting.RED + Enchantment.enchantmentsList[id].getTranslatedName(lvl, item_stack));
                    } else if (((ITFEnchantment) Enchantment.enchantmentsList[id]).isTreasure()) {
                        info.add(EnumChatFormatting.getByChar('6') + Enchantment.enchantmentsList[id].getTranslatedName(lvl, item_stack));
                    }
                }
            }
        }
    }

    @ModifyReturnValue(method = "getRepairItem", at = @At("RETURN"))
    private Item reSet(Item original) {
        if (original != null) {
            return original;
        } else {
            Material material_for_repairs = this.getMaterialForRepairs();
            if (material_for_repairs == Materials.wolf_fur)
                return Items.wolf_fur;
            if (material_for_repairs == Materials.nickel)
                return Items.nickelNugget;
            if (material_for_repairs == Materials.tungsten)
                return Items.tungstenNugget;
            if (material_for_repairs == Materials.ancient_metal_sacred)
                return Items.AncientmetalArmorPiece;
            if (material_for_repairs == Materials.uru)
                return Items.UruNugget;
            return null;
        }
    }

    @Redirect(method = "getExclusiveMaterial", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void noError(String text) {
    }

    @Redirect(method = "getMatchingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private static void noError1(String text) {
    }


}
