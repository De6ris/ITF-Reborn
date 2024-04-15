package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ITFConfig;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class TemperatureManager {

    private EntityPlayer player;

    public int HeatResistance;

    public int FreezingCooldown;

    public int FreezingWarning;

    public float BodyTemperature = 37.2F;

    public TemperatureManager(EntityPlayer player) {
        this.player = player;
    }

    public int getFreezingCooldown() {
        return this.FreezingCooldown;
    }

    public void addFreezingCooldown(int dummy) {
        if (this.FreezingCooldown + dummy < 0) {
            this.FreezingCooldown = 0;
        } else {
            this.FreezingCooldown += dummy;
        }
    }

    public void update() {
        int freezeunit = Math.max(this.FreezingCooldown - 1500 * this.player.getMiscManager().getWeight(player), 0);
        this.BodyTemperature = 37.2F - 1.25E-4F * freezeunit;
        int freezelevel = Math.max(freezeunit / 12000, 0);
        if (freezeunit > 12000 && InFreeze() &&
                freezelevel >= 1) {
            if (freezelevel >= 4) {
                this.FreezingWarning++;
                this.player.triggerAchievement(AchievementExtend.hypothermia);
            }
            if (this.FreezingWarning > 500) {
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 4.0F));
                this.FreezingWarning = 0;
            }
            this.player.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, freezeunit, this.player.isInRain() ? freezelevel : (freezelevel - 1)));
        }
        if (this.HeatResistance > 3200 - this.player.getMiscManager().getWeight(player) * 50) {
            this.player.addPotionEffect(new PotionEffect(Potion.confusion.id, 1600, 1));
            this.HeatResistance = 0;
        }
        if (this.InHeat())
            this.HeatResistance++;
    }


    public boolean InFreeze() {
        BiomeGenBase biome = this.player.worldObj.getBiomeGenForCoords(this.player.getBlockPosX(), this.player.getBlockPosZ());
        ItemStack wearingItemStack = this.player.getCuirass();
        if (EnchantmentHelper.hasEnchantment(wearingItemStack, Enchantments.enchantmentCallofNether))
            return false;
        if (biome.temperature <= ((((ITFWorld) this.player.worldObj).getWorldSeason() == 3) ? 1.0F : 0.16F) && (this.player.isOutdoors() || (this.player.worldObj.provider.dimensionId == -2 && ITFConfig.TagDeadGeothermy.get()))) {
            return this.player.getHelmet() == null || (this.player.getHelmet()).itemID != Items.wolfHelmet.itemID ||
                    this.player.getCuirass() == null || (this.player.getCuirass()).itemID != Items.wolfChestplate.itemID ||
                    this.player.getLeggings() == null || (this.player.getLeggings()).itemID != Items.wolfLeggings.itemID ||
                    this.player.getBoots() == null || (this.player.getBoots()).itemID != Items.wolfBoots.itemID;
        }
        return false;
    }


    public boolean InHeat() {
        BiomeGenBase biome = this.player.worldObj.getBiomeGenForCoords(this.player.getBlockPosX(), this.player.getBlockPosZ());
        return (biome.temperature >= 1.5F && ITFConfig.TagHeatStorm.get());
    }
}
