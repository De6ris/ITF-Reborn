package net.oilcake.mitelros.block;

import net.minecraft.*;

public class BlockEnchantEnhancer extends Block {
    private Icon TEXTURE_TOP;

    private Icon TEXTURE_BOTTOM;

    private Icon TEXTURE_SIDE;

    protected BlockEnchantEnhancer(int par1) {
        super(par1, Material.anvil, new BlockConstants());
        setMaxStackSize(1);
        setLightOpacity(0);
        setLightValue(0.75F);
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
        this.TEXTURE_TOP = mt.registerIcon("enchant_enhancer/top");
        this.TEXTURE_BOTTOM = mt.registerIcon("enchant_enhancer/bottom");
        this.TEXTURE_SIDE = mt.registerIcon("enchant_enhancer/side");
    }


    @Override
    public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
        return true;
    }
}
