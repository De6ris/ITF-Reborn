package net.oilcake.mitelros.util;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;

public final class DispenseBehaviorEmptyBucketRedirect extends BehaviorDefaultDispenseItem {
    public ItemBucket item_bucket;

    private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

    public DispenseBehaviorEmptyBucketRedirect(ItemBucket item_bucket) {
        this.item_bucket = item_bucket;
    }

    public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
        ItemVessel var10;
        EnumFacing var3 = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
        World var4 = par1IBlockSource.getWorld();
        int var5 = par1IBlockSource.getXInt() + var3.getFrontOffsetX();
        int var6 = par1IBlockSource.getYInt() + var3.getFrontOffsetY();
        int var7 = par1IBlockSource.getZInt() + var3.getFrontOffsetZ();
        Material var8 = var4.getBlockMaterial(var5, var6, var7);
        var4.getBlockMetadata(var5, var6, var7);
        if (var8 == Material.water) {
            BiomeGenBase biome = var4.getBiomeGenForCoords(var5, var7);
            if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
                var10 = this.item_bucket.getPeerForContents(Materials.pure_water);
            } else {
                var10 = this.item_bucket.getPeerForContents(Materials.water);
            }
        } else {
            if (var8 != Material.lava)
                return super.dispenseStack(par1IBlockSource, par2ItemStack);
            var10 = this.item_bucket.getPeerForContents(Material.lava);
        }
        if (var8 == Material.lava) {
            World world = par1IBlockSource.getWorld();
            if (world.rand.nextFloat() < this.item_bucket.getChanceOfMeltingWhenFilledWithLava()) {
                world.blockFX(EnumBlockFX.item_consumed_by_lava, var5, var6, var7);
                par2ItemStack.stackSize--;
                this.suppress_dispense_particles = true;
                return par2ItemStack;
            }
        }
        var4.setBlockToAir(var5, var6, var7);
        if (--par2ItemStack.stackSize == 0) {
            par2ItemStack.itemID = var10.itemID;
            par2ItemStack.stackSize = 1;
        } else if (((TileEntityDispenser) par1IBlockSource.getBlockTileEntity()).addItem(new ItemStack((Item) var10)) < 0) {
            this.defaultDispenserItemBehavior.dispense(par1IBlockSource, new ItemStack((Item) var10));
        }
        this.suppress_dispense_particles = true;
        return par2ItemStack;
    }
}
