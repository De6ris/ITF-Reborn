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
import net.xiaoyu233.fml.util.IdUtil;

public class Blocks {
    private static int getNextBlockID() {
        if (!ITFConfig.FixID.getBooleanValue()) return IdUtil.getNextBlockID();
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

    public static final Block torchWoodIdle = (new BlockTorchIdle(getNextBlockID())).setHardness(0.0F).setLightValue(0.5F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("torch");

    public static final Block torchWoodExtinguished = (new BlockTorchIdle(getNextBlockID())).setHardness(0.0F).setLightValue(0.1F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("torch");

    public static final Block blockObserver = new BlockObserver(getNextBlockID(), Material.stone).setHardness(2.5F).setResistance(20.0f).setStepSound(Block.soundStoneFootstep);

    public static final Block blockReceiver = new BlockReceiver(getNextBlockID()).setHardness(2.5F).setResistance(20.0f).setStepSound(Block.soundStoneFootstep);

    public static final Block blockSulphur = new BlockOre(getNextBlockID(), Materials.sulphur, 1).setHardness(1.2F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blockEnchantPredicator = new BlockEnchantPredicator(getNextBlockID());

    public static final Block magicTable = new BlockMagicTable(getNextBlockID());

    public static final Block itfWorkBench = new ITFWorkbench(getNextBlockID());

    public static final Block uruBeacon = new BlockUruBeacon(getNextBlockID());

    public static void registerBlocks(ItemRegistryEvent registryEvent) {
        registryEvent.registerAnvil(anvilNickel, "nickel_anvil");
        anvilNickel.stepSound = Block.soundAnvilFootstep;
        registryEvent.registerAnvil(anvilTungsten, "tungsten_anvil");
        anvilTungsten.stepSound = Block.soundAnvilFootstep;
        registryEvent.registerItemBlock(blastFurnaceStoneIdle, "blastfurnace_stone_idle");
        registryEvent.registerItemBlock(blastFurnaceObsidianIdle, "blastfurnace_obsidian_idle");
        registryEvent.registerItemBlock(blastFurnaceNetherrackIdle, "blastfurnace_netherrack_idle");
        registryEvent.registerItemBlock(blastFurnaceStoneBurning, "blastfurnace_stone_burning");
        registryEvent.registerItemBlock(blastFurnaceObsidianBurning, "blastfurnace_obsidian_burning");
        registryEvent.registerItemBlock(blastFurnaceNetherrackBurning, "blastfurnace_netherrack_burning");
        registryEvent.registerItemBlock(blockEnchantReserver, "block_enchant_reserver");
        registryEvent.registerItemBlock(oreNickel, "ore/nickel_ore");
        registryEvent.registerItemBlock(blockNickel, "block/nickel_block");
        registryEvent.registerItemBlock(fenceNickel, "bars/nickel_bars");
        registryEvent.registerItemBlock(doorNickel, "door/door_nickel");
        registryEvent.registerItemBlock(blockSmokerIdle, "block_smoker_idle");
        registryEvent.registerItemBlock(blockSmokerBurning, "block_smoker_burning");
        registryEvent.registerItemBlock(oreTungsten, "ore/tungsten_ore");
        registryEvent.registerItemBlock(blockTungsten, "block/tungsten_block");
        registryEvent.registerItemBlock(fenceTungsten, "bars/tungsten_bars");
        registryEvent.registerItemBlock(doorTungsten, "door/door_tungsten");
        registryEvent.registerItemBlock(flowerextend, "flowers/");
        registryEvent.registerItemBlock(blockEnchantEnhancer, "block_enchant_enhancer");
        registryEvent.registerItemBlock(oreUru, "ore/uru_ore");
        registryEvent.registerItemBlock(beetroots, "beetroot");
        registryEvent.registerItemBlock(beetrootsDead, "beetroot");
        registryEvent.registerItemBlock(flowerPotExtend, "flower_pot");
        registryEvent.registerItemBlock(blockAzurite, "azurite_block");
        registryEvent.registerItemBlock(azuriteCluster, "azurite_cluster");
        registryEvent.registerItemBlock(torchWoodIdle, "torch_idle");
        registryEvent.registerItemBlock(torchWoodExtinguished, "torch_off");
        registryEvent.registerItemBlock(blockSulphur, "sulphur");
        registryEvent.registerItemBlock(blockObserver, "block_observer");
        registryEvent.registerItemBlock(blockReceiver, "block_receiver");
        registryEvent.registerItemBlock(blockEnchantPredicator, "block_enchant_predicator");
        registryEvent.registerItemBlock(magicTable, "magic_table");
        registryEvent.registerItemBlock(itfWorkBench, "crafting_table");
        registryEvent.registerItemBlock(uruBeacon, "beacon");
        uruBeacon.setUnlocalizedName("uru_beacon");
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
                'A', Item.ingotMithril, 'B', Items.forgingnote,
                'D', Item.diamond, 'O', Block.obsidian,
                'X', Item.expBottle);
        register.registerShapedRecipe(new ItemStack(magicTable), true, "MBM", "HTP", "MEM",
                'M', Item.ingotMithril, 'T', Block.enchantmentTable,
                'H', blockEnchantEnhancer, 'P', blockEnchantPredicator,
                'B', Items.forgingnote, 'E', Item.eyeOfEnder);
        register.registerShapedRecipe(new ItemStack(uruBeacon), true, "UNU", "UBU", "UUU",
                'U', Items.uruIngot, 'B', Block.beacon,
                'N', Items.forgingnote);

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
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 4), true, new ItemStack(flowerextend, 1, 2));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(flowerextend, 1, 3));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 9), true, new ItemStack(flowerextend, 1, 4));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(flowerextend, 1, 5));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), true, new ItemStack(flowerextend, 1, 6));
        register.registerShapelessRecipe(new ItemStack(Items.Agave, 1, 1), true, new ItemStack(flowerextend, 1, 7));

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