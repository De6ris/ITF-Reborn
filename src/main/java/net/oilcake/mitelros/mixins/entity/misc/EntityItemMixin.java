package net.oilcake.mitelros.mixins.entity.misc;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {
    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @ModifyArg(method = "spentTickInWater", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemVessel;getPeerForContents(Lnet/minecraft/Material;)Lnet/minecraft/ItemVessel;", ordinal = 1))
    private Material itfWater(Material var1) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(getBlockPosX(), getBlockPosZ());
        if (biome == BiomeGenBase.river || biome == BiomeGenBase.desertRiver) {
            return Materials.pure_water;
        } else {
            return Material.water;
        }
    }

    @Inject(method = "convertItem", at = @At("HEAD"), cancellable = true)
    private void checkNull(Item item, CallbackInfo ci) {
        if (this.worldObj.isRemote) {
            Minecraft.setErrorMessage("convertItem: not meant to be called on client");
            ci.cancel();
            return;
        }
        if (item == null) {
            this.setDead();
            this.entityFX(EnumEntityFX.burned_up_in_lava);
            ci.cancel();
        }
    }
}
