package net.oilcake.mitelros.mixins;

import java.util.ArrayList;
import java.util.List;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.ResourceLocation;
import net.minecraft.SoundPool;
import net.minecraft.SoundsMITE;
import net.minecraft.StatCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SoundsMITE.class)
public abstract class SoundsMITEMixin {// TODO
    @Shadow
    private List<String> sounds = new ArrayList<>();


    @Inject(method = {"<init>(Lnet/minecraft/SoundManager;)V"}, at = {@At("RETURN")})
    public void injectCtor(CallbackInfo callbackInfo) {
        add("records/imported/damnation.ogg");
        add("records/imported/connected.ogg");
        add("sound/imported/random/totem_use.ogg");
        add("sound/imported/meme/brainpower.ogg");
        add("sound/imported/mob/spiderking/hit1.ogg");
        add("sound/imported/mob/spiderking/hit1.ogg");
        add("sound/imported/mob/spiderking/hit1.ogg");
        add("sound/imported/mob/spiderking/hit1.ogg");
        add("sound/imported/mob/spiderking/death.ogg");
        add("sound/imported/mob/spiderking/say1.ogg");
        add("sound/imported/mob/spiderking/say2.ogg");
        add("sound/imported/mob/spiderking/say3.ogg");
        add("sound/imported/random/melting.ogg");
    }

    @Shadow
    private boolean add(String path) {
        return this.sounds.add(path);
    }
}
