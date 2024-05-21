package net.oilcake.mitelros.mixins.network;

import net.minecraft.*;
import net.oilcake.mitelros.api.*;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.entity.misc.EntityWandFireBall;
import net.oilcake.mitelros.entity.misc.EntityWandIceBall;
import net.oilcake.mitelros.entity.misc.EntityWandShockWave;
import net.oilcake.mitelros.entity.misc.EntityWandSlimeBall;
import net.oilcake.mitelros.network.S2CEnchantReserverInfo;
import net.oilcake.mitelros.network.S2CEnchantmentInfo;
import net.oilcake.mitelros.network.S2COpenWindow;
import net.oilcake.mitelros.network.S2CUpdateNutrition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetClientHandler.class)
public abstract class NetClientHandlerMixin extends NetHandler implements ITFNetHandler {
    @Inject(method = "handleVehicleSpawn", at = @At("HEAD"), cancellable = true)
    private void addITFEntity(Packet23VehicleSpawn par1Packet23VehicleSpawn, CallbackInfo ci) {
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
            var8 = new EntityWandFireBall(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 102) {
            var8 = new EntityWandShockWave(this.worldClient, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 103) {
            var8 = new EntityWandSlimeBall(this.worldClient, var2, var4, var6);
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


    @Override
    public void processEnchantReserverInfo(S2CEnchantReserverInfo packet) {
        GuiScreen openingGUI = this.mc.currentScreen;
        if (openingGUI instanceof GuiEnchantReserver)
            ((GuiEnchantReserver) openingGUI).setEnchantInfo(packet.getEXP());
    }

    @Override
    public void handleUpdateNutrition(S2CUpdateNutrition packet) {
        ITFClientPlayer clientPlayer = this.mc.thePlayer;
        clientPlayer.setPhytonutrients(packet.getPhytonutrients());
        clientPlayer.setProtein(packet.getProtein());
        ((ITFFoodStats) this.mc.thePlayer.getFoodStats()).setSatiationWater(packet.getWater(), false);
        ((ITFPlayer) this.mc.thePlayer).getTemperatureManager().setBodyTemperature(packet.getTemp());
    }

    @Override
    public void handleEnchantmentInfo(S2CEnchantmentInfo packet) {
        GuiScreen openingGUI = this.mc.currentScreen;
        if (openingGUI instanceof GuiEnchantment guiEnchantment)
            ((ITFGui) guiEnchantment).setEnchantmentInfo(packet.getInfo());
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
