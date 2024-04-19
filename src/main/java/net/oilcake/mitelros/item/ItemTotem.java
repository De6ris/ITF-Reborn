package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.util.AchievementExtend;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.entity.EntityLongdeadSentry;

public class ItemTotem extends Item {
    public ItemTotem(int id, Material material, String texture) {
        super(id, material, texture);
        setMaxStackSize(16);
        setCraftingDifficultyAsComponent(100.0F);
        setCreativeTab(CreativeTabs.tabTools);
    }

    private void performEffectCommon(EntityPlayer player, ItemTotem totem) {
        player.clearActivePotions();
        player.setHealth(Math.max(player.getHealth(), 2.0F), true, player.getHealFX());
        player.makeSound("imported.random.totem_use", 3.0F, 1.0F + player.rand.nextFloat() * 0.1F);
        player.addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 4));
        player.vision_dimming = 1.0F;
    }

    private void performEffectSpecified(EntityPlayer player, ItemTotem totem) {
        Material totem_material = totem.getHardestMetalMaterial();
        if (totem_material == Material.gold) {
            player.setHealth(player.getMaxHealth(), true, player.getHealFX());
            for (int i = 0; i < 8; i++)
                player.entityFX(EnumEntityFX.heal);
        } else if (totem_material == Material.ancient_metal) {
            for (int i = 0; i < 8; i++)
                player.entityFX(EnumEntityFX.curse_effect_learned);
            player.addExperience(Math.min(player.experience / 5, 30000));
        } else if (totem_material == Material.iron) {
            for (int i = 0; i < 8; i++)
                player.entityFX(EnumEntityFX.smoke_and_steam);
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 400, (int) ((1.0F - player.getHealthFraction()) * 4.0F)));
        } else if (totem_material == Materials.nickel) {
            for (int i = 0; i < 8; i++)
                player.entityFX(EnumEntityFX.summoned);
            ((ITFPlayer) player).getHuntManager().setHunt_counter(400);
            ((ITFPlayer) player).getHuntManager().hunt_cap = true;
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 400, (int) ((1.0F - player.getHealthFraction()) * 2.0F)));
        } else if (totem_material == Materials.tungsten) {
            float delta = player.getHealthFraction() - 0.5F;
            for (int i = 0; i < 8; i++)
                player.entityFX(EnumEntityFX.smoke);
            player.worldObj.createExplosion((Entity) player, player.posX, player.posY + 1.5D, player.posZ, 0.0F, 4.0F - 4.0F * delta, true);
            player.setHealth(player.getMaxHealth() / 2.0F, true, player.getHealFX());
        } else if (totem_material == Materials.adamantium) {
            EntityLongdeadSentry sentry = new EntityLongdeadSentry(player.worldObj);
            sentry.setPosition(player.posX, player.posY, player.posZ);
            sentry.refreshDespawnCounter(-9600);
            player.worldObj.spawnEntityInWorld(sentry);
            sentry.onSpawnWithEgg(null);
            sentry.entityFX(EnumEntityFX.summoned);
        } else if (totem_material == Material.rusted_iron) {
            for (int i = 0; i < 8; i++) {
                player.entityFX(EnumEntityFX.smoke_and_steam);
            }
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 400, 10));
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 127));
        } else {
            Minecraft.setErrorMessage("effectSpecified(): Undefined Material " + totem_material.toString() + ".");
        }
    }

    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        ItemStack totem = player.getHeldItemStack();
        if (totem.getItem() instanceof ItemTotem) {
            performEffectCommon(player, (ItemTotem) totem.getItem());
            performEffectSpecified(player, (ItemTotem) totem.getItem());
            player.convertOneOfHeldItem((ItemStack) null);
            return true;
        }
        return false;
    }

    public void performNegativeEffect(EntityPlayer player) {
        ItemStack totem = player.getHeldItemStack();
        if (totem.getItem() instanceof ItemTotem) {
            performEffectCommon(player, (ItemTotem) totem.getItem());
            performEffectSpecified(player, (ItemTotem) totem.getItem());
            player.triggerAchievement((StatBase) AchievementExtend.cheatdeath);
            player.convertOneOfHeldItem((ItemStack) null);
        }
    }
}
