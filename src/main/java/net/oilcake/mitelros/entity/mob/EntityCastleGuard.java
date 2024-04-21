package net.oilcake.mitelros.entity.mob;


import net.minecraft.*;

public class EntityCastleGuard extends EntityEarthElemental {
    public EntityCastleGuard(World world) {
        super(world);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(SharedMonsterAttributes.followRange, 48.0D);
        this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.22000000298023224D);
        this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 12.0D);
        this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 40.0D);
    }

    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        this.dropItem(Block.stoneBrick.blockID, 1);
    }

    @Override
    public boolean isMagma() {
        return false;
    }

    @Override
    public int getBlockHarvestLevel() {
        return this.getBlock().getMinHarvestLevel(2);
    }

    @Override
    public int getExperienceValue() {
        return super.getExperienceValue() * 4;
    }
}
