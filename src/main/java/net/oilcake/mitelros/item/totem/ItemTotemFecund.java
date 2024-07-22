package net.oilcake.mitelros.item.totem;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;

public class ItemTotemFecund extends ItemTotem {
    public ItemTotemFecund(int id) {
        super(id, Material.gold, "totem");
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.heal);
        }
        player.setHealth(player.getMaxHealth(), true, player.getHealFX());
        if (ITFConfig.TagTotemBlessing.getBooleanValue()) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 1));
        }
    }
}
