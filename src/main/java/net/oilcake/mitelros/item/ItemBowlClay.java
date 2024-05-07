package net.oilcake.mitelros.item;

import net.minecraft.*;

public class ItemBowlClay extends ItemBowl {
    public ItemBowlClay(int id, Material contents, String texture) {
        super(id, contents, texture);
        this.vessel_material = Material.hardened_clay;
        this.setMaterial(Material.hardened_clay);
        this.setContainerItem(Items.clayBowlEmpty);
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
        return (this == Items.clayBowlMilk) ? 0.0F : super.getCompostingValue();
    }

    @Override
    public ItemVessel getPeerForContents(Material contents) {
        return ItemBowlClay.getPeer(this.getVesselMaterial(), contents);
    }

    public static ItemVessel getPeer(Material vessel_material, Material contents) {
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
