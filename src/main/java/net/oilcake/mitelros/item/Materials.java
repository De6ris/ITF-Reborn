package net.oilcake.mitelros.item;

import moddedmite.rustedironcore.api.register.MaterialProperties;
import net.minecraft.*;

public class Materials extends Material {
    public static final Materials nickel;

    public static final Materials tungsten;

    public static final Materials vibranium;

    public static final Materials uru;

    public static final Material porkchop_stew;

    public static final Material lampchop_stew;

    public static final Material fish_soup;

    public static final Material mashedCactus;

    public static final Material glowberries;

    public static final Material orePieces;

    public static final Materials wolf_fur;

    public static final Materials custom_a;

    public static final Materials custom_b;

    public static final Material lemonade;

    public static final Material suspicious_water;

    public static final Material dangerous_water;

    public static final Materials magical;

    public static final Materials ancient_metal_sacred;

    public static final Material agave;

    public static final Material beetroot;
    public static final Material beetroot_soup;

    public static final Material crystal;

    public static final Material sulphur;

    public static final Material peeledSugarcane;

    public static final Materials ice_chunk;
    public static final Material ice_sucker;
    public static final Material melon_ice;
    public static final Material chocolate_smoothie;
    public static final Material frost;

    static {
        nickel = (Materials) (new Materials(EnumEquipmentMaterials.NICKEL)).setRequiresTool().setMetal(false).setMinHarvestLevel(3);
        tungsten = (Materials) (new Materials(EnumEquipmentMaterials.TUNGSTEN)).setRequiresTool().setMetal(true).setHarmedByLava(false).setMinHarvestLevel(4);
        vibranium = (Materials) (new Materials(EnumEquipmentMaterials.VIBRANIUM)).setRequiresTool().setMetal(true).setMinHarvestLevel(1);
        uru = (Materials) (new Materials(EnumEquipmentMaterials.URU)).setRequiresTool().setMetal(true).setHarmedByLava(false).setMinHarvestLevel(5);
        porkchop_stew = (new MaterialFood("porkchop_stew")).setHarmedByPepsin();
        lampchop_stew = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
        fish_soup = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
        mashedCactus = new MaterialFood("mashed_cactus");
        glowberries = new MaterialFood("glowberries");
        orePieces = new Material("Pieces");
        wolf_fur = (Materials) (new Materials(EnumEquipmentMaterials.WOLF_FUR)).setMinHarvestLevel(0);
        custom_a = (Materials) (new Materials(EnumEquipmentMaterials.CUSTOM_A)).setMetal(false).setMinHarvestLevel(0);
        custom_b = (Materials) (new Materials(EnumEquipmentMaterials.CUSTOM_B)).setMetal(false).setMinHarvestLevel(0);
        lemonade = (new MaterialFood("lemonade")).setDrinkable();
        suspicious_water = (new MaterialLiquid("suspicious_water", MapColor.waterColor)).setCanDouseFire().setDrinkable();
        dangerous_water = (new MaterialLiquid("swampland_water", MapColor.waterColor)).setCanDouseFire().setDrinkable();
        magical = (Materials) (new Materials(EnumEquipmentMaterials.MAGICAL)).setMetal(false).setMinHarvestLevel(0);
        ancient_metal_sacred = (Materials) (new Materials(EnumEquipmentMaterials.ANCIENT_METAL_SACRED)).setRequiresTool().setMetal(true).setMinHarvestLevel(3);
        agave = new MaterialFood("agave");
        beetroot = new MaterialFood("beetroot");
        beetroot_soup = new MaterialFood("beetroot_soup");
        crystal = new Material("crystal").setDurability(4.0F);
        sulphur = new Material("sulphur").setDurability(2.0F);
        peeledSugarcane = new MaterialFood("peeledSugarcane");
        ice_chunk = (Materials) (new Materials(EnumEquipmentMaterials.ICE_CHUNK)).setMinHarvestLevel(0);
        ice_sucker = new MaterialFood("ice_sucker");
        melon_ice = new MaterialFood("melon_ice");
        chocolate_smoothie = new MaterialFood("chocolate_smoothie");
        frost = new Material("frost");
        MaterialProperties.registerArrowMaterial(nickel, 0.7F);
        MaterialProperties.registerArrowMaterial(tungsten, 0.9F);
    }

    public Materials(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
    }

    @Override
    public float getDamageVsEntity() {
        if (this == vibranium)
            return 2.0F;
        if (this == uru)
            return 6.0F;
        if (this == nickel)
            return 4.0F;
        if (this == tungsten)
            return 5.0F;
        if (this == magical)
            return 0.0F;
        Minecraft.setErrorMessage("getDamageVsEntity: unhandled material " + this.name);
        return 0.0F;
    }

    public static Item getITFRepairItem(Material material_for_repairs) {
        if (material_for_repairs == Materials.nickel)
            return Items.nickelNugget;
        if (material_for_repairs == Materials.tungsten)
            return Items.tungstenNugget;
        if (material_for_repairs == Materials.ancient_metal_sacred)
            return Items.ancientMetalArmorPiece;
        if (material_for_repairs == Materials.uru)
            return Items.uruNugget;
        return null;
    }

    public static ItemVessel getITFBowl(Material vessel_material, Material contents) {
        if (vessel_material == Material.wood) {
            if (contents == Materials.fish_soup)
                return Items.bowlSalmonSoup;
            if (contents == Materials.beetroot_soup)
                return Items.bowlBeetrootSoup;
            if (contents == Materials.lampchop_stew)
                return Items.bowlLampchopStew;
            if (contents == Materials.porkchop_stew)
                return Items.bowlPorkchopStew;
            if (contents == Materials.suspicious_water)
                return Items.bowlWaterSuspicious;
            if (contents == Materials.dangerous_water)
                return Items.bowlWaterSwampland;
        }
        if (vessel_material == Material.hardened_clay) {
            if (contents == null)
                return Items.clayBowlEmpty;
            if (contents == Material.mushroom_stew)
                return Items.clayBowlMushroomStew;
            if (contents == Material.milk)
                return Items.clayBowlMilk;
            if (contents == Material.water)
                return Items.clayBowlWater;
            if (contents == Material.beef_stew)
                return Items.clayBowlBeefStew;
            if (contents == Material.chicken_soup)
                return Items.clayBowlChickenSoup;
            if (contents == Material.vegetable_soup)
                return Items.clayBowlVegetableSoup;
            if (contents == Material.ice_cream)
                return Items.clayBowlIceCream;
            if (contents == Material.salad)
                return Items.clayBowlSalad;
            if (contents == Material.cream_of_mushroom_soup)
                return Items.clayBowlCreamOfMushroomSoup;
            if (contents == Material.cream_of_vegetable_soup)
                return Items.clayBowlCreamOfVegetableSoup;
            if (contents == Material.mashed_potato)
                return Items.clayBowlMashedPotato;
            if (contents == Material.porridge)
                return Items.clayBowlPorridge;
            if (contents == Material.cereal)
                return Items.clayBowlCereal;
            if (contents == Materials.lampchop_stew)
                return Items.clayBowlLampchopSoup;
            if (contents == Materials.porkchop_stew)
                return Items.clayBowlPorkchopStew;
            if (contents == Materials.suspicious_water)
                return Items.clayBowlWaterSuspicious;
            if (contents == Materials.dangerous_water)
                return Items.clayBowlWaterSwampland;
        }
        return null;
    }
}
