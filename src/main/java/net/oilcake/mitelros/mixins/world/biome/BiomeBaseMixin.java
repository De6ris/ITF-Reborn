package net.oilcake.mitelros.mixins.world.biome;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.entity.EntityUnknown;
import net.oilcake.mitelros.util.Config;
import net.oilcake.mitelros.world.BiomeBases;
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
public class BiomeBaseMixin {
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

    public void DisgenAnimals() {
        removeEntityFromSpawnableLists(EntityCow.class);
        removeEntityFromSpawnableLists(EntityChicken.class);
        removeEntityFromSpawnableLists(EntitySheep.class);
        removeEntityFromSpawnableLists(EntityPig.class);
        removeEntityFromSpawnableLists(EntityHorse.class);
    }

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.spawnableMonsterList.add(new SpawnListEntry(EntityRetinueZombie.class, (Config.TagFallenInMineLVL2.get() || Config.TagFallenInMineLVL1.get()) ? 35 : 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBoneBodyguard.class, (Config.TagBattleSufferLVL2.get() || Config.TagBattleSufferLVL1.get()) ? 35 : 10, 4, 4));
        if (Config.TagCreaturesV2.get())
            RegenAnimals();
        if (Config.TagApocalypse.get())
            DisgenAnimals();
    }

    @Shadow
    public void removeEntityFromSpawnableLists(Class _class) {
    }

    @Inject(method = "isHillyOrMountainous", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (this.biomeID == BiomeBases.BIOME_WINDSWEPT_PLEATU.biomeID) {
            cir.setReturnValue(true);
        }
    }

    @ModifyExpressionValue(method = "canSpawnLightningBolt", at = @At(value = "FIELD", target = "Lnet/minecraft/BiomeGenBase;enableRain:Z", opcode = Opcodes.GETFIELD))
    private boolean inject(boolean original) {
        return original && this.rainfall != 0.0F;
    }
}
