package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.AchievementExtend;

public class FeastManager {
    private final EntityPlayer player;
    public boolean Feast_trigger_salad;

    public boolean Feast_trigger_porridge;

    public boolean Feast_trigger_beef_stew;

    public boolean Feast_trigger_cereal;

    public boolean Feast_trigger_chicken_soup;

    public boolean Feast_trigger_mushroom_soup;

    public boolean Feast_trigger_cream_mushroom_soup;

    public boolean Feast_trigger_vegetable_soup;

    public boolean Feast_trigger_cream_vegetable_soup;

    public boolean Feast_trigger_ice_cream;

    public boolean Feast_trigger_chestnut_soup;

    public boolean Feast_trigger_lemonade;

    public boolean Feast_trigger_mashed_potatoes;

    public boolean Feast_trigger_porkchop_stew;

    public boolean Feast_trigger_pumpkin_soup;

    public boolean Feast_trigger_sorbet;

    public boolean Feast_trigger_salmon_soup;

    public boolean Feast_trigger_beetroot_soup;

    public boolean rewarded_disc_damnation;

    public boolean rewarded_disc_connected;

    public FeastManager(EntityPlayer player) {
        this.player = player;
        this.Feast_trigger_salad = false;
        this.Feast_trigger_porridge = false;
        this.Feast_trigger_beef_stew = false;
        this.Feast_trigger_cereal = false;
        this.Feast_trigger_chicken_soup = false;
        this.Feast_trigger_mushroom_soup = false;
        this.Feast_trigger_cream_mushroom_soup = false;
        this.Feast_trigger_vegetable_soup = false;
        this.Feast_trigger_cream_vegetable_soup = false;
        this.Feast_trigger_ice_cream = false;
        this.Feast_trigger_chestnut_soup = false;
        this.Feast_trigger_lemonade = false;
        this.Feast_trigger_mashed_potatoes = false;
        this.Feast_trigger_porkchop_stew = false;
        this.Feast_trigger_pumpkin_soup = false;
        this.Feast_trigger_sorbet = false;
        this.Feast_trigger_salmon_soup = false;
        this.Feast_trigger_beetroot_soup = false;
        this.rewarded_disc_damnation = false;
        this.rewarded_disc_connected = false;
    }

    public void achievementCheck() {
        this.feastCheck();
        this.potionCheck();
    }

    private void feastCheck() {
        boolean success = (this.Feast_trigger_sorbet && this.Feast_trigger_cereal && this.Feast_trigger_chestnut_soup && this.Feast_trigger_chicken_soup && this.Feast_trigger_beef_stew && this.Feast_trigger_cream_mushroom_soup && this.Feast_trigger_cream_vegetable_soup && this.Feast_trigger_ice_cream && this.Feast_trigger_lemonade && this.Feast_trigger_mashed_potatoes && this.Feast_trigger_porkchop_stew && this.Feast_trigger_salad && this.Feast_trigger_pumpkin_soup && this.Feast_trigger_porridge && this.Feast_trigger_mushroom_soup && this.Feast_trigger_vegetable_soup && this.Feast_trigger_salmon_soup && this.Feast_trigger_beetroot_soup && !this.rewarded_disc_damnation);
        if (!success) {
            return;
        }
        player.triggerAchievement(AchievementExtend.feast);
        player.addExperience(2500);
        this.rewarded_disc_damnation = true;
        EntityItem RewardingRecord = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(Items.recordDamnation.itemID, 1));
        player.worldObj.spawnEntityInWorld(RewardingRecord);
        RewardingRecord.entityFX(EnumEntityFX.summoned);
    }

    private void potionCheck() {
        if (player.isPotionActive(Potion.moveSpeed) && player.isPotionActive(Potion.regeneration) &&
                player.isPotionActive(Potion.fireResistance) && player.isPotionActive(Potion.nightVision) &&
                player.isPotionActive(Potion.damageBoost) && player.isPotionActive(Potion.resistance) &&
                player.isPotionActive(Potion.invisibility) && !this.rewarded_disc_connected) {
            player.triggerAchievement(AchievementExtend.invincible);
            player.addExperience(2500);
            this.rewarded_disc_connected = true;
            EntityItem RewardingRecord2 = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(Items.recordConnected.itemID, 1));
            player.worldObj.spawnEntityInWorld(RewardingRecord2);
            RewardingRecord2.entityFX(EnumEntityFX.summoned);
        }
    }

    public void update(ItemVessel itemVessel) {
        if (itemVessel.contains(Material.beef_stew))
            this.Feast_trigger_beef_stew = true;
        if (itemVessel.contains(Material.chicken_soup))
            this.Feast_trigger_chicken_soup = true;
        if (itemVessel.contains(Material.cereal))
            this.Feast_trigger_cereal = true;
        if (itemVessel.contains(Materials.lampchop_stew))
            this.Feast_trigger_chestnut_soup = true;
        if (itemVessel.contains(Material.ice_cream))
            this.Feast_trigger_ice_cream = true;
        if (itemVessel.contains(Materials.lemonade))
            this.Feast_trigger_lemonade = true;
        if (itemVessel.contains(Material.mashed_potato))
            this.Feast_trigger_mashed_potatoes = true;
        if (itemVessel.contains(Material.mushroom_stew))
            this.Feast_trigger_mushroom_soup = true;
        if (itemVessel.contains(Material.cream_of_mushroom_soup))
            this.Feast_trigger_cream_mushroom_soup = true;
        if (itemVessel.contains(Material.cream_of_vegetable_soup))
            this.Feast_trigger_cream_vegetable_soup = true;
        if (itemVessel.contains(Material.porridge))
            this.Feast_trigger_porridge = true;
        if (itemVessel.contains(Materials.porkchop_stew))
            this.Feast_trigger_porkchop_stew = true;
        if (itemVessel.contains(Material.pumpkin_soup))
            this.Feast_trigger_pumpkin_soup = true;
        if (itemVessel.contains(Material.sorbet))
            this.Feast_trigger_sorbet = true;
        if (itemVessel.contains(Material.salad))
            this.Feast_trigger_salad = true;
        if (itemVessel.contains(Material.vegetable_soup))
            this.Feast_trigger_vegetable_soup = true;
        if (itemVessel.contains(Materials.fish_soup))
            this.Feast_trigger_salmon_soup = true;
        if (itemVessel.contains(Materials.beetroot_soup))
            this.Feast_trigger_beetroot_soup = true;
    }
}
