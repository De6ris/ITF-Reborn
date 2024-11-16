package net.oilcake.mitelros.item.material;

import huix.glacier.api.extension.material.IArmorMaterial;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Material;

public class MaterialIceChunk extends Material implements IArmorMaterial {
    public MaterialIceChunk(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
    }

    @Override
    public int getProtection() {
        return 2;
    }
}
