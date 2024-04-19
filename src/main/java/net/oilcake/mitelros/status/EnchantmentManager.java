package net.oilcake.mitelros.status;

import net.minecraft.*;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.potion.PotionExtend;

import java.util.List;

public class EnchantmentManager {
    private EntityPlayer player;

    public EnchantmentManager(EntityPlayer player) {
        this.player = player;
    }

    public void thresh(EntityLivingBase entity_living_base) {
        ItemStack[] item_stack_to_drop = entity_living_base.getWornItems();
        int rand = player.rand.nextInt(item_stack_to_drop.length);
        if (item_stack_to_drop[rand] != null && player.rand.nextFloat() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentThresher, player.getHeldItemStack()) && entity_living_base instanceof EntityLiving entity_living) {
            entity_living.dropItemStack(item_stack_to_drop[rand], entity_living.height / 2.0F);
            entity_living.clearMatchingEquipmentSlot(item_stack_to_drop[rand]);
            entity_living.ticks_disarmed = 40;
        }
    }

    public void destroy(Entity target) {
        if (player.isPotionActive(PotionExtend.stunning)) {
            return;
        }
        ItemStack heldItemStack = player.getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentDestroying)) {
            int destorying = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, heldItemStack);
            target.worldObj.createExplosion(player, target.posX, target.posY, target.posZ, 0.0F, destorying * 0.5F, true);
        }
    }

    public void sweep(float damage) {
        ItemStack heldItemStack = player.getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentSweeping)) {
            List targets = player.getNearbyEntities(5.0F, 5.0F);
            this.attackMonsters(targets, damage * EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentSweeping, heldItemStack));
        }
    }

    public void attackMonsters(List targets, float damage) {
        for (int i = 0; i < targets.size(); i++) {
            EntityLivingBase entityMonster = (targets.get(i) instanceof EntityLivingBase) ? (EntityLivingBase) targets.get(i) : null;
            if (entityMonster != null
                    && entityMonster.getDistanceSqToEntity(player) <= player.getReach(EnumEntityReachContext.FOR_MELEE_ATTACK, entityMonster)
                    && entityMonster.canEntityBeSeenFrom(player.posX, player.getEyePosY(), player.posZ, 5.0D))
                entityMonster.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(player), damage));
        }
    }

    public void arroganceUpdate() {
        if (this.underArrogance()) {
            player.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));
        }
    }

    public boolean underArrogance() {
        boolean Hel_Arro = false;
        boolean Cst_Arro = false;
        boolean Lgs_Arro = false;
        boolean Bts_Arro = false;
        boolean Hnd_Arro = false;
        ItemStack Helmet = player.getHelmet();
        ItemStack Cuirass = player.getCuirass();
        ItemStack Leggings = player.getLeggings();
        ItemStack Boots = player.getBoots();
        ItemStack Holding = player.getHeldItemStack();
        if (Helmet != null)
            Hel_Arro = EnchantmentHelper.hasEnchantment(Helmet, Enchantments.enchantmentArrogance);
        if (Cuirass != null)
            Cst_Arro = EnchantmentHelper.hasEnchantment(Cuirass, Enchantments.enchantmentArrogance);
        if (Leggings != null)
            Lgs_Arro = EnchantmentHelper.hasEnchantment(Leggings, Enchantments.enchantmentArrogance);
        if (Boots != null)
            Bts_Arro = EnchantmentHelper.hasEnchantment(Boots, Enchantments.enchantmentArrogance);
        if (Holding != null)
            Hnd_Arro = EnchantmentHelper.hasEnchantment(Holding, Enchantments.enchantmentArrogance);
        boolean Arro = (Hel_Arro || Cst_Arro || Lgs_Arro || Bts_Arro || Hnd_Arro);
        return (this.player.experience < 2300 && Arro);
    }

    public void mendingUpdate() {
        ItemStack holding = player.getHeldItemStack();
        if (holding != null && this.willRepair(holding) &&
                (float) holding.getRemainingDurability() / holding.getMaxDamage() < 0.5F && player.getExperienceLevel() >= 10 + 15 * holding.getItem().getHardestMetalMaterial().min_harvest_level) {
            player.addExperience(-holding.getMaxDamage() / 8, false, true);
            holding.setItemDamage(holding.getItemDamage() - holding.getMaxDamage() / 8);
        }
        ItemStack[] item_stack_to_repair = player.getWornItems();
        for (ItemStack itemStack : item_stack_to_repair) {
            if (itemStack != null && willRepair(itemStack) &&
                    (float) itemStack.getRemainingDurability() / itemStack.getMaxDamage() < 0.5F && player.getExperienceLevel() >= 10 + 15 * itemStack.getItem().getHardestMetalMaterial().min_harvest_level) {
                player.addExperience(-12, false, true);
                itemStack.setItemDamage(itemStack.getItemDamage() - 1);
            }
        }
    }

    public void lightMendingUpdate() {
        if (this.player.isInSunlight()) {
            ItemStack holding = this.player.getHeldItemStack();
            if (holding != null && this.sunlight(holding)) {
                holding.setItemDamage(holding.getItemDamage() - 1);
            }
            ItemStack[] item_stack_to_repair = this.player.getWornItems();
            for (ItemStack itemStack : item_stack_to_repair) {
                if (itemStack != null && this.sunlight(itemStack) && this.player.rand.nextInt(200) == 0) {
                    itemStack.setItemDamage(itemStack.getItemDamage() - 1);
                }
            }
        } else {
            World world = this.player.worldObj;
            int x = MathHelper.floor_double(this.player.posX);
            int y = MathHelper.floor_double(this.player.posY);
            int z = MathHelper.floor_double(this.player.posZ);
            if (!world.isDaytime() && world.canBlockSeeTheSky(x, y, z)) {
                ItemStack holding = this.player.getHeldItemStack();
                if (holding != null && this.moonlight(holding)) {
                    holding.setItemDamage(holding.getItemDamage() - 1);
                }
                ItemStack[] item_stack_to_repair = this.player.getWornItems();
                for (ItemStack itemStack : item_stack_to_repair) {
                    if (itemStack != null && this.moonlight(itemStack)) {
                        itemStack.setItemDamage(itemStack.getItemDamage() - 1);
                    }
                }
            }
        }
    }

    public int onAddingEXP(int amount) {
        if (amount <= 0) {
            return amount;
        } else {
            int before = amount;
            ItemStack holding = player.getHeldItemStack();
            if (holding != null && this.willRepair(holding))
                for (; player.getHeldItemStack().getItemDamage() >= 16 && amount > 0; amount--)
                    player.getHeldItemStack().setItemDamage(holding.getItemDamage() - 16);
            player.addScore(before - amount);
            return amount;
        }
    }

    public boolean sunlight(ItemStack holding) {
        return EnchantmentHelper.hasEnchantment(holding, Enchantments.enchantmentSunlightMending);
    }

    public boolean moonlight(ItemStack holding) {
        return EnchantmentHelper.hasEnchantment(holding, Enchantments.enchantmentMoonlightMending);
    }

    public boolean willRepair(ItemStack holding) {
        return EnchantmentHelper.hasEnchantment(holding, Enchantments.enchantmentMending);
    }

    public static void vanish(InventoryPlayer inventory) {
        int var1;
        for (var1 = 0; var1 < inventory.mainInventory.length; var1++) {
            if (inventory.mainInventory[var1] != null && EnchantmentHelper.hasEnchantment(inventory.mainInventory[var1], Enchantments.enchantmentVanishing)) {
                inventory.destroyInventoryItemStack(inventory.mainInventory[var1]);
                inventory.mainInventory[var1] = null;
            }
        }
        for (var1 = 0; var1 < inventory.armorInventory.length; var1++) {
            if (inventory.armorInventory[var1] != null && EnchantmentHelper.hasEnchantment(inventory.armorInventory[var1], Enchantments.enchantmentVanishing)) {
                inventory.destroyInventoryItemStack(inventory.armorInventory[var1]);
                inventory.armorInventory[var1] = null;
            }
        }
        inventory.player.sendPacket(new Packet85SimpleSignal(EnumSignal.clear_inventory));
    }
}
