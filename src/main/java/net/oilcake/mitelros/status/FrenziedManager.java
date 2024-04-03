package net.oilcake.mitelros.status;

public class FrenziedManager {

    private int counter;

    public void update() {
        if (this.counter > 0)
            this.counter--;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
}
