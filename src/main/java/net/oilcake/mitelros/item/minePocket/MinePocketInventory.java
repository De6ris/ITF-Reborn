package net.oilcake.mitelros.item.minePocket;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class MinePocketInventory extends InventoryBasic {
    private final ItemStack itemStack;

    public MinePocketInventory(String par1Str, boolean par2, ItemStack itemStack) {
        super(par1Str, par2, 5);
        if (itemStack.itemID != Items.minePocket.itemID) {
            Minecraft.setErrorMessage("why create inventory for not mine pocket");
        }
        this.itemStack = itemStack;
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        if (tagCompound != null && tagCompound.hasKey("Items")) {
            NBTTagList minePocket = tagCompound.getTagList("Items");
            for (int index = 0; index < minePocket.tagCount(); index++) {
                NBTTagCompound nbtBase = (NBTTagCompound) minePocket.tagAt(index);
                int slot = nbtBase.getByte("Slot");
                this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(nbtBase));
            }
        }
    }

    @Override
    public void closeChest() {
        NBTTagCompound tagCompound = this.itemStack.getTagCompound();
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
        }
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.getSizeInventory(); ++var3) {
            ItemStack var4 = this.getStackInSlot(var3);
            if (var4 == null) continue;
            NBTTagCompound var5 = new NBTTagCompound();
            var5.setByte("Slot", (byte) var3);
            var4.writeToNBT(var5);
            var2.appendTag(var5);
        }
        tagCompound.setTag("Items", var2);
        this.itemStack.setTagCompound(tagCompound);
    }
}
