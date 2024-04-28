package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class TemperatureManager {

    private final EntityPlayer player;

    public int heatResistance;

    public int freezingCoolDown;

    public int freezingWarning;
    public int heatWarning;
    public static final float normalTemperature = 37.2F;

    public float bodyTemperature = normalTemperature;

    public TemperatureManager(EntityPlayer player) {
        this.player = player;
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
        float tosubTract = 1E-5F * this.calcDelta() * (ITFConfig.TagExtremeClimate.get() ? 0.33f : 1.0f);
        this.bodyTemperature -= tosubTract;
        this.bodyTemperature += (float) (1E-5D * this.getUnit());
        if (this.bodyTemperature < 0.0f) this.bodyTemperature = 0.0f;
        if (this.bodyTemperature > 60.0f) this.bodyTemperature = 60.0f;
        this.checkFreeze();
        this.checkHeat();
    }

    public double getUnit() {
        int freezeUnit = this.findFreezeSource();
        int heatUnit = this.findHeatSource();
        double commonFactor = this.calculateCommonFactor();
        return (commonFactor + heatUnit - freezeUnit) * (ITFConfig.TagExtremeClimate.get() ? 3.0d : 1.0d);
    }

    private void checkFreeze() {
        boolean invincible = this.player.inCreativeMode() ||
                EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentCallOfNether) ||
                this.calcArmorHeat() == 8 ||
                this.player.isPotionActive(PotionExtend.frost_resistance);

        int freezeLevel = invincible ? -1 : (int) ((normalTemperature - this.bodyTemperature) / 3.0F);

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

        if (freezeLevel >= 4) {
            this.freezingWarning++;
            this.player.triggerAchievement(AchievementExtend.hypothermia);
            if (this.freezingWarning > 500) {
                this.player.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, 20 + (this.freezingWarning >> 3), this.player.isInRain() ? freezeLevel : (freezeLevel - 1)));
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 2.0F));
                this.freezingWarning = 0;
            }
        } else if (this.freezingWarning > 0) {
            this.freezingWarning -= 1;
        }
    }


    private void checkHeat() {
        boolean invincible = this.player.inCreativeMode() ||
                EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentCallOfPolar) ||
                this.calcArmorHeat() == -8 ||
                this.player.isPotionActive(Potion.fireResistance);

        int heatLevel = invincible ? -1 : (int) ((this.bodyTemperature - normalTemperature) / 3.0F);

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

        if (heatLevel >= 4) {
            this.heatWarning++;
            this.player.triggerAchievement(AchievementExtend.hyperthermia);
            if (this.heatWarning > 500) {
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.heat, 2.0F));
                ((ITFFoodStats) this.player.getFoodStats()).addWater(-1);
                this.player.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 + (this.heatWarning >> 3), 1));
                this.heatWarning = 0;
            }
        } else if (this.heatWarning > 0) {
            this.heatWarning -= 1;
        }
    }


    private double calculateCommonFactor() {
        double result = 0.0d;
        World world = this.player.worldObj;
        int dimensionId = world.getDimensionId();
        switch (dimensionId) {
            case -1 -> {
                return result + 32;
            }
            case 1 -> {
                return result - 4;
            }
            case -2 -> {
                return result + heightFactor(this.player.getBlockPosY(), 128) + (ITFConfig.TagDeadGeothermy.get() ? -4 : 0);
            }
        }

        boolean isOutdoors = this.player.isOutdoors();
        boolean isRaining = world.isPrecipitating(false);
        if (isRaining) {
            float rainStrength = world.getRainStrength(1.0f);
            result -= rainStrength * (isOutdoors ? 8.0f : 4.0f);
        }

        int time = world.getTimeOfDay();
        result += sunshine(time) * (isOutdoors ? 2.0f : 1.0f);

        result += seasonFactor(world.getDayOfWorld());
        result += biomeFactor(world.getBiomeGenForCoords(this.player.getBlockPosX(), this.player.getBlockPosZ()));
        if (this.player.getBlockPosY() > 64) result += heightFactor(this.player.getBlockPosY(), 64);
        result += this.calcArmorHeat() * 0.1f;
        int potionEffect = (this.player.isPotionActive(PotionExtend.warm) ? this.player.getActivePotionEffect(PotionExtend.warm).getAmplifier() + 1 : 0)
                - (this.player.isPotionActive(PotionExtend.cool) ? this.player.getActivePotionEffect(PotionExtend.cool).getAmplifier() + 1 : 0);
        if (potionEffect * (normalTemperature - this.bodyTemperature) > 0) result += potionEffect * 4;
        return result;
    }

    private float calcDelta() {
        return this.bodyTemperature - normalTemperature;
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
                (temperature > 0.8f ? 1 : -1) * (temperature - 0.8f) * (temperature - 0.8f) * 2.0f;
    }

    public static double seasonFactor(int day) {
        return 2.0d * Math.sin(0.0490873852123 * (day - 16));
    }


    public int findFreezeSource() {
        World world = this.player.worldObj;
        int freeze = 0;
        if (this.player.getDrunkManager().isDrunk()) {
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
        int heat = 0;
        if (this.player.isBurning()) heat += 16;
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
                    if (blockId == Block.lavaMoving.blockID || blockId == Block.lavaStill.blockID) {
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
            heat -= 1;
            if (helmet.itemID == Items.wolfHelmet.itemID) heat += 3;
            else if (helmet.itemID == Item.helmetLeather.itemID) heat += 2;
            else if (helmet.itemID == Items.iceHelmet.itemID) heat -= 1;
        }
        if (cuirass != null) {
            heat -= 1;
            if (cuirass.itemID == Items.wolfChestplate.itemID) heat += 3;
            else if (cuirass.itemID == Item.plateLeather.itemID) heat += 2;
            else if (cuirass.itemID == Items.iceChestplate.itemID) heat -= 1;
        }
        if (leggings != null) {
            heat -= 1;
            if (leggings.itemID == Items.wolfLeggings.itemID) heat += 3;
            else if (leggings.itemID == Item.legsLeather.itemID) heat += 2;
            else if (leggings.itemID == Items.iceLeggings.itemID) heat -= 1;
        }
        if (boots != null) {
            heat -= 1;
            if (boots.itemID == Items.wolfBoots.itemID) heat += 3;
            else if (boots.itemID == Item.bootsLeather.itemID) heat += 2;
            else if (boots.itemID == Items.iceBoots.itemID) heat -= 1;
        }
        return heat;
    }
}
