package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.ItemTotem;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.CurseExtend;
import net.oilcake.mitelros.util.QualityHandler;

import java.util.List;

import static net.oilcake.mitelros.ITFStart.MOD_ID;

public class MiscManager {

    private EntityPlayer player;

    private int detectorDelay = 0;

    public MiscManager(EntityPlayer player) {
        this.player = player;
    }


    public float getNickelArmorCoverage() {
        float coverage = 0.0F;
        ItemStack[] worn_items = this.player.getWornItems();
        for (int i = 0; i < worn_items.length; i++) {
            ItemStack item_stack = worn_items[i];
            if (item_stack != null)
                if (item_stack.isArmor()) {
                    ItemArmor barding = item_stack.getItem().getAsArmor();
                    if (barding.getArmorMaterial() == Materials.nickel)
                        coverage += barding.getCoverage() * barding.getDamageFactor(item_stack, this.player);
                } else if (item_stack.getItem() instanceof ItemHorseArmor var6) {
                    if (var6.getArmorMaterial() == Materials.nickel)
                        coverage += var6.getCoverage();
                }
        }
        return coverage;
    }

    public float calculateITFStv(float str_vs_block) {
        if (player.isPotionActive(PotionExtend.freeze))
            str_vs_block *= 1.0F - (player.getActivePotionEffect(PotionExtend.freeze).getAmplifier() + 1) * 0.5F;
        if (ITFConfig.FinalChallenge.get())
            str_vs_block *= 1.0F - Constant.calculateCurrentDifficulty() / 100.0F;
        if (ITFConfig.Realistic.get())
            str_vs_block *= Math.min((float) Math.pow(player.getHealth(), 2.0D) / 25.0F, 1.0F);
        ItemStack held_item = player.getHeldItemStack();
        if (held_item != null && held_item.getItem() instanceof ItemTool) {
            str_vs_block *= 1.0F + (QualityHandler.getQualityAmplifier(held_item.getQuality()) * 3.0F) / 100.0F;
        }
        return str_vs_block;
    }

    public boolean itfLadder() {
        int x = MathHelper.floor_double(player.posX);
        int y = MathHelper.floor_double(player.boundingBox.minY);
        int z = MathHelper.floor_double(player.posZ);
        int var0 = player.worldObj.getBlockId(x, y, z);
        if (var0 == Block.ladder.blockID || var0 == Block.vine.blockID)
            return true;
        float yaw = player.rotationYaw % 360.0F;
        if (yaw < -45.0F)
            yaw += 360.0F;
        int towards = (int) ((yaw + 45.0F) % 360.0F) / 90;
        switch (towards) {
            case 0:
                z++;
                break;
            case 1:
                x--;
                break;
            case 2:
                z--;
                break;
            case 3:
                x++;
                break;
            default:
                Minecraft.setErrorMessage("isOnLadder: Undefined Facing : " + towards + ".");
                break;
        }
        Block block1 = player.worldObj.getBlock(x, y, z);
        Block block2 = player.worldObj.getBlock(x, y + 1, z);
        return ((player.fallDistance == 0.0F && block1 != null && block1.isSolid(0) && block2 == null) || (block2 != null && !block2.isSolid(0)));
    }

    public void detectorUpdate() {
        //探测器：绿宝石
        if (this.player.inventory.getHotbarSlotContainItem(Items.detectorEmerald) > 0) {
            if (detectorDelay < 80) {
                detectorDelay++;
            } else {
                detectorDelay = 0;
                List<Entity> targets = this.player.getNearbyEntities(16.0F, 8.0F);
                float range_div = Math.min(2.0F, 20.0F / this.detectNearestMonstersDistance(targets));
                if (range_div > 0.0F) {
                    this.player.makeSound("imported.random.warning", 0.3F, 1.0F + range_div);
                    detectorDelay += (int) (38.0F * range_div);
                }
            }
        }
        //探测器：钻石
        if (this.player.inventory.getHotbarSlotContainItem(Items.detectorDiamond) > 0) {
            if (detectorDelay < 80) {
                detectorDelay++;
            } else {
                detectorDelay = 0;
                List<Entity> targets = this.player.getNearbyEntities(28.0F, 12.0F);
                float range_div = Math.min(2.0F, 40.0F / this.detectNearestMonstersDistance(targets));
                if (range_div > 0.0F) {
                    this.player.makeSound("imported.random.warning", 0.3F, 1.0F + range_div);
                    detectorDelay += (int) (38.0F * range_div);
                }
            }
        }
    }

    public void curseUpdate() {
        if (player.hasCurse(CurseExtend.fear_of_light)) {
            float light_modifier = (18 - player.worldObj.getBlockLightValue(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY + player.yOffset), MathHelper.floor_double(player.posZ))) / 15.0F;
            if (light_modifier >= 0.5F || player.hasCurse(CurseExtend.fear_of_light, true)) ;
        }
        if (player.hasCurse(CurseExtend.fear_of_darkness)) {
            float light_modifier = (player.worldObj.getBlockLightValue(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY + player.yOffset), MathHelper.floor_double(player.posZ)) + 3) / 15.0F;
            if (light_modifier >= 0.5F || player.hasCurse(CurseExtend.fear_of_darkness, true)) ;
        }
    }

    public float detectNearestMonstersDistance(List<Entity> targets) {
        float distance = -1.0F;
        for (int i = 0; i < targets.size(); i++) {
            EntityMob entityMonster = targets.get(i) instanceof EntityMob ? (EntityMob) targets.get(i) : null;
            if (entityMonster != null) {
                if (distance < 0.0F) {
                    distance = (float) entityMonster.getDistanceSqToEntity(this.player);
                } else {
                    distance = Math.min(distance, (float) entityMonster.getDistanceSqToEntity(this.player));
                }
            }
        }
        return distance;
    }

    public EntityDamageResult destroyTotemCheck(EntityDamageResult entityDamageResult) {
        if (entityDamageResult != null && player.getHealthFraction() <= 0.1D && !entityDamageResult.entityWasDestroyed()) {
            ItemStack var5 = player.getHeldItemStack();
            if (var5 != null && var5.getItem() instanceof ItemTotem) {
                entityDamageResult.entity_was_destroyed = false;
                this.activeNegativeUndying();
                player.setHeldItemStack(null);
            }
        }
        return entityDamageResult;
    }

    public void activeNegativeUndying() {
        this.player.clearActivePotions();
        this.player.setHealth(this.player.getMaxHealth(), true, this.player.getHealFX());
        this.player.entityFX(EnumEntityFX.smoke_and_steam);
        this.player.makeSound("imported.random.totem_use", 3.0F, 1.0F + this.player.rand.nextFloat() * 0.1F);
        this.player.addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 4));
        this.player.vision_dimming += 0.75F;
        this.player.triggerAchievement(AchievementExtend.cheatdeath);
    }

    public void wolfFurCheck() { // TODO [Optional] add itf metal to wearAllPlateArmor check
        boolean wearing_full_suit_wolf_fur = true;
        for (int i = 0; i < 4; ++i) {
            if (player.inventory.armorInventory[i] != null && player.inventory.armorInventory[i].getItem() instanceof ItemArmor armor) {
                Material material = armor.getArmorMaterial();
                if (material != Materials.wolf_fur) {
                    wearing_full_suit_wolf_fur = false;
                    break;
                }
            } else {
                wearing_full_suit_wolf_fur = false;
                break;
            }
        }
        if (wearing_full_suit_wolf_fur) {
            player.triggerAchievement(AchievementExtend.BravetheCold);
        }
    }


    public void broadcast() {
        player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey(MOD_ID + "挂载成功,当前版本:").setColor(EnumChatFormatting.BLUE)
                .appendComponent(ChatMessageComponent.createFromText(ITFStart.Version).setColor(EnumChatFormatting.YELLOW))
                .appendComponent(ChatMessageComponent.createFromTranslationKey(",作者:Lee074,Huix,Kalsey,由Debris移植到高版本FML,现由Debris和Xy_Lose共同维护")));
        int difficulty = Constant.calculateCurrentDifficulty();
        if (difficulty != 0) {
            player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[MITE-ITF]")
                    .appendComponent(ChatMessageComponent.createFromTranslationKey("当前难度：" + difficulty)
                            .setColor((difficulty >= 16) ? EnumChatFormatting.DARK_RED :
                                    ((difficulty >= 12) ? EnumChatFormatting.RED :
                                            ((difficulty >= 8) ? EnumChatFormatting.YELLOW :
                                                    ((difficulty >= 4) ? EnumChatFormatting.GREEN :
                                                            ((difficulty > 0) ? EnumChatFormatting.AQUA :
                                                                    EnumChatFormatting.BLUE)))))));
        }
    }
}
