package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.AchievementExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlotCrafting.class)
public abstract class SlotResultMixin extends Slot {
    @Shadow
    private EntityPlayer thePlayer;

    public SlotResultMixin(IInventory inventory, int slot_index, int display_x, int display_y) {
        super(inventory, slot_index, display_x, display_y);
    }

    @Inject(method = "onCrafting(Lnet/minecraft/ItemStack;)V", at = @At("HEAD"))
    private void inject(ItemStack par1ItemStack, CallbackInfo ci) {
        Item item = par1ItemStack.getItem();
        Block block = (item instanceof ItemBlock) ? ((ItemBlock) item).getBlock() : null;
        if (item == Items.LeggingsAncientmetalsacred || item == Items.ChestplateAncientmetalsacred || item == Items.HelmetAncientmetalsacred || item == Items.BootsAncientmetalsacred)
            this.thePlayer.addStat(AchievementExtend.forgingLegend, 1);
        if (item == Items.forgingnote)
            this.thePlayer.addStat(AchievementExtend.copying, 1);
        if (item == Items.bowTungsten)
            this.thePlayer.addStat(AchievementExtend.Arbalistic, 1);
        if (block == Block.beacon)
            this.thePlayer.addStat(AchievementExtend.getBeacon, 1);
        if (item == Items.mashedCactus)
            this.thePlayer.addStat(AchievementList.seeds, 1);
        if (item == Items.glowberries || item == Items.Agave)
            this.thePlayer.addStat(AchievementExtend.mashedCactus, 1);
        if (item == Items.Pulque || item == Items.Ale)
            this.thePlayer.addStat(AchievementExtend.cheersforMinecraft, 1);
        if (item == Items.experimentalPotion)
            this.thePlayer.addStat(AchievementExtend.nochoice, 1);
    }
}
