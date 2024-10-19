package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IPlayerAttributeListener;
import net.minecraft.EntityPlayer;

public class PlayerAttributeListener implements IPlayerAttributeListener {
    @Override
    public int onNutritionLimitModify(EntityPlayer player, int original) {
        return 960000;
    }

    @Override
    public int onNutritionInitModify(EntityPlayer player, int original) {
        return 960000;
    }
}
