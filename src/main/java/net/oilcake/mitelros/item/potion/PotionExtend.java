package net.oilcake.mitelros.item.potion;

import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.Potion;
import net.minecraft.SharedMonsterAttributes;
import net.oilcake.mitelros.util.Constant;

public class PotionExtend extends Potion {
    public static final Potion dehydration = (new PotionExtend(getNextPotionID(), true, 4251856)).setIconIndex(3, 2).setPotionName("potion.extend.dehydration");
    public static final Potion thirsty = (new PotionExtend(getNextPotionID(), true, 16761125)).setIconIndex(3, 2).setPotionName("potion.extend.thirsty");
    public static final Potion freeze = (new PotionExtend(getNextPotionID(), true, 65535)).setIconIndex(7, 2).setPotionName("potion.extend.freeze").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.4, 2);
    public static final Potion stunning = new PotionExtend(getNextPotionID(), true, 9145210).setIconIndex(7, 2).setPotionName("potion.extend.stunning").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -1, 2);
    public static final Potion stretch = new PotionExtend(getNextPotionID(), false, 114514).setIconIndex(5, 2).setPotionName("potion.extend.stretch");
    public static final Potion warm = new PotionExtend(getNextPotionID(), false, 16744448).setPotionName("potion.extend.warm");
    public static final Potion cool = new PotionExtend(getNextPotionID(), false, 10092544).setPotionName("potion.extend.cool");
    public static final Potion frostResistance = new PotionExtend(getNextPotionID(), false, 10092544).setIconIndex(6, 2).setPotionName("potion.extend.frost_resistance");

    public PotionExtend(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    public void performEffect(EntityLivingBase par1EntityLivingBase, int par2) {
        if (!par1EntityLivingBase.onClient() &&
                this.id == dehydration.id && par1EntityLivingBase instanceof EntityPlayer &&
                !par1EntityLivingBase.worldObj.isRemote)
            ((EntityPlayer) par1EntityLivingBase).getFoodStats().addHungerServerSide(0.05F * (par2 + 1));
    }

    public int getEffectInterval(int amplifier) {
        if (this.id == dehydration.id)
            return 1;
        return -1;
    }

    public static int getNextPotionID() {
        return Constant.nextPotionID++;
    }
}
