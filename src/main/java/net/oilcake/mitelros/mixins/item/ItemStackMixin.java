package net.oilcake.mitelros.mixins.item;

import com.google.common.collect.Multimap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.AttributeModifier;
import net.minecraft.Enchantment;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.EnumQuality;
import net.minecraft.Item;
import net.minecraft.ItemAppleGold;
import net.minecraft.ItemMap;
import net.minecraft.ItemPotion;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.NBTTagList;
import net.minecraft.NBTTagString;
import net.minecraft.Slot;
import net.minecraft.SlotCrafting;
import net.minecraft.StatCollector;
import net.minecraft.TileEntityFurnace;
import net.minecraft.Translator;
import net.oilcake.mitelros.api.ITFEnchantment;
import net.oilcake.mitelros.api.ITFFurnace;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.ItemGoldenAppleLegend;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemStack.class})
public class ItemStackMixin {

    @Shadow
    @Final
    public static DecimalFormat field_111284_a;

    @Shadow
    public int stackSize;

    @Shadow
    public int itemID;

    @Shadow
    public NBTTagCompound stackTagCompound;

    @Shadow
    private int subtype;

    public int getWater() {
        return ((ITFItem) getItem()).getWater();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public List getTooltip(EntityPlayer par1EntityPlayer, boolean par2, Slot slot) {
        ArrayList<String> var3 = new ArrayList();
        Item var4 = Item.itemsList[this.itemID];
        String var5 = EnumChatFormatting.WHITE + getMITEStyleDisplayName();
        boolean is_map = (this.itemID == Item.map.itemID);
        if (par2 && par1EntityPlayer.inCreativeMode() && !is_map) {
            String var6 = "";
            if (var5.length() > 0) {
                var5 = var5 + " (";
                var6 = ")";
            }
            if (getHasSubtypes()) {
                var5 = var5 + String.format("#%04d/%d%s", Integer.valueOf(this.itemID), Integer.valueOf(this.subtype), var6);
            } else {
                var5 = var5 + String.format("#%04d%s", Integer.valueOf(this.itemID), var6);
            }
            if (hasSignature())
                var5 = var5 + " [" + getSignature() + "]";
        } else if (!hasDisplayName() && is_map) {
            if (ItemMap.isBeingExtended(ReflectHelper.dyCast(this))) {
                var5 = "Extended Map";
            } else {
                var5 = var5 + " #" + this.subtype;
            }
        }
        var3.add(var5);
        if (var4.hasQuality())
            var3.add(EnumChatFormatting.GRAY + getQuality().getDescriptor());
        var4.addInformationBeforeEnchantments(ReflectHelper.dyCast(this), par1EntityPlayer, var3, par2, slot);
        if (hasTagCompound()) {
            NBTTagList var14 = getEnchantmentTagList();
            if (var14 != null) {
                if (var14.tagCount() > 0)
                    var3.add("");
                for (int experience_cost = 0; experience_cost < var14.tagCount(); experience_cost++) {
                    int required_heat_level = ((NBTTagCompound) var14.tagAt(experience_cost)).getShort("id");
                    int hypothetical_level = ((NBTTagCompound) var14.tagAt(experience_cost)).getShort("lvl");
                    if (Enchantment.enchantmentsList[required_heat_level] != null)
                        if (((ITFEnchantment) (Enchantment.enchantmentsList[required_heat_level])).isReverse()) {
                            var3.add(EnumChatFormatting.RED + Enchantment.enchantmentsList[required_heat_level].getTranslatedName(hypothetical_level, ReflectHelper.dyCast(this)));
                        } else if (((ITFEnchantment) Enchantment.enchantmentsList[required_heat_level]).isTreasure()) {
                            var3.add(EnumChatFormatting.getByChar('6') + Enchantment.enchantmentsList[required_heat_level].getTranslatedName(hypothetical_level, ReflectHelper.dyCast(this)));
                        } else {
                            var3.add(EnumChatFormatting.AQUA + Enchantment.enchantmentsList[required_heat_level].getTranslatedName(hypothetical_level, ReflectHelper.dyCast(this)));
                        }
                }
            }
        }
        var4.addInformation(ReflectHelper.dyCast(this), par1EntityPlayer, var3, par2, slot);
        if (hasTagCompound() && this.stackTagCompound.hasKey("display")) {
            NBTTagCompound var17 = this.stackTagCompound.getCompoundTag("display");
            if (var17.hasKey("color") && par2) {
                var3.add("");
                var3.add("Dyed Color: #" + Integer.toHexString(var17.getInteger("color")).toUpperCase());
            }
            if (var17.hasKey("Lore")) {
                NBTTagList var19 = var17.getTagList("Lore");
                if (var19.tagCount() > 0)
                    for (int required_heat_level = 0; required_heat_level < var19.tagCount(); required_heat_level++)
                        var3.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + ((NBTTagString) var19.tagAt(required_heat_level)).data);
            }
        }
        Multimap var16 = getAttributeModifiers();
        if (par2 && !var16.isEmpty()) {
            var3.add("");
            Iterator<Map.Entry> var15 = var16.entries().iterator();
            while (var15.hasNext()) {
                double var12;
                Map.Entry var18 = var15.next();
                AttributeModifier var21 = (AttributeModifier) var18.getValue();
                double var10 = var21.getAmount();
                if (var21.getOperation() != 1 && var21.getOperation() != 2) {
                    var12 = var21.getAmount();
                } else {
                    var12 = var21.getAmount() * 100.0D;
                }
                if (var10 > 0.0D) {
                    var3.add(EnumChatFormatting.BLUE + StatCollector.translateToLocalFormatted("attribute.modifier.plus." + var21.getOperation(), new Object[]{field_111284_a.format(var12), StatCollector.translateToLocal("attribute.name." + var18.getKey())}));
                    continue;
                }
                if (var10 < 0.0D) {
                    var12 *= -1.0D;
                    var3.add(EnumChatFormatting.RED + StatCollector.translateToLocalFormatted("attribute.modifier.take." + var21.getOperation(), new Object[]{field_111284_a.format(var12), StatCollector.translateToLocal("attribute.name." + var18.getKey())}));
                }
            }
        }
        if (par2 && var4 instanceof ItemTool tool) {
            if (tool.getToolMaterial() == Material.silver)
                var3.add(EnumChatFormatting.WHITE + Translator.get("item.tooltip.bonusVsUndead"));
        }
        if (par2 && getQuality() != null) {
            float modifier = getQuality().getDurabilityModifier();
            if (modifier < 1.0F) {
                var3.add(EnumChatFormatting.RED + Translator.getFormatted("item.tooltip.durabilityPenalty", new Object[]{Integer.valueOf((int) ((1.0F - modifier) * 100.0F))}));
            } else if (modifier > 1.0F) {
                var3.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.durabilityBonus", new Object[]{Integer.valueOf((int) ((modifier - 1.0F) * 100.0F))}));
            }
        }
        if (isArtifact()) {
            var3.add("");
            var3.add(EnumChatFormatting.AQUA + "Artifact");
        }
        if (hasTagCompound() && par2 && this.stackTagCompound.hasKey("flavor_text")) {
            String text = this.stackTagCompound.getString("flavor_text");
            List<String> text_lines = Minecraft.theMinecraft.fontRenderer.listFormattedStringToWidth(text, 120);
            var3.add("");
            for (int hypothetical_level = 0; hypothetical_level < text_lines.size(); hypothetical_level++)
                var3.add(EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + text_lines.get(hypothetical_level));
        }
        if (par2 && (Minecraft.theMinecraft.gameSettings.advancedItemTooltips || par1EntityPlayer.inCreativeMode()) && isItemStackDamageable()) {
            var3.add("");
            if (isItemDamaged()) {
                var3.add(Translator.get("item.tooltip.durability") + " " + (getMaxDamage() - getItemDamageForDisplay()) + " / " + getMaxDamage());
            } else {
                var3.add(Translator.get("item.tooltip.durability") + " " + getMaxDamage());
            }
        }
        if (slot instanceof SlotCrafting slot_crafting) {
            int experience_cost = ((EntityClientPlayerMP) par1EntityPlayer).crafting_experience_cost;
            if (experience_cost == 0 && (par1EntityPlayer.getAsEntityClientPlayerMP()).crafting_experience_cost_tentative > 0)
                experience_cost = (par1EntityPlayer.getAsEntityClientPlayerMP()).crafting_experience_cost_tentative;
            if (experience_cost == 0 && slot_crafting.getNumCraftingResults(par1EntityPlayer) > 1) {
                var3.add("");
                Item item = getItem();
                if (item.hasQuality()) {
                    Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.differentQuality", var3);
                } else if (item instanceof net.minecraft.ItemRunestone) {
                    Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.differentRunestone", var3);
                }
            } else if (experience_cost > 0) {
                int hypothetical_level = par1EntityPlayer.getExperienceLevel(par1EntityPlayer.experience - experience_cost);
                int level_cost = par1EntityPlayer.getExperienceLevel() - hypothetical_level;
                var3.add("");
                if (level_cost == 0) {
                    Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.qualityCostLessThanOneLevel", var3);
                } else if (level_cost == 1) {
                    Translator.addToList(EnumChatFormatting.YELLOW, "container.crafting.qualityCostOneLevel", var3);
                } else {
                    Translator.addToListFormatted(EnumChatFormatting.YELLOW, "container.crafting.qualityCostMoreThanOneLevel", var3, Integer.valueOf(level_cost));
                }
            }
        } else if (slot != null && slot.inventory instanceof TileEntityFurnace tile_entity_furnace) {
            if (tile_entity_furnace.getStackInSlot(0) == ReflectHelper.dyCast(this)) {
                int required_heat_level = TileEntityFurnace.getHeatLevelRequired(this.itemID);
                int hypothetical_level = (tile_entity_furnace.heat_level > 0) ? tile_entity_furnace.heat_level : tile_entity_furnace.getFuelHeatLevel();
                if (hypothetical_level > 0 && hypothetical_level < required_heat_level)
                    var3.add(EnumChatFormatting.RED + Translator.get("container.furnace.needsMoreHeat"));
                if (hypothetical_level > 0 && hypothetical_level > required_heat_level + 1)
                    var3.add(EnumChatFormatting.RED + Translator.get("container.furnace.tooHot"));
                if (tile_entity_furnace.getInputItemStack().getItem() instanceof net.minecraft.ItemFood && ((ITFFurnace) tile_entity_furnace).isBlastFurnace())
                    var3.add(EnumChatFormatting.YELLOW + Translator.get("container.furnace.wontFit"));
                if (!(tile_entity_furnace.getInputItemStack().getItem() instanceof net.minecraft.ItemFood) && ((ITFFurnace) tile_entity_furnace).isSmoker())
                    var3.add(EnumChatFormatting.YELLOW + Translator.get("container.furnace.wontFit"));
            }
        }
        return var3;
    }

    @Inject(method ="isEnchantable", at = @At("HEAD"))
    private void legendary(CallbackInfoReturnable<Boolean> cir) {
        if (ItemGoldenAppleLegend.isUnenchantedGoldenApple(this.getItem().getItemStackForStatsIcon())) {
            cir.setReturnValue(true);
        }
    }

    @Shadow
    public int getMaxStackSize() {
        return 0;
    }

    @Shadow
    public boolean isItemEnchanted() {
        return false;
    }

    public boolean isItemStackEqualC(ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype, boolean ignore_tag_compound) {
        return isItemStackEqual(par1ItemStack, ignore_stack_size, ignore_quality, ignore_damage_but_not_subtype, ignore_tag_compound);
    }

    @Shadow
    public EnumQuality getQuality() {
        return null;
    }

    @Shadow
    public Multimap getAttributeModifiers() {
        return null;
    }

    @Shadow
    protected boolean isItemStackEqual(ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype, boolean ignore_tag_compound) {
        return false;
    }

    @Shadow
    public String getMITEStyleDisplayName() {
        return null;
    }

    @Shadow
    public boolean hasSignature() {
        return false;
    }

    @Shadow
    public int getSignature() {
        return 1;
    }

    @Shadow
    public boolean hasTagCompound() {
        return false;
    }

    @Shadow
    public NBTTagList getEnchantmentTagList() {
        return null;
    }

    @Shadow
    public boolean hasDisplayName() {
        return false;
    }

    @Shadow
    public int getMaxDamage() {
        return 1;
    }

    @Shadow
    public Item getItem() {
        return null;
    }

    @Shadow
    public boolean isItemStackDamageable() {
        return false;
    }

    @Shadow
    public static boolean areItemStackTagsEqual(ItemStack par0ItemStack, ItemStack par1ItemStack) {
        return false;
    }

    @Shadow
    public boolean getHasSubtypes() {
        return false;
    }

    @Shadow
    public int getItemDamageForDisplay() {
        return 1;
    }

    @Shadow
    public boolean isItemDamaged() {
        return false;
    }

    @Shadow
    public boolean isArtifact() {
        return false;
    }
}
