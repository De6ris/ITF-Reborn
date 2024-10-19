package net.oilcake.mitelros.mixins.world.biome;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.mob.*;
import net.oilcake.mitelros.registry.ITFRegistryImpl;
import net.oilcake.mitelros.world.ITFBiomes;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BiomeGenBase.class)
public abstract class BiomeGenBaseMixin {
    @Shadow
    protected List spawnableMonsterList;

    @Shadow
    protected List spawnableCreatureList;

    @Shadow
    @Final
    public int biomeID;

    @Shadow
    public float rainfall;

    public void RegenAnimals() {
        removeEntityFromSpawnableLists(EntityCow.class);
        removeEntityFromSpawnableLists(EntityChicken.class);
        removeEntityFromSpawnableLists(EntitySheep.class);
        removeEntityFromSpawnableLists(EntityPig.class);
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 5, 4, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 5, 4, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 4, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 5, 4, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityUnknown.class, 110, 0, 0));
    }

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.spawnableMonsterList.add(new SpawnListEntry(EntityRetinueZombie.class, (ITFConfig.TagFallenInMine.getIntegerValue() > 0) ? 35 : 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBoneBodyguard.class, (ITFConfig.TagBattleSuffer.getIntegerValue() > 0) ? 35 : 10, 4, 4));
        if (ITFConfig.TagDimensionInvade.get()) {
            this.spawnableMonsterList.add(new SpawnListEntry(EntityLongdead.class, 50, 4, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityLongdeadGuardian.class, 25, 2, 1));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityAncientBoneLord.class, 5, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 20, 4, 1));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityStalkerCreeper.class, 30, 2, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityInfernalCreeper.class, 20, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityDemonSpider.class, 20, 1, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityHellhound.class, 20, 1, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityEvil.class, 2, 1, 4));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 10, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderKing.class, 2, 1, 1));
//            this.spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 10, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherBoneLord.class, 1, 1, 1));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherBodyguard.class, 3, 1, 1));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityWitherBodyguard.class, 3, 1, 1));
            this.spawnableMonsterList.add(new SpawnListEntry(EntitySpirit.class, 5, 1, 1));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityPigmanGuard.class, 5, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityPigmanLord.class, 1, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityEvil.class, 20, 1, 2));
            this.spawnableMonsterList.add(new SpawnListEntry(EntityFireElemental.class, 2, 1, 2));
        }
        if (ITFConfig.TagCreaturesV2.get())
            RegenAnimals();
    }

    @Inject(method = "getSpawnableList", at = @At("HEAD"), cancellable = true)
    private void removeAnimalsThatProvidesMeat(EnumCreatureType par1EnumCreatureType, CallbackInfoReturnable<List> cir) {
        if (ITFConfig.TagApocalypse.getBooleanValue() && par1EnumCreatureType == EnumCreatureType.animal) {
            List original = this.spawnableCreatureList;
            for (Class<? extends Entity> meatAnimal : ITFRegistryImpl.meatAnimals) {
                this.removeEntityFromSpawnableList(original, meatAnimal);
            }
            cir.setReturnValue(original);
        }
    }

    @Shadow
    public void removeEntityFromSpawnableLists(Class _class) {
    }

    @Shadow
    public abstract void removeEntityFromSpawnableList(List list, Class _class);

    @Inject(method = "isHillyOrMountainous", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (this.biomeID == ITFBiomes.BIOME_WINDSWEPT_PLEATU.biomeID) {
            cir.setReturnValue(true);
        }
    }

    @ModifyExpressionValue(method = "canSpawnLightningBolt", at = @At(value = "FIELD", target = "Lnet/minecraft/BiomeGenBase;enableRain:Z", opcode = Opcodes.GETFIELD))
    private boolean inject(boolean original) {
        return original && this.rainfall != 0.0F;
    }
}
