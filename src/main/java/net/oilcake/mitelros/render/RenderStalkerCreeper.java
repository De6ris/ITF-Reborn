package net.oilcake.mitelros.render;

import net.minecraft.RenderCreeper;
import net.oilcake.mitelros.entity.mob.EntityStalkerCreeper;

public class RenderStalkerCreeper extends RenderCreeper {
  public RenderStalkerCreeper() {
    this.shadowSize *= this.scale = EntityStalkerCreeper.getScale();
  }
  
  public String getSubtypeName() {
    return "stalker_creeper";
  }
}
