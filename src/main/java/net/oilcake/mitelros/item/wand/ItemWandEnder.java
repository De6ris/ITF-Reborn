package net.oilcake.mitelros.item.wand;

import net.minecraft.*;

public class ItemWandEnder extends ItemWand {
    public ItemWandEnder(int id) {
        super(id, Material.adamantium);
    }

    @Override
    public int getTicksForMaxPull() {
        return 160;
    }

    @Override
    public void onUseSuccess(ItemStack item_stack, World world, EntityPlayer player) {
        world.playSoundAtEntity(player, "random.bow", 0.5f, 0.4f / (itemRand.nextFloat() * 0.4f + 0.8f));
        world.spawnEntityInWorld(new EntityEnderPearl(world, player));
    }
}
