package net.oilcake.mitelros.block.register;

import net.minecraft.Block;
import net.minecraft.BlockAnvil;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.register.ItemRegistry;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;

import static net.oilcake.mitelros.ITFStart.NameSpace;

public class BlockRegistry extends Blocks {

    private static ItemRegistryEvent eventCache = null;

    public static void registerBlocks(ItemRegistryEvent registryEvent) {
        eventCache = registryEvent;

        registerAnvil("nickel_anvil", anvilNickel);
        registerAnvil("tungsten_anvil", anvilTungsten);
        register("blastfurnace_stone_idle", blastFurnaceStoneIdle);
        register("blastfurnace_obsidian_idle", blastFurnaceObsidianIdle);
        register("blastfurnace_obsidian_idle", blastFurnaceObsidianIdle);
        register("blastfurnace_netherrack_idle", blastFurnaceNetherrackIdle);
        register("blastfurnace_stone_burning", blastFurnaceStoneBurning, false);
        register("blastfurnace_obsidian_burning", blastFurnaceObsidianBurning, false);
        register("blastfurnace_netherrack_burning", blastFurnaceNetherrackBurning, false);
        register("block_enchant_reserver", blockEnchantReserver);
        register("ore/nickel_ore", oreNickel);
        register("block/nickel_block", blockNickel);
        register("bars/nickel_bars", fenceNickel);
        register("door/door_nickel", doorNickel, false);
        register("block_smoker_idle", blockSmokerIdle);
        register("block_smoker_burning", blockSmokerBurning, false);
        register("ore/tungsten_ore", oreTungsten);
        register("block/tungsten_block", blockTungsten);
        register("bars/tungsten_bars", fenceTungsten);
        register("door/door_tungsten", doorTungsten, false);
        register("flowers/", flowerextend);
        register("block_enchant_enhancer", blockEnchantEnhancer);
        register("ore/uru_ore", oreUru);
        register("beetroot", beetroots, false, false);
        register("beetroot", beetrootsDead, false, false);
        register("flower_pot", flowerPotExtend, false, false);
        register("azurite_block", blockAzurite);
        register("azurite_cluster", azuriteCluster);
        register("torch_idle", torchWoodIdle);
        register("torch_off", torchWoodExtinguished);
        register("sulphur", blockSulphur);
        register("block_observer", blockObserver);
        register("block_receiver", blockReceiver);
        register("block_enchant_predicator", blockEnchantPredicator);
        register("magic_table", magicTable);
        register("crafting_table", nickelWorkBench);
        register("crafting_table", tungstenWorkBench);
        register("beacon", uruBeacon, false, true);
        register("obsidian", tungstenRuneStone, false, true);

        uruBeacon.setUnlocalizedName("uru_beacon");
        tungstenRuneStone.setUnlocalizedName("runestone");

        eventCache = null;
    }

    private static void register(String texture, Block block) {
        register(texture, block, true);
    }

    private static void register(String texture, Block block, boolean show) {
        register(texture, block, true, show);
    }

    private static void register(String texture, Block block, boolean withDomain, boolean show) {
        String translationKey = texture;
        if (withDomain) texture = ITFStart.ResourceDomainColon + texture;
        eventCache.registerItemBlock(NameSpace, texture, translationKey, block);
        if (show) {
            block.setCreativeTab(ItemRegistry.ITFBlock);
        }
    }

    private static void registerAnvil(String texture, BlockAnvil block) {
        String translationKey = texture;
        texture = ITFStart.ResourceDomainColon + texture;
        eventCache.registerAnvil(NameSpace, texture, translationKey, block);
        block.setCreativeTab(ItemRegistry.ITFBlock);
    }
}
