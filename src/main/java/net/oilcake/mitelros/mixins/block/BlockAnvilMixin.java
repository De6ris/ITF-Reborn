package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.oilcake.mitelros.block.BlockHandler.getMatchingBlock;

@Mixin(BlockAnvil.class)
public class BlockAnvilMixin extends BlockFalling {
    @Shadow
    public Material metal_type;

    public BlockAnvilMixin(int par1, Material material, BlockConstants constants) {
        super(par1, material, constants);
    }

    @Inject(method = "dropBlockAsEntityItem", at = @At("HEAD"), cancellable = true)
    private void benching(BlockBreakInfo info, CallbackInfoReturnable<Integer> cir) {
        if (ITFConfig.TagBenchingV2.get() || info.wasExploded()) {
            TileEntityAnvil tile_entity_anvil = (TileEntityAnvil) info.tile_entity;
            float centesimal = getAnvilDurabilityByCentesimal(tile_entity_anvil.damage);
            if (centesimal <= 0.5D) {
                int expecting_nuggets = (int) (279.0F * centesimal);
                if (info.wasExploded())
                    expecting_nuggets = (int) (expecting_nuggets * 0.5D);
                while (expecting_nuggets > 80) {
                    expecting_nuggets -= 81;
                    dropBlockAsEntityItem(info, (getMatchingBlock(BlockOreStorage.class, this.metal_type)).blockID);
                }
                while (expecting_nuggets > 8) {
                    expecting_nuggets -= 9;
                    dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemIngot.class, this.metal_type)).itemID);
                }
                while (expecting_nuggets > 0) {
                    expecting_nuggets--;
                    dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, this.metal_type)).itemID);
                }
                cir.setReturnValue(0);
            }
            cir.setReturnValue(super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage)));
        }
    }

    @Unique
    public float getAnvilDurabilityByCentesimal(int damage) {
        float nowDurability = (getDurability() - damage);
        return nowDurability / getDurability();
    }

    @Shadow
    public int getDurability() {
        return 0;
    }
}
