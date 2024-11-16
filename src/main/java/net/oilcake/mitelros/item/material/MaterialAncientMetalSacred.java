package net.oilcake.mitelros.item.material;

import huix.glacier.api.extension.material.IArmorMaterial;
import huix.glacier.api.extension.material.IRepairableMaterial;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Item;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;

public class MaterialAncientMetalSacred extends Material implements IArmorMaterial, IRepairableMaterial {

    public MaterialAncientMetalSacred(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setRequiresTool().setMetal(true).setMinHarvestLevel(3);
    }

    @Override
    public int getProtection() {
        return 9;
    }

    @Override
    public Item getRepairItem() {
        return Items.ancientMetalArmorPiece;
    }
}
