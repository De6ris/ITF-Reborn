package net.oilcake.mitelros.item.minePocket;

import net.minecraft.*;
import net.oilcake.mitelros.item.ItemPieces;

/**
 * Basically copied from container hopper
 * */
public class ContainerMinePocket extends Container {

    private final IInventory inventory;

    public ContainerMinePocket(EntityPlayer player, IInventory par2IInventory) {
        super(player);
        int var4;
        this.inventory = par2IInventory;
        par2IInventory.openChest();
        int var3 = 51;
        for (var4 = 0; var4 < par2IInventory.getSizeInventory(); ++var4) {
            this.addSlotToContainer(new Slot(par2IInventory, var4, 44 + var4 * 18, 20) {
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
            this.addSlotToContainer(new Slot(player.inventory, var4, 8 + var4 * 18, 58 + var3));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.inventory.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            if (par2 < this.inventory.getSizeInventory() ? !this.mergeItemStack(var5, this.inventory.getSizeInventory(), this.inventorySlots.size(), true) : !this.mergeItemStack(var5, 0, this.inventory.getSizeInventory(), false)) {
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
        this.inventory.closeChest();
    }
}
