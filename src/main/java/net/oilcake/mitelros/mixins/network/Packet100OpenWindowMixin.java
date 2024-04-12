package net.oilcake.mitelros.mixins.network;

import net.minecraft.EntityClientPlayerMP;
import net.minecraft.InventoryBasic;
import net.minecraft.Packet100OpenWindow;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Packet100OpenWindow.class})
public class Packet100OpenWindowMixin {
    @Shadow
    public int inventoryType;

    @Shadow
    public int slotsCount;

    @Shadow
    public boolean useProvidedWindowTitle;

    @Shadow
    public int windowId;

    @Shadow
    public String windowTitle;

    @Shadow
    public int x;

    @Shadow
    public int y;

    @Shadow
    public int z;

    @ModifyArg(method = "readPacketData", at = @At(value = "INVOKE", target = "Lnet/minecraft/Packet100OpenWindow;readString(Ljava/io/DataInput;I)Ljava/lang/String;"), index = 1)
    private int inject(int par2) {
        return 32767;
    }

    @Inject(method = "handleOpenWindow", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;setErrorMessage(Ljava/lang/String;)V"), cancellable = true)
    private void inject(EntityClientPlayerMP player, CallbackInfo ci) {
        if (this.inventoryType == 14) {
            ((ITFPlayer)player).displayGUIEnchantReserver(this.x, this.y, this.z, new EnchantReserverSlots(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
            player.openContainer.windowId = this.windowId;
            ci.cancel();
        }
    }
}
