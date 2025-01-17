package net.oilcake.mitelros.item.api;

import huix.glacier.api.extension.item.IRockItem;
import net.minecraft.ItemRock;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;

public class ITFRock extends ItemRock implements IRockItem {
    public ITFRock(int id, Material material, String texture) {
        super(id, material, texture);
    }

    @Override
    public int getExperienceValueWhenSacrificed() {
        if (this == Items.shardAzurite) return 5;
        return 0;
    }
}
