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
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin extends NetHandler implements ITFNetHandler {

    @Unique
    private int waterCounter = 10;
    @Unique
    private int phytoCounter = 10;
    @Unique
    private int proteinCounter = 10;

    @Inject(method = "handleUpdateHealth", at = @At("TAIL"))
    private void itfHealth(Packet8UpdateHealth par1Packet8UpdateHealth, CallbackInfo ci) {
        this.updateWater(par1Packet8UpdateHealth);
        this.updatePhyto(par1Packet8UpdateHealth);
        this.updateProtein(par1Packet8UpdateHealth);
        this.updateTemperature(par1Packet8UpdateHealth);
    }

    private void updateTemperature(Packet8UpdateHealth par1Packet8UpdateHealth) {
        ((ITFPlayer) this.mc.thePlayer.getAsPlayer()).setTemperature(((ITFPacket8) par1Packet8UpdateHealth).getTemperature());
    }

    private void updateProtein(Packet8UpdateHealth par1Packet8UpdateHealth) {
        int protein = ((ITFPacket8) par1Packet8UpdateHealth).getProtein();
        if (protein != 0) {
            ((ITFPlayer) this.mc.thePlayer.getAsPlayer()).setProtein(protein);
            this.proteinCounter = 0;
            return;
        } else {
            this.proteinCounter++;
        }
        if (this.proteinCounter > 15) {
            ((ITFPlayer) this.mc.thePlayer.getAsPlayer()).setProtein(0);
            this.proteinCounter = 0;
        }
    }

    private void updatePhyto(Packet8UpdateHealth par1Packet8UpdateHealth) {
        int phyto = ((ITFPacket8) par1Packet8UpdateHealth).getPhytonutrients();
        if (phyto != 0) {
            ((ITFPlayer) this.mc.thePlayer.getAsPlayer()).setPhytonutrients(phyto);
            this.phytoCounter = 0;
            return;
        } else {
            this.phytoCounter++;
        }
        if (this.phytoCounter > 15) {
            ((ITFPlayer) this.mc.thePlayer.getAsPlayer()).setPhytonutrients(0);
            this.phytoCounter = 0;
        }
    }

    private void updateWater(Packet8UpdateHealth par1Packet8UpdateHealth) {
        int water = ((ITFPacket8) par1Packet8UpdateHealth).getWater();
        if (water != 0) {
            ((ITFFoodStats) this.mc.thePlayer.getFoodStats()).setSatiationWater(water, false);
            this.waterCounter = 0;
            return;
        } else {
            this.waterCounter++;
        }
        if (this.waterCounter > 15) {
            ((ITFFoodStats) this.mc.thePlayer.getFoodStats()).setSatiationWater(((ITFPacket8) par1Packet8UpdateHealth).getWater(), false);
            this.waterCounter = 0;
        }
    }

    @Inject(method = "handleVehicleSpawn", at = @At("HEAD"), cancellable = true)
    private void itfEntity(Packet23VehicleSpawn par1Packet23VehicleSpawn, CallbackInfo ci) {
        if (par1Packet23VehicleSpawn.type < 100 || par1Packet23VehicleSpawn.type > 102) {
            return;
        }
        ci.cancel();
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

        if (par1Packet23VehicleSpawn.type == 100) {
            var8 = new EntityWandIceBall(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 101) {
            var8 = new EntityWandFireball(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 102) {
            var8 = new EntityWandShockWave(this.worldClient, var2, var4, var6);
        }

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
