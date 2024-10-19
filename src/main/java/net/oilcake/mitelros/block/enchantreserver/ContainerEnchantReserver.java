package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.network.ITFNetwork;
import net.oilcake.mitelros.network.packets.S2CEnchantReserverInfo;

import javax.annotation.Nullable;

public class ContainerEnchantReserver extends Container {
    @Nullable
    private final TileEntityEnchantReserver tileEntityEnchantReserver;

    private final EnchantReserverInventory inventory;

    private final int blockX;

    private final int blockY;

    private final int blockZ;

    public ContainerEnchantReserver(EnchantReserverInventory inventory, EntityPlayer player, int x, int y, int z) {
        super(player);
        this.blockX = x;
        this.blockY = y;
        this.blockZ = z;
        inventory.initSlots(this);
        this.inventory = inventory;
        if (!(player.getWorld()).isRemote) {
            this.tileEntityEnchantReserver = (TileEntityEnchantReserver) player.getWorldServer().getBlockTileEntity(x, y, z);
        } else {
            this.tileEntityEnchantReserver = null;
        }
        onCraftMatrixChanged(inventory);
        int index;
        for (index = 0; index < 3; index++) {
            for (int var8 = 0; var8 < 9; var8++) {
                addSlotToContainer(new Slot(player.inventory, var8 + index * 9 + 9, 8 + var8 * 18, 84 + index * 18));
            }
        }
        for (index = 0; index < 9; index++) {
            addSlotToContainer(new Slot(player.inventory, index, 8 + index * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.inventory.getSize()) {
                if (!mergeItemStack(itemstack1, this.inventory.getSize(), this.inventorySlots.size(), false))
                    return null;
            } else if (itemstack1.getItem() instanceof ItemPotion) {
                if (!mergeItemStack(itemstack1, this.inventory.getInputIndex(), this.inventory.getInputIndex() + 1, false))
                    return null;
            } else if (itemstack1.getItem() instanceof ItemRock &&
                    !mergeItemStack(itemstack1, this.inventory.getOutputIndex(), this.inventory.getOutputIndex() + 1, false)) {
                return null;
            }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, itemstack1);
        }
        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        if (!this.world.isRemote)
            this.inventory.onContainerClosed();
    }

    public void updateInfo() {
        if (!this.world.isRemote) {
            assert this.tileEntityEnchantReserver != null;
            ITFNetwork.sendToClient(((ServerPlayer) this.player), new S2CEnchantReserverInfo(this.tileEntityEnchantReserver.getEXP()));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        if (this.world.getBlock(this.blockX, this.blockY, this.blockZ) instanceof BlockEnchantReserver && this.world.getBlockTileEntity(this.blockX, this.blockY, this.blockZ) instanceof TileEntityEnchantReserver)
            return (player.getDistanceSq(this.blockX + 0.5D, this.blockY + 0.5D, this.blockZ + 0.5D) <= 64.0D);
        return false;
    }
}
