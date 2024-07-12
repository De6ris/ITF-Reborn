package net.oilcake.mitelros.item;

import net.minecraft.*;

public class ItemBowlClay extends ItemBowl {
    public ItemBowlClay(int id, Material contents, String texture) {
        super(id, contents, texture);
        this.vessel_material = Material.hardened_clay;
        this.setMaterial(Material.hardened_clay);
        this.addMaterial(contents);
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
        return Materials.getITFBowl(this.getVesselMaterial(), contents);
    }
}
