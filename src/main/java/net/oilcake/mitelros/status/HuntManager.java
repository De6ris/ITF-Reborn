package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityUndeadGuard;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class HuntManager {

    public int hunt_counter = 0;

    public boolean hunt_cap = false;

    public void setHunt_counter(int counter) {
        this.hunt_counter = counter;
    }

    public void update(EntityPlayer player) {
        if (this.hunt_counter > (this.hunt_cap ? -1 : 0))
            this.hunt_counter--;
        if (this.hunt_counter % 80 == 79) {
            EntityUndeadGuard Belongings = new EntityUndeadGuard(player.worldObj);
            Belongings.setPosition(player.posX, player.posY, player.posZ);
            Belongings.refreshDespawnCounter(-9600);
            player.worldObj.spawnEntityInWorld((Entity) Belongings);
            Belongings.onSpawnWithEgg(null);
            Belongings.entityFX(EnumEntityFX.summoned);
        }
        if (this.hunt_counter < 0) {
            player.attackEntityFrom(new Damage(DamageSourceExtend.sacrificed, 10000.0F));
            this.hunt_counter = 0;
            this.hunt_cap = false;
        }
    }

}
