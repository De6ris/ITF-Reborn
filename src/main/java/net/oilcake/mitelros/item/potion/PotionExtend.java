package net.oilcake.mitelros.item.potion;

import net.minecraft.*;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class PotionExtend extends moddedmite.rustedironcore.api.util.PotionExtend {
    public static final Potion dehydration = new PotionExtend(getNextPotionID(), true, 4251856, "thirsty").setPotionName("potion.extend.dehydration");
    public static final Potion thirsty = new PotionExtend(getNextPotionID(), true, 16761125, "thirsty").setPotionName("potion.extend.thirsty");
    public static final Potion stunning = new PotionExtend(getNextPotionID(), true, 9145210).setPotionName("potion.extend.stunning").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -1, 2);
    public static final Potion stretch = new PotionExtend(getNextPotionID(), false, 114514, "stretch").setPotionName("potion.extend.stretch");

    public PotionExtend(int id, boolean isBadEffect, int effectiveness) {
        super(id, isBadEffect, effectiveness);
    }

    public PotionExtend(int id, boolean isBadEffect, int effectiveness, String texturePath) {
        super(id, isBadEffect, effectiveness, new ResourceLocation("textures/gui/mob_effects/" + texturePath + ".png"));
    }

    @Override
    public void performEffect(EntityLivingBase par1EntityLivingBase, int par2) {
        if (!par1EntityLivingBase.onClient() &&
                this.id == dehydration.id && par1EntityLivingBase instanceof EntityPlayer &&
                !par1EntityLivingBase.worldObj.isRemote)
            ((EntityPlayer) par1EntityLivingBase).getFoodStats().addHungerServerSide(0.05F * (par2 + 1));
    }

    @Override
    public int getEffectInterval(int amplifier) {
        if (this.id == dehydration.id)
            return 1;
        return -1;
    }

    public static int getNextPotionID() {
        return IdUtil.getNextPotionId();
    }
}
