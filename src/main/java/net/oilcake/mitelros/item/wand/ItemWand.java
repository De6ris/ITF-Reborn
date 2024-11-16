package net.oilcake.mitelros.item.wand;

import net.minecraft.*;

public abstract class ItemWand extends ItemTool {
    private final Material reinforcement_material;

    public ItemWand(int id, Material material) {
        super(id, material);
        this.reinforcement_material = material;
        setMaxStackSize(1);
        setMaxDamage(192);
        this.setLowestCraftingDifficultyToProduce(0.0F);
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

    private int getTicksPulled(ItemStack item_stack, int item_in_use_count) {
        return item_stack.getMaxItemUseDuration() - item_in_use_count;
    }

    private float getFractionPulled(ItemStack item_stack, int item_in_use_count) {
        return Math.min((float) this.getTicksPulled(item_stack, item_in_use_count) / this.getTicksForMaxPull(), 1.0F);
    }

    public int getTicksForMaxPull() {
        return 15;
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
            float fraction_pulled = this.getFractionPulled(item_stack, item_in_use_count);
            if (fraction_pulled >= 0.85F) {
                this.onUseSuccess(item_stack, world, player);
            }
            if (!player.isPlayerInCreative()) {
                player.tryDamageHeldItem(DamageSource.generic, 1);
            }
        }
    }

    public abstract void onUseSuccess(ItemStack item_stack, World world, EntityPlayer player);

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
