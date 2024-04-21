package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.entity.misc.EntityWandFireBall;
import net.oilcake.mitelros.entity.misc.EntityWandIceBall;
import net.oilcake.mitelros.entity.misc.EntityWandShockWave;
import net.oilcake.mitelros.entity.misc.EntityWandSlimeBall;

public class ItemWand extends ItemTool implements IDamageableItem {
    private Material reinforcement_material;

    public ItemWand(int id, Material material) {
        super(id, material);
        setMaxStackSize(1);
        setMaxDamage(192);
        setCreativeTab(CreativeTabs.tabCombat);
    }

    @Override
    public int getNumComponentsForDurability() {
        return 3;
    }

    @Override
    public Material getMaterialForDurability() {
        return Material.diamond;
    }

    @Override
    public Material getMaterialForRepairs() {
        return (this.reinforcement_material == null) ? Material.diamond : this.reinforcement_material;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        player.setHeldItemInUse();
        return true;
    }

    public static int getTicksForMaxPull(ItemStack item_stack) {
        return 40;
    }

    public static int getTicksPulled(ItemStack item_stack, int item_in_use_count) {
        return item_stack.getMaxItemUseDuration() - item_in_use_count;
    }

    public static float getFractionPulled(ItemStack item_stack, int item_in_use_count) {
        return Math.min((float) getTicksPulled(item_stack, item_in_use_count) / getTicksForMaxPull(item_stack), 1.0F);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }

    @Override
    public EnumItemInUseAction getItemInUseAction(ItemStack par1ItemStack, EntityPlayer player) {
        return EnumItemInUseAction.BOW;
    }

    @Override
    public int getItemEnchantability() {
        return 0;
    }

    @Override
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack item_stack, World world, EntityPlayer player, int item_in_use_count) {
        if (!world.isRemote) {
            float fraction_pulled = getFractionPulled(item_stack, item_in_use_count);
            fraction_pulled = (fraction_pulled * fraction_pulled + fraction_pulled * 2.0F) / 3.0F;
            if (fraction_pulled >= 0.25F) {
                if (this.itemID == Items.lavaWand.itemID) {
                    world.playSoundAtEntity(player, "mob.ghast.fireball", 1.0F, 1.0F);
                    world.spawnEntityInWorld(new EntityWandFireBall(world, player));
                }
                if (this.itemID == Items.freezeWand.itemID) {
                    world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    world.spawnEntityInWorld(new EntityWandIceBall(world, player));
                }
                if (this.itemID == Items.shockWand.itemID) {
                    world.playSoundAtEntity(player, "ambient.weather.thunder", 1.0F, 1.0F);
                    world.spawnEntityInWorld(new EntityWandShockWave(world, player));
                }
                if (this.itemID == Items.slimeWand.itemID) {
                    world.playSoundAtEntity(player, "mob.slime.big1", 1.0F, 1.0F);
                    world.spawnEntityInWorld(new EntityWandSlimeBall(world, player));
                }
            }
            if (!player.isPlayerInCreative())
                player.tryDamageHeldItem(DamageSource.generic, 1);
        }
    }

    @Override
    public float getBaseDamageVsEntity() {
        return 0.0F;
    }

    @Override
    public float getBaseDecayRateForBreakingBlock(Block block) {
        return 0.0F;
    }

    @Override
    public float getBaseDecayRateForAttackingEntity(ItemStack itemStack) {
        return 0.0F;
    }

    @Override
    public String getToolType() {
        return "wand";
    }

    @Override
    public boolean canBlock() {
        return false;
    }
}
