package net.oilcake.mitelros.item.totem;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;

public class ItemTotemUnknown extends ItemTotem {
    public ItemTotemUnknown(int id) {
        super(id, Material.rusted_iron, "totem");
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.smoke_and_steam);
        }
        int duration = ITFConfig.TagTotemBlessing.getBooleanValue() ? 800 : 400;
        player.addPotionEffect(new PotionEffect(Potion.resistance.id, duration, 10));
        player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, duration, 127));
    }
}
