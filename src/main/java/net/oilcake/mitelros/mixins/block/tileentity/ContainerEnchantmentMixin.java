package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import net.oilcake.mitelros.network.PacketEnchantmentInfo;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.ItemGoldenAppleLegend;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.*;
import java.util.List;
import java.util.Random;

@Mixin(ContainerEnchantment.class)
public abstract class ContainerEnchantmentMixin extends Container {
    @Shadow
    public IInventory tableInventory;
    @Shadow
    private int posX;

    @Shadow
    private int posY;

    @Shadow
    private int posZ;

    public ContainerEnchantmentMixin(EntityPlayer player) {
        super(player);
    }

    @Shadow
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }

    @Shadow
    private Random rand;

    @Shadow
    public int[] enchantLevels;

    @Inject(method = "onCraftMatrixChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/ContainerEnchantment;detectAndSendChanges()V"))
    private void sendPredicatePacket(IInventory par1IInventory, CallbackInfo ci) {
        if (this.world.isRemote) return;
        ItemStack itemStack = this.tableInventory.getStackInSlot(0);
        if (itemStack == null || ItemPotion.isBottleOfWater(itemStack) || ItemAppleGold.isUnenchantedGoldenApple(itemStack)) {
            return;
        }
        boolean predicated = (this.world.getBlock(this.posX, this.posY - 1, this.posZ) == Blocks.blockEnchantPredicator) || this.world.getBlock(this.posX, this.posY, this.posZ) == Blocks.magicTable;
        if (!predicated) return;
        boolean extended = itemStack.getMaterialForRepairs() == Materials.uru;
        int[] result = this.predict(this.rand, itemStack, this.enchantLevels, extended);
        System.out.println(extended + "extended");
        this.player.sendPacket(new PacketEnchantmentInfo(result));
    }

    @Unique
    public int[] predict(Random random, ItemStack itemStack, int[] levels, boolean extended) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(random);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int[] results = new int[12];
        for (int i = 0; i < 12; i++) {
            results[i] = -1;
        }

        Random copiedRandom;
        for (int line = 0; line < 3; line++) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream;
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                copiedRandom = (Random) objectInputStream.readObject();
                objectInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            List enchantmentList = EnchantmentHelper.buildEnchantmentList(copiedRandom, itemStack, levels[line]);
            if (enchantmentList == null) continue;

            boolean isBook = itemStack.itemID == Item.book.itemID;
            int onlyEnchantment = isBook ? copiedRandom.nextInt(enchantmentList.size()) : -1;
            if (isBook) {
                enchantmentList = List.of(enchantmentList.get(onlyEnchantment));
            }

            for (int index = 0; (index < enchantmentList.size()) && (index < (extended ? 2 : 1)); ++index) {
                EnchantmentData enchantmentData = (EnchantmentData) enchantmentList.get(index);
                results[line * 4 + 2 * index] = enchantmentData.enchantmentobj.effectId;
                results[line * 4 + 2 * index + 1] = enchantmentData.enchantmentLevel;
            }
        }
        return results;
    }

    @Inject(method = "enchantItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/ItemAppleGold;isUnenchantedGoldenApple(Lnet/minecraft/ItemStack;)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void itfApple(EntityPlayer par1EntityPlayer, int par2, CallbackInfoReturnable<Boolean> cir, ItemStack var3, int experience_cost) {
        if (ItemGoldenAppleLegend.isUnenchantedGoldenApple(var3)) {
            par1EntityPlayer.addExperience(-experience_cost);
            this.tableInventory.setInventorySlotContents(0, new ItemStack(Items.goldenAppleLegend, 1, 1));
            par1EntityPlayer.triggerAchievement(AchievementExtend.decimator);
            cir.setReturnValue(true);
        }
    }

    @ModifyArg(method = "calcEnchantmentLevelsForSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/EnchantmentHelper;getEnchantmentLevelsAlteredByItemEnchantability(ILnet/minecraft/Item;)I"))
    private int enhance(int enchantment_levels) {
        boolean enhanced = (this.world.getBlock(this.posX, this.posY - 1, this.posZ) == Blocks.blockEnchantEnhancer || this.world.getBlock(this.posX, this.posY, this.posZ) == Blocks.magicTable);
        return enchantment_levels * (enhanced ? 2 : 1);
    }
}
