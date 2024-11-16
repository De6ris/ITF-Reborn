package net.oilcake.mitelros.item.minePocket;

import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.oilcake.mitelros.api.IItemLocked;
import net.oilcake.mitelros.api.ITFPlayer;

public class ItemMinePocket extends Item implements IItemLocked {
    public ItemMinePocket(int id, Material material, String texture) {
        super(id, material, texture);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        ItemStack heldItemStack = player.getHeldItemStack();
        if (heldItemStack.getItem() instanceof ItemMinePocket) {
            if (player.onServer()) {
                ((ITFPlayer) player).itf$DisplayGuiMinePocket(new MinePocketInventory(heldItemStack.getDisplayName(), false, heldItemStack));
            }
            return true;
        }
        return false;
    }
}
