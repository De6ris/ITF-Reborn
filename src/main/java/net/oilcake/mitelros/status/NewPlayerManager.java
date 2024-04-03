package net.oilcake.mitelros.status;

public class NewPlayerManager {
    private boolean isNew = true;

    public NewPlayerManager() {
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean getNew() {
        return this.isNew;
    }

}
