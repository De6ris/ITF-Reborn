package net.oilcake.mitelros.api;

import net.minecraft.BlockAnvil;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.oilcake.mitelros.ITFStart;

public class ITFAnvil extends BlockAnvil {
    private Icon[] iconArray;
    private static final String[] anvilIconNames = new String[]{"top_damaged_0", "top_damaged_1", "top_damaged_2"};

    public ITFAnvil(int par1, Material metal_type) {
        super(par1, metal_type);
    }

    @Override
    public Icon getIcon(int par1, int par2) {
        if (this.field_82521_b == 3 && par1 == 1) {
            int var3 = (par2 >> 2) % this.iconArray.length;
            return this.iconArray[var3];
        } else {
            return this.blockIcon;
        }
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(ITFStart.ResourceDomainColon + "anvil/" + this.metal_type.name + "/base");
        this.iconArray = new Icon[anvilIconNames.length];

        for (int var2 = 0; var2 < this.iconArray.length; ++var2) {
            this.iconArray[var2] = par1IconRegister.registerIcon(ITFStart.ResourceDomainColon + "anvil/" + this.metal_type.name + "/" + anvilIconNames[var2]);
        }

    }
}
