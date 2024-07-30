package net.oilcake.mitelros.item.wand;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.oilcake.mitelros.entity.misc.EntityWandIceBall;
import net.oilcake.mitelros.item.Materials;

public class ItemWandFreeze extends ItemWand {
    public ItemWandFreeze(int id) {
        super(id, Materials.nickel);
    }

    @Override
    public void onUseSuccess(ItemStack item_stack, World world, EntityPlayer player) {
        world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        world.spawnEntityInWorld(new EntityWandIceBall(world, player));
    }
}
