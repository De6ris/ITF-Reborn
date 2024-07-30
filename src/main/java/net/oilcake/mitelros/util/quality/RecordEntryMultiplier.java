package net.oilcake.mitelros.util.quality;

public record RecordEntryMultiplier(EnumEffectEntry effectEntry, float multiplier) {
    public RecordEntryMultiplier(EnumEffectEntry effectEntry) {
        this(effectEntry, 1.0F);
    }
}
