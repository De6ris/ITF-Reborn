package net.oilcake.mitelros.util;

import net.minecraft.Entity;
import net.minecraft.IEntitySelector;

public final class EntitySelectorHunter implements IEntitySelector {
  public boolean isEntityApplicable(Entity var1) {
    return (var1 instanceof net.minecraft.IMob && !(var1 instanceof net.oilcake.mitelros.entity.EntityUndeadGuard));
  }
}
