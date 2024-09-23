package net.oilcake.mitelros.event;

import moddedmite.rustedironcore.api.interfaces.IFurnaceUpdateListener;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.TileEntityFurnace;
import net.oilcake.mitelros.api.ITFFurnace;
import net.oilcake.mitelros.block.BlockBlastFurnace;
import net.oilcake.mitelros.block.BlockSmoker;
import net.oilcake.mitelros.item.Items;

public class FurnaceListener implements IFurnaceUpdateListener {
    @Override
    public void onFurnaceUpdatePre(TileEntityFurnace tileEntityFurnace) {
        ITFFurnace itfFurnace = (ITFFurnace) tileEntityFurnace;
        if (!tileEntityFurnace.getWorldObj().isRemote && !tileEntityFurnace.isBurning() && itfFurnace.itf$IsActive() && tileEntityFurnace.getStackInSlot(1) == null) {
            itfFurnace.itf$setActive(false);
        }
    }

    @Override
    public int onFurnaceBurnTimeDecreaseModify(TileEntityFurnace tileEntityFurnace, int original) {
        if (tileEntityFurnace.getFurnaceBlock() instanceof BlockBlastFurnace || tileEntityFurnace.getFurnaceBlock() instanceof BlockSmoker) {
            return original * 2;
        }
        return original;
    }

    @Override
    public boolean onFurnaceBeginToBurn(TileEntityFurnace tileEntityFurnace, boolean original) {
        return original && (((ITFFurnace) tileEntityFurnace).itf$CanNormallyWork() || ((ITFFurnace) tileEntityFurnace).canBurnbyItself());
    }

    @Override
    public void onFurnaceFuelConsumed(TileEntityFurnace tileEntityFurnace) {
        if (((ITFFurnace) tileEntityFurnace).itf$IsBlastFurnace())
            tileEntityFurnace.getWorldObj().playSoundEffect((tileEntityFurnace.xCoord + 0.5F), (tileEntityFurnace.yCoord + 0.5F), (tileEntityFurnace.zCoord + 0.5F), "imported.random.melting");
    }

    @Override
    public void onFurnaceCookTimeAdd(TileEntityFurnace tileEntityFurnace) {
        ((ITFFurnace) tileEntityFurnace).itf$setActive(true);
    }

    @Override
    public int onFurnaceCookTimeIncreaseModify(TileEntityFurnace tileEntityFurnace, int original) {
        int item_id = (tileEntityFurnace.getInputItemStack()).itemID;
        int speed_bonus = 1;
        if (item_id == Items.pieceCopper.itemID || item_id == Items.pieceSilver.itemID || item_id == Items.pieceGold.itemID || item_id == Items.pieceGoldNether.itemID || item_id == Items.pieceIron.itemID || item_id == Items.pieceNickel.itemID)
            speed_bonus = 4;
        if (item_id == Items.pieceMithril.itemID || item_id == Items.pieceTungsten.itemID || item_id == Items.pieceAdamantium.itemID)
            speed_bonus = 2;
        original *= speed_bonus;
        if (tileEntityFurnace.getFurnaceBlock() instanceof BlockBlastFurnace) {
            original *= 2;
        } else if (tileEntityFurnace.getFurnaceBlock() instanceof BlockSmoker) {
            original *= 2;
        }
        return original;
    }

    @Override
    public void onFurnaceCookSuccess(TileEntityFurnace tileEntityFurnace) {
        ItemStack inputItemStack = tileEntityFurnace.getInputItemStack();
        ItemStack outputItemStack = tileEntityFurnace.getOutputItemStack();
        if (inputItemStack != null && outputItemStack.getItem() instanceof net.minecraft.ItemMeat)
            tileEntityFurnace.getWorldObj().playSoundEffect((tileEntityFurnace.xCoord + 0.5F), (tileEntityFurnace.yCoord + 0.5F), (tileEntityFurnace.zCoord + 0.5F), "imported.random.sizzle");
        if ((inputItemStack != null && outputItemStack.getItem() == Item.bowlWater) || outputItemStack.getItem() == Items.clayBowlWater)
            tileEntityFurnace.getWorldObj().playSoundEffect((tileEntityFurnace.xCoord + 0.5F), (tileEntityFurnace.yCoord + 0.5F), (tileEntityFurnace.zCoord + 0.5F), "imported.random.boil");
    }
}
