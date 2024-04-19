package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class TemperatureManager {

    private final EntityPlayer player;

    public int heatResistance;

    public int freezingCoolDown;

    public int freezingWarning;
    public static final float normalTemperature = 37.2F;

    public float bodyTemperature = normalTemperature;

    public TemperatureManager(EntityPlayer player) {
        this.player = player;
    }

    public int getFreezingCoolDown() {
        return this.freezingCoolDown;
    }

    public void addFreezingCoolDown(int coolDown) {
        if (this.freezingCoolDown + coolDown < 0) {
            this.freezingCoolDown = 0;
        } else {
            this.freezingCoolDown += coolDown;
        }
    }

    public void update() {
        float difference = this.bodyTemperature - normalTemperature;
        float toAdd = 1E-5F * difference * (1.0F + (float) this.calcArmorHeat() / 20);
        this.bodyTemperature -= toAdd;
        this.bodyTemperature += 1E-4F * this.getUnit();
        this.checkFreeze();
        this.checkHeat();
    }

    public int getUnit() {
        int freezeUnit = this.calculateFreezeUnit();
        int heatUnit = this.calculateHeatUnit();
        return heatUnit - freezeUnit;
    }

    private void checkFreeze() {
        int freezeLevel = (int) ((normalTemperature - this.bodyTemperature) / 5.0F);
        if (freezeLevel >= 1) {
            this.freezingCoolDown += freezeLevel;
        } else {
            if (this.freezingCoolDown > 0) {
                this.freezingCoolDown -= 1;
            }
        }
        if (this.freezingCoolDown > 12800 - this.calcArmorHeat() * 100 && !this.player.inCreativeMode() && !EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentCallOfNether)) {
            if (freezeLevel >= 4) {
                this.freezingWarning++;
                this.player.triggerAchievement(AchievementExtend.hypothermia);
            }
            if (this.freezingWarning > 500) {
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 4.0F));
                this.freezingWarning = 0;
            }
            this.player.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, this.freezingCoolDown >> 3, this.player.isInRain() ? freezeLevel : (freezeLevel - 1)));
            this.freezingCoolDown = 0;
        }
    }

    private void checkHeat() {
        boolean hot = this.bodyTemperature > 40.0F;
        if (hot) {
            this.heatResistance++;
        } else {
            if (this.heatResistance > 0) {
                this.heatResistance -= 1;
            }
        }
        if (this.heatResistance > 12800 - this.calcArmorHeat() * 100 && !this.player.inCreativeMode() && !EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentCallOfPolar)) {
            this.player.addPotionEffect(new PotionEffect(Potion.confusion.id, this.heatResistance >> 3, 1));
            this.heatResistance = 0;
        }
    }


    public boolean inFreeze() {
        BiomeGenBase biome = this.player.worldObj.getBiomeGenForCoords(this.player.getBlockPosX(), this.player.getBlockPosZ());
        boolean isOut = this.player.isOutdoors() || (ITFConfig.TagDeadGeothermy.get() && this.player.worldObj.provider.dimensionId == -2);
        if (biome.temperature <= ((((ITFWorld) this.player.worldObj).getWorldSeason() == 3) ? 1.0F : 0.16F) && isOut) {
            return this.player.getHelmet() == null || (this.player.getHelmet()).itemID != Items.wolfHelmet.itemID ||
                    this.player.getCuirass() == null || (this.player.getCuirass()).itemID != Items.wolfChestplate.itemID ||
                    this.player.getLeggings() == null || (this.player.getLeggings()).itemID != Items.wolfLeggings.itemID ||
                    this.player.getBoots() == null || (this.player.getBoots()).itemID != Items.wolfBoots.itemID;
        }
        return false;
    }

    public int calculateFreezeUnit() {
        World world = this.player.worldObj;
        int basis = ITFConfig.TagLegendFreeze.get() ? 3 : 1;
        int freeze = 0;
        if (((ITFWorld) world).getWorldSeason() == 3) {
            freeze += basis;
        }
        if (this.player.isInRain()) {
            freeze += basis;
        }
        if (this.inFreeze()) {
            freeze += basis;
        }
        if (this.player.getDrunkManager().isDrunk()) {
            freeze += basis;
        }
        if (this.player.isInWater()) {
            freeze += 16;
        } else {
            int x = this.player.getBlockPosX();
            int y = this.player.getBlockPosY();
            int z = this.player.getBlockPosZ();
            int coldest = 0;
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    for (int dz = -2; dz <= 2; dz++) {
                        Block block = world.getBlock(x + dx, y + dy, z + dz);
                        if (block == null) continue;
                        int decay = Math.abs(dx) + Math.abs(dz);
                        int blockId = block.blockID;
                        if (blockId == Block.snow.blockID || blockId == Block.blockSnow.blockID) {
                            coldest = Math.max(coldest, 2 - decay);
                        }
                        if (blockId == Block.ice.blockID) {
                            coldest = Math.max(coldest, 4 - 2 * decay);
                        }
                    }
                }
            }
            freeze += coldest;
        }
        return freeze;
    }

    public int calculateHeatUnit() {
        World world = this.player.worldObj;
        int heat = 0;
        int basis = ITFConfig.TagHeatStorm.get() ? 3 : 1;
        if (((ITFWorld) world).getWorldSeason() == 1) {
            heat += basis;
        }
        BiomeGenBase biome = world.getBiomeGenForCoords(this.player.getBlockPosX(), this.player.getBlockPosZ());
        if (biome.temperature >= 1.5F) {
            heat += basis;
        }
        if (this.player.isInSunlight()) {
            heat += basis;
        }
        if (this.player.isInFire()) {
            heat += 32;
        }
        if (this.player.isInNether()) {
            heat += 128;
        }
        int x = this.player.getBlockPosX();
        int y = this.player.getBlockPosY();
        int z = this.player.getBlockPosZ();
        int hottest = 0;
        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                for (int dz = -2; dz <= 2; dz++) {
                    Block block = world.getBlock(x + dx, y + dy, z + dz);
                    if (block == null) continue;
                    int decay = Math.abs(dx) + Math.abs(dz);
                    if (block instanceof BlockFurnace furnace && furnace.isActive) {
                        hottest = Math.max(hottest, 8 - 2 * decay);
                    } else {
                        int blockId = block.blockID;
                        if (blockId == Block.fire.blockID) {
                            hottest = Math.max(hottest, 16 - 4 * decay);
                        }
                        if (blockId == Block.lavaMoving.blockID || blockId == Block.lavaStill.blockID) {
                            hottest += Math.max(hottest, 64 - 16 * decay);
                        }
                    }
                }
            }
        }
        heat += hottest;
        return heat;
    }

    public int calcArmorHeat() {
        int heat = 0;
        ItemStack helmet = player.getHelmet();
        ItemStack cuirass = player.getCuirass();
        ItemStack leggings = player.getLeggings();
        ItemStack boots = player.getBoots();
        if (helmet != null) {
            heat += 2;
            if (helmet.itemID == Items.wolfHelmet.itemID) heat += 2;
            else if (helmet.itemID == Item.helmetLeather.itemID) heat += 1;
        }
        if (cuirass != null) {
            heat += 2;
            if (cuirass.itemID == Items.wolfChestplate.itemID) heat += 2;
            else if (cuirass.itemID == Item.plateLeather.itemID) heat += 1;
        }
        if (leggings != null) {
            heat += 2;
            if (leggings.itemID == Items.wolfLeggings.itemID) heat += 2;
            else if (leggings.itemID == Item.legsLeather.itemID) heat += 1;
        }
        if (boots != null) {
            heat += 2;
            if (boots.itemID == Items.wolfBoots.itemID) heat += 2;
            else if (boots.itemID == Item.bootsLeather.itemID) heat += 1;
        }
        return heat;
    }
}
