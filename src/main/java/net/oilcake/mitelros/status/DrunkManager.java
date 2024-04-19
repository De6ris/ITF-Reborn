package net.oilcake.mitelros.status;

import net.minecraft.EntityPlayer;

public class DrunkManager {
    private final EntityPlayer player;

    public boolean Hasdrunked = false;

    private int drunk_duration = 0;

    public DrunkManager(EntityPlayer player) {
        this.player = player;
    }

    public void setHasdrunked(boolean hasdrunked) {
        this.Hasdrunked = hasdrunked;
    }

    public boolean isDrunk() {
        return this.drunk_duration > 0;
    }

    public void update1() {
        if (this.Hasdrunked) {
            this.drunk_duration = 6000;
            this.Hasdrunked = false;
        }
    }

    public void update2() {
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
