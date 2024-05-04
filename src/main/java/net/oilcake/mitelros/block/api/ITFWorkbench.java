package net.oilcake.mitelros.block.api;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;

public class ITFWorkbench extends BlockWorkbench {
    private Icon itf_workbenchIconTop;
    protected Icon[] itf_front_icons = new Icon[2];
    protected Icon[] itf_side_icons = new Icon[2];
    public static final Material[] itf_tool_materials = new Material[]{Materials.nickel, Materials.tungsten};

    public ITFWorkbench(int blockId) {
        super(blockId);
    }

    @Override
    public Icon getIcon(int side, int metadata) {
        if (side == 0) {
            return Block.planks.getBlockTextureFromSide(side);
        }
        if (side == 1) {
            return this.itf_workbenchIconTop;
        }
        if (side == 2 || side == 3) {
            return this.itf_front_icons[metadata];
        }
        return this.itf_side_icons[metadata];
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.itf_workbenchIconTop = par1IconRegister.registerIcon("crafting_table_top");
        for (int i = 0; i < this.itf_front_icons.length; ++i) {
            this.itf_front_icons[i] = par1IconRegister.registerIcon("crafting_table/" + ITFWorkbench.getToolMaterial(i).name + "/front");
            this.itf_side_icons[i] = par1IconRegister.registerIcon("crafting_table/" + ITFWorkbench.getToolMaterial(i).name + "/side");
        }
    }

    public static Material getToolMaterial(int metadata) {
        return itf_tool_materials[metadata];
    }

    public static float getCraftingSpeedModifier(Material benchMaterial) {
        if (benchMaterial == Material.flint || benchMaterial == Material.obsidian) {
            return 0.25F;
        } else if (benchMaterial == Material.copper || benchMaterial == Material.silver || benchMaterial == Material.gold) {
            return 0.4F;
        } else if (benchMaterial == Material.iron || benchMaterial == Materials.nickel) {
            return 0.5F;
        } else if (benchMaterial == Material.ancient_metal) {
            return 0.75F;
        } else if (benchMaterial == Material.mithril) {
            return 1.0F;
        } else if (benchMaterial == Materials.tungsten) {
            return 1.5F;
        } else if (benchMaterial == Material.adamantium) {
            return 2.5F;
        }
        return 0.0F;
    }

    public static Material getMaterialToCheckToolBenchHardnessAgainst(int metadata) {
        return switch (metadata) {
            case 0 -> Material.copper;
            case 1 -> Material.mithril;
            default -> Material.obsidian;//dummy
        };
    }

    @Override
    public String getMetadataNotes() {
        return "0 nickel, 1tungsten";
    }

    @Override
    public boolean isValidMetadata(int metadata) {
        return metadata < 2;
    }
}
