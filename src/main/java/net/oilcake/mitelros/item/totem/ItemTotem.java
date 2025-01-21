package net.oilcake.mitelros.item.totem;

import net.minecraft.*;
import net.oilcake.mitelros.sound.Sounds;
import net.oilcake.mitelros.util.AchievementExtend;

public abstract class ItemTotem extends Item {
    public ItemTotem(int id, Material material, String texture) {
        super(id, material, texture);
        setMaxStackSize(1);
        setCraftingDifficultyAsComponent(100.0F);
    }

    public void trigger(EntityPlayer player, boolean isProactive) {
        player.clearActivePotions();
        player.setHealth(isProactive ? Math.max(player.getHealth(), 2.0F) : player.getMaxHealth(), true, player.getHealFX());
        player.entityFX(EnumEntityFX.smoke_and_steam);
        player.makeSound(Sounds.totemUse.toString(), 3.0F, 1.0F + player.rand.nextFloat() * 0.1F);
        if (!player.isPlayerInCreative()) {
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 4));
            player.vision_dimming = 1.0F;
        }
        if (!isProactive) {
            player.triggerAchievement(AchievementExtend.cheatdeath);
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    @Override
    public EnumItemInUseAction getItemInUseAction(ItemStack item_stack, EntityPlayer player) {
        return EnumItemInUseAction.BLOCK;
    }

    @Override
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (item_stack == null) {
            return;
        }
        if (!this.canTrigger(world, player)) {
            return;
        }
        if (player.onServer()) {
            this.trigger(player, true);
            this.specifiedEffect(player);
            if (!player.isPlayerInCreative()) {
                player.convertOneOfHeldItem(null);
            }
        }
    }

    public abstract void specifiedEffect(EntityPlayer player);

    public boolean canTrigger(World world, EntityPlayer player) {
        return true;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        player.setHeldItemInUse();
        return true;
    }
}
