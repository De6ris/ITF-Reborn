package net.oilcake.mitelros.mixins.util;

import net.minecraft.ReferenceFileWriter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.File;

@Mixin(ReferenceFileWriter.class)
public class ReferenceFileWriterMixin {
    /**
     * @author Debris
     * @reason Because it errors
     */
    @Overwrite// Wont fix
    private static void writeItemReachFile(File dir) throws Exception {
    }
}
