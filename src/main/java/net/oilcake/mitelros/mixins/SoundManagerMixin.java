package net.oilcake.mitelros.mixins;

import net.minecraft.GameSettings;
import net.minecraft.ResourceManager;
import net.minecraft.SoundManager;
import net.oilcake.mitelros.ModSoundManager;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(SoundManager.class)
public class SoundManagerMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addModSound(ResourceManager par1ResourceManager, GameSettings par2GameSettings, File par3File, CallbackInfo ci) {
        ModSoundManager.registerSounds(ReflectHelper.dyCast(this));
    }
}
