package net.oilcake.mitelros.util.quality;

import net.minecraft.*;
import net.oilcake.mitelros.config.ITFConfig;

import java.util.ArrayList;
import java.util.List;

public enum EnumToolType {
    Tool(new RecordEntryMultiplier(EnumEffectEntry.Digging, 3.0F), new RecordEntryMultiplier(EnumEffectEntry.Blocking, 0.5F)),
    Armor(new RecordEntryMultiplier(EnumEffectEntry.Protection)),
    Sword(new RecordEntryMultiplier(EnumEffectEntry.Digging, 1.5F), new RecordEntryMultiplier(EnumEffectEntry.Blocking), new RecordEntryMultiplier(EnumEffectEntry.Attack, 0.5F)),
    Bow(new RecordEntryMultiplier(EnumEffectEntry.ArrowSpeed), new RecordEntryMultiplier(EnumEffectEntry.PullSpeed, 0.5F), new RecordEntryMultiplier(EnumEffectEntry.ArrowDamage));

    final List<RecordEntryMultiplier> entryMultipliers;

    EnumToolType(RecordEntryMultiplier... entryMultipliers) {
        this.entryMultipliers = List.of(entryMultipliers);
    }


    private static EnumToolType mapItemToEnumType(Item item) {
        if (item instanceof ItemArmor) {
            return EnumToolType.Armor;
        } else if (item instanceof ItemTool itemTool) {
            if (itemTool.getToolType().equals("sword")) {
                return EnumToolType.Sword;
            } else {
                return EnumToolType.Tool;
            }
        } else if (item instanceof ItemBow) {
            return Bow;
        } else {
//            System.out.println("undefined effect for item: " + item.getUnlocalizedName() + " id: " + item.itemID);
            return null;
        }
    }

    private boolean hasEffectEntry(EnumEffectEntry effectEntry) {
        for (RecordEntryMultiplier entryMultiplier : this.entryMultipliers) {
            if (entryMultiplier.effectEntry() == effectEntry) {
                return true;
            }
        }
        return false;
    }

    private float getEffectEntryMultiplier(EnumEffectEntry effectEntry) {
        for (RecordEntryMultiplier entryMultiplier : this.entryMultipliers) {
            if (entryMultiplier.effectEntry() == effectEntry) {
                return entryMultiplier.multiplier();
            }
        }
        return 1.0F;
    }

    public static float getMultiplierForEntry(ItemStack itemStack, EnumEffectEntry effectEntry) {
        if (itemStack == null) return 1.0F;
        EnumQuality quality = itemStack.getQuality();
        if (quality == null) return 1.0F;
        EnumToolType qualityType = mapItemToEnumType(itemStack.getItem());
        if (qualityType == null) return 1.0F;
        if (qualityType.hasEffectEntry(effectEntry)) {
            return getBasicQualityMultiplier(qualityType, quality, effectEntry);
        }
        return 1.0F;
    }

    private static float getBasicQualityMultiplier(EnumToolType enumToolType, EnumQuality quality, EnumEffectEntry effectEntry) {
        return 1.0F +
                enumToolType.getEffectEntryMultiplier(effectEntry) *
                        (ITFConfig.TagWorkOfHeaven.getBooleanValue() ? 0.02F : 0.01F) *
                        (switch (quality) {
                            case wretched -> -3;
                            case poor, average -> 0;
                            case fine -> 3;
                            case excellent -> 6;
                            case superb -> 9;
                            case masterwork -> 12;
                            case legendary -> 15;
                        });
    }

    public static List<String> getQualityInfo(Item item, EnumQuality quality) {
        List<String> info = new ArrayList<>();
        EnumToolType qualityType = mapItemToEnumType(item);
        if (qualityType == null) return info;
        float finalMultiplier;
        for (RecordEntryMultiplier entryMultiplier : qualityType.entryMultipliers) {
            finalMultiplier = getBasicQualityMultiplier(qualityType, quality, entryMultiplier.effectEntry());
            if (finalMultiplier != 1.0F) {
                info.add(entryMultiplier.effectEntry().getInfo(finalMultiplier));
            }
        }
        return info;
    }

}
