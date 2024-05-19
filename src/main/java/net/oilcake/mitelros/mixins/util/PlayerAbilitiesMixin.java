package net.oilcake.mitelros.mixins.util;

import net.minecraft.EntityPlayer;
import net.minecraft.PlayerCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerCapabilities.class)
public class PlayerAbilitiesMixin {

    @Shadow
    public EntityPlayer player;

    @ModifyConstant(method = "getWalkSpeed", constant = @Constant(floatValue = 0.75F))
    private float slowerIfHungry(float constant) {
        return 0.25F;
    }
}
