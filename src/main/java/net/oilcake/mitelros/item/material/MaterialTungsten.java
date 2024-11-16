package net.oilcake.mitelros.item.material;

import huix.glacier.api.extension.material.*;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;

public class MaterialTungsten extends Material implements IBowMaterial, IArmorMaterial, IArrowMaterial, IRepairableMaterial, IToolMaterial, ICoinMaterial, IBucketMaterial {
    public MaterialTungsten(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
        this.setRequiresTool().setMetal(true).setHarmedByLava(false).setMinHarvestLevel(4);
    }

    @Override
    public float getDamageVsEntity() {
        return 5.0F;
    }

    @Override
    public int velocityBonus() {
        return 35;
    }

    @Override
    public int maxDamage() {
        return 256;
    }


    @Override
    public int getProtection() {
        return 9;
    }

    @Override
    public float getChanceOfRecovery() {
        return 0.9F;
    }

    @Override
    public Item getRepairItem() {
        return Items.tungstenNugget;
    }

    @Override
    public float getHarvestEfficiency() {
        return 2.75F;
    }

    @Override
    public int getExperienceValue() {
        return 5000;
    }

    @Override
    public ItemCoin getForInstance() {
        return Items.tungstenCoin;
    }

    @Override
    public Item getNuggetPeer() {
        return Items.tungstenNugget;
    }

    @Override
    public float getMeltingChance() {
        return 0.0F;
    }
}
