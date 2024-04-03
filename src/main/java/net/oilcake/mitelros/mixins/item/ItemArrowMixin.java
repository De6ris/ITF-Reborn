package net.oilcake.mitelros.mixins.item;

import java.util.List;

import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Item;
import net.minecraft.ItemArrow;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemArrow.class})
public class ItemArrowMixin extends Item {
    @Shadow
    @Final
    @Mutable
    public static Material[] material_types;

    @Shadow
    @Final
    public Material arrowhead_material;

    @Inject(method = {"<clinit>()V"}, at = {@At("RETURN")})
    private static void injectClinit(CallbackInfo callback) {
        material_types = new Material[]{
                Material.flint, Material.obsidian, Material.copper, Material.silver, Material.gold, Material.iron, Material.rusted_iron, Material.ancient_metal, Material.mithril, Material.adamantium,
                (Material) Materials.nickel, (Material) Materials.tungsten, (Material) Materials.magical};
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add("");
            info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.missileDamage", new Object[]{Integer.valueOf((int) getMaterialDamageVsEntity())}));
            info.add(EnumChatFormatting.GRAY + Translator.getFormatted("item.tooltip.missileRecovery", new Object[]{Integer.valueOf((int) (getChanceOfRecovery() * 100.0F))}));
            if (this.arrowhead_material == Materials.nickel)
                info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
        }
    }

    @Overwrite
    public float getChanceOfRecovery() {
        if (ReflectHelper.dyCast(this) == arrowFlint)
            return 0.3F;
        if (ReflectHelper.dyCast(this) == Item.arrowObsidian)
            return 0.4F;
        if (ReflectHelper.dyCast(this) == Item.arrowCopper)
            return 0.6F;
        if (ReflectHelper.dyCast(this) == Item.arrowSilver)
            return 0.6F;
        if (ReflectHelper.dyCast(this) == Item.arrowGold)
            return 0.5F;
        if (ReflectHelper.dyCast(this) == Item.arrowRustedIron)
            return 0.5F;
        if (ReflectHelper.dyCast(this) == Item.arrowIron)
            return 0.7F;
        if (ReflectHelper.dyCast(this) == Items.arrowNickel)
            return 0.7F;
        if (ReflectHelper.dyCast(this) == Item.arrowMithril)
            return 0.8F;
        if (ReflectHelper.dyCast(this) == Items.arrowAncientMetal)
            return 0.8F;
        if (ReflectHelper.dyCast(this) == Items.arrowTungsten)
            return 0.9F;
        if (ReflectHelper.dyCast(this) == Item.arrowAdamantium)
            return 0.9F;
        if (ReflectHelper.dyCast(this) == Items.arrowMagical)
            return 0.0F;
        return 0.7F;
    }

    @Shadow
    public float getMaterialDamageVsEntity() {
        return 1.0F;
    }
}
