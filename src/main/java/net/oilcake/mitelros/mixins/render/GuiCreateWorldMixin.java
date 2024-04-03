package net.oilcake.mitelros.mixins.render;

import java.io.FileWriter;
import java.util.Date;
import java.util.Random;

import net.minecraft.EnumGameType;
import net.minecraft.GuiButton;
import net.minecraft.GuiCreateWorld;
import net.minecraft.GuiTextField;
import net.minecraft.I18n;
import net.minecraft.MITEConstant;
import net.minecraft.MathHelper;
import net.minecraft.StatList;
import net.minecraft.WorldSettings;
import net.minecraft.WorldType;
import net.minecraft.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiCreateWorld.class})
public class GuiCreateWorldMixin extends GuiScreen {// TODO itfWorldGen
    @Shadow
    private GuiButton buttonCustomize;
    @Shadow
    private GuiButton buttonWorldType;
    @Shadow
    private GuiButton buttonGenerateStructures;

    @Inject(method = "initGui", at = @At("TAIL"))
    public void inject(CallbackInfo ci) {
        this.buttonGenerateStructures.enabled = false;
        this.buttonWorldType.enabled = true;
        this.buttonCustomize.enabled = false;
    }
}
