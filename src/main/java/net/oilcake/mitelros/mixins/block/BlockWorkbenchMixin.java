package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ITFConfig;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockWorkbench.class)
public abstract class BlockWorkbenchMixin extends Block {
    protected Icon[] front_icons = new Icon[17];

    protected Icon[] side_icons = new Icon[17];

    private final Random random = new Random();

    @Shadow
    @Mutable
    @Final
    private static Material[] tool_materials;

    protected BlockWorkbenchMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
    private void injectInit(int par1, CallbackInfo callbackInfo) {
        setMinHarvestLevel(1);
    }

    @Overwrite
    public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
        if (world == null) {
            return true;
        } else {
            return world.getBlockMetadata(x, y, z) > 3 && world.getBlockMetadata(x, y, z) < 13;
        }

    }

    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (ITFConfig.TagBenchingV2.get() || info.wasExploded()) {
            if (info.wasExploded()) {
                int quantity_drops = 2 + (int) (this.random.nextFloat() * 4.0F);
                if (info.getMetadata() < 4) {
                    dropBlockAsEntityItem(info, Item.chipFlint.itemID, 0, quantity_drops / 2, 1.0F);
                } else if (info.getMetadata() > 12) {
                    if (this.random.nextInt(16) == 0) {
                        dropBlockAsEntityItem(info, Block.obsidian.blockID);
                    } else {
                        dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, quantity_drops, 1.0F);
                    }
                } else {
                    dropBlockAsEntityItem(info, Item.sinew.itemID, 0, 1, 1.5F);
                    if (info.getMetadata() == 4) {
                        if (this.random.nextInt(8) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.copper));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Material.copper)).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 5) {
                        if (this.random.nextInt(8) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.silver));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Material.silver)).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 6) {
                        if (this.random.nextInt(8) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.gold));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Material.gold)).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 7) {
                        if (this.random.nextInt(6) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.iron));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Material.iron)).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 8) {
                        if (this.random.nextInt(4) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.ancient_metal));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Material.ancient_metal)).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 9) {
                        if (this.random.nextInt(3) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.mithril));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Material.mithril)).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 10) {
                        this.random.nextInt(1);
                        dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.adamantium));
                    } else if (info.getMetadata() == 11) {
                        if (this.random.nextInt(6) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.nickel));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Materials.nickel)).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 12) {
                        if (this.random.nextInt(2) == 0) {
                            dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.tungsten));
                        } else {
                            dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, Materials.tungsten)).itemID, 0, quantity_drops, 1.0F);
                        }
                    }
                }
                dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F);
            } else if (info.getMetadata() < 4) {
                dropBlockAsEntityItem(info, Item.knifeFlint.itemID);
                dropBlockAsEntityItem(info, Block.wood.blockID, info.getMetadata(), 1, 1.0F);
            } else if (info.getMetadata() > 12) {
                dropBlockAsEntityItem(info, Item.knifeObsidian.itemID);
                dropBlockAsEntityItem(info, Block.wood.blockID, info.getMetadata() - 13, 1, 1.0F);
            } else {
                dropBlockAsEntityItem(info, Item.leather.itemID, 0, 1, 1.0F);
                if (info.getMetadata() == 4) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.copper));
                } else if (info.getMetadata() == 5) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.silver));
                } else if (info.getMetadata() == 6) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.gold));
                } else if (info.getMetadata() == 7) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.iron));
                } else if (info.getMetadata() == 8) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.ancient_metal));
                } else if (info.getMetadata() == 9) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.mithril));
                } else if (info.getMetadata() == 10) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.adamantium));
                } else if (info.getMetadata() == 11) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.nickel));
                } else if (info.getMetadata() == 12) {
                    dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.tungsten));
                }
                dropBlockAsEntityItem(info, Block.planks.blockID, 0);
                dropBlockAsEntityItem(info, Item.stick.itemID);
            }
            return 0;
        }
        return super.dropBlockAsEntityItem(info);
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
    }

    @Inject(method = {"<clinit>()V"}, at = {@At("RETURN")})
    private static void injectClinit(CallbackInfo callback) {
        tool_materials = new Material[]{
                Material.flint, Material.copper, Material.silver, Material.gold, Material.iron, Material.ancient_metal, Material.mithril, Material.adamantium, Materials.nickel, Materials.tungsten,
                Material.obsidian};
    }

    @ModifyConstant(method = "getIcon", constant = @Constant(intValue = 10))
    private int modify(int constant) {
        return 12;
    }

    @ModifyConstant(method = "getIcon", constant = @Constant(intValue = 11))
    private int modify_1(int constant) {
        return 13;
    }

    @ModifyConstant(method = "getToolMaterial", constant = @Constant(intValue = 10))
    private static int modify_2(int constant) {
        return 12;
    }

    @ModifyConstant(method = "getToolMaterial", constant = @Constant(intValue = 8))
    private static int modify_3(int constant) {
        return 10;
    }

    @ModifyConstant(method = "isValidMetadata", constant = @Constant(intValue = 15))
    private int modify_4(int constant) {
        return 17;
    }

    @Inject(method = "registerIcons", at = @At("TAIL"))
    private void inject(IconRegister par1IconRegister, CallbackInfo ci) {
        int i = this.front_icons.length - 3;
        this.front_icons[i] = par1IconRegister.registerIcon("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/front");
        this.side_icons[i] = par1IconRegister.registerIcon("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/side");
    }
}
