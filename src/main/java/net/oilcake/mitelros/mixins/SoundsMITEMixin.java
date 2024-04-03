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

@Mixin({SoundsMITE.class})
public abstract class SoundsMITEMixin {// TODO

    @Shadow protected abstract void loadMITESound(String path);

    @Shadow public boolean loaded;
    @Shadow
    private List<String> sounds = new ArrayList<>();


//    @ModifyExpressionValue(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/ResourcePack;resourceExists(Lnet/minecraft/ResourceLocation;)Z"))
//    private boolean inject(boolean original) {
//        return original || ;
//    }

    @Inject(method = "load", at = @At("HEAD"))
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!this.loaded) {
            this.loadMITESound("records/damnation.ogg");
            this.loadMITESound("records/connected.ogg");
            this.loadMITESound("sound/random/totem_use.ogg");
            this.loadMITESound("sound/meme/brainpower.ogg");
            this.loadMITESound("sound/mob/spiderking/hit1.ogg");
            this.loadMITESound("sound/mob/spiderking/hit1.ogg");
            this.loadMITESound("sound/mob/spiderking/hit1.ogg");
            this.loadMITESound("sound/mob/spiderking/hit1.ogg");
            this.loadMITESound("sound/mob/spiderking/death.ogg");
            this.loadMITESound("sound/mob/spiderking/say1.ogg");
            this.loadMITESound("sound/mob/spiderking/say2.ogg");
            this.loadMITESound("sound/mob/spiderking/say3.ogg");
            this.loadMITESound("sound/random/melting.ogg");
        }
    }


//    @Inject(method = {"<init>(Lnet/minecraft/SoundManager;)V"}, at = {@At("RETURN")})
//    public void injectCtor(CallbackInfo callbackInfo) {
//        add("records/damnation.ogg");
//        add("records/connected.ogg");
//        add("sound/random/totem_use.ogg");
//        add("sound/meme/brainpower.ogg");
//        add("sound/mob/spiderking/hit1.ogg");
//        add("sound/mob/spiderking/hit1.ogg");
//        add("sound/mob/spiderking/hit1.ogg");
//        add("sound/mob/spiderking/hit1.ogg");
//        add("sound/mob/spiderking/death.ogg");
//        add("sound/mob/spiderking/say1.ogg");
//        add("sound/mob/spiderking/say2.ogg");
//        add("sound/mob/spiderking/say3.ogg");
//        add("sound/random/melting.ogg");
////        add("records/imported/damnation.ogg");
////        add("records/imported/connected.ogg");
////        add("sound/imported/random/totem_use.ogg");
////        add("sound/imported/meme/brainpower.ogg");
////        add("sound/imported/mob/spiderking/hit1.ogg");
////        add("sound/imported/mob/spiderking/hit1.ogg");
////        add("sound/imported/mob/spiderking/hit1.ogg");
////        add("sound/imported/mob/spiderking/hit1.ogg");
////        add("sound/imported/mob/spiderking/death.ogg");
////        add("sound/imported/mob/spiderking/say1.ogg");
////        add("sound/imported/mob/spiderking/say2.ogg");
////        add("sound/imported/mob/spiderking/say3.ogg");
////        add("sound/imported/random/melting.ogg");
//    }

    @Shadow
    private boolean add(String path) {
        return this.sounds.add(path);
    }
}
