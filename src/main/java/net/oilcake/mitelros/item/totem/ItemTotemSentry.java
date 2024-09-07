package net.oilcake.mitelros.item.totem;

import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityFX;
import net.minecraft.Material;
import net.oilcake.mitelros.entity.mob.EntityLongdeadSentry;

public class ItemTotemSentry  extends ItemTotem{
    public ItemTotemSentry(int id) {
        super(id, Material.adamantium, "totem");
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        EntityLongdeadSentry sentry = new EntityLongdeadSentry(player.worldObj);
        sentry.setPosition(player.posX, player.posY, player.posZ);
        sentry.refreshDespawnCounter(-9600);
        player.worldObj.spawnEntityInWorld(sentry);
        sentry.onSpawnWithEgg(null);
        sentry.entityFX(EnumEntityFX.summoned);
    }
}
