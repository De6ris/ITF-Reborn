package net.oilcake.mitelros.api;

import moddedmite.rustedironcore.api.item.FishingRodItem;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.oilcake.mitelros.ITFStart;

public class ITFFishingRod extends FishingRodItem {
    private Icon my_cast_icon;
    private Icon my_uncast_icon;
    private final Material material;

    public ITFFishingRod(int id, Material hook_material) {
        super(id, hook_material);
        this.material = hook_material;
    }

    public void registerIcons(IconRegister par1IconRegister) {
        this.my_cast_icon = par1IconRegister.registerIcon(this.getIconString() + "_cast");// vanilla cast icon
        this.my_uncast_icon = par1IconRegister.registerIcon(ITFStart.ResourceDomainColon + "fishing_rods/" + this.material.name + "_uncast");
    }

    public Icon getIconFromSubtype(int par1) {
        return this.my_uncast_icon;
    }

    public Icon func_94597_g() {
        return this.my_cast_icon;
    }
}
