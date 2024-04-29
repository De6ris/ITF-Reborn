package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

    @Shadow
    @Final
    public int itemID;
    @Unique
    private int water;
    @Unique
    private int temperature;

    @Unique
    private String extraInfo;


    @Redirect(method = "<init>(ILjava/lang/String;I)V", at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"))
    public void removePrint(PrintStream printStream, String messsage) {
    }

    @Redirect(method = "doesSubtypeMatterForProduct(Lnet/minecraft/ItemStack;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    public void removeErrorInfo(String text) {
    }

    @Inject(method = "getReachBonus(Lnet/minecraft/Block;I)F", at = @At("HEAD"), cancellable = true)
    private void alwaysBonus(Block block, int metadata, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(this.reach_bonus);
    }

    @Inject(method = "addInformation", at = @At(value = "INVOKE", target = "Lnet/minecraft/Item;getNutrition()I"))
    private void itfFoodInfo(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (item_stack.getItem() == Items.Agave) {
            info.add(EnumChatFormatting.AQUA + Translator.getFormatted("item.tooltip.water.agave", "40%"));
        } else if (this.water > 0) {
            info.add(EnumChatFormatting.AQUA + Translator.getFormatted("item.tooltip.water.add", this.water));
        } else if (this.water < 0) {
            info.add(EnumChatFormatting.YELLOW + Translator.getFormatted("item.tooltip.water.minus", this.water));
        }
        if (ReflectHelper.dyCast(this) instanceof ItemMeat meat && meat.is_cooked) {
            info.add(EnumChatFormatting.GOLD + Translator.getFormatted("item.tooltip.temperature.add", 1));
        }
        if (this.temperature > 0) {
            info.add(EnumChatFormatting.GOLD + Translator.getFormatted("item.tooltip.temperature.add", this.temperature));
        } else if (this.temperature < 0) {
            info.add(EnumChatFormatting.GREEN + Translator.getFormatted("item.tooltip.temperature.minus", -this.temperature));
        }
    }

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void extraInfo(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info) {
            String string = ((ITFItem) this).getExtraInfo();
            if (string != null) {
                info.add(EnumChatFormatting.LIGHT_GRAY + string);
            }
        }
    }

    public int getFoodWater() {
        return this.water;
    }

    public void setFoodWater(int water) {
        this.water = water;
    }

    @Override
    public int getFoodTemperature() {
        return this.temperature;
    }

    @Override
    public void setFoodTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getExtraInfo() {
        return extraInfo;
    }

    @Override
    public Item setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
        return ReflectHelper.dyCast(this);
    }

    @Inject(method = "getRepairItem", at = @At("HEAD"), cancellable = true)
    private void itfRepairItem(CallbackInfoReturnable<Item> cir) {
        Material material_for_repairs = this.getMaterialForRepairs();
        Item repairItem = itfRepairItem(material_for_repairs);
        if (repairItem != null) cir.setReturnValue(repairItem);
    }

    @Unique
    private static Item itfRepairItem(Material material_for_repairs) {
        if (material_for_repairs == Materials.nickel)
            return Items.nickelNugget;
        if (material_for_repairs == Materials.tungsten)
            return Items.tungstenNugget;
        if (material_for_repairs == Materials.ancient_metal_sacred)
            return Items.AncientmetalArmorPiece;
        if (material_for_repairs == Materials.uru)
            return Items.uruNugget;
        return null;
    }

    @Redirect(method = "getExclusiveMaterial", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private void noError(String text) {
    }

    @Redirect(method = "getMatchingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    private static void noError1(String text) {
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void reachBonus(CallbackInfo ci) {
        Item.blazeRod.setReachBonus(0.5F);
    }

    @ModifyReturnValue(method = "preventsHandDamage", at = @At("RETURN"))
    private boolean reachBonus(boolean original) {
        return original || this.itemID == Item.blazeRod.itemID || this.itemID == Items.enderRod.itemID;
    }

}
