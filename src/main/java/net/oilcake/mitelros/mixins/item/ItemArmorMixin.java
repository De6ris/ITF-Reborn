package net.oilcake.mitelros.mixins.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.misc.QualityHandler;
import net.oilcake.mitelros.util.Config;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemArmor.class)
public abstract class ItemArmorMixin extends Item implements IDamageableItem {
    @Mutable
    @Shadow
    @Final
    private boolean is_leather;

    @Shadow
    private Material effective_material;

    @Shadow
    @Final
    private boolean is_chain_mail;

    @Inject(method = {"<init>(ILnet/minecraft/Material;IZ)V"}, at = {@At("RETURN")})
    public void injectCtor(CallbackInfo callbackInfo) {
        this.is_leather = (this.effective_material == Material.leather || this.effective_material == Materials.wolf_fur);
        setCraftingDifficultyAsComponent(this.effective_material.durability * 100.0F * getNumComponentsForDurability());
    }

    @Inject(method = "addInformation", at = @At("TAIL"))
    private void inject(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo ci) {
        if (extended_info && item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel)
            info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemarmor.tooltip.slimeresistance", new Object[0]));
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final float getMultipliedProtection(ItemStack item_stack) {
        float multiplied_protection = (float) (this.getNumComponentsForDurability() * this.getMaterialProtection()) / 24.0f;
        if (item_stack != null && item_stack.hasEnchantment(Enchantment.protection, false)) {
            multiplied_protection += multiplied_protection * item_stack.getEnchantmentLevelFraction(Enchantment.protection) * 0.5f;
        }
        float qualityAmplifier = item_stack != null && item_stack.getItem().hasQuality() ? QualityHandler.getQualityAmplifier(item_stack.getQuality()) : 0;
        multiplied_protection *= 1.0F + qualityAmplifier / 100.0F;
        return multiplied_protection;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final int getMultipliedDurability() {
        float durability = getNumComponentsForDurability() * this.effective_material.durability;
        if (!this.is_chain_mail)
            durability *= 2.0F;
        if (durability < 1.0F)
            durability = 1.0F;
        return (int) durability;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getMaterialProtection() {
        int protection;
        if (this.effective_material == Materials.custom_a || this.effective_material == Materials.custom_b) {
            protection = 0;
        } else if (this.effective_material == Material.leather) {
            protection = 2;
        } else if (this.effective_material == Materials.wolf_fur) {
            protection = 3;
        } else if (this.effective_material == Material.rusted_iron || this.effective_material == Materials.vibranium || this.effective_material == Material.gold) {
            protection = 6;
        } else if (this.effective_material == Material.copper || this.effective_material == Material.silver) {
            protection = 7;
        } else if (this.effective_material == Materials.uru) {
            protection = 10;
        } else if (this.effective_material != Material.iron && this.effective_material != Material.ancient_metal && this.effective_material != Materials.nickel) {
            if (this.effective_material == Material.mithril || this.effective_material == Materials.tungsten || this.effective_material == Materials.ancient_metal_sacred) {
                protection = 9;
            } else {
                if (this.effective_material != Material.adamantium)
                    return 0;
                protection = 10;
            }
        } else {
            protection = 8;
        }
        if (this.is_chain_mail)
            protection -= 2;
        return protection;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final float getDamageFactor(ItemStack item_stack, EntityLivingBase owner) {
        if (owner != null && !owner.isEntityPlayer())
            return Config.TagInstinctSurvival.get() ? 0.75F : 0.5F;
        if (owner instanceof EntityPlayer && item_stack.getMaxDamage() > 1 && item_stack.getItemDamage() >= item_stack.getMaxDamage() - 1)
            return 0.0F;
        float armor_damage_factor = Config.TagArmament.get() ? (4.0F - item_stack.getItemDamage() / item_stack.getItem().getMaxDamage(item_stack) * 4.0F) : (2.0F - item_stack.getItemDamage() / item_stack.getItem().getMaxDamage(item_stack) * 2.0F);
        if (armor_damage_factor > 1.0F)
            armor_damage_factor = 1.0F;
        return armor_damage_factor;
    }

    @Shadow
    public final float getProtectionAfterDamageFactor(ItemStack item_stack, EntityLivingBase owner) {
        return 1.0F;
    }
}
