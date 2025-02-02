package net.oilcake.mitelros.status;

import moddedmite.rustedironcore.random.RandomUtil;
import net.minecraft.*;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.potion.PotionExtend;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class EnchantmentManager {
    private final EntityPlayer player;

    public EnchantmentManager(EntityPlayer player) {
        this.player = player;
    }

    public void update() {
        this.arroganceUpdate();
        this.mendingUpdate();
        this.lightMendingUpdate();
    }

    public void thresh(EntityLivingBase entity_living_base) {
        if (entity_living_base instanceof EntityLiving entity_living) {
            if (player.rand.nextFloat() > EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentThresher, player.getHeldItemStack()))
                return;
            ItemStack randomArmor = RandomUtil.getRandom(List.of(entity_living_base.getWornItems()), player.rand);
            if (randomArmor != null && this.shouldThresh(randomArmor)) {
                entity_living.dropItemStack(randomArmor, entity_living.height / 2.0F);
                entity_living.clearMatchingEquipmentSlot(randomArmor);
                entity_living.ticks_disarmed = 40;
            }
        }
    }

    private boolean shouldThresh(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemArmor armor && armor.getArmorMaterial().durability <= EnumEquipmentMaterial.ancient_metal.durability;
    }

    public void destroy(Entity target) {
        if (player.isPotionActive(PotionExtend.stunning)) {
            return;
        }
        ItemStack heldItemStack = player.getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentDestroying)) {
            int destroyingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, heldItemStack);
            target.worldObj.createExplosion(this.player, target.posX, target.posY, target.posZ, 0.0F, destroyingLevel * 0.5F, true);
        }
    }

    public void sweep(Entity target, float damage) {
        ItemStack heldItemStack = player.getHeldItemStack();
        if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentSweeping)) {
            List targets = target.getNearbyEntities(2.0F, 2.0F);
            targets.removeIf(x -> !(x instanceof EntityLivingBase));
            this.attackMonsters(targets, damage * 0.4f * EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentSweeping, heldItemStack));
        }
    }

    public void attackMonsters(List<EntityLivingBase> targets, float damage) {
        targets.stream().filter(x -> x != this.player)
                .filter(x -> x.getDistanceSqToEntity(player) <= Math.pow(player.getReach(EnumEntityReachContext.FOR_MELEE_ATTACK, x), 2))
                .forEach(x -> x.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(player), damage)));
    }

    public void arroganceUpdate() {
        if (this.underArrogance()) {
            player.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));
        }
    }

    public boolean underArrogance() {
        return this.player.experience < 2300 && Stream.concat(Arrays.stream(this.player.getWornItems()), Stream.of(this.player.getHeldItemStack()))
                .filter(Objects::nonNull)
                .anyMatch(itemStack -> EnchantmentHelper.hasEnchantment(itemStack, Enchantments.enchantmentArrogance));
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
            if (holding != null && holding.getItemDamage() > 0 && this.sunlight(holding) && !this.player.isBlocking()) {
                holding.setItemDamage(holding.getItemDamage() - 1);
            }
            ItemStack[] armors = this.player.getWornItems();
            for (ItemStack armor : armors) {
                if (armor != null && armor.getItemDamage() > 0 && this.sunlight(armor) && this.player.rand.nextInt(200) == 0) {
                    armor.setItemDamage(armor.getItemDamage() - 1);
                }
            }
        } else {
            World world = this.player.worldObj;
            int x = MathHelper.floor_double(this.player.posX);
            int y = MathHelper.floor_double(this.player.posY);
            int z = MathHelper.floor_double(this.player.posZ);
            if (!world.isDaytime() && world.canBlockSeeTheSky(x, y, z)) {
                ItemStack holding = this.player.getHeldItemStack();
                if (holding != null && holding.getItemDamage() > 1 && this.moonlight(holding) && !this.player.isBlocking()) {
                    holding.setItemDamage(holding.getItemDamage() - 2);
                }
                ItemStack[] armors = this.player.getWornItems();
                for (ItemStack armor : armors) {
                    if (armor != null && armor.getItemDamage() > 1 && this.moonlight(armor) && this.player.rand.nextInt(200) == 0) {
                        armor.setItemDamage(armor.getItemDamage() - 2);
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
