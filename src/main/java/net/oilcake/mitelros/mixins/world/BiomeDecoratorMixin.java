package net.oilcake.mitelros.mixins.world;

import java.util.Random;

import net.minecraft.BiomeDecorator;
import net.minecraft.World;
import net.minecraft.WorldGenMinable;
import net.oilcake.mitelros.api.ITFBiomeDecorator;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.world.WorldGenFlowersExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDecorator.class)
public abstract class BiomeDecoratorMixin implements ITFBiomeDecorator {
    @Unique
    private WorldGenMinable nickelGen;

    @Unique
    private WorldGenMinable tungstenGen;

    @Unique
    private WorldGenMinable azuriteGen;

    @Unique
    protected WorldGenFlowersExtend flowerExtendGen;

    @Unique
    protected int flowersExtendPerChunk;

    @Shadow
    protected int waterlilyPerChunk;

    @Shadow
    protected int treesPerChunk;

    @Shadow
    protected int grassPerChunk;

    @Shadow
    protected int flowersPerChunk;

    @Shadow
    protected Random randomGenerator;

    @Shadow
    protected int chunk_X;

    @Shadow
    protected int chunk_Z;

    @Shadow
    protected World currentWorld;

    @Inject(method = "<init>(Lnet/minecraft/BiomeGenBase;)V", at = @At("RETURN"))
    public void BiomeDecorator(CallbackInfo callbackInfo) {
        this.nickelGen = new WorldGenMinable(Blocks.oreNickel.blockID, 6);
        this.tungstenGen = new WorldGenMinable(Blocks.oreTungsten.blockID, 3);
        this.azuriteGen = new WorldGenMinable(Blocks.blockAzurite.blockID, 4);
        this.flowerExtendGen = new WorldGenFlowersExtend(Blocks.flowerextend.blockID);
        this.flowersExtendPerChunk = 2;
    }

    @Inject(method = "generateOres", at = @At("HEAD"))
    private void itfOre(CallbackInfo ci) {
        if (this.currentWorld.isOverworld()) {
            genMinable(10, this.azuriteGen, true);
            genMinable(2, this.tungstenGen);
            genMinable(30, this.nickelGen, true);
        } else if (this.currentWorld.isUnderworld()) {
            genMinable(10, this.tungstenGen, true);
            genMinable(30, this.nickelGen, true);
        }
    }

    @Inject(method = "decorate(Lnet/minecraft/World;Ljava/util/Random;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/BiomeDecorator;decorate()V", shift = At.Shift.AFTER))
    private void Injector_world_decorate(CallbackInfo c) {
        Random random_test = new Random();
        for (int var2 = 0; var2 < this.flowersExtendPerChunk; var2++) {
            int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            int var4 = this.randomGenerator.nextInt(128);
            int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.flowerExtendGen.setMetadata(random_test.nextInt(8));
            this.flowerExtendGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
        }
    }

    @Shadow
    protected void genMinable(int frequency, WorldGenMinable world_gen_minable) {
    }

    @Shadow protected abstract void genMinable(int frequency, WorldGenMinable world_gen_minable, boolean vein_size_increases_with_depth);

    @Override
    public void setWaterlilyPerChunk(int waterlilyPerChunk) {
        this.waterlilyPerChunk = waterlilyPerChunk;
    }

    public void setTreesPerChunk(int treesPerChunk) {
        this.treesPerChunk = treesPerChunk;
    }

    public void setFlowersPerChunk(int flowersPerChunk) {
        this.flowersPerChunk = flowersPerChunk;
    }

    public void setGrassPerChunk(int grassPerChunk) {
        this.grassPerChunk = grassPerChunk;
    }

    public void setFlowersExtendPerChunk(int flowersPerChunk) {
        this.flowersExtendPerChunk = flowersPerChunk;
    }
}
