package net.oilcake.mitelros.block;

import net.minecraft.BlockEnchantmentTable;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;

public class BlockMagicTable extends BlockEnchantmentTable {
    private Icon TEXTURE_TOP;

    private Icon TEXTURE_BOTTOM;

    private Icon TEXTURE_SIDE;

    protected BlockMagicTable(int par1, Material gem_type) {
        super(par1, gem_type);
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        return switch (side) {
            case 1 -> this.TEXTURE_TOP;
            case 0 -> this.TEXTURE_BOTTOM;
            case 2, 3, 4, 5 -> this.TEXTURE_SIDE;
            default -> super.getIcon(side, metadata);
        };
    }

    @Override
    public void registerIcons(IconRegister mt) {
        this.TEXTURE_TOP = mt.registerIcon("magic_table/top");
        this.TEXTURE_BOTTOM = mt.registerIcon("magic_table/bottom");
        this.TEXTURE_SIDE = mt.registerIcon("magic_table/side");
    }

}
