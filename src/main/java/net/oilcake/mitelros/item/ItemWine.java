package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.item.potion.PotionExtend;

import java.util.List;

public class ItemWine extends Item {
    public ItemWine(int id) {
        super(id, Material.glass, "alcohol");
        setMaxStackSize(1);
        setCraftingDifficultyAsComponent(512.0F);
        setCreativeTab(CreativeTabs.tabMisc);
        ((ITFItem) this).itf$SetFoodWater(2);
    }

    @Override
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            ((ITFPlayer) player).itf$GetDrunkManager().setHasDrunk(true);
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 400, 0));
            player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 2560, 0));
            ((ITFPlayer) player).itf$AddWater(((ITFItem) this).itf$GetFoodWater());
        }
        super.onItemUseFinish(item_stack, world, player);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    @Override
    public boolean isDrinkable(int item_subtype) {
        return true;
    }

    @Override
    public Item getItemProducedOnItemUseFinish() {
        return glassBottle;
    }

    @Override
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info)
            info.add(EnumChatFormatting.RED + Translator.getFormatted("itemwine.tooltip.warning", new Object[0]));
    }
}
