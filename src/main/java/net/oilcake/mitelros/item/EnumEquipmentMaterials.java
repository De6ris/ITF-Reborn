package net.oilcake.mitelros.item;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.EnumQuality;
import net.oilcake.mitelros.ITFStart;
import net.xiaoyu233.fml.util.EnumExtends;

public enum EnumEquipmentMaterials {
    NICKEL(8.0F, 30, EnumQuality.masterwork, "nickel"),
    TUNGSTEN(128.0F, 50, EnumQuality.legendary, "tungsten"),
    URU(192.0F, 100, EnumQuality.legendary, "uru"),
    WOLF_FUR(2.0F, 20, EnumQuality.excellent, "wolf_fur"),
    CUSTOM_A(0.0625F, 0, EnumQuality.average, "custom_a"),
    CUSTOM_B(0.0625F, 0, EnumQuality.average, "custom_b"),
    VIBRANIUM(4.0F, 0, EnumQuality.poor, "vibranium"),
    MAGICAL(0.125F, 100, EnumQuality.wretched, "magical"),
    ANCIENT_METAL_SACRED(16.0F, 60, EnumQuality.masterwork, "ancient_metal_sacred"),
    ICE_CHUNK(0.5F, 10, EnumQuality.fine, "ice_chunk"),
    ;

    public final String EnumName;
    public final float durability;
    public final int enchantability;
    public final EnumQuality max_quality;
    public final String name;

    EnumEquipmentMaterials(float durability, int enchantability, EnumQuality max_quality, String material_name) {
        this.EnumName = ITFStart.NameSpaceCompactWithColon + this.name();
        this.durability = durability;
        this.enchantability = enchantability;
        this.max_quality = max_quality;
        this.name = material_name;
    }

    public static void register() {
        for (EnumEquipmentMaterials arg : values()) {
            EnumExtends.EQUIPMENT_MATERIAL.addEnum(arg.EnumName, () -> new Object[]{arg.durability, arg.enchantability, arg.max_quality, arg.name});
        }
    }

    public EnumEquipmentMaterial get() {
        return ClassTinkerers.getEnum(EnumEquipmentMaterial.class, this.EnumName);
    }
}
