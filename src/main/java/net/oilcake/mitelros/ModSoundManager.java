package net.oilcake.mitelros;

import net.minecraft.SoundManager;

import java.util.HashSet;
import java.util.Set;

public class ModSoundManager {
    private static final Set<ModSoundRegistryEntry> soundRegistry = new HashSet<>();

    public static final ModSoundRegistryEntry damnation = new ModSoundRegistryEntry("miteitfrb:imported.damnation");
    public static final ModSoundRegistryEntry connected = new ModSoundRegistryEntry("miteitfrb:imported.connected");
    public static final ModSoundRegistryEntry spiderkingSay = new ModSoundRegistryEntry("miteitfrb:imported.mob.spiderking.say", 3);
    public static final ModSoundRegistryEntry spiderkingHit = new ModSoundRegistryEntry("miteitfrb:imported.mob.spiderking.hit", 4);
    public static final ModSoundRegistryEntry spiderkingDeath = new ModSoundRegistryEntry("miteitfrb:imported.mob.spiderking.death");
    public static final ModSoundRegistryEntry brainPower = new ModSoundRegistryEntry("miteitfrb:imported.meme.brainpower");
    public static final ModSoundRegistryEntry totemUse = new ModSoundRegistryEntry("miteitfrb:imported.random.totem_use");

    public static void registerSounds(SoundManager soundManager) {
        for (ModSoundRegistryEntry entry : soundRegistry) {
            int variantCount = entry.variantCount();

            if (variantCount > 1) {
                for (int i = 0; i < variantCount; i++) {
                    String path = entry.sound().replace('.', '/');
                    soundManager.addSound(path + (i + 1) + ".ogg");
                }
            } else {
                String path = entry.sound().replace('.', '/');
                soundManager.addSound(path + ".ogg");
            }
        }
    }

    static void addSoundToRegistry(ModSoundRegistryEntry entry) {
        soundRegistry.add(entry);
    }
}
