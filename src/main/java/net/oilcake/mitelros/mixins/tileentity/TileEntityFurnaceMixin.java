package net.oilcake.mitelros.mixins.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFurnace;
import net.oilcake.mitelros.block.BlockBlastFurnace;
import net.oilcake.mitelros.block.BlockSmoker;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityFurnace.class)
public abstract class TileEntityFurnaceMixin extends TileEntity implements ISidedInventory, ITFFurnace {

    @Shadow
    private ItemStack[] furnaceItemStacks = new ItemStack[3];

    @Unique
    private boolean activated = false;

    @Inject(method = "getHeatLevelRequired", at = @At("HEAD"), cancellable = true)
    private static void itfHeatLevel(int item_id, CallbackInfoReturnable<Integer> cir) {
        Item item = Item.getItem(item_id);
        if (item instanceof net.minecraft.ItemTool)
            cir.setReturnValue((item.getHardestMetalMaterial() == Materials.tungsten) ? 4 : ((item.getHardestMetalMaterial() == Material.rusted_iron) ? 2 : 3));
        if (item instanceof net.minecraft.ItemArmor)
            cir.setReturnValue((item.getHardestMetalMaterial() == Materials.tungsten) ? 4 : ((item.getHardestMetalMaterial() == Material.rusted_iron) ? 2 : 3));
        if (item_id == Items.ancientMetalArmorPiece.itemID)
            cir.setReturnValue(3);
    }

    @Inject(method = "canSmelt", at = @At(value = "INVOKE", target = "Lnet/minecraft/TileEntityFurnace;getFurnaceBlock()Lnet/minecraft/BlockFurnace;"), cancellable = true)
    private void canSmelt(int heat_level, CallbackInfoReturnable<Boolean> cir) {
        if (!this.checkSmelt(heat_level)) cir.setReturnValue(false);
    }

    @Unique
    private boolean checkSmelt(int heat_level) {
        Item item = this.getInputItemStack().getItem();
        if (item instanceof ItemFood && this.itf$IsBlastFurnace()) {
            return false;
        }
        if (item instanceof ItemArmor && !this.itf$IsBlastFurnace()) {
            return false;
        }
        if (item instanceof ItemTool && !this.itf$IsBlastFurnace()) {
            return false;
        }
        if (!(item instanceof ItemFood) && this.itf$IsSmoker()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean itf$IsBlastFurnace() {
        return getFurnaceBlock() instanceof BlockBlastFurnace;
    }

    @Override
    public boolean itf$IsSmoker() {
        return getFurnaceBlock() instanceof BlockSmoker;
    }

    @Override
    public boolean itf$canBurnByItself() {
        return (getFuelHeatLevel() > 2);
    }

    @Override
    public boolean itf$IsActive() {
        return this.activated;
    }

    @Override
    public void itf$setActive(boolean activated) {
        this.activated = true;
    }

    @Override
    public boolean itf$CanNormallyWork() {
        return (this.activated && this.furnaceItemStacks[1] != null);
    }

    @Shadow
    public int getFuelHeatLevel() {
        return 1;
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
    public ItemStack getInputItemStack() {
        return this.furnaceItemStacks[0];
    }

}
