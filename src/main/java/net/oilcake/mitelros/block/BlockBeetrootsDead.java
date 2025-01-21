package net.oilcake.mitelros.block;

import net.minecraft.BlockCropsDead;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.oilcake.mitelros.ITFStart;

public class BlockBeetrootsDead extends BlockCropsDead {
    protected BlockBeetrootsDead(int block_id) {
        super(block_id, 4);
    }

    public int getGrowthStage(int metadata) {
        int growth = getGrowth(metadata);
        if (growth == 6)
            growth = 5;
        return growth / 2;
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.iconArray = new Icon[this.num_growth_stages];

        for (int i = 0; i < this.num_growth_stages; ++i) {
            this.iconArray[i] = par1IconRegister.registerIcon(ITFStart.ResourceDomainColon + "crops/" + this.getTextureName() + "/dead/" + i);
        }

    }
}
