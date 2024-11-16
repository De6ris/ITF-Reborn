package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.material.*;

public class Materials extends Material {
    public static final EnumEquipmentMaterial NICKEL = EnumEquipmentMaterials.NICKEL.get();
    public static final EnumEquipmentMaterial TUNGSTEN = EnumEquipmentMaterials.TUNGSTEN.get();
    public static final EnumEquipmentMaterial URU = EnumEquipmentMaterials.URU.get();
    public static final EnumEquipmentMaterial WOLF_FUR = EnumEquipmentMaterials.WOLF_FUR.get();
    public static final EnumEquipmentMaterial CUSTOM_A = EnumEquipmentMaterials.CUSTOM_A.get();
    public static final EnumEquipmentMaterial CUSTOM_B = EnumEquipmentMaterials.CUSTOM_B.get();
    public static final EnumEquipmentMaterial VIBRANIUM = EnumEquipmentMaterials.VIBRANIUM.get();
    public static final EnumEquipmentMaterial MAGICAL = EnumEquipmentMaterials.MAGICAL.get();
    public static final EnumEquipmentMaterial ANCIENT_METAL_SACRED = EnumEquipmentMaterials.ANCIENT_METAL_SACRED.get();
    public static final EnumEquipmentMaterial ICE_CHUNK = EnumEquipmentMaterials.ICE_CHUNK.get();


    public static final Material nickel;

    public static final Material tungsten;

    public static final Material vibranium;

    public static final Material uru;

    public static final Material porkchop_stew;

    public static final Material lampchop_stew;

    public static final Material fish_soup;

    public static final Material mashedCactus;

    public static final Material glowberries;

    public static final Material orePieces;

    public static final Material wolf_fur;

    public static final Materials custom_a;

    public static final Materials custom_b;

    public static final Material lemonade;

    public static final Material pure_water;

    public static final Materials magical;

    public static final Material ancient_metal_sacred;

    public static final Material agave;

    public static final Material beetroot;
    public static final Material beetroot_soup;

    public static final Material crystal;

    public static final Material sulphur;

    public static final Material peeledSugarcane;

    public static final Material ice_chunk;
    public static final Material ice_sucker;
    public static final Material melon_ice;
    public static final Material chocolate_smoothie;
    public static final Material frost;

    static {
        nickel = new MaterialNickel(NICKEL);
        tungsten = new MaterialTungsten(TUNGSTEN);
        vibranium = new MaterialFakeVibranium(VIBRANIUM);
        uru = new MaterialUru(URU);
        porkchop_stew = (new MaterialFood("porkchop_stew")).setHarmedByPepsin();
        lampchop_stew = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
        fish_soup = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
        mashedCactus = new MaterialFood("mashed_cactus");
        glowberries = new MaterialFood("glowberries");
        orePieces = new Material("Pieces");
        wolf_fur = new MaterialWolfFur(WOLF_FUR).setMinHarvestLevel(0);
        custom_a = (Materials) (new Materials(CUSTOM_A)).setMetal(false).setMinHarvestLevel(0);
        custom_b = (Materials) (new Materials(CUSTOM_B)).setMetal(false).setMinHarvestLevel(0);
        lemonade = (new MaterialFood("lemonade")).setDrinkable();
        pure_water = new MaterialPureWater("pure_water");
        magical = (Materials) (new Materials(MAGICAL)).setMetal(false).setMinHarvestLevel(0);
        ancient_metal_sacred = new MaterialAncientMetalSacred(ANCIENT_METAL_SACRED);
        agave = new MaterialFood("agave");
        beetroot = new MaterialFood("beetroot");
        beetroot_soup = new MaterialFoodExtend("beetroot_soup", 6);
        crystal = new Material("crystal").setDurability(4.0F);
        sulphur = new Material("sulphur").setDurability(2.0F);
        peeledSugarcane = new MaterialFood("peeledSugarcane");
        ice_chunk = new MaterialIceChunk(ICE_CHUNK).setMinHarvestLevel(0);
        ice_sucker = new MaterialFood("ice_sucker");
        melon_ice = new MaterialFood("melon_ice");
        chocolate_smoothie = new MaterialFood("chocolate_smoothie");
        frost = new Material("frost");
    }

    public Materials(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
    }

    @Override
    public float getDamageVsEntity() {
        if (this == magical)
            return 0.0F;
        Minecraft.setErrorMessage("getDamageVsEntity: unhandled material " + this.name);
        return 0.0F;
    }

    public static ItemVessel getITFBowl(Material vessel_material, Material contents) {
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
            if (contents == Materials.pure_water)
                return Items.clayBowlWaterPure;
        }
        return null;
    }
}
