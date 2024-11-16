package net.oilcake.mitelros.item.material;

import net.minecraft.MapColor;
import net.minecraft.MaterialLiquid;
import net.oilcake.mitelros.item.api.IWateryMaterial;

public class MaterialPureWater extends MaterialLiquid implements IWateryMaterial {
    public MaterialPureWater(String name) {
        super(name, MapColor.waterColor);
        this.setCanDouseFire().setDrinkable();
    }

    @Override
    public int getWater() {
        return 2;
    }
}
