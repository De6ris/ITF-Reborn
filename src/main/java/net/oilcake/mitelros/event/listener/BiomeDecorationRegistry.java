package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.events.BiomeDecorationRegisterEvent;
import moddedmite.rustedironcore.api.event.handler.BiomeDecorationHandler;
import moddedmite.rustedironcore.api.world.Dimension;
import net.oilcake.mitelros.world.WorldGenSulphur;

import java.util.function.Consumer;

public class BiomeDecorationRegistry implements Consumer<BiomeDecorationRegisterEvent> {
    @Override
    public void accept(BiomeDecorationRegisterEvent event) {
        event.register(Dimension.NETHER, new WorldGenSulphur())
                .setChance(256)
                .setHeightSupplier(BiomeDecorationHandler.HeightSupplier.SURFACE);
    }
}
