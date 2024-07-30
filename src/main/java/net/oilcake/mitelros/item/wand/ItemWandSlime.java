package net.oilcake.mitelros.item.wand;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.entity.misc.EntityWandSlimeBall;

public class ItemWandSlime extends ItemWand {
    public ItemWandSlime(int id) {
        super(id, Material.copper);
    }

    @Override
    public void onUseSuccess(ItemStack item_stack, World world, EntityPlayer player) {
        world.playSoundAtEntity(player, "mob.slime.big1", 1.0F, 1.0F);
        world.spawnEntityInWorld(new EntityWandSlimeBall(world, player));
    }
}
