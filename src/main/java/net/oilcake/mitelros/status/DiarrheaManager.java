package net.oilcake.mitelros.status;

import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.item.potion.PotionExtend;

public class DiarrheaManager {
    private EntityPlayer player;
    private int diarrheaCounter;

    public DiarrheaManager(EntityPlayer player) {
        this.player = player;
    }

    public void update() {
        if (player.isPotionActive(PotionExtend.dehydration) && this.diarrheaCounter <= 1200) {
            this.diarrheaCounter++;
        } else if (this.diarrheaCounter > 0) {
            this.diarrheaCounter--;
        }
        if (this.diarrheaCounter >= 1200) {
            player.dropItem(Item.manure);
            player.triggerAchievement(AchievementExtend.pull);
            this.diarrheaCounter = 0;
        }
    }

    public void setDiarrheaCounter(int diarrheaCounter) {
        this.diarrheaCounter = diarrheaCounter;
    }

    public int getDiarrheaCounter() {
        return diarrheaCounter;
    }
}
