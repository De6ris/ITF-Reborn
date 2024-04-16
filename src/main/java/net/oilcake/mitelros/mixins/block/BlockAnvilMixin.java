package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import static net.oilcake.mitelros.block.BlockHandler.getMatchingBlock;

@Mixin({BlockAnvil.class})
public class BlockAnvilMixin extends BlockFalling {
    @Shadow
    public Material metal_type;

    public BlockAnvilMixin(int par1, Material material, BlockConstants constants) {
        super(par1, material, constants);
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        TileEntityAnvil tile_entity_anvil = (TileEntityAnvil) info.tile_entity;
        if (((Boolean) ITFConfig.TagBenchingV2.get()) || info.wasExploded()) {
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
                return 0;
            }
            return super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
        }
        return super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
    }

    @Unique
    public float getAnvilDurabilityByCentesimal(int damage) {
        float nowDurability = (getDurability() - damage);
        return nowDurability / getDurability();
    }

    @Shadow
    public int getMinimumDamageForStage(int stage) {
        return 0;
    }

    @Shadow
    public int getDurability() {
        return 0;
    }
}
