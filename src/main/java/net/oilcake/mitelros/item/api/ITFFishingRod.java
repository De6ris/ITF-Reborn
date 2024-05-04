package net.oilcake.mitelros.item.api;

import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.ItemFishingRod;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Materials;

public class ITFFishingRod extends ItemFishingRod {
    public ITFFishingRod(int par1, Material hook_material) {
        super(par1, hook_material);
    }

    private Icon itf_castIcon;
    private final Icon[] itf_uncastIcons = new Icon[2];

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.itf_castIcon = par1IconRegister.registerIcon(this.getIconString() + "_cast");
        for (int i = 0; i < this.itf_uncastIcons.length; ++i) {
            this.itf_uncastIcons[i] = par1IconRegister.registerIcon("fishing_rods/" + this.getMaterialByOrdinal(i).name + "_uncast");
        }
    }

    @Override
    public Icon getIconFromSubtype(int par1) {
        return this.itf_uncastIcons[this.getMaterialOrdinal()];
    }

    private int getMaterialOrdinal() {
        if (this.getHookMaterial() == Materials.nickel) {
            return 0;
        }
        if (this.getHookMaterial() == Materials.tungsten) {
            return 1;
        }
        return -1;
    }

    private Material getMaterialByOrdinal(int ordinal) {
        return switch (ordinal) {
            case 0 -> Materials.nickel;
            case 1 -> Materials.tungsten;
            default -> Material.flint;//dummy
        };
    }

    @Override
    public Icon func_94597_g() {
        return this.itf_castIcon;
    }
}
