package net.oilcake.mitelros.entity.mob;

import net.minecraft.*;
import net.oilcake.mitelros.compat.CompatUtil;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityLichShadow extends EntitySkeleton {
    private int max_num_evasions;

    private int num_evasions;

    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        int looting = damage_source.getLootingModifier();
        int num_drops = this.rand.nextInt(3 + looting) - 1;
        if (num_drops > 0 && !recently_hit_by_player)
            num_drops -= this.rand.nextInt(num_drops + 1);
        for (int i = 0; i < num_drops; i++)
            dropItem(Items.ancientMetalArmorPiece.itemID, 1);
    }

    public void addRandomWeapon() {
        List<RandomItemListEntry> items = new ArrayList<>();
        items.add(new RandomItemListEntry(Item.swordGold, 2));
        RandomItemListEntry entry = (RandomItemListEntry) WeightedRandom.getRandomItem(this.rand, items);
        setHeldItemStack((new ItemStack(entry.item)).randomizeForMob((EntityLiving) this, true));
    }

    public EntityLichShadow(World par1World) {
        super(par1World);
        if (par1World != null && onServer())
            this.max_num_evasions = this.num_evasions = 6;
        setCanPickUpLoot(false);
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("max_num_evasions", (byte) this.max_num_evasions);
        par1NBTTagCompound.setByte("num_evasions", (byte) this.num_evasions);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.max_num_evasions = par1NBTTagCompound.getByte("max_num_evasions");
        this.num_evasions = par1NBTTagCompound.getByte("num_evasions");
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        setEntityAttribute(SharedMonsterAttributes.followRange, 128.0D);
        setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27000001072883606D);
        CompatUtil.setAttackDamage(this, 6.0D);
        CompatUtil.setMaxHealth(this, 24.0D);
    }

    protected void addRandomEquipment() {
        addRandomWeapon();
        setBoots((new ItemStack((Item) Item.bootsAncientMetal)).randomizeForMob((EntityLiving) this, true));
        setLeggings((new ItemStack((Item) Item.legsAncientMetal)).randomizeForMob((EntityLiving) this, true));
        setCuirass((new ItemStack((Item) Item.plateAncientMetal)).randomizeForMob((EntityLiving) this, true));
        setHelmet((new ItemStack((Item) Item.helmetAncientMetal)).randomizeForMob((EntityLiving) this, true));
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (onServer() && getHealth() > 0.0F) {
            int ticks_existed_with_offset = getTicksExistedWithOffset();
            if (this.num_evasions < this.max_num_evasions && ticks_existed_with_offset % 120 == 0)
                this.num_evasions++;
            if (hasPath() && (getTarget() != null || this.fleeing) && ticks_existed_with_offset % 10 == 0 && this.rand.nextInt(3) == 0) {
                PathEntity path = getPathToEntity();
                if (!path.isFinished()) {
                    int n = path.getNumRemainingPathPoints();
                    if (n > 1) {
                        int path_index_advancement = MathHelper.clamp_int(this.rand.nextInt(n), 1, 4);
                        PathPoint path_point = path.getPathPointFromCurrentIndex(path_index_advancement);
                        if (path_point.distanceSqTo((Entity) this) > 3.0D && tryTeleportTo(path_point.xCoord + 0.5D, path_point.yCoord, path_point.zCoord + 0.5D))
                            path.setCurrentPathIndex(path.getCurrentPathIndex() + path_index_advancement - 1);
                    }
                }
            }
        }
    }

    public boolean tryTeleportTo(double pos_x, double pos_y, double pos_z) {
        if (!this.isDead && getHealth() > 0.0F) {
            int x = MathHelper.floor_double(pos_x);
            int y = MathHelper.floor_double(pos_y);
            int z = MathHelper.floor_double(pos_z);
            if (y >= 1 && this.worldObj.blockExists(x, y, z))
                while (true) {
                    y--;
                    if (this.worldObj.isBlockSolid(x, y, z)) {
                        y++;
                        if (!this.worldObj.isBlockSolid(x, y, z) && !this.worldObj.isLiquidBlock(x, y, z)) {
                            double delta_pos_x = pos_x - this.posX;
                            double delta_pos_y = pos_y - this.posY;
                            double delta_pos_z = pos_z - this.posZ;
                            AxisAlignedBB bb = this.boundingBox.translateCopy(delta_pos_x, delta_pos_y, delta_pos_z);
                            if (this.worldObj.getCollidingBoundingBoxes((Entity) this, bb).isEmpty() && !this.worldObj.isAnyLiquid(bb)) {
                                World var10000 = this.worldObj;
                                double distance = World.getDistanceFromDeltas(delta_pos_x, delta_pos_y, delta_pos_z);
                                this.worldObj.blockFX(EnumBlockFX.particle_trail, x, y, z, (new SignalData()).setByte(EnumParticle.runegate.ordinal()).setShort((int) (16.0D * distance)).setApproxPosition(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)));
                                this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.endermen.portal", 1.0F, 1.0F);
                                setPosition(pos_x, pos_y, pos_z);
                                this.send_position_update_immediately = true;
                                return true;
                            }
                            return false;
                        }
                        return false;
                    }
                    if (y < 1)
                        return false;
                    pos_y--;
                }
            return false;
        }
        return false;
    }

    public boolean tryTeleportAwayFrom(Entity entity, double min_distance) {
        if (!this.isDead && getHealth() > 0.0F) {
            double min_distance_sq = min_distance * min_distance;
            int x = getBlockPosX();
            int y = getFootBlockPosY();
            int z = getBlockPosZ();
            double threat_pos_x = (entity == null) ? this.posX : entity.posX;
            double threat_pos_z = (entity == null) ? this.posZ : entity.posZ;
            for (int attempts = 0; attempts < 64; attempts++) {
                int dx = this.rand.nextInt(11) - 5;
                int dy = this.rand.nextInt(9) - 4;
                int dz = this.rand.nextInt(11) - 5;
                if (Math.abs(dx) >= 3 || Math.abs(dz) >= 3) {
                    int try_x = x + dx;
                    int try_y = y + dy;
                    int try_z = z + dz;
                    double try_pos_x = try_x + 0.5D;
                    double try_pos_z = try_z + 0.5D;
                    World var10000 = this.worldObj;
                    if (World.getDistanceSqFromDeltas(try_pos_x - threat_pos_x, try_pos_z - threat_pos_z) >= min_distance_sq && try_y >= 1 && this.worldObj.blockExists(try_x, try_y, try_z)) {
                        do {
                            try_y--;
                        } while (!this.worldObj.isBlockSolid(try_x, try_y, try_z) && try_y >= 1);
                        if (try_y >= 1) {
                            try_y++;
                            if (!this.worldObj.isBlockSolid(try_x, try_y, try_z) && !this.worldObj.isLiquidBlock(try_x, try_y, try_z) && tryTeleportTo(try_pos_x, try_y, try_pos_z)) {
                                EntityPlayer target = findPlayerToAttack(Math.min(getMaxTargettingRange(), 24.0F));
                                if (target != null && target != getTarget())
                                    setTarget((EntityLivingBase) target);
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    public EntityDamageResult attackEntityFrom(Damage damage) {
        boolean can_evade = (!damage.isFallDamage() && !damage.isFireDamage() && !damage.isPoison());
        if (can_evade && this.num_evasions > 0) {
            this.num_evasions--;
            if (tryTeleportAwayFrom((Entity) getTarget(), 8.0D))
                return null;
        }
        return super.attackEntityFrom(damage);
    }
}
