package net.oilcake.mitelros.item.totem;

import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityFX;
import net.oilcake.mitelros.item.Materials;

public class ItemTotemDestroy extends ItemTotem {
    public ItemTotemDestroy(int id) {
        super(id, Materials.tungsten, "totem");
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        float delta = player.getHealthFraction() - 0.5F;
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.smoke);
        }
        player.worldObj.createExplosion(player, player.posX, player.posY + 1.5D, player.posZ, 0.0F, 4.0F - 4.0F * delta, true);
        player.setHealth(player.getMaxHealth() / 2.0F, true, player.getHealFX());
    }
}
