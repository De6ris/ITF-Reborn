package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockFlowerPotExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
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

    @Inject(method = "onBlockActivated", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockFlowerPotMulti;a(Lnet/minecraft/ItemStack;)I", ordinal = 0), cancellable = true)
    private void itfFlower(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z, CallbackInfoReturnable<Boolean> cir) {
        ItemStack item_stack = player.getHeldItemStack();
        if (item_stack.itemID != Blocks.flowerextend.blockID) return;
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
        cir.setReturnValue(true);

    }

    @Inject(method = "getMetaForPlant", at = @At("HEAD"), cancellable = true)
    private static void inject(ItemStack par0ItemStack, CallbackInfoReturnable<Integer> cir) {
        int var1 = (par0ItemStack.getItem()).itemID;
        if (var1 == Blocks.flowerextend.blockID)
            cir.setReturnValue((par0ItemStack.getItemSubtype() == 0) ? 13 : 0);
    }
}
