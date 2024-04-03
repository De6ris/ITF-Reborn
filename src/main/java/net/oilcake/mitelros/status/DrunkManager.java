package net.oilcake.mitelros.status;

public class DrunkManager {

    public boolean Hasdrunked = false;

    private int drunk_duration = 0;

    public void setHasdrunked(boolean hasdrunked) {
        this.Hasdrunked = hasdrunked;
    }

    public boolean isDrunk() {
        return this.drunk_duration > 0;
    }

    public void update() {
        if (this.Hasdrunked) {
            this.drunk_duration = 6000;
            this.Hasdrunked = false;
        }
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
