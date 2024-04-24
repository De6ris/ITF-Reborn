package net.oilcake.mitelros.util;

import net.minecraft.ChatMessageComponent;
import net.minecraft.DamageSource;
import net.minecraft.EntityLivingBase;
import net.minecraft.Translator;

public class DamageSourceExtend extends DamageSource {
    private boolean is_unblockable;

    private boolean bypasses_mundane_armor;

    private boolean is_absolute;

    private float hungerDamage = 0.3F;

    public String damageType;

    protected DamageSourceExtend(String par1Str) {
        super(par1Str);
        this.damageType = par1Str;
    }

    public static DamageSourceExtend freeze = (new DamageSourceExtend("freeze")).setUnblockable();
    public static DamageSourceExtend heat = (new DamageSourceExtend("heat")).setUnblockable();

    public static DamageSourceExtend thirsty = (new DamageSourceExtend("thirsty")).setUnblockable();

    public static DamageSourceExtend malnourished = (new DamageSourceExtend("malnourished")).setUnblockable();

    public static DamageSourceExtend sacrificed = (new DamageSourceExtend("sacrificed")).setUnblockable();

    protected DamageSourceExtend setUnblockable() {
        this.is_unblockable = true;
        return setDamageBypassesMundaneArmor();
    }

    protected DamageSourceExtend setDamageBypassesMundaneArmor() {
        this.bypasses_mundane_armor = true;
        this.hungerDamage = 0.0F;
        return this;
    }

    public boolean bypassesMundaneArmor() {
        return (this.bypasses_mundane_armor || this.is_unblockable || this.is_absolute);
    }

    public ChatMessageComponent getDeathMessage(EntityLivingBase par1EntityLivingBase) {
        EntityLivingBase var2 = par1EntityLivingBase.func_94060_bK();
        String var3 = par1EntityLivingBase.getEntityName() + " " + Translator.get("death.extend.attack." + this.damageType);
        return ChatMessageComponent.createFromTranslationWithSubstitutions(var3);
    }

    public boolean isFreezing() {
        return (this == freeze);
    }

    public boolean isDehydration() {
        return (this == thirsty);
    }
}
