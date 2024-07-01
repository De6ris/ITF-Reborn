package net.oilcake.mitelros.item.minePocket;

import net.minecraft.*;
import net.oilcake.mitelros.api.IItemLocked;
import net.oilcake.mitelros.item.ItemPieces;

/**
 * Basically copied from container hopper
 */
public class ContainerMinePocket extends Container {
    private final IInventory minePocketInventory;

    public ContainerMinePocket(EntityPlayer player, IInventory minePocketInventory) {
        super(player);
        int var4;
        this.minePocketInventory = minePocketInventory;
        minePocketInventory.openChest();
        int var3 = 51;
        for (var4 = 0; var4 < minePocketInventory.getSizeInventory(); ++var4) {
            this.addSlotToContainer(new Slot(minePocketInventory, var4, 44 + var4 * 18, 20) {
                @Override
                public boolean isItemValid(ItemStack itemStack) {
                    return itemStack.getItem() instanceof ItemNugget || itemStack.getItem() instanceof ItemPieces;
                }
            });
        }
        for (var4 = 0; var4 < 3; ++var4) {
            for (int var5 = 0; var5 < 9; ++var5) {
                this.addSlotToContainer(new Slot(player.inventory, var5 + var4 * 9 + 9, 8 + var5 * 18, var4 * 18 + var3));
            }
        }
        for (var4 = 0; var4 < 9; ++var4) {
            ItemStack stackInSlot = player.inventory.getStackInSlot(var4);
            if (stackInSlot != null && stackInSlot.getItem() instanceof IItemLocked) {
                this.addSlotToContainer(new Slot(player.inventory, var4, 8 + var4 * 18, 58 + var3) {
                    @Override
                    public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
                        return false;
                    }
                });
            } else {
                this.addSlotToContainer(new Slot(player.inventory, var4, 8 + var4 * 18, 58 + var3));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.minePocketInventory.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot) this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 < this.minePocketInventory.getSizeInventory() ? !this.mergeItemStack(var5, this.minePocketInventory.getSizeInventory(), this.inventorySlots.size(), true) : !this.mergeItemStack(var5, 0, this.minePocketInventory.getSizeInventory(), false)) {
                return null;
            }
            if (var5.stackSize == 0) {
                var4.putStack(null);
            } else {
                var4.onSlotChanged();
            }
        }
        return var3;
    }

    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        this.minePocketInventory.closeChest();
    }
}
