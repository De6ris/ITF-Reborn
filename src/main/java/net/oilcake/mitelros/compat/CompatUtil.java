package net.oilcake.mitelros.compat;

import net.minecraft.EntityLivingBase;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.xiaoyu233.fml.FishModLoader;

public class CompatUtil {
    public static boolean hasITE = FishModLoader.hasMod("mite_ite") || FishModLoader.hasMod("ga-higher");

    public static int getITEDay(World world) {
        return World.getDayOfWorld(world.getWorldInfo().getWorldTotalTime(0));
    }

    public static double getAttribute(int day, double base) {
        return getAttribute(day, base, 0.05D);
    }

    public static double getAttribute(int day, double base, double ratio) {
        return hasITE ? base : base * 2.0D + day * ratio;
    }

    public static void setMaxHealth(EntityLivingBase entity, double base) {
        entity.setEntityAttribute(SharedMonsterAttributes.maxHealth, getAttribute(getITEDay(entity.worldObj), base));
    }

    public static void setAttackDamage(EntityLivingBase entity, double base) {
        entity.setEntityAttribute(SharedMonsterAttributes.attackDamage, getAttribute(getITEDay(entity.worldObj), base));
    }
}
