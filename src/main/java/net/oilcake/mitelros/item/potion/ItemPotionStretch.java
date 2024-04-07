package net.oilcake.mitelros.item.potion;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.api.ITFPlayer;

import java.util.List;

public class ItemPotionStretch extends Item {

    public ItemPotionStretch(int id) {
        super(id, Material.glass, "stretch_potion");
        setMaxStackSize(1);
        setCraftingDifficultyAsComponent(25.0F);
        setCreativeTab(CreativeTabs.tabMisc);
        ((ITFItem) this).setWater(3);
    }

    @Override
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.addPotionEffect(new PotionEffect(PotionExtend.stretch.id, 3600, 0));
            ((ITFPlayer) player).addWater(((ITFItem) this).getWater());
        }
        super.onItemUseFinish(item_stack, world, player);
    }

    @Override
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        super.addInformation(item_stack, player, info, extended_info, slot);
        if (extended_info) {
            info.add(EnumChatFormatting.AQUA + Translator.getFormatted("item.tooltip.reach.add", new Object[]{3}));
        }
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    public boolean isDrinkable(int item_subtype) {
        return true;
    }

    public Item getItemProducedOnItemUseFinish() {
        return glassBottle;
    }
}
