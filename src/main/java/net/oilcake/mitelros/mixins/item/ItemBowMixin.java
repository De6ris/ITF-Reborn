package net.oilcake.mitelros.mixins.item;

import java.util.List;
import java.util.Random;

import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityArrow;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.EnumSignal;
import net.minecraft.Item;
import net.minecraft.ItemArrow;
import net.minecraft.ItemBow;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Packet85SimpleSignal;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.minecraft.World;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemBow.class)
public class ItemBowMixin extends Item {
    @Final
    @Shadow
    @Mutable
    private static Material[] possible_arrow_materials;

    @Shadow
    private Material reinforcement_material;

    public ItemBowMixin(int id, Material reinforcement_material) {
        super(id, Material.wood, "bows/" + reinforcement_material.toString() + "/");
    }

    @Inject(method = {"<clinit>()V"}, at = {@At("FIELD")})
    private static void injectClinit(CallbackInfo callback) {
        possible_arrow_materials = new Material[]{
                Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal,
                Materials.nickel, Materials.tungsten, Materials.magical};
    }

    @Overwrite
    public static int getTicksForMaxPull(ItemStack item_stack) {
        int TicksPull;
        Material material = item_stack.getMaterialForRepairs();
        if (material == Materials.tungsten) {
            TicksPull = 30;
        } else if (material == Material.mithril) {
            TicksPull = 27;
        } else if (material == Material.ancient_metal) {
            TicksPull = 24;
        } else {
            TicksPull = 20;
        }
        return (int) (TicksPull * (1.0F - 0.5F * EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.quickness, item_stack)));
    }

    @Overwrite
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        if (!player.inCreativeMode() && player.inventory.getReadiedArrow() == null && !EnchantmentHelper.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity)) {
            return false;
        } else {
            player.nocked_arrow = player.inventory.getReadiedArrow();
            if ((player.nocked_arrow == null && player.inCreativeMode()) || EnchantmentHelper.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity))
                player.nocked_arrow = Items.arrowMagical;
            if (player.onServer())
                player.sendPacketToAssociatedPlayers((new Packet85SimpleSignal(EnumSignal.nocked_arrow)).setShort(player.nocked_arrow.itemID).setEntityID(player), false);
            player.setHeldItemInUse();
            return true;
        }
    }

    @Redirect(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;inCreativeMode()Z"))
    private boolean inject(EntityPlayer instance) {
        return true;
    }

    @Inject(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;tryDamageHeldItem(Lnet/minecraft/DamageSource;I)Lnet/minecraft/ItemDamageResult;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void modifyDamage(ItemStack item_stack, World world, EntityPlayer player, int item_in_use_count, CallbackInfo ci, ItemArrow arrow, float fraction_pulled, EntityArrow entity_arrow, int power, int punch) {
        if (power > 0) {
            entity_arrow.setDamage(entity_arrow.getDamage() - (double) ((float) power * 0.5F) - 0.5);
        }
        Material material = item_stack.getMaterialForRepairs();
        if (material == Materials.tungsten) {
            entity_arrow.setDamage(entity_arrow.getDamage() * 1.149999976158142D);
        } else if (material == Material.mithril) {
            entity_arrow.setDamage(entity_arrow.getDamage() * 1.100000023841858D);
        } else if (material == Material.ancient_metal) {
            entity_arrow.setDamage(entity_arrow.getDamage() * 1.0499999523162842D);
        } else {
            entity_arrow.setDamage(entity_arrow.getDamage() * 0.75D);
        }
        if (power > 0) {
            entity_arrow.setDamage(entity_arrow.getDamage() + (double) ((float) power * 0.5F) + 0.5);
        }
    }

    @Inject(method = {"<init>(ILnet/minecraft/Material;)V"}, at = {@At("RETURN")})
    private void injectInit(CallbackInfo callbackInfo) {
        this.setMaxDamage((this.reinforcement_material == Materials.tungsten) ? 256 : ((this.reinforcement_material == Material.mithril) ? 128 : ((this.reinforcement_material == Material.ancient_metal) ? 64 : 32)));
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info && this.reinforcement_material.isMetal()) {
            int bonus = (this.reinforcement_material == Material.mithril) ? 25 : ((this.reinforcement_material == Materials.tungsten) ? 35 : 10);
            info.add("");
            info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.velocityBonus", new Object[]{Integer.valueOf(bonus)}));
        }
        super.addInformation(item_stack, player, info, extended_info, slot);
    }

    @Redirect(method = {"<init>(ILnet/minecraft/Material;)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemBow;setMaxDamage(I)Lnet/minecraft/Item;"))
    private Item redirectSetMaxDamage(ItemBow instance, int i) {
        if (this.reinforcement_material == Materials.tungsten) {
            setMaxDamage(256);
        } else if (this.reinforcement_material == Material.mithril) {
            setMaxDamage(128);
        } else if (this.reinforcement_material == Material.ancient_metal) {
            setMaxDamage(64);
        } else {
            setMaxDamage(32);
        }
        return null;
    }
}
