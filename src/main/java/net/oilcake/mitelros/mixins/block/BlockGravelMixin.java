package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.BlockGravel;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockGravel.class)
public class BlockGravelMixin extends Block {// TODO add itf metal to loot
    protected BlockGravelMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
}
