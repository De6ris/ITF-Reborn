package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import net.oilcake.mitelros.util.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Damage.class})
public class DamageMixin {
    @Shadow
    private float amount;

    public Entity getResponsibleEntityC() {
        return getResponsibleEntity();
    }

    @Shadow
    Entity getResponsibleEntity() {
        return null;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected float applyTargetDefenseModifiers(EntityLivingBase target, EntityDamageResult result) {
        if (target.onClient())
            Minecraft.setErrorMessage("applyTargetDefenseModifiers: called on client?");
        if (this.amount <= 0.0F)
            return 0.0F;
        if (isAbsolute())
            return this.amount;
        if (target instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) target;
            if (!bypassesMundaneArmor() && player.isBlocking()) {
                this.amount /= 2.0F;
                if (this.amount < 1.0F)
                    this.amount = 1.0F;
                ItemStack item_stack = player.getHeldItemStack();
                if (item_stack != null && item_stack.getItem() instanceof ItemTool) {
                    ItemTool item_tool = (ItemTool) item_stack.getItem();
                    result.applyHeldItemDamageResult(item_stack.tryDamageItem(DamageSource.generic, (int) (this.amount * item_tool.getToolDecayFromAttackingEntity(item_stack, (EntityLivingBase) null)), target));
                }
            }
        }
        float total_protection = target.getTotalProtection(getSource());
        DebugAttack.setTargetProtection(total_protection);
        float amount_dealt_to_armor = Math.min(target.getProtectionFromArmor(getSource(), false), this.amount);
        target.tryDamageArmor(getSource(), amount_dealt_to_armor, result);
        DebugAttack.setDamageDealtToArmor(amount_dealt_to_armor);
        boolean using_morningstar = false;
        if (getItemAttackedWith() != null &&
                getItemAttackedWith().getItem() instanceof net.oilcake.mitelros.item.ItemMorningStar)
            using_morningstar = true;
        float piercing = Enchantment.piercing.getLevelFraction(getItemAttackedWith()) * 5.0F + (using_morningstar ? 3.0F : 0.0F);
        float effective_protection = Math.max(total_protection - piercing, 0.0F);
        DebugAttack.setPiercing(piercing);
        if (target instanceof EntityPlayer && effective_protection >= this.amount) {
            int delta = (int) (effective_protection - this.amount);
            for (int i = -1; i < delta; i++) {
                if (target.rand.nextFloat() < 0.2F)
                    return 0.0F;
            }
            return Math.max(this.amount - effective_protection, ((Boolean) ITFConfig.TagArmament.get()) ? 0.0F : 1.0F);
        }
        return Math.max(this.amount - effective_protection, ((Boolean) ITFConfig.TagInstinctSurvival.get()) ? 0.0F : 1.0F);
    }

    @Shadow
    private DamageSource getSource() {
        return null;
    }

    @Shadow
    private boolean bypassesMundaneArmor() {
        return false;
    }

    @Shadow
    private boolean isAbsolute() {
        return false;
    }

    @Shadow
    public ItemStack getItemAttackedWith() {
        return null;
    }
}
