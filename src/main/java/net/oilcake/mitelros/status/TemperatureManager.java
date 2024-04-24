package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.util.AchievementExtend;
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

    public void update() {
        float difference = this.bodyTemperature - normalTemperature;
        float armorFactor = (float) this.calcArmorHeat() / 20;
        float toAdd = 1E-5F * difference * (1.0F + (difference > 0 ? -armorFactor : armorFactor)) * (ITFConfig.TagExtremeClimate.get() ? 0.33f : 1.0f);
        this.bodyTemperature -= toAdd;
        this.bodyTemperature += (float) (1E-4D * this.getUnit());
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
        boolean invincible = this.player.inCreativeMode() || EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentCallOfNether) || this.calcArmorHeat() == 16;

        int freezeLevel = invincible ? -1 : (int) ((normalTemperature - this.bodyTemperature) / 3.0F);

        if (freezeLevel <= 0) {
            if (this.freezingCoolDown > 0) {
                this.freezingCoolDown += freezeLevel - 1;
            }
        } else {
            this.freezingCoolDown += freezeLevel;
            if (this.freezingCoolDown > 6400 + this.calcArmorHeat() * 200) {
                this.player.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, 20 + (this.freezingCoolDown >> 3), this.player.isInRain() ? freezeLevel : (freezeLevel - 1)));
                this.freezingCoolDown = 0;
            }
        }

        if (freezeLevel >= 4) {
            this.freezingWarning++;
            this.player.triggerAchievement(AchievementExtend.hypothermia);
            if (this.freezingWarning > 500) {
                this.player.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, 20 + (this.freezingWarning >> 3), this.player.isInRain() ? freezeLevel : (freezeLevel - 1)));
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 4.0F));
                this.freezingWarning = 0;
            }
        } else if (this.freezingWarning > 0) {
            this.freezingWarning -= 1;
        }
    }


    private void checkHeat() {
        boolean invincible = this.player.inCreativeMode() || EnchantmentHelper.hasEnchantment(this.player.getCuirass(), Enchantments.enchantmentCallOfPolar);

        int heatLevel = invincible ? -1 : (int) ((this.bodyTemperature - normalTemperature) / 3.0F);

        if (heatLevel <= 0) {
            if (this.heatResistance > 0) {
                this.heatResistance += heatLevel - 1;
            }
        } else {
            this.heatResistance += heatLevel;
            if ((this.heatResistance > 6400 - this.calcArmorHeat() * 200)) {
                this.player.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 + (this.heatResistance >> 3), 1));
                this.heatResistance = 0;
            }
        }

        if (heatLevel >= 4) {
            this.heatWarning++;
            this.player.triggerAchievement(AchievementExtend.hyperthermia);
            if (this.heatWarning > 500) {
                this.player.attackEntityFrom(new Damage(DamageSourceExtend.heat, 4.0F));
                this.player.decreaseWaterServerSide(1.0F);
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
        if (this.player.isBurning()) result += 16;
        switch (dimensionId) {
            case -1 -> {
                return result + 32;
            }
            case 1 -> {
                return result - 2;
            }
            case -2 -> {
                return result + heightFactor(this.player.getBlockPosY(), 128) + (ITFConfig.TagDeadGeothermy.get() ? -2 : 0);
            }
        }

        boolean isOutdoors = this.player.isOutdoors();
        boolean isRaining = world.isPrecipitating(false);
        if (isRaining) {
            float rainStrength = world.getRainStrength(1.0f);
            result -= rainStrength * (isOutdoors ? 2.0f : 1.0f);
        }

        int time = world.getTimeOfDay();
        result += sunshine(time) * (isOutdoors ? 2.0f : 1.0f);

        result += seasonFactor(world.getDayOfWorld());
        result += biomeFactor(world.getBiomeGenForCoords(this.player.getBlockPosX(), this.player.getBlockPosZ()));
        result += heightFactor(this.player.getBlockPosY(), 64);

        return result;
    }

    public static double sunshine(int time) {
        return Math.sin(0.000261799387799 * (time - 6000));
    }

    public static double heightFactor(int y, int balance) {
        return y == balance ? 0.0d : (y > balance ? -1 : 1) * (balance - y) * (balance - y) * 0.001d;
    }

    public static double biomeFactor(BiomeGenBase biome) {
        return 2.0f * (biome.temperature - 0.8f);
    }

    public static double seasonFactor(int day) {
        return Math.sin(0.0490873852123 * (day - 16));
    }


    public int findFreezeSource() {
        World world = this.player.worldObj;
        int freeze = 0;
        if (this.player.getDrunkManager().isDrunk()) {
            freeze += 1;
        }
        if (this.player.isInWater()) {
            freeze += 8;
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
        int x = this.player.getBlockPosX();
        int y = this.player.getBlockPosY();
        int z = this.player.getBlockPosZ();
        int hottest = 0;
        for (int dx = -7; dx <= 7; dx++) {
            for (int dy = -7; dy <= 7; dy++) {
                for (int dz = -7; dz <= 7; dz++) {
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
