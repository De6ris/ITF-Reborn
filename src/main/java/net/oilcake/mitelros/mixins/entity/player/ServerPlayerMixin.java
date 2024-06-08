package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.ContainerEnchantReserver;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.minePocket.ContainerMinePocket;
import net.oilcake.mitelros.network.S2COpenWindow;
import net.oilcake.mitelros.network.S2CUpdateNutrition;
import net.oilcake.mitelros.status.EnchantmentManager;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends EntityPlayer implements ICrafting, ITFPlayer {
    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Shadow
    protected abstract void incrementWindowID();

    @Unique
    private int last_water;
    @Unique
    private double last_temperature;

    @Unique
    private int last_phytonutrients;

    @Unique
    private int last_protein;

    @Shadow
    private int currentWindowId;

    @Shadow
    private int protein;

    @Shadow
    private int essential_fats;

    @Shadow
    private int phytonutrients;

    @Shadow
    public NetServerHandler playerNetServerHandler;


    @Shadow
    public abstract boolean isMalnourished();

    @Inject(method = "onDeath", at = @At("RETURN"))
    public void onDeath(DamageSource par1DamageSource, CallbackInfo callbackInfo) {
        if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            EnchantmentManager.vanish(this.inventory);
        }
    }

    @Inject(method = "onUpdate", at = @At("RETURN"))
    public void onUpdate(CallbackInfo callbackInfo) {
        this.sendPacket((new Packet85SimpleSignal(EnumSignal.malnourished)).setInteger(((this.protein <= 800000) ? 1 : 0) | ((this.phytonutrients <= 800000) ? 4 : 0) | (this.getCurrent_insulin_resistance_lvl()) << 3 | getInsulinResistance() << 8));
    }

    @Inject(method = "onUpdateEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/FoodStats;getHunger()F"))
    private void inject(CallbackInfo ci) {
        if (!this.is_cursed && ITFConfig.TagRejection.getBooleanValue()) {
            EntityWitch temp = new EntityWitch(this.worldObj);
            int username_hash = 0;
            for (int i = 0; i < this.username.length(); i++)
                username_hash += this.username.charAt(i) * i;
            this.worldObj.getAsWorldServer().addCurse(getAsEntityPlayerMP(), temp, Curse.getRandomCurse(new Random((this.rand.nextInt() + username_hash))), 0);
            learnCurseEffect();
        }
        int water = this.getWater();
        double temperature = this.getTemperatureManager().getBodyTemperature();
        if (water != this.last_water || this.phytonutrients != this.last_phytonutrients || this.protein != this.last_protein || temperature != this.last_temperature) {
            this.playerNetServerHandler.sendPacketToPlayer(new S2CUpdateNutrition(phytonutrients, protein, water, temperature));
            this.last_water = water;
            this.last_phytonutrients = this.phytonutrients;
            this.last_protein = this.protein;
            this.last_temperature = temperature;
        }
    }

    @ModifyArg(method = "setProtein", at = @At(value = "INVOKE", target = "Lnet/minecraft/MathHelper;clamp_int(III)I"), index = 2)
    private int widenProtein(int par0) {
        return 960000;
    }

    @ModifyArg(method = "setEssentialFats", at = @At(value = "INVOKE", target = "Lnet/minecraft/MathHelper;clamp_int(III)I"), index = 2)
    private int widenFat(int par0) {
        return 960000;
    }

    @ModifyArg(method = "setPhytonutrients", at = @At(value = "INVOKE", target = "Lnet/minecraft/MathHelper;clamp_int(III)I"), index = 2)
    private int widenPhytonutrients(int par0) {
        return 960000;
    }

    @Inject(method = "<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/World;Ljava/lang/String;Lnet/minecraft/ItemInWorldManager;)V", at = @At("RETURN"))
    private void injectInit(MinecraftServer par1MinecraftServer, World par2World, String par3Str, ItemInWorldManager par4ItemInWorldManager, CallbackInfo callback) {
        this.protein = this.essential_fats = this.phytonutrients = 960000;
    }

    @Override
    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        incrementWindowID();
        TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
        this.playerNetServerHandler.sendPacketToPlayer((new S2COpenWindow(this.currentWindowId, S2COpenWindow.EnumInventoryType.EnchantReserver, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));// 9 is dummy
        this.openContainer = new ContainerEnchantReserver(slots, this, x, y, z);
        this.openContainer.windowId = this.currentWindowId;
        this.sendContainerAndContentsToPlayer(this.openContainer, ((ContainerEnchantReserver) this.openContainer).getInventory());
        this.openContainer.addCraftingToCrafters(this);
    }

    @Override
    public void displayGuiMinePocket(IInventory inventory) {
        this.incrementWindowID();
        this.playerNetServerHandler.sendPacketToPlayer(new S2COpenWindow(this.currentWindowId, S2COpenWindow.EnumInventoryType.MinePocket, inventory.getCustomNameOrUnlocalized(), 5, true));
        this.openContainer = new ContainerMinePocket(this, inventory);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    @Inject(method = "isMalnourished", at = @At("HEAD"), cancellable = true)
    private void inject_1(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.protein <= 800000 || this.phytonutrients <= 800000);
    }

    @Inject(method = "isDoubleMalnourished", at = @At("HEAD"), cancellable = true)
    private void inject_2(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.protein <= 800000 && this.phytonutrients <= 800000);
    }

    @Override
    public int malnourishedLevel() {
        int min = Math.min(this.protein, this.phytonutrients);
        if (min < 160000) return 3;
        if (min < 320000) return 2;
        if (min < 800000) return 1;
        return 0;
    }

    public boolean isMalnourishedFinal() {
        if (this.protein == 0)
            return true;
        return (this.phytonutrients == 0);
    }

    @Unique
    private int getCurrent_insulin_resistance_lvl() {
        if (this.insulin_resistance_level == null)
            return 0;
        return this.insulin_resistance_level.ordinal() + 1;
    }

    @Inject(method = "getWetnessAndMalnourishmentHungerMultiplier", at = @At("RETURN"), cancellable = true)
    private void malnourishment(CallbackInfoReturnable<Float> cir) {
        float original = cir.getReturnValue();
        float oldFactor = this.isMalnourished() ? 0.5F : 0.0F;
        original -= oldFactor;
        float newFactor;
        if (this.isMalnourishedFinal()) {
            newFactor = 31.0F;
        } else {
            int level = this.malnourishedLevel();
            newFactor = level == 3 ? 3.0F : (level == 2 ? 1.0F : (level == 1 ? 0.5F : 0.0F));
        }
        cir.setReturnValue(original + newFactor);
    }

    @Inject(method = "travelToDimension", at = @At(value = "INVOKE", target = "Lnet/minecraft/World;removeEntity(Lnet/minecraft/Entity;)V"))
    private void itfAchievement(int par1, CallbackInfo ci) {
        if (Constant.calculateCurrentDifficulty() >= 12)
            triggerAchievement(AchievementExtend.stormStriker);
    }
}
