package net.oilcake.mitelros.status;

import net.minecraft.EntityPlayer;

public class DrunkManager {
    private final EntityPlayer player;

    public boolean hasDrunk = false;

    private int drunk_duration = 0;

    public DrunkManager(EntityPlayer player) {
        this.player = player;
    }

    public void setHasDrunk(boolean hasDrunk) {
        this.hasDrunk = hasDrunk;
    }

    public boolean isDrunk() {
        return this.drunk_duration > 0;
    }

    public void update() {
        if (this.hasDrunk) {
            this.drunk_duration = 6000;
            this.hasDrunk = false;
        }
        this.decrease();
    }

    public void decrease() {
        if (this.drunk_duration > 0) this.drunk_duration--;
    }

    public void setDrunk_duration(int drunk_duration) {
        this.drunk_duration = drunk_duration;
    }

    public int getDrunk_duration() {
        return drunk_duration;
    }
}
