package net.oilcake.mitelros.mixins.block;

import java.util.Random;
import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockConstants;
import net.minecraft.BlockWorkbench;
import net.minecraft.EntityLivingBase;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Item;
import net.minecraft.ItemIngot;
import net.minecraft.ItemNugget;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockWorkbench.class})
public class BlockWorkbenchMixin extends Block {
  protected Icon[] front_icons = new Icon[17];
  
  protected Icon[] side_icons = new Icon[17];
  
  private final Random random = new Random();
  
  @Shadow
  @Mutable
  @Final
  private static Material[] tool_materials;
  
  @Shadow
  private Icon workbenchIconTop;
  
  @Shadow
  private Icon icon_flint_top;
  
  @Shadow
  private Icon icon_obsidian_top;
  
  protected BlockWorkbenchMixin(int par1, Material par2Material, BlockConstants constants) {
    super(par1, par2Material, constants);
  }
  
  @Inject(method = {"<init>(I)V"}, at = {@At("RETURN")})
  private void injectInit(int par1, CallbackInfo callbackInfo) {
    setMinHarvestLevel(1);
  }
  
  @Overwrite
  public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
    return (world.getBlockMetadata(x, y, z) > 3 && world.getBlockMetadata(x, y, z) < 13);
  }
  
  public int dropBlockAsEntityItem(BlockBreakInfo info) {
    if (((Boolean)ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue).booleanValue() || info.wasExploded()) {
      if (info.wasExploded()) {
        int quantity_drops = 2 + (int)(this.random.nextFloat() * 4.0F);
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
              dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, (Material)Materials.nickel));
            } else {
              dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, (Material)Materials.nickel)).itemID, 0, quantity_drops, 1.0F);
            } 
          } else if (info.getMetadata() == 12) {
            if (this.random.nextInt(2) == 0) {
              dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, (Material)Materials.tungsten));
            } else {
              dropBlockAsEntityItem(info, (Item.getMatchingItem(ItemNugget.class, (Material)Materials.tungsten)).itemID, 0, quantity_drops, 1.0F);
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
          dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, (Material)Materials.nickel));
        } else if (info.getMetadata() == 12) {
          dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, (Material)Materials.tungsten));
        } 
        dropBlockAsEntityItem(info, Block.planks.blockID, 0);
        dropBlockAsEntityItem(info, Item.stick.itemID);
      } 
      return 0;
    } 
    return super.dropBlockAsEntityItem(info);
  }
  
  protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {}
  
  @Inject(method = {"<clinit>()V"}, at = {@At("RETURN")})
  private static void injectClinit(CallbackInfo callback) {
    tool_materials = new Material[] { 
        Material.flint, Material.copper, Material.silver, Material.gold, Material.iron, Material.ancient_metal, Material.mithril, Material.adamantium, (Material)Materials.nickel, (Material)Materials.tungsten, 
        Material.obsidian };
  }
  
  @Overwrite
  public Icon getIcon(int side, int metadata) {
    if (metadata < 4)
      return (side == 1) ? this.icon_flint_top : Block.wood.getIcon(side, metadata); 
    if (metadata >= 13)
      return (side == 1) ? this.icon_obsidian_top : Block.wood.getIcon(side, metadata - 13); 
    if (side == 0)
      return Block.planks.getBlockTextureFromSide(side); 
    if (side == 1)
      return this.workbenchIconTop; 
    return (side != 2 && side != 3) ? this.side_icons[metadata] : this.front_icons[metadata];
  }
  
  @Overwrite
  public static Material getToolMaterial(int metadata) {
    if (metadata > 12)
      return tool_materials[10]; 
    return (metadata < 4) ? tool_materials[0] : tool_materials[metadata - 3];
  }
  
  @Overwrite
  public static ItemStack getBlockComponent(int metadata) {
    Material tool_material = getToolMaterial(metadata);
    if (tool_material == Material.flint)
      return new ItemStack((Block)Block.wood, 1, metadata); 
    return (tool_material == Material.obsidian) ? new ItemStack((Block)Block.wood, 1, metadata - 11) : null;
  }
  
  @Overwrite
  public boolean isValidMetadata(int metadata) {
    return (metadata >= 0 && metadata < 17);
  }
  
  @Overwrite
  public void registerIcons(IconRegister par1IconRegister) {
    this.icon_flint_top = par1IconRegister.registerIcon("crafting_table/flint/top");
    this.icon_obsidian_top = par1IconRegister.registerIcon("crafting_table/obsidian/top");
    this.workbenchIconTop = par1IconRegister.registerIcon("crafting_table_top");
    for (int i = 4; i < this.front_icons.length - 3; i++) {
      this.front_icons[i] = par1IconRegister.registerIcon("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/front");
      this.side_icons[i] = par1IconRegister.registerIcon("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/side");
    } 
  }
}
