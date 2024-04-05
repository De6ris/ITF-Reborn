package net.oilcake.mitelros.mixins.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.minecraft.Packet8UpdateHealth;
import net.oilcake.mitelros.api.ITFPacket8;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Packet8UpdateHealth.class)
public abstract class Packet8UpdateHealthMixin extends Packet implements ITFPacket8 {
    public int water;

    public int phytonutrients;

    public int protein;

    @Shadow
    public float healthMP;

    @Shadow
    public int nutrition;

    @Shadow
    public int satiation;

    @Shadow
    public float vision_dimming;

    public Packet8UpdateHealthMixin(float health, int satiation, int nutrition, float vision_dimming) {
        this.healthMP = health;
        this.satiation = satiation;
        this.nutrition = nutrition;
        this.vision_dimming = vision_dimming;
    }

    public int setWater(int water) {
        return this.water = water;
    }

    @Inject(method = "readPacketData(Ljava/io/DataInput;)V", at = @At("RETURN"))
    private void injectReadPacketData(DataInput par1DataInput, CallbackInfo c) throws IOException {
        this.water = par1DataInput.readInt();
        this.protein = par1DataInput.readInt();
        this.phytonutrients = par1DataInput.readInt();
    }

    @Inject(method = "writePacketData(Ljava/io/DataOutput;)V", at = @At("RETURN"))
    private void injectWritePacketData(DataOutput par1DataOutput, CallbackInfo c) throws IOException {
        par1DataOutput.writeInt(this.water);
        par1DataOutput.writeInt(this.protein);
        par1DataOutput.writeInt(this.phytonutrients);
    }

    public void setPhytonutrients(int phytonutrients) {
        this.phytonutrients = phytonutrients;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    @Override
    public int getWater() {
        return this.water;
    }

    public int getPhytonutrients() {
        return this.phytonutrients;
    }

    public int getProtein() {
        return this.protein;
    }

    @ModifyReturnValue(method = "getPacketSize", at = @At("RETURN"))
    public int itfPackSize(int original) {
        return original + 12;
    }
}
