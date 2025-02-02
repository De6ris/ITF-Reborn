package net.oilcake.mitelros.block;

import moddedmite.rustedironcore.api.block.DoorBlock;
import moddedmite.rustedironcore.api.block.PaneBlock;
import moddedmite.rustedironcore.api.block.WorkbenchBlock;
import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.minecraft.*;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.api.ITFAnvil;
import net.oilcake.mitelros.api.ITFRunestone;
import net.oilcake.mitelros.api.ITFWorkbench;
import net.oilcake.mitelros.block.enchantreserver.BlockEnchantReserver;
import net.oilcake.mitelros.block.observer.BlockObserver;
import net.oilcake.mitelros.block.receiver.BlockReceiver;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Blocks {
    private static int getNextBlockID() {
        if (!ITFConfig.FixedID.getBooleanValue()) return IdUtil.getNextBlockID();
        return Constant.nextBlockID--;
    }

    public static final Block blastFurnaceStoneIdle = (new BlockBlastFurnace(getNextBlockID(), Material.stone, false)).setHardness(4.8F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blastFurnaceObsidianIdle = (new BlockBlastFurnace(getNextBlockID(), Material.obsidian, false)).setHardness(38.4F).setResistance(40.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blastFurnaceNetherrackIdle = (new BlockBlastFurnace(getNextBlockID(), Material.netherrack, false)).setHardness(153.6F).setResistance(80.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blastFurnaceStoneBurning = (new BlockBlastFurnace(getNextBlockID(), Material.stone, true)).setHardness(4.8F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final Block blastFurnaceObsidianBurning = (new BlockBlastFurnace(getNextBlockID(), Material.obsidian, true)).setHardness(38.4F).setResistance(40.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final Block blastFurnaceNetherrackBurning = (new BlockBlastFurnace(getNextBlockID(), Material.netherrack, true)).setHardness(153.6F).setResistance(80.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final Block blockSmokerIdle = (new BlockSmoker(getNextBlockID(), false)).setHardness(2.0F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep);

    public static final Block blockSmokerBurning = (new BlockSmoker(getNextBlockID(), true)).setHardness(2.0F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);

    public static final BlockAnvil anvilNickel = new ITFAnvil(getNextBlockID(), Materials.nickel);

    public static final Block blockEnchantReserver = (new BlockEnchantReserver(getNextBlockID())).setHardness(8.0F).setResistance(20.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.0F);

    public static final Block blockNickel = (new BlockOreBlockExtend(getNextBlockID(), Materials.nickel)).setStepSound(Block.soundMetalFootstep);

    public static final Block fenceNickel = (new PaneBlock(getNextBlockID(), ITFStart.ResourceDomainColon + "bars/nickel_bars", ITFStart.ResourceDomainColon + "bars/nickel_bars", Materials.nickel, false)).setStepSound(Block.soundMetalFootstep).setResistance(6.0F).setHardness(3.2F).setMinHarvestLevel(3);

    public static final DoorBlock doorNickel = (DoorBlock) (new DoorBlock(getNextBlockID(), Materials.nickel, () -> Items.doorNickel)).setStepSound(Block.soundMetalFootstep).setMinHarvestLevel(3);

    public static final Block oreNickel = (new BlockOre(getNextBlockID(), Materials.nickel, 2)).setHardness(3.0F).setResistance(20.0F);

    public static final Block oreTungsten = (new BlockOre(getNextBlockID(), Materials.tungsten, 4)).setHardness(3.5F).setResistance(30.0F);

    public static final Block blockTungsten = (new BlockOreStorage(getNextBlockID(), Materials.tungsten)).setStepSound(Block.soundMetalFootstep);

    public static final Block fenceTungsten = (new PaneBlock(getNextBlockID(), ITFStart.ResourceDomainColon + "bars/tungsten_bars", ITFStart.ResourceDomainColon + "bars/tungsten_bars", Materials.tungsten, false)).setStepSound(Block.soundMetalFootstep).setResistance(96.0F).setHardness(51.2F).setMinHarvestLevel(5);

    public static final DoorBlock doorTungsten = (DoorBlock) (new DoorBlock(getNextBlockID(), Materials.tungsten, () -> Items.doorTungsten)).setStepSound(Block.soundMetalFootstep).setMinHarvestLevel(5);

    public static final BlockAnvil anvilTungsten = new ITFAnvil(getNextBlockID(), Materials.tungsten);

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

    public static final WorkbenchBlock nickelWorkBench = new ITFWorkbench(getNextBlockID(), Materials.nickel, 0.5F, Material.copper);

    public static final Block uruBeacon = new BlockUruBeacon(getNextBlockID());

    public static final BlockRunestone tungstenRuneStone = (BlockRunestone) new ITFRunestone(getNextBlockID(), Materials.tungsten).setHardness(2.4f).setResistance(20.0f).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("runestone").setTextureName("obsidian");

    public static final WorkbenchBlock tungstenWorkBench = new ITFWorkbench(getNextBlockID(), Materials.tungsten, 1.5F, Material.mithril);

    static {
        anvilNickel.stepSound = Block.soundAnvilFootstep;
        anvilTungsten.stepSound = Block.soundAnvilFootstep;
    }

    public static void registerRecipes(CraftingRecipeRegisterEvent register) {
        shapedRecipe(register);
        shapelessRecipe(register);
        //TODO fix block recipe with subtype
    }

    public static void shapedRecipe(CraftingRecipeRegisterEvent register) {
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

        nickelWorkBench.registerSimpleRecipe(register);
        tungstenWorkBench.registerSimpleRecipe(register);
    }

    public static void shapelessRecipe(CraftingRecipeRegisterEvent register) {
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

    public static void nineToOne(CraftingRecipeRegisterEvent register, ItemStack itemStack, Item item) {
        register.registerShapedRecipe(itemStack, true, "XXX", "XXX", "XXX",
                'X', item);
    }

}