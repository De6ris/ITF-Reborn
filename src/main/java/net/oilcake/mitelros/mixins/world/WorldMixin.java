package net.oilcake.mitelros.mixins.world;

import net.minecraft.Debug;
import net.minecraft.*;
import net.oilcake.mitelros.api.ITFWorld;
import net.oilcake.mitelros.util.ITFConfig;
import org.spongepowered.asm.mixin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mixin({World.class})
public abstract class WorldMixin implements ITFWorld {
    @Shadow
    public abstract World getWorld();

    private static final double pi = Math.acos(-1.0D);

    @Mutable
    @Shadow
    @Final
    public WorldProvider provider;

    private static final int SPRING = 0;

    private static final int SUMMER = 1;

    private static final int AUTUMN = 2;

    private static final int WINTER = 3;

    public WorldMixin(WorldProvider provider) {
        this.provider = provider;
    }

    public Explosion newExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities, boolean b) {
        Explosion explosion = new Explosion(this.getWorld(), exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
        explosion.doExplosionA();
        explosion.affectedBlockPositions.clear();
        explosion.doExplosionB(false);
        return explosion;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final List generateWeatherEvents(int day) {
        if (!isOverworld())
            Debug.setErrorMessage("generateWeatherEvents: called for " + getDimensionName());
        List<WeatherEvent> events = new ArrayList();
        if (day < 2)
            return events;
        long first_tick_of_day = ((day - 1) * 24000 - 6000);
        Random random = new Random(getWorldCreationTime() + (getDimensionId() * 938473) + day);
        random.nextInt();
        for (int i = 0; i < 3 && random.nextInt(4) <= 0; i++) {
            int duration_static = 6000 * (((Boolean) ITFConfig.TagEternalRaining.get()) ? 6 : 1);
            int duration_random = random.nextInt(12000) * (((Boolean) ITFConfig.TagEternalRaining.get()) ? 2 : 1);
            int duration = duration_random + duration_static;
            duration = (int) (duration * getRainDurationModify(getWorldSeason()));
            WeatherEvent event = new WeatherEvent(first_tick_of_day + random.nextInt(24000), duration);
            if (!isHarvestMoon(event.start, true) && !isHarvestMoon(event.end, true) && !isHarvestMoon(event.start + 6000L, true) && !isHarvestMoon(event.end - 6000L, true) && !isBloodMoon(event.start, false) && !isBloodMoon(event.end, false) && !isBlueMoon(event.start, false) && !isBlueMoon(event.end, false))
                events.add(event);
        }
        if (isBloodMoon(first_tick_of_day + 6000L, false)) {
            WeatherEvent event = new WeatherEvent(first_tick_of_day + 5000L, 14000);
            event.setStorm(event.start, event.end);
            events.add(event);
        }
        return events;
    }

    @Shadow
    public static final boolean isBloodMoon(long unadjusted_tick, boolean exclusively_at_night) {
        return exclusively_at_night;
    }


    @Shadow
    public static final boolean isBlueMoon(long unadjusted_tick, boolean exclusively_at_night) {
        return exclusively_at_night;
    }


    @Shadow
    public static final boolean isHarvestMoon(long unadjusted_tick, boolean exclusively_at_night) {
        return exclusively_at_night;
    }

    @Shadow
    public final int getDimensionId() {
        return this.provider.dimensionId;
    }

    @Shadow
    private long getWorldCreationTime() {
        return 0L;
    }

    @Shadow
    private String getDimensionName() {
        return null;
    }

    @Shadow
    private boolean isOverworld() {
        return false;
    }

    @Shadow
    @Final
    public int getDayOfWorld() {
        return 0;
    }

    public int getWorldSeason() {
        return getSeasonType(getDayOfWorld());
    }

    public int getSeasonType(int day) {
        return day % 128 / 32;
    }

    public float getRainDurationModify(int Season) {
        switch (Season) {
            case 0:
                return 1.0F;
            case 1:
                return 2.25F;
            case 2:
                return 0.75F;
            case 3:
                return 0.5F;
        }
        Debug.setErrorMessage("getRainDurationModify: called for num " + Season + " for calculating. Use the default.");
        return 1.0F;
    }

    public float getSeasonGrowthModifier() {
        int day_in_row = getDayOfWorld();
        float growModifier = (float) Math.sin((day_in_row - 16) / 64.0D * pi);
        return growModifier;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final boolean canSnowAt(int par1, int par2, int par3) {
        BiomeGenBase var4 = getBiomeGenForCoords(par1, par3);
        float var5 = var4.getFloatTemperature();
        if (var5 > ((getWorldSeason() == 3) ? 1.0F : 0.15F))
            return false;
        if (par2 >= 0 && par2 < 256 && getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10) {
            int var6 = getBlockId(par1, par2 - 1, par3);
            int var7 = getBlockId(par1, par2, par3);
            Block block_below = Block.getBlock(var6);
            Block block = Block.getBlock(var7);
            if (block_below == Block.tilledField && block != Block.pumpkinStem)
                return true;
            if (var7 == 0 && Block.snow.isLegalAt(getWorld(), par1, par2, par3, 0) && var6 != Block.ice.blockID)
                return true;
        }
        return false;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public boolean isFreezing(int x, int z) {
        return ((getBiomeGenForCoords(x, z)).temperature <= ((getWorldSeason() == 3) ? 1.0F : 0.15F));
    }

    @Shadow
    @Final
    public int getSavedLightValue(EnumSkyBlock block, int par1, int par2, int par3) {
        return 0;
    }

    @Shadow
    public BiomeGenBase getBiomeGenForCoords(int par1, int par3) {
        return null;
    }

    @Shadow
    @Final
    public int getBlockId(int par1, int par2, int par3) {
        return 0;
    }
}
