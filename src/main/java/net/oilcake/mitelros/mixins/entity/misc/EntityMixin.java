package net.oilcake.mitelros.mixins.entity.misc;

import net.minecraft.Damage;
import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin {
    @Redirect(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/DebugAttack;start(Lnet/minecraft/Entity;Lnet/minecraft/Damage;)V"))
    private void noAttackInfo(Entity target, Damage damage) {
    }
}
