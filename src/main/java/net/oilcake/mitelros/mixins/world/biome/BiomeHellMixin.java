package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.mob.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeGenHell.class)
public abstract class BiomeHellMixin extends BiomeGenBase {
    protected BiomeHellMixin(int par1) {
        super(par1);
    }

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.spawnableMonsterList.add(new SpawnListEntry(EntityInfernalCreeper.class, 20, 1, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityDemonSpider.class, 20, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityHellhound.class, 20, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEvil.class, 50, 1, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPigmanLord.class, 5, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityPigmanGuard.class, 10, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpirit.class, 10, 1, 2));
        if (ITFConfig.TagDimensionInvade.getBooleanValue()) {
            this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderKing.class, 10, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityLongdeadGuardian.class, 40, 2, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityLongdead.class, 80, 4, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityAncientBoneLord.class, 20, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityStalkerCreeper.class, 50, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 40, 4, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherBoneLord.class, 10, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherBodyguard.class, 15, 2, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityBlackWidowSpider.class, 40, 4, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityWoodSpider.class, 60, 4, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityStray.class, 40, 2, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityHusk.class, 40, 2, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityCastleGuard.class, 20, 1, 2));
            this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityVampireBat.class, 20, 8, 8));
            this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityNightwing.class, 4, 1, 4));
        }
    }
}
