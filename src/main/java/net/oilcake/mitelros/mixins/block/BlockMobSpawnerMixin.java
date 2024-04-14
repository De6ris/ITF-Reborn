package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityCastleGuard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockMobSpawner.class})
public class BlockMobSpawnerMixin extends BlockContainer {
  protected BlockMobSpawnerMixin(int par1, Material par2Material, BlockConstants block_constants) {
    super(par1, par2Material, block_constants);
  }

  @Overwrite
  public int dropBlockAsEntityItem(BlockBreakInfo info) {
    this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, 15 + info.world.rand.nextInt(30));
    if (info.world.isUnderworld() && (double)info.world.rand.nextFloat() < 1) {
      EntityCastleGuard CustleGuard = new EntityCastleGuard(info.world);
      CustleGuard.setPosition(info.x, info.y, info.z);
      CustleGuard.refreshDespawnCounter(-9600);
      CustleGuard.entityFX(EnumEntityFX.summoned);
      CustleGuard.onSpawnWithEgg(null);
      info.world.spawnEntityInWorld(CustleGuard);
    }
    return 0;
  }
  
  @Shadow
  public TileEntity createNewTileEntity(World world) {
    return null;
  }
}
