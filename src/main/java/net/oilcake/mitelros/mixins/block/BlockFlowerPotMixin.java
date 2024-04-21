package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockFlowerPotExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockFlowerPot.class)
public abstract class BlockFlowerPotMixin extends Block {
    @Shadow
    public static ItemStack getPlantForMeta(int par0) {
        return null;
    }

    @Shadow
    public static int getMetaForPlant(ItemStack par0ItemStack) {
        return 0;
    }

    protected BlockFlowerPotMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    @ModifyConstant(method = "isValidMetadata", constant = @Constant(intValue = 13))
    private int modify(int constant) {
        return constant + 1;
    }

    @Inject(method = "getPlantForMeta", at = @At("HEAD"), cancellable = true)
    private static void addFlower(int par0, CallbackInfoReturnable<ItemStack> cir) {
        if (par0 == 13) cir.setReturnValue(new ItemStack(Blocks.flowerextend));
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        ItemStack item_stack = player.getHeldItemStack();
        if (item_stack == null)
            return false;
        if (BlockFlowerPotMulti.getMetaForPlant(item_stack) != 0) {
            if (player.onServer()) {
                int i = world.getBlockMetadata(x, y, z);
                if (i != 0) {
                    BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
                    dropBlockAsEntityItem(info, getPlantForMeta(i));
                    world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
                world.setBlock(x, y, z, flowerPotMulti.blockID, BlockFlowerPotMulti.getMetaForPlant(item_stack), 2);
                if (!player.inCreativeMode())
                    player.convertOneOfHeldItem(null);
            }
            return true;
        }
        if (BlockFlowerPotExtend.getMetaForPlant(item_stack) != 0) {
            if (player.onServer()) {
                int i = world.getBlockMetadata(x, y, z);
                if (i != 0) {
                    BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
                    dropBlockAsEntityItem(info, getPlantForMeta(i));
                    world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                }
                world.setBlock(x, y, z, Blocks.flowerPotExtend.blockID, BlockFlowerPotExtend.getMetaForPlant(item_stack), 2);
                if (!player.inCreativeMode())
                    player.convertOneOfHeldItem(null);
            }
            return true;
        }
        int metadata_for_plant = getMetaForPlant(item_stack);
        if (metadata_for_plant == 0)
            return false;
        int metadata = world.getBlockMetadata(x, y, z);
        if (metadata == metadata_for_plant)
            return false;
        if (player.onServer()) {
            if (metadata != 0) {
                BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
                dropBlockAsEntityItem(info, getPlantForMeta(metadata));
                world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }
            world.setBlockMetadataWithNotify(x, y, z, metadata_for_plant, 2);
            if (!player.inCreativeMode())
                player.convertOneOfHeldItem(null);
        }
        return true;
    }

    @Inject(method = "getMetaForPlant", at = @At("HEAD"), cancellable = true)
    private static void inject(ItemStack par0ItemStack, CallbackInfoReturnable<Integer> cir) {
        int var1 = (par0ItemStack.getItem()).itemID;
        if (var1 == Blocks.flowerextend.blockID)
            cir.setReturnValue((par0ItemStack.getItemSubtype() == 0) ? 13 : 0);
    }
}
