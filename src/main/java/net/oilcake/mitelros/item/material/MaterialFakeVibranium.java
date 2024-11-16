package net.oilcake.mitelros.item.material;

import huix.glacier.api.extension.material.IArmorMaterial;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Material;

public class MaterialFakeVibranium extends Material implements IArmorMaterial {
    public MaterialFakeVibranium(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setRequiresTool().setMetal(true).setMinHarvestLevel(1);
    }

    @Override
    public int getProtection() {
        return 6;
    }

    @Override
    public float getDamageVsEntity() {
        return 2.0F;
    }
}
