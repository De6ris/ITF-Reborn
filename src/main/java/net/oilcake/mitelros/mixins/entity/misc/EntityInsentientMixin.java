package net.oilcake.mitelros.mixins.entity.misc;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.config.ITFConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
public abstract class EntityInsentientMixin extends EntityLivingBase {
    @Shadow public abstract float getReach();

    public EntityInsentientMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "isTargetWithinStrikingDistance", at = @At(value = "RETURN", ordinal = 2), cancellable = true)
    private void realistic(EntityLivingBase target, CallbackInfoReturnable<Boolean> cir) {
        if (ITFConfig.Realistic.get()) {
            boolean condition1 = (getDistance(target.posX, target.boundingBox.minY - (this.boundingBox.maxY - this.boundingBox.minY) * 2.0D / 3.0D, target.posZ) <= this.getReach());
            boolean condition2 = (getDistance(target.posX, target.boundingBox.maxY + (this.boundingBox.maxY - this.boundingBox.minY) * 2.0D / 3.0D, target.posZ) <= this.getReach());
            cir.setReturnValue(condition1 || condition2);
        }
    }

    @ModifyConstant(method = "getReach", constant = @Constant(floatValue = 0.6F))
    private float realistic(float constant) {
        return ITFConfig.Realistic.get() ? 1.0F : constant;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean tryDisableNearbyLightSource() {// TODO hard to rewrite
        if (!this.worldObj.isRemote && this.recentlyHit == 0 && this.distanceToNearestPlayer() > 4.0) {
            int x = MathHelper.floor_double(this.posX);
            int y = MathHelper.floor_double(this.posY);
            int z = MathHelper.floor_double(this.posZ);
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dy = -1; dy <= 1 + (int) this.height; ++dy) {
                    for (int dz = -1; dz <= 1; ++dz) {
                        int block_id = this.worldObj.getBlockId(x + dx, y + dy, z + dz);
                        if (block_id == Block.torchWood.blockID || block_id == Block.torchRedstoneActive.blockID || block_id == Blocks.torchWoodIdle.blockID) {
                            if (this.worldObj.setBlock(x + dx, y + dy, z + dz, Blocks.torchWoodExtinguished.blockID, this.worldObj.getBlockMetadata(x + dx, y + dy, z + dz), 2)) {
                                this.playSound("random.fizz", 0.5F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F));
                                return true;
                            }
                        }
                        if (block_id != Block.pumpkinLantern.blockID || !this.worldObj.setBlock(x + dx, y + dy, z + dz, Block.pumpkin.blockID, this.worldObj.getBlockMetadata(x + dx, y + dy, z + dz), 3))
                            continue;
                        EntityItem entity_item = new EntityItem(this.worldObj, x + dx, y + dy, z + dz, new ItemStack(Block.torchWood));
                        entity_item.delayBeforeCanPickup = 10;
                        this.worldObj.spawnEntityInWorld(entity_item);
                        this.playSound("random.pop", 0.05f, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Shadow
    public double distanceToNearestPlayer() {
        return 0.0D;
    }
}
