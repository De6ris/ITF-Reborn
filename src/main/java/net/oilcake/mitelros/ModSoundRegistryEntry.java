package net.oilcake.mitelros;

public record ModSoundRegistryEntry(String sound, int variantCount) {
    public ModSoundRegistryEntry(String sound) {
        this(sound, 1);
    }

    public ModSoundRegistryEntry(String sound, int variantCount) {
        if (variantCount < 1) {
            throw new IllegalArgumentException("Variant count cannot be less than one!");
        }

        this.sound = sound;
        this.variantCount = variantCount;
        ModSoundManager.addSoundToRegistry(this);
    }
}
