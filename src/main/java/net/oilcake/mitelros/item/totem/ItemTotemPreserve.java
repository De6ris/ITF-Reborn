package net.oilcake.mitelros.item.totem;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;

public class ItemTotemPreserve extends ItemTotem {
    public ItemTotemPreserve(int id) {
        super(id, Material.iron, "totem");
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.smoke_and_steam);
        }
        int duration = ITFConfig.TagTotemBlessing.getBooleanValue() ? 800 : 400;
        player.addPotionEffect(new PotionEffect(Potion.resistance.id, duration, (int) ((1.0F - player.getHealthFraction()) * 4.0F)));
    }
}
