package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockGravel.class)
public class BlockGravelMixin extends Block {// TODO add itf metal to loot
    protected BlockGravelMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
}
