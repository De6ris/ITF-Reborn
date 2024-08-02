package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.api.ITFDoor;
import net.oilcake.mitelros.block.api.ITFPane;
import net.oilcake.mitelros.block.api.ITFWorkbench;
import net.oilcake.mitelros.block.enchantreserver.BlockEnchantReserver;
import net.oilcake.mitelros.block.observer.BlockObserver;
import net.oilcake.mitelros.block.receiver.BlockReceiver;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.api.block.AnvilBlock;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

import static net.oilcake.mitelros.ITFStart.NameSpace;

public class Blocks {
    private static int getNextBlockID() {
        if (!ITFConfig.FixedID.getBooleanValue()) return IdUtil.getNextBlockID();
        return Constant.nextBlockID--;
    }

    public static final Block blastFurnaceStoneIdle = (new BlockBlastFurnace(getNextBlockID(), Material.stone, false)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(4.8F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blastFurnaceObsidianIdle = (new BlockBlastFurnace(getNextBlockID(), Material.obsidian, false)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(38.4F).setResistance(40.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blastFurnaceNetherrackIdle = (new BlockBlastFurnace(getNextBlockID(), Material.netherrack, false)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(153.6F).setResistance(80.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blastFurnaceStoneBurning = (new BlockBlastFurnace(getNextBlockID(), Material.stone, true)).setHardness(4.8F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final Block blastFurnaceObsidianBurning = (new BlockBlastFurnace(getNextBlockID(), Material.obsidian, true)).setHardness(38.4F).setResistance(40.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final Block blastFurnaceNetherrackBurning = (new BlockBlastFurnace(getNextBlockID(), Material.netherrack, true)).setHardness(153.6F).setResistance(80.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final Block blockSmokerIdle = (new BlockSmoker(getNextBlockID(), false)).setHardness(2.0F).setResistance(20.0F).setCreativeTab(CreativeTabs.tabDecorations).setStepSound(Block.soundStoneFootstep);

    public static final Block blockSmokerBurning = (new BlockSmoker(getNextBlockID(), true)).setHardness(2.0F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final BlockAnvil anvilNickel = new AnvilBlock(getNextBlockID(), Materials.nickel);

    public static final Block blockEnchantReserver = (new BlockEnchantReserver(getNextBlockID())).setHardness(8.0F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.0F);

    public static final Block blockNickel = (new BlockOreBlockExtend(getNextBlockID(), Materials.nickel)).setStepSound(Block.soundMetalFootstep);

    public static final Block fenceNickel = (new ITFPane(getNextBlockID(), "bars/nickel_bars", "bars/nickel_bars", Materials.nickel, false)).setStepSound(Block.soundMetalFootstep).setResistance(6.0F).setHardness(3.2F).setMinHarvestLevel(3);

    public static final Block doorNickel = (new ITFDoor(getNextBlockID(), Materials.nickel)).setStepSound(Block.soundMetalFootstep).setMinHarvestLevel(3);

    public static final Block oreNickel = (new BlockOre(getNextBlockID(), Materials.nickel, 2)).setHardness(3.0F).setResistance(20.0F);

    public static final Block oreTungsten = (new BlockOre(getNextBlockID(), Materials.tungsten, 4)).setHardness(3.5F).setResistance(30.0F);

    public static final Block blockTungsten = (new BlockOreStorage(getNextBlockID(), Materials.tungsten)).setStepSound(Block.soundMetalFootstep);

    public static final Block fenceTungsten = (new ITFPane(getNextBlockID(), "bars/tungsten_bars", "bars/tungsten_bars", Materials.tungsten, false)).setStepSound(Block.soundMetalFootstep).setResistance(96.0F).setHardness(51.2F).setMinHarvestLevel(5);

    public static final Block doorTungsten = (new ITFDoor(getNextBlockID(), Materials.tungsten)).setStepSound(Block.soundMetalFootstep).setMinHarvestLevel(5);

    public static final BlockAnvil anvilTungsten = new AnvilBlock(getNextBlockID(), Materials.tungsten);

    public static final BlockFlowerExtend flowerextend = new BlockFlowerExtend(getNextBlockID());

    public static final Block blockEnchantEnhancer = (new BlockEnchantEnhancer(getNextBlockID())).setHardness(8.0F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block oreUru = (new BlockOre(getNextBlockID(), Materials.uru, 4)).setHardness(5.0F).setResistance(150.0F);

    public static final Block beetroots = (new BlockBeetroots(getNextBlockID())).setUnlocalizedName("beetroot");

    public static final Block beetrootsDead = (new BlockBeetrootsDead(getNextBlockID())).setUnlocalizedName("beetroot");

    public static final Block flowerPotExtend = (new BlockFlowerPotExtend(getNextBlockID())).setHardness(0.0F).setStepSound(Block.soundPowderFootstep).setUnlocalizedName("flowerPot");

    public static final Block blockAzurite = new BlockGrowableOre(getNextBlockID(), Materials.crystal, 2).setStepSound(Block.soundGlassFootstep).setHardness(1.2F).setResistance(12.0f).setLightValue(1.0F);

    public static final Block azuriteCluster = new BlockCaveMisc(getNextBlockID(), Materials.crystal).setLightValue(0.75F).setHardness(0.6F).setMinHarvestLevel(1).setResistance(6.0f).setStepSound(Block.soundGlassFootstep);

    public static final Block torchWoodIdle = (new BlockTorchIdle(getNextBlockID())).setLightValue(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("torch");

    public static final Block torchWoodExtinguished = (new BlockTorchIdle(getNextBlockID())).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("torch");

    public static final Block blockObserver = new BlockObserver(getNextBlockID(), Material.stone).setHardness(2.5F).setResistance(20.0f).setStepSound(Block.soundStoneFootstep);

    public static final Block blockReceiver = new BlockReceiver(getNextBlockID()).setHardness(2.5F).setResistance(20.0f).setStepSound(Block.soundStoneFootstep);

    public static final Block blockSulphur = new BlockOre(getNextBlockID(), Materials.sulphur, 1).setHardness(1.2F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blockEnchantPredicator = new BlockEnchantPredicator(getNextBlockID());

    public static final Block magicTable = new BlockMagicTable(getNextBlockID());

    public static final Block itfWorkBench = new ITFWorkbench(getNextBlockID());

    public static final Block uruBeacon = new BlockUruBeacon(getNextBlockID());
    public static final BlockRunestone tungstenRuneStone = (BlockRunestone) new BlockRunestone(getNextBlockID(), Materials.tungsten).setHardness(2.4f).setResistance(20.0f).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("runestone").setTextureName("obsidian");

    public static void registerBlocks(ItemRegistryEvent registryEvent) {
        registryEvent.registerAnvil(NameSpace, "nickel_anvil", anvilNickel);
        anvilNickel.stepSound = Block.soundAnvilFootstep;
        registryEvent.registerAnvil(NameSpace, "tungsten_anvil", anvilTungsten);
        anvilTungsten.stepSound = Block.soundAnvilFootstep;
        registryEvent.registerItemBlock(NameSpace, "blastfurnace_stone_idle", blastFurnaceStoneIdle);
        registryEvent.registerItemBlock(NameSpace, "blastfurnace_obsidian_idle", blastFurnaceObsidianIdle);
        registryEvent.registerItemBlock(NameSpace, "blastfurnace_obsidian_idle", blastFurnaceObsidianIdle);
        registryEvent.registerItemBlock(NameSpace, "blastfurnace_netherrack_idle", blastFurnaceNetherrackIdle);
        registryEvent.registerItemBlock(NameSpace, "blastfurnace_stone_burning", blastFurnaceStoneBurning);
        registryEvent.registerItemBlock(NameSpace, "blastfurnace_obsidian_burning", blastFurnaceObsidianBurning);
        registryEvent.registerItemBlock(NameSpace, "blastfurnace_netherrack_burning", blastFurnaceNetherrackBurning);
        registryEvent.registerItemBlock(NameSpace, "block_enchant_reserver", blockEnchantReserver);
        registryEvent.registerItemBlock(NameSpace, "ore/nickel_ore", oreNickel);
        registryEvent.registerItemBlock(NameSpace, "block/nickel_block", blockNickel);
        registryEvent.registerItemBlock(NameSpace, "bars/nickel_bars", fenceNickel);
        registryEvent.registerItemBlock(NameSpace, "door/door_nickel", doorNickel);
        registryEvent.registerItemBlock(NameSpace, "block_smoker_idle", blockSmokerIdle);
        registryEvent.registerItemBlock(NameSpace, "block_smoker_burning", blockSmokerBurning);
        registryEvent.registerItemBlock(NameSpace, "ore/tungsten_ore", oreTungsten);
        registryEvent.registerItemBlock(NameSpace, "block/tungsten_block", blockTungsten);
        registryEvent.registerItemBlock(NameSpace, "bars/tungsten_bars", fenceTungsten);
        registryEvent.registerItemBlock(NameSpace, "door/door_tungsten", doorTungsten);
        registryEvent.registerItemBlock(NameSpace, "flowers/", flowerextend);
        registryEvent.registerItemBlock(NameSpace, "block_enchant_enhancer", blockEnchantEnhancer);
        registryEvent.registerItemBlock(NameSpace, "ore/uru_ore", oreUru);
        registryEvent.registerItemBlock(NameSpace, "beetroot", beetroots);
        registryEvent.registerItemBlock(NameSpace, "beetroot", beetrootsDead);
        registryEvent.registerItemBlock(NameSpace, "flower_pot", flowerPotExtend);
        registryEvent.registerItemBlock(NameSpace, "azurite_block", blockAzurite);
        registryEvent.registerItemBlock(NameSpace, "azurite_cluster", azuriteCluster);
        registryEvent.registerItemBlock(NameSpace, "torch_idle", torchWoodIdle);
        registryEvent.registerItemBlock(NameSpace, "torch_off", torchWoodExtinguished);
        registryEvent.registerItemBlock(NameSpace, "sulphur", blockSulphur);
        registryEvent.registerItemBlock(NameSpace, "block_observer", blockObserver);
        registryEvent.registerItemBlock(NameSpace, "block_receiver", blockReceiver);
        registryEvent.registerItemBlock(NameSpace, "block_enchant_predicator", blockEnchantPredicator);
        registryEvent.registerItemBlock(NameSpace, "magic_table", magicTable);
        registryEvent.registerItemBlock(NameSpace, "crafting_table", itfWorkBench);
        registryEvent.registerItemBlock(NameSpace, "beacon", uruBeacon);
        uruBeacon.setUnlocalizedName("uru_beacon");
        registryEvent.registerItemBlock(NameSpace, "obsidian", tungstenRuneStone);
        tungstenRuneStone.setUnlocalizedName("runestone");
    }

    public static void registerRecipes(RecipeRegistryEvent register) {
        shapedRecipe(register);
        shapelessRecipe(register);
        furnaceRecipe();
        //TODO fix block recipe with subtype
    }

    public static void shapedRecipe(RecipeRegistryEvent register) {
        register.registerShapedRecipe(new ItemStack(blockSmokerIdle), true, " A ", "ABA", " A ",
                'A', Block.wood,
                'B', Block.furnaceIdle);
        register.registerShapedRecipe(new ItemStack(fenceNickel, 16), true, "AAA", "AAA",
                'A', Items.nickelIngot);
        register.registerShapedRecipe(new ItemStack(blockEnchantReserver), true, "CBC", "ABA", "CAC",
                'A', Block.obsidian, 'B', Item.diamond, 'C', Item.ingotAncientMetal);
        register.registerShapedRecipe(new ItemStack(blockEnchantEnhancer), true, "CAC", "DBD", "AAA",
                'A', Block.obsidian, 'B', Item.diamond, 'C', Item.ingotMithril, 'D',
                Item.expBottle);
        register.registerShapedRecipe(new ItemStack(blastFurnaceStoneIdle), true, "AAA", "ABA", "CCC",
                'A', Block.stone, 'B', Block.furnaceIdle, 'C', Item.ingotIron);
        register.registerShapedRecipe(new ItemStack(blastFurnaceObsidianIdle), true, "AAA", "ABA", "CCC",
                'A', Block.obsidian, 'B', Block.furnaceObsidianIdle, 'C', Item.ingotMithril);
        register.registerShapedRecipe(new ItemStack(blastFurnaceNetherrackIdle), true, "AAA", "ABA", "CCC",
                'A', Block.netherrack, 'B', Block.furnaceNetherrackIdle, 'C', Item.ingotAdamantium);
        register.registerShapedRecipe(new ItemStack(anvilNickel), true, "AAA", " I ", "III",
                'A', blockNickel,
                'I', Items.nickelIngot);
        register.registerShapedRecipe(new ItemStack(anvilTungsten), true, "AAA", " I ", "III",
                'A', blockTungsten,
                'I', Items.tungstenIngot);
        register.registerShapedRecipe(new ItemStack(fenceTungsten, 16), true, "XXX", "XXX",
                'X', Items.tungstenIngot);
        register.registerShapedRecipe(new ItemStack(azuriteCluster), true, "EE",
                'E', Items.shardAzurite);
        register.registerShapedRecipe(new ItemStack(blockObserver), true, "XXX", "ABS", "XXX",
                'X', Block.cobblestone, 'A', Item.netherQuartz,
                'B', Item.redstone, 'S', Items.shardAzurite);
        register.registerShapedRecipe(new ItemStack(blockReceiver), true, "XSX", "SBS", "XSX",
                'X', Block.cobblestone, 'S', Items.shardAzurite,
                'B', Item.redstone);
        register.registerShapedRecipe(new ItemStack(blockEnchantPredicator), true, "XDX", "ABA", "OAO",
                'A', Item.ingotMithril, 'B', Items.forgingNote,
                'D', Item.diamond, 'O', Block.obsidian,
                'X', Item.expBottle);
        register.registerShapedRecipe(new ItemStack(magicTable), true, "MBM", "HTP", "MEM",
                'M', Item.ingotMithril, 'T', Block.enchantmentTable,
                'H', blockEnchantEnhancer, 'P', blockEnchantPredicator,
                'B', Items.forgingNote, 'E', Item.eyeOfEnder);
        register.registerShapedRecipe(new ItemStack(uruBeacon), true, "UNU", "UBU", "UUU",
                'U', Items.uruIngot, 'B', Block.beacon,
                'N', Items.forgingNote);
        register.registerShapedRecipe(new ItemStack(tungstenRuneStone), true, " n ", "n#n", " n ",
                '#', Block.obsidian, 'n', Items.tungstenNugget);

        for (int i = 0; i < Blocks.itfWorkBench.getNumSubBlocks(); ++i) {
            Material tool_material = ITFWorkbench.getToolMaterial(i);
            for (int plank_subtype = 0; plank_subtype < 4; ++plank_subtype) {
                register.registerShapedRecipe(new ItemStack(Blocks.itfWorkBench, 1, i), true, "IL", "s#", 'I', ItemIngot.getMatchingItem(ItemIngot.class, tool_material), 'L', Item.leather, 's', Item.stick, '#', new ItemStack(Block.planks, 1, plank_subtype));
            }
        }
    }

    public static void shapelessRecipe(RecipeRegistryEvent register) {
        register.registerShapelessRecipe(new ItemStack(Items.glowberries, 1), true, new ItemStack(flowerextend, 1, 0));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(flowerextend, 1, 1));
//        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 4), true, new ItemStack(flowerextend, 1, 2));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(flowerextend, 1, 3));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 9), true, new ItemStack(flowerextend, 1, 4));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(flowerextend, 1, 5));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), true, new ItemStack(flowerextend, 1, 6));
        register.registerShapelessRecipe(new ItemStack(Items.agave, 1, 1), true, new ItemStack(flowerextend, 1, 7));

//        register.registerShapelessRecipe(new ItemStack(Items.glowberries, 1), true, new ItemStack(luminescentHerb, 1));
        for (int i = 0; i <= 4; i++) {
            register.registerShapelessRecipe(new ItemStack(Item.stick, 1), true, new ItemStack(Blocks.torchWoodIdle, i), new ItemStack(Blocks.torchWoodExtinguished, 4 - i));
        }

        nineToOne(register, new ItemStack(blockNickel), Items.nickelIngot);
        nineToOne(register, new ItemStack(blockTungsten), Items.tungstenIngot);
        nineToOne(register, new ItemStack(blockAzurite), Items.shardAzurite);
    }

    public static void furnaceRecipe() {
        FurnaceRecipes.smelting().addSmelting(oreTungsten.blockID, new ItemStack(Items.tungstenIngot));
        FurnaceRecipes.smelting().addSmelting(oreNickel.blockID, new ItemStack(Items.nickelIngot));
        FurnaceRecipes.smelting().addSmelting(oreUru.blockID, new ItemStack(Items.uruIngot));
    }

    public static void nineToOne(RecipeRegistryEvent register, ItemStack itemStack, Item item) {
        register.registerShapedRecipe(itemStack, true, "XXX", "XXX", "XXX",
                'X', item);
    }

}