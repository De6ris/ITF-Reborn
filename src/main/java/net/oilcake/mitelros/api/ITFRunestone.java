package net.oilcake.mitelros.api;

import net.minecraft.BlockRunestone;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.oilcake.mitelros.ITFStart;

public class ITFRunestone extends BlockRunestone {
    public ITFRunestone(int id, Material rune_metal) {
        super(id, rune_metal);
    }

    protected Icon[] iconArray = new Icon[16];

    public Icon getIcon(int side, int metadata) {
        return side != 0 && side != 1 ? this.iconArray[metadata] : this.blockIcon;
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());

        for (int i = 0; i < this.iconArray.length; ++i) {
            this.iconArray[i] = par1IconRegister.registerIcon(ITFStart.ResourceDomainColon + "runestones/" + this.rune_metal.name + "/" + i);
        }

    }
}
