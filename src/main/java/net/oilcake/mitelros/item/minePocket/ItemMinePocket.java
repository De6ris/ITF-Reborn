package net.oilcake.mitelros.item.minePocket;

import net.minecraft.*;
import net.oilcake.mitelros.api.IItemLocked;
import net.oilcake.mitelros.api.ITFPlayer;

public class ItemMinePocket extends Item implements IItemLocked {
    public ItemMinePocket(int id, Material material, String texture) {
        super(id, material, texture);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
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
