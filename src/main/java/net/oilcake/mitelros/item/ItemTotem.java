package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.entity.misc.EntityLongdeadSentry;

public class ItemTotem extends Item {
    private final Material totemMaterial;

    public ItemTotem(int id, Material material, String texture) {
        super(id, material, texture);
        this.totemMaterial = material;
        setMaxStackSize(1);
        setCraftingDifficultyAsComponent(100.0F);
        setCreativeTab(CreativeTabs.tabTools);
    }

    private void performEffectCommon(EntityPlayer player, ItemTotem totem) {
        player.clearActivePotions();
        player.setHealth(Math.max(player.getHealth(), 2.0F), true, player.getHealFX());
        player.makeSound("imported.random.totem_use", 3.0F, 1.0F + player.rand.nextFloat() * 0.1F);
        if (!player.isPlayerInCreative()) {
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 4));
            player.vision_dimming = 1.0F;
        }
    }

    private void performEffectSpecified(EntityPlayer player, ItemTotem totem) {
        if (player.onClient()) return;
        Material totem_material = totem.totemMaterial;
        if (totem_material == Material.gold) {
            fecundEffect(player);
        } else if (totem_material == Material.ancient_metal) {
            knowledgeEffect(player);
        } else if (totem_material == Material.iron) {
            preserveEffect(player);
        } else if (totem_material == Materials.nickel) {
            huntingEffect(player);
        } else if (totem_material == Materials.tungsten) {
            destroyEffect(player);
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
        } else if (totem_material == Material.dirt) {
            flatten(player, player.worldObj, player.getBlockPosX(), player.getBlockPosY(), player.getBlockPosZ(), ITFConfig.TagTotemBlessing.getBooleanValue() ? 32 : 16);
        } else {
            Minecraft.setErrorMessage("effectSpecified(): Undefined Material " + totem_material.toString() + ".");
        }
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        ItemStack totem = player.getHeldItemStack();
        if (totem.getItem() instanceof ItemTotem) {
            performEffectCommon(player, (ItemTotem) totem.getItem());
            performEffectSpecified(player, (ItemTotem) totem.getItem());
            if (!player.isPlayerInCreative()) {
                player.convertOneOfHeldItem(null);
            }
            return true;
        }
        return false;
    }

    private static void huntingEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++)
            player.entityFX(EnumEntityFX.summoned);
        player.getHuntManager().setHunt_counter(400);
        player.getHuntManager().hunt_cap = true;
        player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 400, (int) ((1.0F - player.getHealthFraction()) * 2.0F)));
    }

    private static void knowledgeEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++)
            player.entityFX(EnumEntityFX.curse_effect_learned);
        int xpToAdd = player.experience / 5;
        player.addExperience(ITFConfig.TagTotemBlessing.getBooleanValue() ? xpToAdd : Math.min(xpToAdd, 30000));
    }

    private static void fecundEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.heal);
        }
        player.setHealth(player.getMaxHealth(), true, player.getHealFX());
        if (ITFConfig.TagTotemBlessing.getBooleanValue()) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 1));
        }
    }

    private static void preserveEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++)
            player.entityFX(EnumEntityFX.smoke_and_steam);
        player.addPotionEffect(new PotionEffect(Potion.resistance.id, 400, (int) ((1.0F - player.getHealthFraction()) * 4.0F)));

    }

    private static void flatten(EntityPlayer player, World world, int startX, int y, int startZ, int range) {
        if (world.getDimensionId() != 0) {
            destroyEffect(player);
        }
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.smoke_and_steam);
        }
        BiomeGenBase biome = player.getBiome();
        int surfaceID = biome.isDesertBiome() ? Block.sand.blockID : Block.dirt.blockID;
        int deepID = biome.isDesertBiome() ? Block.sandStone.blockID : Block.stone.blockID;
        for (int x = startX; x < startX + range; x++) {
            for (int z = startZ; z < startZ + range; z++) {
                world.setBlock(x, y - 5, z, deepID, 0, 2);
                world.setBlock(x, y - 4, z, deepID, 0, 2);
                world.setBlock(x, y - 3, z, surfaceID, 0, 2);
                world.setBlock(x, y - 2, z, surfaceID, 0, 2);
                world.setBlock(x, y - 1, z, surfaceID, 0, 2);
                world.setBlockToAir(x, y, z);
                world.setBlockToAir(x, y + 1, z);
                world.setBlockToAir(x, y + 2, z);
                world.setBlockToAir(x, y + 3, z);
                world.setBlockToAir(x, y + 4, z);
            }
        }
    }

    private static void destroyEffect(EntityPlayer player) {
        float delta = player.getHealthFraction() - 0.5F;
        for (int i = 0; i < 8; i++)
            player.entityFX(EnumEntityFX.smoke);
        player.worldObj.createExplosion(player, player.posX, player.posY + 1.5D, player.posZ, 0.0F, 4.0F - 4.0F * delta, true);
        player.setHealth(player.getMaxHealth() / 2.0F, true, player.getHealFX());
    }
}
