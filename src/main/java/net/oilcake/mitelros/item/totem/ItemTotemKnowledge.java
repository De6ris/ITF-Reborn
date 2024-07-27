package net.oilcake.mitelros.item.totem;

import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityFX;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.api.ITFPlayer;
import net.oilcake.mitelros.config.ITFConfig;

public class ItemTotemKnowledge extends ItemTotem {

    public ItemTotemKnowledge(int id) {
        super(id, Material.ancient_metal, "totem");
    }

    @Override
    public boolean canTrigger(World world, EntityPlayer player) {
        return ITFConfig.TagTotemBlessing.getBooleanValue() || ((ITFPlayer) player).itf_GetMiscManager().getKnowledgeTotemCounter() < 10;
    }

    @Override
    public void specifiedEffect(EntityPlayer player) {
        for (int i = 0; i < 8; i++) {
            player.entityFX(EnumEntityFX.curse_effect_learned);
        }
        int xpToAdd = player.experience / 5;
        player.addExperience(Math.min(xpToAdd, 30000));
        if (!ITFConfig.TagTotemBlessing.getBooleanValue()) {
            ((ITFPlayer) player).itf_GetMiscManager().addKnowledgeTotemCounter();
        }
    }
}
