package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.Arrays;

public class TileEntityEnchantReserver extends TileEntity implements ISidedInventory {
    private static final int[] slots_top = new int[]{0};

    private static final int[] slots_bottom = new int[]{1};

    private static final int[] slots_sides = new int[]{1};

    private final EnchantReserverSlots slots = new EnchantReserverSlots(this);

    private final ItemStack[] items = new ItemStack[2];

    public EntityPlayer player;

    private boolean isUsing;

    public void setItem(int index, ItemStack itemStack) {
        this.items[index] = itemStack;
    }

    public int getSizeInventory() {
        return 2;
    }

    public boolean isUsing() {
        return this.isUsing;
    }

    public ItemStack getItem(int index) {
        return this.items[index];
    }

    public EnchantReserverSlots getSlots() {
        return this.slots;
    }

    private int EXP;

    private int last_EXP = -999999;

    public int getEXP() {
        return this.EXP;
    }

    public int setEXP(int exp) {
        return this.EXP = exp;
    }

    public int getMAXEXP() {
        return getStorage() + getLaunchEXP();
    }
    public int getLaunchEXP() {
        return 0;
    }

    public int getStorage() {
        return 30000;
    }

    public int[] getAccessibleSlotsFromSide(int par1) {
        return (par1 == 0) ? slots_bottom : ((par1 == 1) ? slots_top : slots_sides);
    }

    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return isItemValidForSlot(par1, par2ItemStack);
    }

    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        if (par3 == 0 && par1 == 1)
            return (!(par2ItemStack.getItem() instanceof net.minecraft.ItemNugget) && par2ItemStack.getItem() != Item.potion);
        return true;
    }

    public void updateEntity() {
        this.slots.updateInfo();
        if (!(getWorldObj()).isRemote) {
            if (getEXP() != this.last_EXP)
                this.last_EXP = this.EXP;
            ItemStack inputStack = this.slots.getInPutStack();
            if (inputStack != null)
                if (inputStack.itemID == Item.diamond.itemID && inputStack.stackSize * 500 + getEXP() <= getMAXEXP()) {
                    int size = inputStack.stackSize;
                    this.EXP += 500 * size;
                    this.slots.getInPut().putStack(null);
                } else if (inputStack.itemID == Item.emerald.itemID && inputStack.stackSize * 250 + getEXP() <= getMAXEXP()) {
                    int size = inputStack.stackSize;
                    this.EXP += 250 * size;
                    this.slots.getInPut().putStack(null);
                } else if (inputStack.itemID == Item.netherQuartz.itemID && inputStack.stackSize * 50 + getEXP() <= getMAXEXP()) {
                    int size = inputStack.stackSize;
                    this.EXP += 50 * size;
                    this.slots.getInPut().putStack(null);
                } else if (inputStack.itemID == Items.dyePowder.itemID && inputStack.getItemSubtype() == 4 && inputStack.stackSize * 25 + getEXP() <= getMAXEXP()) {
                    int size = inputStack.stackSize;
                    this.EXP += 25 * size;
                    this.slots.getInPut().putStack(null);
                }
            ItemStack outputStack = this.slots.getOutPutStack();
            if (outputStack != null) {
                if (getEXP() >= 200 &&
                        outputStack.itemID == Item.potion.itemID && outputStack.stackSize * 200 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 200 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(Item.expBottle.getItemStackForStatsIcon());
                }
                if (getEXP() >= 5 &&
                        outputStack.itemID == Item.copperNugget.itemID && outputStack.stackSize * 5 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 5 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Item.coinCopper, outputStack.stackSize));
                }
                if (getEXP() >= 25 &&
                        outputStack.itemID == Item.silverNugget.itemID && outputStack.stackSize * 25 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 25 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Item.coinSilver, outputStack.stackSize));
                }
                if (getEXP() >= 50 &&
                        outputStack.itemID == Items.nickelNugget.itemID && outputStack.stackSize * 50 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 50 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Items.nickelCoin, outputStack.stackSize));
                }
                if (getEXP() >= 100 &&
                        outputStack.itemID == Item.goldNugget.itemID && outputStack.stackSize * 100 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 100 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Item.coinGold, outputStack.stackSize));
                }
                if (getEXP() >= 500 &&
                        outputStack.itemID == Item.ancientMetalNugget.itemID && outputStack.stackSize * 500 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 500 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Item.coinAncientMetal, outputStack.stackSize));
                }
                if (getEXP() >= 2500 &&
                        outputStack.itemID == Item.mithrilNugget.itemID && outputStack.stackSize * 2500 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 2500 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Item.coinMithril, outputStack.stackSize));
                }
                if (getEXP() >= 5000 &&
                        outputStack.itemID == Items.tungstenNugget.itemID && outputStack.stackSize * 5000 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 5000 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Items.tungstenCoin, outputStack.stackSize));
                }
                if (getEXP() >= 10000 &&
                        outputStack.itemID == Item.adamantiumNugget.itemID && outputStack.stackSize * 10000 <= getEXP() - getLaunchEXP()) {
                    this.EXP -= 10000 * outputStack.stackSize;
                    this.slots.getOutPut().putStack(new ItemStack(Item.coinAdamantium, outputStack.stackSize));
                }
            }
        }
    }

    public ItemStack getStackInSlot(int i) {
        return this.items[i];
    }

    public ItemStack decrStackSize(int index, int count) {
        if (this.items[index] != null) {
            ItemStack var3;
            if ((this.items[index]).stackSize <= count) {
                var3 = this.items[index];
                this.items[index] = null;
            } else {
                var3 = this.items[index].splitStack(count);
                if ((this.items[index]).stackSize == 0)
                    this.items[index] = null;
            }
            return var3;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.items[par1] != null) {
            ItemStack var2 = this.items[par1];
            this.items[par1] = null;
            return var2;
        }
        return null;
    }

    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.items[var1] = var2;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        if (player.getWorld().getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof net.oilcake.mitelros.block.BlockEnchantReserver && player.getWorld().getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityEnchantReserver)
            return (player.getDistance(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D);
        return false;
    }

    public void openChest() {
        this.slots.updateInfo();
        this.isUsing = true;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.EXP = par1NBTTagCompound.getInteger("EXP");
        this.slots.readFromNBT(par1NBTTagCompound, this);
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("EXP", this.EXP);
        this.slots.writeToNBT(par1NBTTagCompound);
    }

    public void closeChest() {
        this.isUsing = false;
    }

    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return this.slots.isItemValidForSlot(var1, var2);
    }

    public void dropAllItems() {
        this.slots.dropItems(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public void destroyInventory() {
        Arrays.fill(this.items, null);
    }
}
