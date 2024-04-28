package net.oilcake.mitelros.item;

import net.minecraft.*;

public class ItemBowlClay extends ItemBowl {
    public ItemBowlClay(int id, Material contents, String texture) {
        super(id, contents, texture);
        this.vessel_material = Material.hardened_clay;
        this.setMaterial(Material.hardened_clay);
        this.setContainerItem(Items.claybowlEmpty);
        this.setTextureName("hardened_clay_bowls/" + texture);
    }

    @Override
    public int getSimilarityToItem(Item item) {
        if (item instanceof ItemBowlClay item_bowl) {
            if (item_bowl.isEmpty() || isEmpty())
                return 2;
        }
        return super.getSimilarityToItem(item);
    }

    @Override
    public int getBurnTime(ItemStack item_stack) {
        return 0;
    }

    public static boolean isSoupOrStew(Item item) {
        if (!(item instanceof ItemBowlClay))
            return false;
        Material contents = ((ItemBowlClay) item).getContents();
        return (contents instanceof MaterialSoup || contents instanceof MaterialStew);
    }

    @Override
    public float getCompostingValue() {
        return (this == Items.claybowlMilk) ? 0.0F : super.getCompostingValue();
    }

    @Override
    public ItemVessel getPeerForContents(Material contents) {
        return ItemBowlClay.getPeer(this.getVesselMaterial(), contents);
    }

    public static ItemVessel getPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.hardened_clay) {
            if (contents == null)
                return Items.claybowlEmpty;
            if (contents == Material.mushroom_stew)
                return Items.claybowlMushroomStew;
            if (contents == Material.milk)
                return Items.claybowlMilk;
            if (contents == Material.water)
                return Items.claybowlWater;
            if (contents == Material.beef_stew)
                return Items.claybowlBeefStew;
            if (contents == Material.chicken_soup)
                return Items.claybowlChickenSoup;
            if (contents == Material.vegetable_soup)
                return Items.claybowlVegetableSoup;
            if (contents == Material.ice_cream)
                return Items.claybowlIceCream;
            if (contents == Material.salad)
                return Items.claybowlSalad;
            if (contents == Material.cream_of_mushroom_soup)
                return Items.claybowlCreamOfMushroomSoup;
            if (contents == Material.cream_of_vegetable_soup)
                return Items.claybowlCreamOfVegetableSoup;
            if (contents == Material.mashed_potato)
                return Items.claybowlMashedPotato;
            if (contents == Material.porridge)
                return Items.claybowlPorridge;
            if (contents == Material.cereal)
                return Items.claybowlCereal;
            if (contents == Materials.chestnut_soup)
                return Items.claybowlChestnutSoup;
            if (contents == Materials.porkchop_stew)
                return Items.claybowlPorkchopStew;
            if (contents == Materials.suspicious_water)
                return Items.claybowlWaterSuspicious;
            if (contents == Materials.dangerous_water)
                return Items.claybowlWaterSwampland;
        }
        return null;
    }
}
