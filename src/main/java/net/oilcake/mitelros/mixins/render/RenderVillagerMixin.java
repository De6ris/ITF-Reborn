package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RenderVillager.class)
public abstract class RenderVillagerMixin extends RenderLiving {
    public RenderVillagerMixin(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected ResourceLocation func_110902_a(EntityVillager par1EntityVillager) {
        return switch (par1EntityVillager.getProfession()) {
            case 1, 2, 3, 4, 5, 6 -> this.textures[1];
            case 7, 8 -> this.textures[2];
            case 9, 10 -> this.textures[3];
            case 11, 12 -> this.textures[4];
            case 13, 14 -> this.textures[5];
            default -> this.textures[0];
        };
    }
}
