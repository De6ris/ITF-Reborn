package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.listener.IArmorModelListener;
import net.minecraft.ItemArmor;
import net.minecraft.Material;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.ITFStart;
import net.oilcake.mitelros.item.Materials;

import java.util.HashMap;
import java.util.Map;

public class ArmorModelListener implements IArmorModelListener {
    private final Map<String, ResourceLocation> ITF_TEXTURE_MAP = new HashMap<>();

    @Override
    public ResourceLocation getArmorTexture(ItemArmor itemArmor, int slotIndex) {
        Material material = itemArmor.getArmorMaterial();
        if (!Materials.ARMOR_MATERIALS.contains(material)) {
            return null;
        }

        String path = String.format("textures/models/armor/%s_layer_%d.png", itemArmor.getTextureFilenamePrefix(), slotIndex == 2 ? 2 : 1);
        ResourceLocation identifier = this.ITF_TEXTURE_MAP.get(path);
        if (identifier == null) {
            identifier = new ResourceLocation(ITFStart.ResourceDomain, path);
            this.ITF_TEXTURE_MAP.put(path, identifier);
        }
        return identifier;
    }
}
