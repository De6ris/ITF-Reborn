package net.oilcake.mitelros.mixins.util;

import net.minecraft.SoundsMITE;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(SoundsMITE.class)
public abstract class SoundsMITEMixin {// TODO register in another way
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
        add("sound/imported/random/warning.ogg");
    }

    @Shadow
    private boolean add(String path) {
        return this.sounds.add(path);
    }
}
