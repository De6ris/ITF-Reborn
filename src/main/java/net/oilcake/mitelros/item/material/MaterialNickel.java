package net.oilcake.mitelros.item.material;

import huix.glacier.api.extension.material.*;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;

public class MaterialNickel extends Material implements IArmorMaterial, IArrowMaterial, IRepairableMaterial, IToolMaterial, ICoinMaterial {
    @Override
    public float getChanceOfRecovery() {
        return 0.7F;
    }

    public MaterialNickel(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setRequiresTool().setMetal(false).setMinHarvestLevel(3);
    }

    @Override
    public float getDamageVsEntity() {
        return 4.0F;
    }

    @Override
    public int getProtection() {
        return 8;
    }

    @Override
    public Item getRepairItem() {
        return Items.nickelNugget;
    }

    @Override
    public float getHarvestEfficiency() {
        return 2.0F;
    }

    @Override
    public int getExperienceValue() {
        return 50;
    }

    @Override
    public ItemCoin getForInstance() {
        return Items.nickelCoin;
    }

    @Override
    public Item getNuggetPeer() {
        return Items.nickelNugget;
    }
}
