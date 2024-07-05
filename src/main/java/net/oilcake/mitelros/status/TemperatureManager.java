package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class TemperatureManager {
    private final EntityPlayer player;

    public int heatResistance;

    public int freezingCoolDown;

    public int freezingWarning;
    public int heatWarning;
    public static final double normalTemperature = 37.2D;
    private double bodyTemperature = normalTemperature;

    public TemperatureManager(EntityPlayer player) {
        this.player = player;
    }

    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public void addBodyTemperature(double delta) {
        this.bodyTemperature += delta;
    }

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public void addFreezingCoolDown(int coolDown) {
        if (this.freezingCoolDown + coolDown < 0) {
            this.freezingCoolDown = 0;
        } else {
            this.freezingCoolDown += coolDown;
        }
    }

    public void addHeatResistance(int resistance) {
        if (this.heatResistance + resistance < 0) {
            this.heatWarning = 0;
        } else {
            this.heatResistance += resistance;
        }
    }

    public void update() {
        this.addBodyTemperature(1E-5D * this.getUnit());
        if (this.bodyTemperature < 20.0D) this.bodyTemperature = 20.0D;
        if (this.bodyTemperature > 50.0D) this.bodyTemperature = 50.0D;
        this.checkFreeze();
        this.checkHeat();
    }

    public double getUnit() {
        int freezeUnit = this.findFreezeSource();
        int heatUnit = this.findHeatSource();
        double commonFactor = this.calculateCommonFactor();
        return (commonFactor + heatUnit - freezeUnit) * (ITFConfig.TagExtremeClimate.getBooleanValue() ? 3.0d : 1.0d);
    }

    public boolean feelsHot() {
        return this.bodyTemperature > normalTemperature;
    }

    public boolean feelsCold() {
        return this.bodyTemperature < normalTemperature;
    }

    private void checkFreeze() {
        boolean invincible = this.player.inCreativeMode() ||
                EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentFrostResistance) ||
                this.calcArmorHeat() > 7 ||
                this.player.isPotionActive(PotionExtend.frostResistance);

        int freezeLevel = invincible ? -1 : (int) ((normalTemperature - this.bodyTemperature) / 3.0D);

        if (freezeLevel <= 0) {
            if (this.freezingCoolDown > 0) {
                this.freezingCoolDown += freezeLevel - 1;
            }
        } else {
            this.freezingCoolDown += freezeLevel;
            if (this.freezingCoolDown > 6400) {
                this.player.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, 20 + (this.freezingCoolDown >> 3), this.player.isInRain() ? freezeLevel : (freezeLevel - 1)));
                this.freezingCoolDown = 0;
            }
        }

        if (freezeLevel >= 2) {
            this.freezingWarning += freezeLevel - 1;
            this.player.triggerAchievement(AchievementExtend.hypothermia);
            if (this.freezingWarning > 600) {
                this.player.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, 20 + (this.freezingWarning >> 2), this.player.isInRain() ? freezeLevel : (freezeLevel - 1)));
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 1.0F));
                if (this.player.getSatiation() > 0) {
                    this.player.getFoodStats().addNutrition(-1);
                } else {
                    this.player.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 1.0F));
                }
                this.freezingWarning = 0;
            }
        } else if (this.freezingWarning > 0) {
            this.freezingWarning -= 1;
        }
    }

    private void checkHeat() {
        boolean invincible = this.player.inCreativeMode() ||
                EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentHeatResistance) ||
                this.calcArmorHeat() < -9 ||
                this.player.isPotionActive(Potion.fireResistance);

        int heatLevel = invincible ? -1 : (int) ((this.bodyTemperature - normalTemperature) / 3.0D);

        if (heatLevel <= 0) {
            if (this.heatResistance > 0) {
                this.heatResistance += heatLevel - 1;
            }
        } else {
            this.heatResistance += heatLevel;
            if ((this.heatResistance > 6400)) {
                this.player.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 + (this.heatResistance >> 3), 1));
                this.heatResistance = 0;
            }
        }

        if (heatLevel >= 2) {
            this.heatWarning += heatLevel - 1;
            this.player.triggerAchievement(AchievementExtend.hyperthermia);
            if (this.heatWarning > 600) {
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.heat, 1.0F));
                if (((ITFPlayer) this.player).itf$GetWater() > 0) {
                    ((ITFFoodStats) this.player.getFoodStats()).itf$AddWater(-1);
                } else {
                    this.player.attackEntityFrom(new Damage(DamageSourceExtend.heat, 1.0F));
                }
                this.player.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 + (this.heatWarning >> 2), 1));
                this.heatWarning = 0;
            }
        } else if (this.heatWarning > 0) {
            this.heatWarning -= 1;
        }
    }

    private double calculateCommonFactor() {
        boolean debug = false;
        double result = 0.0d;
        int potionEffect = (this.player.isPotionActive(PotionExtend.warm) ? this.player.getActivePotionEffect(PotionExtend.warm).getAmplifier() + 1 : 0)
                - (this.player.isPotionActive(PotionExtend.cool) ? this.player.getActivePotionEffect(PotionExtend.cool).getAmplifier() + 1 : 0);
        if (potionEffect > 0 && normalTemperature - this.bodyTemperature > 1.0D) {
            result += potionEffect * 8;
        } else if (potionEffect < 0 && this.bodyTemperature - normalTemperature > 1.0D) {
            result += potionEffect * 8;
        }
        if (this.player.isBurning()) result += 16;
        result += this.calcArmorHeat();
        result -= (this.bodyTemperature - normalTemperature) * (ITFConfig.TagExtremeClimate.getBooleanValue() ? 0.33D : 1.0D);
        if (debug) System.out.println(result + "deltaed");

        World world = this.player.worldObj;
        switch (world.getDimensionId()) {
            case -1 -> {
                return result + 36;
            }
            case 1 -> {
                return result - 4;
            }
            case -2 -> {
                return result + heightFactor(this.player.getBlockPosY(), 128) + (ITFConfig.TagDeadGeothermy.getBooleanValue() ? -4 : 0);
            }
        }
        boolean isOutdoors = this.player.isOutdoors();
        BiomeGenBase biome = world.getBiomeGenForCoords(this.player.getBlockPosX(), this.player.getBlockPosZ());
        boolean isRaining = world.isPrecipitating(false);
        if (isRaining && !biome.isDesertBiome()) {
            float rainStrength = world.getRainStrength(1.0f);
            result -= rainStrength * (isOutdoors ? 4.0f : 1.0f);
        }
        result += sunshine(world.getTimeOfDay()) * (isOutdoors ? 4.0f : 1.0f) * (biome.isDesertBiome() ? 2.0f : 1.0f);
        if (debug) System.out.println(result + "sunshined");

        result += seasonFactor(world.getDayOfWorld());
        if (debug) System.out.println(result + "seasoned");

        result += biomeFactor(biome);
        if (debug) System.out.println(result + "biomed");

        if (this.player.getBlockPosY() > 64) result += heightFactor(this.player.getBlockPosY(), 64);
        if (debug) System.out.println(result + "heighted");
        if (debug) System.out.println("-----------");

        return result;
    }

    private static double sunshine(int time) {
        return Math.sin(0.000261799387799 * (time - 6000));
    }

    public static double heightFactor(int y, int balance) {
        return y == balance ? 0.0d : (y > balance ? -1 : 1) * (balance - y) * (balance - y) * 0.001d;
    }

    public static double biomeFactor(BiomeGenBase biome) {
        float temperature = biome.temperature;
        return temperature == 0.8f ? 0.0d :
                (temperature > 0.8f ? 1 : -1) * (temperature - 0.8f) * (temperature - 0.8f) * 16.0f;
    }

    public static double seasonFactor(int day) {
        return 2.0d * Math.sin(0.0490873852123 * (day - 16));
    }

    public int findFreezeSource() {
        World world = this.player.worldObj;
        int freeze = 0;
        if (this.player.itf$GetDrunkManager().isDrunk()) {
            freeze += 1;
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

    public int findHeatSource() {
        World world = this.player.worldObj;
        int x = this.player.getBlockPosX();
        int y = this.player.getBlockPosY();
        int z = this.player.getBlockPosZ();
        int hottest = 0;
        for (int dx = -4; dx <= 4; dx++) {
            for (int dy = -4; dy <= 4; dy++) {
                for (int dz = -4; dz <= 4; dz++) {
                    Block block = world.getBlock(x + dx, y + dy, z + dz);
                    if (block == null) continue;
                    int decay = Math.abs(dx) + Math.abs(dz);
                    int blockId = block.blockID;
                    if (blockId == Block.lavaMoving.blockID || blockId == Block.lavaStill.blockID || blockId == Block.mantleOrCore.blockID) {
                        hottest = Math.max(hottest, 32 - 2 * decay);
                    }
                    if (decay > 4) continue;
                    if (block instanceof BlockFurnace furnace && furnace.isActive) {
                        hottest = Math.max(hottest, 8 - 2 * decay);
                    } else {
                        if (blockId == Block.fire.blockID) {
                            hottest = Math.max(hottest, 16 - 4 * decay);
                        }
                    }
                }
            }
        }
        return hottest;
    }

    public float calcArmorHeat() {
        float heat = 0;
        for (ItemStack wornItem : this.player.getWornItems()) {
            if (wornItem != null) {
                heat -= 1;
                if (wornItem.hasMaterial(Materials.wolf_fur)) heat += 3.4;
                if (wornItem.hasMaterial(Material.leather)) heat += 2.2;
                if (wornItem.hasMaterial(Materials.ice_chunk)) heat -= 1.4f;
            }
        }
        return heat;
    }
}
