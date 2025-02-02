package net.oilcake.mitelros.item.totem;

import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityFX;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.config.ITFConfig;
import net.oilcake.mitelros.status.MiscManager;

public class ItemTotemKnowledge extends ItemTotem {

    public ItemTotemKnowledge(int id) {
        super(id, Material.ancient_metal, "totem");
    }

    @Override
    public boolean canTrigger(World world, EntityPlayer player) {
        return ITFConfig.TagTotemBlessing.getBooleanValue() || MiscManager.getInstance(player).getKnowledgeTotemCounter() < 10;
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.curse_effect_learned);
        }
        int xpToAdd = player.experience / 5;
        player.addExperience(Math.min(xpToAdd, ITFConfig.TotemKnowledgeLimit.getIntegerValue()));
        if (!ITFConfig.TagTotemBlessing.getBooleanValue()) {
            MiscManager.getInstance(player).addKnowledgeTotemCounter();
        }
    }
}
