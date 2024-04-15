package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.api.ITFFoodStats;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ITFConfig;

public class WaterManager {

    private EntityPlayer player;

    int water_duration;

    private double dry_resist;

    public WaterManager(EntityPlayer player) {
        this.player = player;
    }

    public void update() {
        BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (player.getBlockAtFeet() != null && (player.getBlockAtFeet()).blockMaterial == Material.water && player.isSneaking()) {
            this.water_duration++;
        } else {
            this.water_duration = 0;
        }
        if (this.water_duration > 160) {
            this.water_duration = 0;
            if (biome == BiomeGenBase.swampRiver || biome == BiomeGenBase.swampland) {
                ((ITFFoodStats) player.getFoodStats()).addWater(1);
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
            } else if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
                ((ITFFoodStats) player.getFoodStats()).addWater(2);
            } else {
                ((ITFFoodStats) player.getFoodStats()).addWater(1);
                player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, 160, 0));
            }
        }
        this.dry_resist += (ITFConfig.TagHeatStroke.get() ? 2.0D : 1.0D) + biome.getFloatTemperature();
        if (player.isPotionActive(PotionExtend.dehydration))
            this.dry_resist += Math.min(80.0D, (player.getActivePotionEffect(PotionExtend.dehydration).getAmplifier() + 1) * 20.0D);
        if (player.isPotionActive(PotionExtend.thirsty))
            this.dry_resist += Math.min(80.0D, (player.getActivePotionEffect(PotionExtend.thirsty).getAmplifier() + 1) * 10.0D);
        if (this.dry_resist > 12800.0D) {
            ((ITFFoodStats) player.getFoodStats()).addWater(-1);
            this.dry_resist = 0.0D;
        }
    }
}
