package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.entity.boss.EntityLich;

import java.util.List;

public class ItemBossDetector extends Item implements IDamageableItem {

    public ItemBossDetector(int id, Material material, String texture) {
        super(id, material, texture);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setMaxDamage(192);
    }

    @Override
    public int getNumComponentsForDurability() {
        return 3;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        if (player.onServer()) {
            double detectRange = 48.0D;
            List<?> entitiesWithinAABB = player.getEntityWorld().getEntitiesWithinAABB(EntityLich.class, player.boundingBox.expand(detectRange, detectRange, detectRange));
            entitiesWithinAABB.stream().findFirst().ifPresentOrElse(x -> {
                EntityLich entityLich = (EntityLich) x;
                player.makeSound("mob.villager.yes");
                this.spawnParticles(player.getWorldServer(), player.posX, player.posY, player.posZ, entityLich.posX, entityLich.posY, entityLich.posZ);
            }, () -> {
                player.makeSound("mob.villager.no");
            });
            if (!player.isPlayerInCreative()) {
                player.tryDamageHeldItem(DamageSource.generic, 1);
            }
        }
        return true;
    }

    void spawnParticles(WorldServer worldServer, double startX, double startY, double startZ, double endX, double endY, double endZ) {
        Vec3 normalize = Vec3.createVectorHelper(endX - startX, endY - startY, endZ - startZ).normalize();
        for (int i = 1; i <= 5; i++) {
            double scale = i * 1.5D;
            worldServer.playAuxSFX(2005, (int) (startX + scale * normalize.xCoord), (int) (startY), (int) (startZ + scale * normalize.zCoord), 0);
        }
    }
}
