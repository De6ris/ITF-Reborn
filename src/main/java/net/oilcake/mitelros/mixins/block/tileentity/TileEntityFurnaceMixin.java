package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFurnace;
import net.oilcake.mitelros.block.BlockBlastFurnace;
import net.oilcake.mitelros.block.BlockSmoker;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityFurnace.class)
public abstract class TileEntityFurnaceMixin extends TileEntity implements ISidedInventory, ITFFurnace {
    @Shadow
    protected abstract boolean canSmelt(int heat_level);

    @Shadow
    public static int getHeatLevelRequired(int item_id) {
        return 0;
    }

    @Shadow
    private ItemStack[] furnaceItemStacks = new ItemStack[3];

    @Shadow
    public int currentItemBurnTime;

    @Shadow
    public int furnaceBurnTime;

    @Shadow
    public int furnaceCookTime;

    @Shadow
    public int heat_level = 0;

    private boolean activated = false;

    @Inject(method = "getHeatLevelRequired", at = @At("HEAD"), cancellable = true)
    private static void itfHeatLevel(int item_id, CallbackInfoReturnable<Integer> cir) {
        Item item = Item.getItem(item_id);
        if (item instanceof net.minecraft.ItemTool)
            cir.setReturnValue((item.getHardestMetalMaterial() == Materials.tungsten) ? 4 : ((item.getHardestMetalMaterial() == Material.rusted_iron) ? 2 : 3));
        if (item instanceof net.minecraft.ItemArmor)
            cir.setReturnValue((item.getHardestMetalMaterial() == Materials.tungsten) ? 4 : ((item.getHardestMetalMaterial() == Material.rusted_iron) ? 2 : 3));
        if (item_id == Items.pieceAdamantium.itemID || item_id == Blocks.oreUru.blockID || item_id == Items.pieceUru.itemID)
            cir.setReturnValue(4);
        if (item_id == Blocks.oreTungsten.blockID || item_id == Items.pieceMithril.itemID || item_id == Items.pieceTungsten.itemID || item_id == Items.AncientmetalArmorPiece.itemID)
            cir.setReturnValue(3);
        if (item_id == Blocks.oreNickel.blockID || item_id == Items.pieceCopper.itemID || item_id == Items.pieceSilver.itemID || item_id == Items.pieceGold.itemID || item_id == Items.pieceGoldNether.itemID || item_id == Items.pieceIron.itemID || item_id == Items.pieceNickel.itemID)
            cir.setReturnValue(2);
    }

    @Inject(method = "canSmelt", at = @At("HEAD"), cancellable = true)
    private void canSmelt(int heat_level, CallbackInfoReturnable<Boolean> cir) {
        if (this.furnaceItemStacks[0] == null) {
            cir.setReturnValue(false);
            return;
        }
        if (this.getInputItemStack().getItem() instanceof ItemFood && this.isBlastFurnace()) {
            cir.setReturnValue(false);
            return;
        }
        if (this.getInputItemStack().getItem() instanceof ItemArmor && !this.isBlastFurnace()) {
            cir.setReturnValue(false);
            return;
        }
        if (this.getInputItemStack().getItem() instanceof ItemTool && !this.isBlastFurnace()) {
            cir.setReturnValue(false);
            return;
        }
        if (!(this.getInputItemStack().getItem() instanceof ItemFood) && this.isSmoker()) {
            cir.setReturnValue(false);
            return;
        }
        if (heat_level > getHeatLevelRequired((this.getInputItemStack().getItem()).itemID) + 1) {
            cir.setReturnValue(false);
            System.out.println("bad heat level" + heat_level  + "and" + getHeatLevelRequired((this.getInputItemStack().getItem()).itemID));
        }
    }

    @Override
    public boolean isBlastFurnace() {
        return getFurnaceBlock() instanceof BlockBlastFurnace;
    }

    @Override
    public boolean isSmoker() {
        return getFurnaceBlock() instanceof BlockSmoker;
    }

    public boolean canBurnbyItself() {
        return (getFuelHeatLevel() > 2);
    }

    public void activateFurnace() {
        this.activated = true;
    }

    private boolean canNormallyWork() {
        return (this.activated && this.furnaceItemStacks[1] != null);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void updateEntity() {// TODO hard to rewrite
        if (!this.worldObj.isRemote && !isBurning() && this.activated && this.furnaceItemStacks[1] == null) {
            this.activated = false;
        }
        if (this.worldObj.isRemote || this.furnaceBurnTime == 1 || (!isFlooded() && !isSmotheredBySolidBlock())) {
            boolean var1 = (this.furnaceBurnTime > 0);
            boolean var2 = false;
            if (this.furnaceBurnTime > 0) {
                float temp = 1.0F;
                if (getFurnaceBlock() instanceof BlockBlastFurnace || getFurnaceBlock() instanceof BlockSmoker)
                    temp = 2.0F;
                this.furnaceBurnTime = (int) (this.furnaceBurnTime - temp);
            } else {
                this.heat_level = 0;
            }
            if (!this.worldObj.isRemote) {
                if (this.furnaceBurnTime == 0 && canSmelt(this.getFuelHeatLevel()) && (canNormallyWork() || canBurnbyItself())) {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
                    if (this.furnaceBurnTime > 0) {
                        this.heat_level = getItemHeatLevel(this.furnaceItemStacks[1]);
                        var2 = true;
                        if (this.furnaceItemStacks[1] != null) {
                            (this.furnaceItemStacks[1]).stackSize--;
                            if ((this.furnaceItemStacks[1]).stackSize == 0) {
                                Item var3 = this.furnaceItemStacks[1].getItem().getContainerItem();
                                this.furnaceItemStacks[1] = (var3 != null) ? new ItemStack(var3) : null;
                            }
                            if (isBlastFurnace())
                                this.worldObj.playSoundEffect((this.xCoord + 0.5F), (this.yCoord + 0.5F), (this.zCoord + 0.5F), "imported.random.melting");
                        }
                    }
                }
                if (isBurning() && canSmelt(this.heat_level)) {
                    activateFurnace();
                    int temp = 200;
                    int item_id = (getInputItemStack()).itemID;
                    int speed_bonus = 1;
                    if (item_id == Items.pieceCopper.itemID || item_id == Items.pieceSilver.itemID || item_id == Items.pieceGold.itemID || item_id == Items.pieceGoldNether.itemID || item_id == Items.pieceIron.itemID || item_id == Items.pieceNickel.itemID)
                        speed_bonus = 4;
                    if (item_id == Items.pieceMithril.itemID || item_id == Items.pieceTungsten.itemID || item_id == Items.pieceAdamantium.itemID)
                        speed_bonus = 2;
                    this.furnaceCookTime += speed_bonus;
                    if (getFurnaceBlock() instanceof BlockBlastFurnace) {
                        this.furnaceCookTime += speed_bonus;
                    } else if (getFurnaceBlock() instanceof BlockSmoker) {
                        this.furnaceCookTime += speed_bonus;
                    }
                    if (this.furnaceCookTime >= temp) {
                        this.furnaceCookTime = 0;
                        smeltItem(this.heat_level);
                        if (getInputItemStack() != null && getOutputItemStack().getItem() instanceof net.minecraft.ItemMeat)
                            this.worldObj.playSoundEffect((this.xCoord + 0.5F), (this.yCoord + 0.5F), (this.zCoord + 0.5F), "imported.random.sizzle");
                        if ((getInputItemStack() != null && getOutputItemStack().getItem() == Item.bowlWater) || getOutputItemStack().getItem() == Items.claybowlWater)
                            this.worldObj.playSoundEffect((this.xCoord + 0.5F), (this.yCoord + 0.5F), (this.zCoord + 0.5F), "imported.random.boil");
                        var2 = true;
                    }
                } else {
                    this.furnaceCookTime = 0;
                }
                if (var1 != ((this.furnaceBurnTime > 0))) {
                    var2 = true;
                    BlockFurnace.updateFurnaceBlockState((this.furnaceBurnTime > 0), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                }
            }
            if (var2)
                onInventoryChanged();
        } else {
            if (this.furnaceBurnTime > 0) {
                if (isFlooded())
                    this.worldObj.blockFX(EnumBlockFX.steam, this.xCoord, this.yCoord, this.zCoord);
                BlockFurnace.updateFurnaceBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
            this.furnaceBurnTime = 0;
            this.furnaceCookTime = 0;
            this.activated = false;
        }
    }

    @Shadow
    public boolean isBurning() {
        return false;
    }

    @Shadow
    public int getItemBurnTime(ItemStack item_stack) {
        return 1;
    }

    @Shadow
    public int getFuelHeatLevel() {
        return 1;
    }

    @Shadow
    public boolean isSmotheredBySolidBlock() {
        return false;
    }

    @Shadow
    public boolean isFlooded() {
        return false;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite// TODO hard to rewrite
    public void smeltItem(int heat_level) {
        if (canSmelt(heat_level)) {
            byte consumption;
            ItemStack var1 = FurnaceRecipes.smelting().getSmeltingResult(getInputItemStack(), heat_level);
            if (this.furnaceItemStacks[2] == null) {
                this.furnaceItemStacks[2] = var1.copy();
            } else if ((this.furnaceItemStacks[2]).itemID == var1.itemID) {
                this.furnaceItemStacks[2].stackSize += var1.stackSize;
            }
            if ((getInputItemStack()).itemID == Block.sand.blockID && var1.itemID == Block.sandStone.blockID) {
                consumption = 4;
            } else if ((getInputItemStack()).itemID == Block.sand.blockID && var1.itemID == Block.glass.blockID) {
                consumption = 4;
            } else if ((getInputItemStack()).itemID == Items.claybowlRaw.itemID && var1.itemID == Items.claybowlEmpty.itemID) {
                consumption = 4;
            } else {
                consumption = 1;
            }
            getInputItemStack().stackSize -= consumption;
            if (getInputItemStack().getItem() == Item.clay && var1.getItem() == Item.brick) {
                int extra_converted = Math.min(getOutputItemStack().getMaxStackSize() - (getOutputItemStack()).stackSize, (getInputItemStack()).stackSize);
                if (extra_converted > 3)
                    extra_converted = 3;
                getOutputItemStack().stackSize += extra_converted;
                getInputItemStack().stackSize -= extra_converted;
            }
            if ((this.furnaceItemStacks[0]).stackSize <= 0)
                this.furnaceItemStacks[0] = null;
        }
    }

    @Inject(method = "readFromNBT", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        this.activated = par1NBTTagCompound.getBoolean("activated");
    }

    @Inject(method = "writeToNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setBoolean("activated", this.activated);
    }

    @Shadow
    public BlockFurnace getFurnaceBlock() {
        return null;
    }

    @Shadow
    public ItemStack getOutputItemStack() {
        return this.furnaceItemStacks[2];
    }

    @Shadow
    public ItemStack getInputItemStack() {
        return this.furnaceItemStacks[0];
    }

    @Shadow
    public int getItemHeatLevel(ItemStack item_stack) {
        return 0;
    }

    @Shadow
    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[0];
    }

    @Shadow
    public boolean canInsertItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    @Shadow
    public boolean canExtractItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    @Shadow
    public int getSizeInventory() {
        return 0;
    }

    @Shadow
    public ItemStack getStackInSlot(int i) {
        return null;
    }

    @Shadow
    public ItemStack decrStackSize(int i, int i1) {
        return null;
    }

    @Shadow
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    @Shadow
    public void setInventorySlotContents(int i, ItemStack itemStack) {
    }

    @Shadow
    public int getInventoryStackLimit() {
        return 0;
    }

    @Shadow
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return false;
    }

    @Shadow
    public void openChest() {
    }

    @Shadow
    public void closeChest() {
    }

    @Shadow
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return false;
    }

    @Shadow
    public void destroyInventory() {
    }
}
