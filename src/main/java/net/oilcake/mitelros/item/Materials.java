package net.oilcake.mitelros.item;

import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.MapColor;
import net.minecraft.Material;
import net.minecraft.MaterialFood;
import net.minecraft.MaterialLiquid;
import net.minecraft.Minecraft;

public class Materials extends Material {
    public static final Materials nickel;

    public static final Materials tungsten;

    public static final Materials vibranium;

    public static final Materials uru;

    public static final Material porkchop_stew;

    public static final Material chestnut_soup;

    public static final Material fish_soup;

    public static final Material mashedCactus;

    public static final Material glowberries;

    public static final Material orePieces;

    public static final Materials wolf_fur;

    public static final Materials custom_a;

    public static final Materials custom_b;

    public static final Material lemonade;

    public static final Material unsafe_water;

    public static final Material dangerous_water;

    public static final Materials magical;

    public static final Materials ancient_metal_sacred;

    public static final Material agave;

    public static final Material beetroot;

    public static final Material crystal;


    static {
        Class<EnumEquipmentMaterial> enumEquipmentMaterialClass = EnumEquipmentMaterial.class;
        nickel = (Materials) (new Materials(EnumEquipmentMaterials.NICKEL)).setRequiresTool().setMetal(false).setMinHarvestLevel(3);
        tungsten = (Materials) (new Materials(EnumEquipmentMaterials.TUNGSTEN)).setRequiresTool().setMetal(true).setHarmedByLava(false).setMinHarvestLevel(4);
        vibranium = (Materials) (new Materials(EnumEquipmentMaterials.VIBRANIUM)).setRequiresTool().setMetal(true).setMinHarvestLevel(1);
        uru = (Materials) (new Materials(EnumEquipmentMaterials.URU)).setRequiresTool().setMetal(true).setMinHarvestLevel(5);
        porkchop_stew = (new MaterialFood("porkchop_stew")).setHarmedByPepsin();
        chestnut_soup = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
        fish_soup = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
        mashedCactus = new MaterialFood("mashed_cactus");
        glowberries = new MaterialFood("glowberries");
        orePieces = new Material("Pieces");
        wolf_fur = (Materials) (new Materials(EnumEquipmentMaterials.WOLF_FUR)).setMetal(false).setMinHarvestLevel(0);
        custom_a = (Materials) (new Materials(EnumEquipmentMaterials.CUSTOM_A)).setMetal(false).setMinHarvestLevel(0);
        custom_b = (Materials) (new Materials(EnumEquipmentMaterials.CUSTOM_B)).setMetal(false).setMinHarvestLevel(0);
        lemonade = (new MaterialFood("lemonade")).setDrinkable();
        unsafe_water = (new MaterialLiquid("suspicious_water", MapColor.waterColor)).setDrinkable();
        dangerous_water = (new MaterialLiquid("swampland_water", MapColor.waterColor)).setDrinkable();
        magical = (Materials) (new Materials(EnumEquipmentMaterials.MAGICAL)).setMetal(false).setMinHarvestLevel(0);
        ancient_metal_sacred = (Materials) (new Materials(EnumEquipmentMaterials.ANCIENT_METAL_SACRED)).setRequiresTool().setMetal(true).setMinHarvestLevel(3);
        agave = new MaterialFood("agave");
        beetroot = new MaterialFood("beetroot");
        crystal = new Material("crystal");
    }


    public Materials(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
    }

    public Materials(String name, MapColor map_color) {
        super(name, map_color);
    }

    public Materials(String name) {
        super(name);
    }

    public float getDamageVsEntity() {
        if (this == wood)
            return 0.0F;
        if (this == flint)
            return 1.0F;
        if (this == obsidian)
            return 2.0F;
        if (this == rusted_iron)
            return 2.0F;
        if (this == vibranium)
            return 2.0F;
        if (this == copper)
            return 3.0F;
        if (this == silver)
            return 3.0F;
        if (this == gold)
            return 2.0F;
        if (this == iron)
            return 4.0F;
        if (this == ancient_metal)
            return 4.0F;
        if (this == mithril)
            return 5.0F;
        if (this == adamantium)
            return 6.0F;
        if (this == uru)
            return 6.0F;
        if (this == diamond)
            return 4.0F;
        if (this == nickel)
            return 4.0F;
        if (this == tungsten)
            return 5.0F;
        if (this == magical)
            return 0.0F;
        Minecraft.setErrorMessage("getDamageVsEntity: unhandled material " + this.name);
        return 0.0F;
    }
}
