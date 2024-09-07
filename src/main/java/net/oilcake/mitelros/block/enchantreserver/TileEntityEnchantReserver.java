package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;

import java.util.Arrays;

public class TileEntityEnchantReserver extends TileEntity implements ISidedInventory {
    private static final int[] slots_top = new int[]{0};

    private static final int[] slots_bottom = new int[]{1};

    private static final int[] slots_sides = new int[]{1};

    private final EnchantReserverInventory inventory = new EnchantReserverInventory(this);

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

    public EnchantReserverInventory getInventory() {
        return this.inventory;
    }

    private int EXP;

    private int last_EXP = -1;

    public int getEXP() {
        return this.EXP;
    }

    public void setEXP(int exp) {
        this.EXP = exp;
    }

    public int getMaxEXP() {
        return getStorage() + getLaunchEXP();
    }

    public int getLaunchEXP() {
        return 0;
    }

    public int getStorage() {
        return 30000;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int par1) {
        return (par1 == 0) ? slots_bottom : ((par1 == 1) ? slots_top : slots_sides);
    }

    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return isItemValidForSlot(par1, par2ItemStack);
    }

    @Override
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        if (par3 == 0 && par1 == 1)
            return (!(par2ItemStack.getItem() instanceof net.minecraft.ItemNugget) && par2ItemStack.getItem() != Item.potion);
        return true;
    }

    @Override
    public void updateEntity() {
        this.inventory.updateInfo();
        if (!(getWorldObj()).isRemote) {
            if (this.EXP != this.last_EXP)
                this.last_EXP = this.EXP;
            ItemStack inputStack = this.inventory.getInPutStack();
            if (inputStack != null) {
                this.checkInput(inputStack);
            }
            ItemStack outputStack = this.inventory.getOutPutStack();
            if (outputStack != null) {
                this.checkOutput(outputStack);
            }
        }
    }

    public void checkOutput(ItemStack outputStack) {
        if (this.EXP >= 200 && outputStack.itemID == Item.potion.itemID && outputStack.stackSize * 200 <= getEXP() - getLaunchEXP()) {
            this.EXP -= 200 * outputStack.stackSize;
            this.inventory.getOutPut().putStack(Item.expBottle.getItemStackForStatsIcon());
            return;
        }
        if (outputStack.getItem() instanceof ItemNugget nugget) {
            ItemCoin coin = ItemCoin.getForMaterial(nugget.getExclusiveMaterial());
            if (coin == null) return;
            int experienceValue = coin.getExperienceValue();
            if (experienceValue == 0) return;
            if (this.EXP >= outputStack.stackSize * experienceValue) {
                this.EXP -= outputStack.stackSize * experienceValue;
                this.inventory.getOutPut().putStack(new ItemStack(coin, outputStack.stackSize));
            }
        }
    }

    public void checkInput(ItemStack inputStack) {
        int xpUnit = ItemRock.getExperienceValueWhenSacrificed(inputStack);
        if (xpUnit != 0 && inputStack.stackSize * xpUnit + this.EXP <= this.getMaxEXP()) {
            this.EXP += inputStack.stackSize * xpUnit;
            this.inventory.getInPut().putStack(null);
        }
    }

    public void dropXP(World world, int x, int y, int z) {
        int var3;
//        world.playAuxSFX(2002, x, y, z, 0);
        for (int var2 = this.EXP; var2 > 0; var2 -= var3) {
            var3 = EntityXPOrb.getXPSplit(var2);
            world.spawnEntityInWorld(new EntityXPOrb(world, x, y, z, var3));
        }
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.items[i];
    }

    @Override
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

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.items[par1] != null) {
            ItemStack var2 = this.items[par1];
            this.items[par1] = null;
            return var2;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.items[var1] = var2;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (player.getWorld().getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof BlockEnchantReserver && player.getWorld().getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityEnchantReserver)
            return (player.getDistance(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D);
        return false;
    }

    @Override
    public void openChest() {
        this.inventory.updateInfo();
        this.isUsing = true;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.EXP = par1NBTTagCompound.getInteger("EXP");
        this.inventory.readFromNBT(par1NBTTagCompound, this);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("EXP", this.EXP);
        this.inventory.writeToNBT(par1NBTTagCompound);
    }

    @Override
    public void closeChest() {
        this.isUsing = false;
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return this.inventory.isItemValidForSlot(var1, var2);
    }

    @Override
    public void destroyInventory() {
        Arrays.fill(this.items, null);
    }
}
