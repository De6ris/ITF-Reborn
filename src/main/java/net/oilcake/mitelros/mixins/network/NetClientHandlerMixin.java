package net.oilcake.mitelros.mixins.network;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFNetHandler;
import net.oilcake.mitelros.api.ITFPacket8;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin extends NetHandler implements ITFNetHandler {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void handleVehicleSpawn(Packet23VehicleSpawn par1Packet23VehicleSpawn) {
        double var2;
        double var4;
        double var6;
        if (par1Packet23VehicleSpawn.position_set_using_unscaled_integers) {
            var2 = par1Packet23VehicleSpawn.unscaled_pos_x;
            var4 = par1Packet23VehicleSpawn.unscaled_pos_y;
            var6 = par1Packet23VehicleSpawn.unscaled_pos_z;
        } else {
            var2 = SpatialScaler.getPosX(par1Packet23VehicleSpawn.scaled_pos_x);
            var4 = SpatialScaler.getPosY(par1Packet23VehicleSpawn.scaled_pos_y);
            var6 = SpatialScaler.getPosZ(par1Packet23VehicleSpawn.scaled_pos_z);
        }

        Entity var8 = null;
        if (par1Packet23VehicleSpawn.type == 10) {
            var8 = EntityMinecart.createMinecart(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
        } else if (par1Packet23VehicleSpawn.type == 90) {
            Entity var9 = getEntityByID(par1Packet23VehicleSpawn.throwerEntityId);
            if (var9 instanceof EntityPlayer) {
                var8 = new EntityFishHook(this.worldClient, var2, var4, var6, (EntityPlayer) var9);
            }

            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 60) {
            if (par1Packet23VehicleSpawn.arrow_item_id == -1) {
                var8 = null;
            } else {
                var2 = par1Packet23VehicleSpawn.exact_pos_x;
                var4 = par1Packet23VehicleSpawn.exact_pos_y;
                var6 = par1Packet23VehicleSpawn.exact_pos_z;
                var8 = new EntityArrow(this.worldClient, var2, var4, var6, (ItemArrow) Item.itemsList[par1Packet23VehicleSpawn.arrow_item_id], par1Packet23VehicleSpawn.launcher_was_enchanted);
                if (par1Packet23VehicleSpawn.arrow_stuck_in_block) {
                    ((EntityArrow) var8).setInGround();
                }

                EntityArrow arrow = (EntityArrow) var8;
                arrow.xTile = par1Packet23VehicleSpawn.arrow_tile_x;
                arrow.yTile = par1Packet23VehicleSpawn.arrow_tile_y;
                arrow.zTile = par1Packet23VehicleSpawn.arrow_tile_z;
                arrow.setInTile(par1Packet23VehicleSpawn.arrow_in_tile);
                arrow.setInData(par1Packet23VehicleSpawn.arrow_in_data);
            }
        } else if (par1Packet23VehicleSpawn.type == 61) {
            var8 = new EntitySnowball(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 71) {
            var8 = new EntityItemFrame(this.worldClient, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z, par1Packet23VehicleSpawn.throwerEntityId);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 77) {
            var8 = new EntityLeashKnot(this.worldClient, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 65) {
            var8 = new EntityEnderPearl(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 72) {
            var8 = new EntityEnderEye(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 76) {
            var8 = new EntityFireworkRocket(this.worldClient, var2, var4, var6, null);
        } else if (par1Packet23VehicleSpawn.type == 63) {
            var8 = new EntityLargeFireball(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 64) {
            var8 = new EntitySmallFireball(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 66) {
            var8 = new EntityWitherSkull(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 62) {
            var8 = new EntityEgg(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 500) {
            var8 = new EntityBrick(this.worldClient, var2, var4, var6, Item.brick);
        } else if (par1Packet23VehicleSpawn.type == 501) {
            var8 = new EntityBrick(this.worldClient, var2, var4, var6, Item.netherrackBrick);
        } else if (MathHelper.isInRange(par1Packet23VehicleSpawn.type, 600, 699)) {
            var8 = new EntityGelatinousSphere(this.worldClient, var2, var4, var6, Item.slimeBall, par1Packet23VehicleSpawn.type - 600);
        } else if (par1Packet23VehicleSpawn.type == 700) {
            var8 = new EntityWeb(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 73) {
            var8 = new EntityPotion(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 75) {
            var8 = new EntityExpBottle(this.worldClient, var2, var4, var6);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 1) {
            var8 = new EntityBoat(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 50) {
            var8 = new EntityTNTPrimed(this.worldClient, var2, var4, var6, null);
        } else if (par1Packet23VehicleSpawn.type == 51) {
            var8 = new EntityEnderCrystal(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 2) {
            var8 = new EntityItem(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 70) {
            var8 = new EntityFallingSand(this.worldClient, MathHelper.floor_double(var2) + 0.5, (double) MathHelper.floor_double(var4) + 0.5, (double) MathHelper.floor_double(var6) + 0.5, par1Packet23VehicleSpawn.throwerEntityId & '\uffff', par1Packet23VehicleSpawn.throwerEntityId >> 16);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 100) {
            var8 = new EntityWandIceBall(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 101) {
            var8 = new EntityWandFireball(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 102) {
            var8 = new EntityWandShockWave(this.worldClient, var2, var4, var6);
        }

        if (var8 != null) {
            var8.rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
            var8.rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);
            if (var8 instanceof EntityBoat) {
                var8.setPositionAndRotation2(var8.posX, var8.posY, var8.posZ, var8.rotationYaw, var8.rotationPitch, 3);
                var8.prevRotationYaw = var8.rotationYaw;
            }

            Entity[] var12 = var8.getParts();
            if (var12 != null) {
                int var10 = par1Packet23VehicleSpawn.entityId - var8.entityId;

                for (int var11 = 0; var11 < var12.length; ++var11) {
                    var12[var11].entityId += var10;
                }
            }

            var8.entityId = par1Packet23VehicleSpawn.entityId;
            this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, var8);
            if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
                if (par1Packet23VehicleSpawn.type == 60) {
                    Entity var13 = getEntityByID(par1Packet23VehicleSpawn.throwerEntityId);
                    if (var13 instanceof EntityLivingBase) {
                        EntityArrow var14 = (EntityArrow) var8;
                        var14.shootingEntity = var13;
                    }

                    var8.setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
                    return;
                }

                var8.setVelocity(par1Packet23VehicleSpawn.approx_motion_x, par1Packet23VehicleSpawn.approx_motion_y, par1Packet23VehicleSpawn.approx_motion_z);
            }
        }

    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void handleUpdateHealth(Packet8UpdateHealth par1Packet8UpdateHealth) {
        this.mc.thePlayer.setPlayerSPHealth(par1Packet8UpdateHealth.healthMP);
        this.mc.thePlayer.getFoodStats().setSatiation(par1Packet8UpdateHealth.satiation, false);
        this.mc.thePlayer.getFoodStats().setNutrition(par1Packet8UpdateHealth.nutrition, false);
        ((ITFFoodStats) this.mc.thePlayer.getFoodStats()).setSatiationWater(((ITFPacket8) par1Packet8UpdateHealth).getWater(), false);
        ((ITFPlayer) this.mc.thePlayer.getAsPlayer()).setPhytonutrients(((ITFPacket8) par1Packet8UpdateHealth).getPhytonutrients());
        ((ITFPlayer) this.mc.thePlayer.getAsPlayer()).setProtein(((ITFPacket8) par1Packet8UpdateHealth).getProtein());
        if (this.mc.thePlayer.vision_dimming < par1Packet8UpdateHealth.vision_dimming)
            this.mc.thePlayer.vision_dimming = par1Packet8UpdateHealth.vision_dimming;
    }

    @SoftOverride
    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
        GuiScreen openingGUI = this.mc.currentScreen;
        if (openingGUI instanceof GuiEnchantReserver)
            ((GuiEnchantReserver) openingGUI).setEnchantInfo(packet.getEXP());
    }

    @Shadow
    private WorldClient worldClient;

    @Shadow
    private Minecraft mc;

    @Shadow
    private Entity getEntityByID(int par1) {
        return null;
    }
}
