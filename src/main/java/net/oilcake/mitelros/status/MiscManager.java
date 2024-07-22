package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.item.totem.ItemTotem;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.EnumQualityEffect;

import java.util.List;

import static net.oilcake.mitelros.ITFStart.MOD_ID;

public class MiscManager {
    private final EntityPlayer player;

    private int detectorDelay = 0;

    private byte knowledgeTotemCounter = 0;

    public byte getKnowledgeTotemCounter() {
        return knowledgeTotemCounter;
    }

    public void setKnowledgeTotemCounter(byte knowledgeTotemCounter) {
        this.knowledgeTotemCounter = knowledgeTotemCounter;
    }

    public void addKnowledgeTotemCounter() {
        this.knowledgeTotemCounter += 1;
    }

    public MiscManager(EntityPlayer player) {
        this.player = player;
    }

    public float getNickelArmorCoverage() {
        float coverage = 0.0F;
        ItemStack[] worn_items = this.player.getWornItems();
        for (ItemStack item_stack : worn_items) {
            if (item_stack != null)
                if (item_stack.isArmor()) {
                    ItemArmor itemArmor = item_stack.getItem().getAsArmor();
                    if (itemArmor.getArmorMaterial() == Materials.nickel)
                        coverage += itemArmor.getCoverage() * itemArmor.getDamageFactor(item_stack, this.player);
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
        if (ITFConfig.FinalChallenge.getBooleanValue())
            str_vs_block *= 1.0F - Constant.calculateCurrentDifficulty() / 100.0F;
        ItemStack held_item = player.getHeldItemStack();
        if (held_item != null && held_item.getItem() instanceof ItemTool) {
            str_vs_block *= 1.0F + (EnumQualityEffect.getQualityMultiplier(held_item.getQuality(), EnumQualityEffect.Digging) / 100.0F);
        }
        return str_vs_block;
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

    public float detectNearestMonstersDistance(List<Entity> targets) {
        float distance = -1.0F;
        for (Entity target : targets) {
            EntityMob entityMonster = target instanceof EntityMob ? (EntityMob) target : null;
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

    public EntityDamageResult totemCheckOnDeath(EntityDamageResult entityDamageResult) {
        if (entityDamageResult != null && player.getHealthFraction() <= 0.1D && !entityDamageResult.entityWasDestroyed()) {
            ItemStack var5 = player.getHeldItemStack();
            if (var5 != null && var5.getItem() instanceof ItemTotem totem) {
                entityDamageResult.entity_was_destroyed = false;
                totem.trigger(this.player, false);
                player.setHeldItemStack(null);
            }
        }
        return entityDamageResult;
    }

    public void wolfFurCheck() {
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

    public void iceChunkCheck() {
        boolean wearing_full_suit_ice_chunk = true;
        for (int i = 0; i < 4; ++i) {
            if (player.inventory.armorInventory[i] != null && player.inventory.armorInventory[i].getItem() instanceof ItemArmor armor) {
                Material material = armor.getArmorMaterial();
                if (material != Materials.ice_chunk) {
                    wearing_full_suit_ice_chunk = false;
                    break;
                }
            } else {
                wearing_full_suit_ice_chunk = false;
                break;
            }
        }
        if (wearing_full_suit_ice_chunk) {
            player.triggerAchievement(AchievementExtend.BravetheHeat);
        }
    }

    public void broadcast() {
        player.sendChatToPlayer(ChatMessageComponent.createFromText(MOD_ID + "挂载成功,当前版本:").setColor(EnumChatFormatting.BLUE)
                .appendComponent(ChatMessageComponent.createFromText(ITFStart.MOD_Version).setColor(EnumChatFormatting.YELLOW))
                .appendComponent(ChatMessageComponent.createFromText(",作者:Lee074,Huix,Kalsey,由Debris移植到高版本FML,现由Debris和Xy_Lose共同维护")));
        int difficulty = Constant.calculateCurrentDifficulty();
        if (difficulty != 0) {
            player.sendChatToPlayer(ChatMessageComponent.createFromText("[MITE-ITF]").appendComponent(getDifficultyMessage(difficulty)));
        }
    }

    public static ChatMessageComponent getDifficultyMessage(int difficulty) {
        return ChatMessageComponent.createFromText("当前难度：" + difficulty).setColor(getColor(difficulty));
    }

    private static EnumChatFormatting getColor(int difficulty) {
        return (difficulty >= 16) ? EnumChatFormatting.DARK_RED :
                ((difficulty >= 12) ? EnumChatFormatting.RED :
                        ((difficulty >= 8) ? EnumChatFormatting.YELLOW :
                                ((difficulty >= 4) ? EnumChatFormatting.GREEN :
                                        ((difficulty > 0) ? EnumChatFormatting.AQUA :
                                                EnumChatFormatting.BLUE))));
    }
}
