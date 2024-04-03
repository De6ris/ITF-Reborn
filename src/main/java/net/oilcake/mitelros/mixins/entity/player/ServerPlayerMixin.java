package net.oilcake.mitelros.mixins.entity.player;

import java.util.List;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.oilcake.mitelros.api.ITFInventory;
import net.oilcake.mitelros.api.ITFPacket8;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.enchantreserver.ContainerEnchantReserver;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ServerPlayer.class})
public abstract class ServerPlayerMixin extends EntityPlayer implements ICrafting, ITFPlayer {
    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Shadow
    protected abstract void incrementWindowID();

    @Unique
    private int last_water = -99999999;

    @Unique
    private int last_FreezingCooldown = -99999999;

    @Unique
    private int last_phytonutrients;

    @Unique
    private int last_protein;

    @Shadow
    private int currentWindowId;

    @Shadow
    public boolean playerConqueredTheEnd;

    @Shadow
    private int protein;

    @Shadow
    private int essential_fats;

    @Shadow
    private int phytonutrients;

    @Shadow
    private float lastHealth;

    @Shadow
    private int last_experience;

    @Shadow
    private int last_nutrition;

    @Shadow
    public NetServerHandler playerNetServerHandler;

    @Shadow
    public MinecraftServer mcServer;

    @Inject(method = "onDeath", at = @At("RETURN"))
    public void onDeath(DamageSource par1DamageSource, CallbackInfo callbackInfo) {
        if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
            ((ITFInventory) this.inventory).vanishingItems();
    }

    @Inject(method = "onUpdate", at = @At("RETURN"))
    public void onUpdate(CallbackInfo callbackInfo) {
        sendPacket((new Packet85SimpleSignal(EnumSignal.malnourished)).setInteger(((this.protein <= 800000) ? 1 : 0) | ((this.phytonutrients <= 800000) ? 4 : 0) | (this.getCurrent_insulin_resistance_lvl()) << 3 | getInsulinResistance() << 8));
    }

    @Inject(method = "onUpdateEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/FoodStats;getHunger()F"))
    private void inject(CallbackInfo ci) {
        float health = this.getHealth();
        int satiation = this.getSatiation();
        int nutrition = this.getNutrition();
        int FreezingCooldown = this.getFreezingCooldown();
        int water = this.getWater();
        if (water != this.last_water || FreezingCooldown != this.last_FreezingCooldown || this.phytonutrients != this.last_phytonutrients || this.protein != this.last_protein) {
            Packet8UpdateHealth updateWater = new Packet8UpdateHealth(health, satiation, nutrition, this.vision_dimming);
            ((ITFPacket8) updateWater).setWater(water);
            ((ITFPacket8) updateWater).setPhytonutrients(this.phytonutrients);
            ((ITFPacket8) updateWater).setProtein(this.protein);
            this.playerNetServerHandler.sendPacketToPlayer(updateWater);
            this.last_water = water;
            this.last_FreezingCooldown = FreezingCooldown;
            this.last_phytonutrients = this.phytonutrients;
            this.last_protein = this.protein;
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

    @Inject(method = {"<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/World;Ljava/lang/String;Lnet/minecraft/ItemInWorldManager;)V"}, at = {@At("RETURN")})
    private void injectInit(MinecraftServer par1MinecraftServer, World par2World, String par3Str, ItemInWorldManager par4ItemInWorldManager, CallbackInfo callback) {
        this.protein = this.essential_fats = this.phytonutrients = 960000;
    }


    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        incrementWindowID();
        TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
        this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 14, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));
        this.openContainer = new ContainerEnchantReserver(slots, this, x, y, z);
        this.openContainer.windowId = this.currentWindowId;
        ReflectHelper.dyCast(ServerPlayer.class, this).sendContainerAndContentsToPlayer(this.openContainer, ((ContainerEnchantReserver) this.openContainer).getInventory());
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

    public boolean isMalnourishedLv1() {
        if (this.protein <= 800000 && this.protein >= 320000)
            return true;
        return (this.phytonutrients <= 800000 && this.phytonutrients >= 320000);
    }

    public boolean isMalnourishedLv2() {
        if (this.protein < 320000 && this.protein >= 160000)
            return true;
        return (this.phytonutrients < 320000 && this.phytonutrients >= 160000);
    }

    public boolean isMalnourishedLv3() {
        if (this.protein < 160000 && this.protein >= 0)
            return true;
        return (this.phytonutrients < 160000 && this.phytonutrients >= 0);
    }

    public boolean isMalnourishedFin() {
        if (this.protein == 0)
            return true;
        return (this.phytonutrients == 0);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public float getWetnessAndMalnourishmentHungerMultiplier() {
        int x = getBlockPosX();
        int y = getFootBlockPosY();
        int z = getBlockPosZ();
        float rain_factor = isInRain() ? (this.worldObj.isThundering(true) ? 0.5F : 0.25F) : 0.0F;
        float immersion_factor = (this.worldObj.getBlockMaterial(x, y + 1, z) == Material.water) ? 0.5F : ((this.worldObj.getBlockMaterial(x, y, z) == Material.water) ? 0.25F : 0.0F);
        float wetness_factor = Math.max(rain_factor, immersion_factor);
        if (isInRain() && !this.worldObj.isThundering(true) && immersion_factor == 0.25F)
            wetness_factor += 0.125F;
        if (this.worldObj.isBiomeFreezing(x, z)) {
            wetness_factor *= 2.0F;
        } else if ((this.worldObj.getBiomeGenForCoords(x, z)).temperature >= BiomeGenBase.desertRiver.temperature) {
            wetness_factor = 0.0F;
        }
        float malnourishment_factor = isMalnourishedLv1() ? 0.5F : (isMalnourishedLv2() ? 1.0F : (isMalnourishedLv3() ? 3.0F : (isMalnourishedFin() ? 31.0F : 0.0F)));
        return 1.0F + wetness_factor + malnourishment_factor;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void travelToDimension(int par1) {
        if (this.dimension == 1 && par1 == 1) {
            triggerAchievement(AchievementList.theEnd2);
            if (Constant.CalculateCurrentDiff() >= 12)
                triggerAchievement(AchievementExtend.stormStriker);
            this.worldObj.removeEntity(this);
            this.playerConqueredTheEnd = true;
            this.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(4, 0));
        } else {
            WorldServer destination_world = this.mcServer.worldServerForDimension(par1);
            if (destination_world.isUnderworld())
                this.worldObj.getWorldInfo().setUnderworldVisited();
            if (destination_world.isTheNether())
                this.worldObj.getWorldInfo().setNetherVisited();
            if (destination_world.isTheEnd() && destination_world.playerEntities.size() == 0)
                ((WorldProviderEnd) (this.mcServer.worldServerForDimension(par1)).provider).heal_ender_dragon = true;
            if (this.dimension == 0 && par1 == 1) {
                triggerAchievement(AchievementList.theEnd);
                ChunkCoordinates var2 = this.mcServer.worldServerForDimension(par1).getEntrancePortalLocation();
                if (var2 != null)
                    this.playerNetServerHandler.setPlayerLocation(var2.posX, var2.posY, var2.posZ, 0.0F, 0.0F);
                par1 = 1;
            } else {
                triggerAchievement(AchievementList.portal);
            }
            this.mcServer.getConfigurationManager().transferPlayerToDimension(getAsEntityPlayerMP(), par1);
            this.last_experience = -1;
            this.lastHealth = -1.0F;
            this.last_nutrition = -1;
        }
    }

    @Shadow
    public INetworkManager getNetManager() {
        return null;
    }

    @Shadow
    public void sendChatToPlayer(ChatMessageComponent chatMessage) {
    }

    @Shadow
    public boolean canCommandSenderUseCommand(int i, String s) {
        return false;
    }

    @Shadow
    public ChunkCoordinates getPlayerCoordinates() {
        return null;
    }

    @Shadow
    public void sendContainerAndContentsToPlayer(Container container, List list) {
    }

    @Shadow
    public void sendSlotContents(Container container, int i, ItemStack itemStack) {
    }

    @Shadow
    public void sendProgressBarUpdate(Container container, int i, int i1) {
    }
}
