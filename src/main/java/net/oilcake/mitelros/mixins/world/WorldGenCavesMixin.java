package net.oilcake.mitelros.mixins.world;

import net.minecraft.BiomeGenBase;
import net.minecraft.MapGenBase;
import net.minecraft.MapGenCaves;
import net.minecraft.World;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({MapGenCaves.class})
public class WorldGenCavesMixin extends MapGenBase {
  @Shadow
  protected void generateLargeCaveNode(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10) {}
  
  @Shadow
  private void generateCaveNode(long nextLong, int par4, int par5, byte[] par6ArrayOfByte, double var9, double var11, double var13, float var19, float var17, float var18, int i, int i1, double v) {}
  
  @Overwrite
  protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {
    float frequency;
    BiomeGenBase biome = par1World.getBiomeGenForCoords(par2 * 16, par3 * 16);
    if (biome != BiomeGenBase.plains && biome != BiomeGenBase.swampland) {
      if (biome == BiomeGenBase.iceMountains) {
        frequency = 1.5F;
      } else if (biome == BiomeGenBase.extremeHills) {
        frequency = 1.5F;
      } else if (biome == BiomeBases.BIOME_WINDSWEPT_PLEATU) {
        frequency = 2.5F;
      } else {
        frequency = 0.75F;
      } 
    } else {
      frequency = 0.5F;
    } 
    if (this.rand.nextInt((int)(15.0F / frequency)) == 0) {
      int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(30) + 1) + 1);
      var7 = (int)(var7 * frequency);
      boolean increase_frequency_of_larger_tunnels = (par1World.getWorldInfo().getEarliestMITEReleaseRunIn() >= 166);
      for (int var8 = 0; var8 < var7; var8++) {
        int rarity_of_large_tunnels;
        double var9 = (par2 * 16 + this.rand.nextInt(16));
        double var11 = this.rand.nextInt(this.rand.nextInt(120) + 8);
        double var13 = (par3 * 16 + this.rand.nextInt(16));
        int var15 = 1;
        if (!increase_frequency_of_larger_tunnels) {
          rarity_of_large_tunnels = 10;
        } else {
          rarity_of_large_tunnels = (var11 > 23.0D && var11 < 33.0D) ? 2 : 10;
        } 
        if (this.rand.nextInt(4) == 0) {
          generateLargeCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13);
          var15 += this.rand.nextInt(4);
        } 
        for (int var16 = 0; var16 < var15; var16++) {
          float var17 = this.rand.nextFloat() * 3.1415927F * 2.0F;
          float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
          float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
          if (this.rand.nextInt(rarity_of_large_tunnels) == 0) {
            var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
            if (rarity_of_large_tunnels < 10)
              var19 *= 1.0F + this.rand.nextFloat() * 0.5F; 
          } else if (increase_frequency_of_larger_tunnels && var11 < 41.0D && var11 > 15.0D && this.rand.nextInt(2) == 0) {
            var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 1.5F + 1.0F;
          } 
          generateCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
        } 
      } 
    } 
  }
}
