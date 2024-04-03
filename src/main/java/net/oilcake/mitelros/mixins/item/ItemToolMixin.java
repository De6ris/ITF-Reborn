package net.oilcake.mitelros.mixins.item;

import java.util.List;

import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemTool.class})
public class ItemToolMixin extends Item {
    @Shadow
    private float damageVsEntity;

    @Shadow
    private Material effective_material;

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        super.addInformation(item_stack, player, info, extended_info, slot);
        if (extended_info &&
                item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel)
            info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public float getMaterialHarvestEfficiency() {
        if (this.effective_material == Material.wood)
            return 1.0F;
        if (this.effective_material == Material.flint)
            return 1.25F;
        if (this.effective_material == Material.obsidian)
            return 1.5F;
        if (this.effective_material == Material.rusted_iron)
            return 1.25F;
        if (this.effective_material == Materials.vibranium)
            return 1.25F;
        if (this.effective_material == Material.copper)
            return 1.75F;
        if (this.effective_material == Material.silver)
            return 1.75F;
        if (this.effective_material == Material.gold)
            return 1.75F;
        if (this.effective_material == Material.iron)
            return 2.0F;
        if (this.effective_material == Material.mithril)
            return 2.5F;
        if (this.effective_material == Material.adamantium)
            return 3.0F;
        if (this.effective_material == Materials.uru)
            return 3.0F;
        if (this.effective_material == Material.diamond)
            return 2.5F;
        if (this.effective_material == Material.ancient_metal)
            return 2.0F;
        if (this.effective_material == Materials.nickel)
            return 2.0F;
        if (this.effective_material == Materials.tungsten)
            return 2.75F;
        Minecraft.setErrorMessage("getMaterialHarvestEfficiency: tool material not handled");
        return 0.0F;
    }
}
