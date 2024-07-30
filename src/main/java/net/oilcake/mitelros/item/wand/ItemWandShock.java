package net.oilcake.mitelros.item.wand;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.entity.misc.EntityWandShockWave;

public class ItemWandShock extends ItemWand {
    public ItemWandShock(int id) {
        super(id, Material.ancient_metal);
    }

    @Override
    public void onUseSuccess(ItemStack item_stack, World world, EntityPlayer player) {
        world.playSoundAtEntity(player, "ambient.weather.thunder", 1.0F, 1.0F);
        world.spawnEntityInWorld(new EntityWandShockWave(world, player));
    }
}
