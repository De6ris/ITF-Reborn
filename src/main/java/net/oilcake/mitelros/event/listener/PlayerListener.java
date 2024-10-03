package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.events.PlayerLoggedInEvent;
import moddedmite.rustedironcore.api.event.listener.IPlayerEventListener;
import net.minecraft.ItemStack;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.item.ItemGuideBook;
import net.oilcake.mitelros.item.Items;

public class PlayerListener implements IPlayerEventListener {
    @Override
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        ServerPlayer player = event.player();
        player.setHealth(player.getHealth());
        ((ITFPlayer) player).itf_GetMiscManager().broadcast();
        if (event.firstLogin()) {
            ItemStack guide = new ItemStack(Items.guide);
            guide.setTagCompound(ItemGuideBook.generateBookContents());
            player.inventory.addItemStackToInventoryOrDropIt(guide);
        }
    }
}
