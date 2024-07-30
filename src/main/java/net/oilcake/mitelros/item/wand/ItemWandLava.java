package net.oilcake.mitelros.item.wand;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.oilcake.mitelros.entity.misc.EntityWandFireBall;
import net.oilcake.mitelros.item.Materials;

public class ItemWandLava extends ItemWand {
    public ItemWandLava(int id) {
        super(id, Materials.tungsten);
    }

    @Override
    public void onUseSuccess(ItemStack item_stack, World world, EntityPlayer player) {
        world.playSoundAtEntity(player, "mob.ghast.fireball", 1.0F, 1.0F);
        world.spawnEntityInWorld(new EntityWandFireBall(world, player));
    }
}
