package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.ICombatListener;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.status.MiscManager;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.quality.EnumEffectEntry;
import net.oilcake.mitelros.util.quality.EnumToolType;

public class CombatListener implements ICombatListener {
    @Override
    public float onPlayerRawMeleeDamageModify(EntityPlayer player, Entity target, boolean critical, boolean suspended_in_liquid, float original) {
        return original * EnumToolType.getMultiplierForEntry(player.getHeldItemStack(), EnumEffectEntry.Attack);
    }

    @Override
    public void onPlayerReceiveDamageModify(EntityPlayer player, Damage damage) {
        if (ITFConfig.FinalChallenge.getBooleanValue())
            damage.scaleAmount(1.0F + Constant.calculateCurrentDifficulty() / 50.0F);
    }

    @Override
    public float onPlayerBlockReachModify(EntityPlayer player, Block block, int metadata, float original) {
        return original + (player.isPotionActive(PotionExtend.stretch) ? player.getActivePotionEffect(PotionExtend.stretch).getAmplifier() * 1.0F + 1.0F : 0.0F);
    }

    @Override
    public float onPlayerEntityReachModify(EntityPlayer player, EnumEntityReachContext context, Entity entity, float original) {
        return original + (player.isPotionActive(PotionExtend.stretch) ? player.getActivePotionEffect(PotionExtend.stretch).getAmplifier() * 1.0F + 1.0F : 0.0F);
    }

    @Override
    public float onPlayerStrVsBlockModify(EntityPlayer player, float original) {
        return MiscManager.getInstance(player).calculateITFStv(original);
    }
}
