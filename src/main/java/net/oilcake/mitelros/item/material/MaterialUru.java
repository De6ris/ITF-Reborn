package net.oilcake.mitelros.item.material;

import huix.glacier.api.extension.material.IArmorMaterial;
import huix.glacier.api.extension.material.IBowMaterial;
import huix.glacier.api.extension.material.IRepairableMaterial;
import huix.glacier.api.extension.material.IToolMaterial;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Item;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;

public class MaterialUru extends Material implements IBowMaterial, IArmorMaterial, IRepairableMaterial, IToolMaterial {
    public MaterialUru(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setRequiresTool().setMetal(true).setHarmedByLava(false).setMinHarvestLevel(5);
    }

    @Override
    public float getDamageVsEntity() {
        return 6.0F;
    }

    @Override
    public int velocityBonus() {
        return 45;
    }

    @Override
    public int maxDamage() {
        return 512;
    }

    @Override
    public int getProtection() {
        return 10;
    }

    @Override
    public Item getRepairItem() {
        return Items.uruNugget;
    }

    @Override
    public float getHarvestEfficiency() {
        return 4.0F;
    }
}
