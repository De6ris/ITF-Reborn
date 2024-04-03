package net.oilcake.mitelros.mixins.item;

import java.io.PrintStream;
import java.util.List;

import net.minecraft.Block;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.IRecipe;
import net.minecraft.Item;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Item.class})
public abstract class ItemMixin implements ITFItem {
    private int water;

    public Item item;

    @Shadow
    private int satiation;

    @Shadow
    private int nutrition;

    @Shadow
    private int sugar_content;

    @Shadow
    private boolean has_protein;

    @Shadow
    private boolean has_essential_fats;

    @Shadow
    private boolean has_phytonutrients;

    @Redirect(method = {"<init>(ILjava/lang/String;I)V"}, at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"))
    public void removePrint(PrintStream printStream, String messsage) {
    }

    @Redirect(method = {"doesSubtypeMatterForProduct(Lnet/minecraft/ItemStack;)Z"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"))
    public void removeErrorInfo(String text) {
    }

    public final Item setFoodValueForWater(int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, int water) {
        this.satiation = satiation;
        this.nutrition = nutrition;
        this.sugar_content = sugar_content;
        this.has_protein = has_protein;
        this.has_essential_fats = has_essential_fats;
        this.has_phytonutrients = has_phytonutrients;
        this.water = water;
        if (satiation > 0 || nutrition > 0)
            setCreativeTab(CreativeTabs.tabFood);
        return this.item;
    }// TODO why no use

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void addInformation(ItemStack item_stack, EntityPlayer player, List<String> info, boolean extended_info, Slot slot) {
        if (extended_info) {
            int satiation = getSatiation(player);
            int nutrition = getNutrition();
            int water = getWater();
            if (this.satiation > 0 || nutrition > 0) {
                info.add("");
                if (this.item instanceof ItemBlock item_block) {
                    if (item_block.getBlock() == Block.mushroomRed) {
                        info.add(EnumChatFormatting.RED + Translator.getFormatted("item.tooltip.satiation", new Object[]{satiation}));
                        info.add(EnumChatFormatting.RED + Translator.getFormatted("item.tooltip.nutrition", new Object[]{nutrition}));
                        return;
                    }
                }
                if (this.satiation > 0) {
                    EnumChatFormatting color = (this.sugar_content > 0 && player.isInsulinResistant()) ? player.getInsulinResistanceLevel().getColor() : EnumChatFormatting.LIGHT_GRAY;
                    info.add((color + Translator.getFormatted("item.tooltip.satiation", new Object[]{satiation})));
                }
                if (nutrition > 0)
                    info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("item.tooltip.nutrition", new Object[]{nutrition}));
            }
            if (this.water != 0)
                if (this.water < 0) {
                    info.add(EnumChatFormatting.YELLOW + Translator.getFormatted("item.tooltip.water.minus", new Object[]{Integer.valueOf(water)}));
                } else {
                    info.add(EnumChatFormatting.AQUA + Translator.getFormatted("item.tooltip.water.add", new Object[]{Integer.valueOf(water)}));
                }
        }
    }

    public int getWater() {
        return this.water;
    }

    public Item setWater(int water) {
        this.water = water;
        return this.item;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public Item getRepairItem() {
        Material material_for_repairs = getMaterialForRepairs();
        if (material_for_repairs == Material.copper)
            return Item.copperNugget;
        if (material_for_repairs == Material.silver)
            return Item.silverNugget;
        if (material_for_repairs == Materials.wolf_fur)
            return Items.Wolf_fur;
        if (material_for_repairs == Material.gold)
            return Item.goldNugget;
        if (material_for_repairs != Material.iron && material_for_repairs != Material.rusted_iron) {
            if (material_for_repairs == Material.mithril)
                return Item.mithrilNugget;
            if (material_for_repairs == Material.adamantium)
                return Item.adamantiumNugget;
            if (material_for_repairs == Materials.nickel)
                return Items.nickelNugget;
            if (material_for_repairs == Materials.tungsten)
                return Items.tungstenNugget;
            if (material_for_repairs == Materials.ancient_metal_sacred)
                return Items.AncientmetalArmorPiece;
            if (material_for_repairs == Materials.uru)
                return Items.UruNugget;
            return (material_for_repairs == Material.ancient_metal) ? Item.ancientMetalNugget : null;
        }
        return Item.ironNugget;
    }

    @Shadow
    public Material getMaterialForRepairs() {
        return null;
    }

    @Shadow
    public Item setCreativeTab(CreativeTabs table) {
        return null;
    }

    @Shadow
    public Item setMaxStackSize(int maxStackSize) {
        return null;
    }

    @Shadow
    @Final
    public int getSatiation(EntityPlayer player) {
        return 1;
    }

    @Shadow
    public int getNutrition() {
        return 1;
    }
}
