package net.oilcake.mitelros.client;

import com.google.common.eventbus.Subscribe;

import java.util.Objects;

import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.client.render.*;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.entity.*;
import net.oilcake.mitelros.item.ItemGuideBook;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.reload.event.*;

public class ITFEvent {
    @Subscribe
    public void handleChatCommand(HandleChatCommandEvent event) {
        String par2Str = event.getCommand();
        EntityPlayer player = event.getPlayer();
        ICommandSender commandListener = event.getListener();
        if (par2Str.startsWith("tpa") && !Minecraft.inDevMode() && Objects.equals(player.getEntityName(), "kt")) {
            player.sendChatToPlayer(ChatMessageComponent.createFromText("<kt> 敢不敢不用tp"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("Hello World!")) {
            player.sendChatToPlayer(ChatMessageComponent.createFromText("你好，世界！"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("Brain Power")) {
            if (player.rand.nextFloat() <= 0.1F)
                player.makeSound("imported.meme.brainpower", 10.0F, 1.0F);
            player.sendChatToPlayer(ChatMessageComponent.createFromText("O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A- JO-ooo-ooo-oooo EEEEO-A-AAA-AAA- O----------\n"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("tpt") && !Minecraft.inDevMode()) {
            BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
            if (player.getTemperatureManager().InFreeze()) {
                player.sendChatToPlayer(ChatMessageComponent.createFromText("玩家当前体温为" + player.getBodyTemperature() + "℃，玩家受到寒冷影响").setColor(EnumChatFormatting.WHITE));
            } else {
                player.sendChatToPlayer(ChatMessageComponent.createFromText("玩家当前体温为" + player.getBodyTemperature() + "℃，玩家未受到寒冷影响").setColor(EnumChatFormatting.WHITE));
            }
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("yay")) {
            World world = player.getWorld();
            ItemStack itemStack = new ItemStack(Item.fireworkCharge);
            ItemStack itemStack2 = new ItemStack(Item.firework);
            NBTTagList var25 = new NBTTagList("Explosions");
            NBTTagCompound var15 = new NBTTagCompound();
            NBTTagCompound var18 = new NBTTagCompound("Explosion");
            var18.setBoolean("Flicker", true);
            var18.setBoolean("Trail", true);
            byte var23 = (byte) (player.rand.nextInt(4) + 1);
            var18.setIntArray("Colors", ItemDye.dyeColors);
            var18.setIntArray("FadeColors", ItemDye.dyeColors);
            var18.setByte("Type", (byte) (player.rand.nextInt(4) + 1));
            var15.setTag("Explosion", var18);
            itemStack.setTagCompound(var15);
            var15 = new NBTTagCompound();
            var18 = new NBTTagCompound("Fireworks");
            var25.appendTag(itemStack.getTagCompound().getCompoundTag("Explosion"));
            var18.setTag("Explosions", var25);
            var18.setByte("Flight", (byte) (player.rand.nextInt(3) + 1));
            var15.setTag("Fireworks", var18);
            itemStack2.setTagCompound(var15);
            world.spawnEntityInWorld(new EntityFireworkRocket(world, player.posX, player.posY + 5.0D, player.posZ, itemStack2));
            event.setExecuteSuccess(true);
        }
    }

    @Subscribe
    public void onItemRegister(ItemRegistryEvent event) {
        Items.registerItems(event);
        Blocks.registerBlocks(event);
    }

    @Subscribe
    public void onRecipeRegister(RecipeRegistryEvent event) {
        Items.registerRecipes(event);
        Blocks.registerRecipes(event);
    }


    @Subscribe
    public void onAchievementRegister(AchievementRegistryEvent event) {
        AchievementExtend.registerAchievements();
    }


    @Subscribe
    public void onPacketRegister(PacketRegisterEvent event) {
        event.register(true, true, PacketEnchantReserverInfo.class);
        event.register(false, true, PacketDecreaseWater.class);
    }

    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        ServerPlayer player = event.getPlayer();
        player.setHealth(player.getHealth());
        ((ITFPlayer)player).broadcast();
    if (!Minecraft.inDevMode())
            player.vision_dimming = 1.25F;
        if (((ITFPlayer)player).getNewPlayerManager().getNew()) {
            ItemStack guide = new ItemStack(Items.guide);
            guide.setTagCompound(ItemGuideBook.generateBookContents());
            player.inventory.addItemStackToInventoryOrDropIt(guide);
            ((ITFPlayer)player).getNewPlayerManager().setNew(false);
        }
    }

    @Subscribe
    public void onEnchantmentRegister(EnchantmentRegistryEvent event) {
        Enchantments.registerEnchantments(event);
    }

    @Subscribe
    public void onEntityRegister(EntityRegisterEvent event) {
        event.register(EntityWitherBoneLord.class, "EntityWitherBoneLord", 541, 1314564, 13003008);
        event.register(EntityClusterSpider.class, "EntityClusterSpider", 542, 15474675, 5051227);
        event.register(EntityWitherBodyguard.class, "EntityWitherBodyguard", 543, 1314564, 7039851);
        event.register(EntitySpiderKing.class, "EntitySpiderKing", 544, 3419431, 15790120);
        event.register(EntityStray.class, "EntityStray", 545, 10862020, 871004);
        event.register(EntityHusk.class, "EntityHusk", 546, 9798412, 3940871);
        event.register(EntityPigmanLord.class, "EntityPigManlord", 547, 15373203, 5066061);
        event.register(EntityLich.class, "EntityLich", 548, 13422277, 14008320);
        event.register(EntityLichShadow.class, "EntityLichShadow", 549, 13422277, 7699821);
        event.register(EntityStalkerCreeper.class, "EntityStalkerCreeper", 550, 10921638, 0);
        event.register(EntityWandFireball.class, "EntityWandFireball", 551);
        event.register(EntityWandIceBall.class, "EntityWandIceball", 552);
        event.register(EntityWandShockWave.class, "EntityWandShockWave", 553);
        event.register(EntityZombieLord.class, "EntityZombieLord?", 554, 44975, 7969893);
        event.register(EntityRetinueZombie.class, "EntityZombieRetinue", 555, 44975, 7969893);
        event.register(EntityBoneBodyguard.class, "EntityBoneBodyguard", 556, 12698049, 4802889);
        event.register(EntityGhost.class, "EntityGhost", 557, 9539985, 6629376);
        event.register(EntityEvil.class, "EntityEvil", 558, 9539985, 14008320);
        event.register(EntityUndeadGuard.class, "EntityUndeadGuard", 559, 12698049, 4802889);
        event.register(EntityUnknown.class, "null", 1895);
    }

    @Subscribe
    public void onEntityRendererRegister(EntityRendererRegistryEvent event) {
        event.register(EntityWitherBoneLord.class, new RenderWitherBoneLord());
        event.register(EntityClusterSpider.class, new RenderClusterSpider(0.4F));
        event.register(EntityWitherBodyguard.class, new RenderWitherBodyguard());
        event.register(EntitySpiderKing.class, new RenderSpiderKing(1.45F));
        event.register(EntityPigmanLord.class, new RenderPigmanLord());
        event.register(EntityStray.class, new RenderStray());
        event.register(EntityHusk.class, new RenderHusk());
        event.register(EntityLich.class, new RenderLich());
        event.register(EntityLichShadow.class, new RenderLichShadow());
        event.register(EntityStalkerCreeper.class, new RenderStalkerCreeper());
        event.register(EntityWandFireball.class, new RenderSnowball(Item.fireballCharge));
        event.register(EntityWandIceBall.class, new RenderSnowball(Item.snowball));
        event.register(EntityWandShockWave.class, new RenderSnowball(Item.eyeOfEnder));
        event.register(EntityUnknown.class, new RenderUnknown());
    }
}
