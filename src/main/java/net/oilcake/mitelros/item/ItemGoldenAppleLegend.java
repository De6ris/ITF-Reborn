package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFItem;

public class ItemGoldenAppleLegend extends ItemAppleGold {
    public ItemGoldenAppleLegend(int id, int satiation, int nutrition, String texture) {
        super(id, satiation, nutrition, texture);
        ((ITFItem) this).itf$SetFoodWater(-8);
    }

    @Override
    public String getUnlocalizedName(ItemStack item_stack) {
        return isEnchantedGoldenApple(item_stack) ? "item.appleGold.enchanted" : super.getUnlocalizedName(item_stack);
    }

    public static boolean isGoldenApple(ItemStack item_stack) {
        return (item_stack != null && item_stack.itemID == Items.goldenAppleLegend.itemID);
    }

    public static boolean isUnenchantedGoldenApple(ItemStack item_stack) {
        return (isGoldenApple(item_stack) && item_stack.getItemSubtype() == 0);
    }

    public static boolean isEnchantedGoldenApple(ItemStack item_stack) {
        return (isGoldenApple(item_stack) && item_stack.getItemSubtype() > 0);
    }

    @Override
    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.getItemSubtype() == 0 && !par2World.isRemote) {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 600, 3));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 3));
        }
        if (par1ItemStack.getItemSubtype() > 0 && !par2World.isRemote) {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 600, 3));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 3));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 1));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 1));
        }
        // I dont call super.onEaten because golden apple gives effects again
    }
}
