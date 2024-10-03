package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IEntityMobListener;
import net.minecraft.EntityMob;
import net.minecraft.NBTTagCompound;
import net.oilcake.mitelros.api.ITFEntityMob;

public class EntityMobListener implements IEntityMobListener {
    @Override
    public void onReadEntityFromNBT(EntityMob entityMob, NBTTagCompound nbtTagCompound) {
        ((ITFEntityMob) entityMob).set_modified_attribute(nbtTagCompound.getBoolean("modified_attribute"));
    }

    @Override
    public void onWriteEntityToNBT(EntityMob entityMob, NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setBoolean("modified_attribute", ((ITFEntityMob) entityMob).modified_attribute());
    }
}
