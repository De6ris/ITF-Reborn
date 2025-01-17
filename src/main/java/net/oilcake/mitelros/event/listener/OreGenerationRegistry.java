package net.oilcake.mitelros.event.listener;

import moddedmite.rustedironcore.api.event.events.OreGenerationRegisterEvent;
import moddedmite.rustedironcore.api.world.Dimension;
import moddedmite.rustedironcore.api.world.MinableWorldGen;
import net.oilcake.mitelros.block.Blocks;

import java.util.function.Consumer;

public class OreGenerationRegistry implements Consumer<OreGenerationRegisterEvent> {
    @Override
    public void accept(OreGenerationRegisterEvent event) {
        MinableWorldGen nickelGen = new MinableWorldGen(Blocks.oreNickel.blockID, 6)
                .setMinVeinHeight((world, minableWorldGen) -> 0)
                .setMaxVeinHeight(((world, minableWorldGen) -> world.isUnderworld() ? 255 : 48))
                .setRandomVeinHeight(MinableWorldGen.Common);

        MinableWorldGen tungstenGen = new MinableWorldGen(Blocks.oreTungsten.blockID, 3)
                .setMinVeinHeight((world, minableWorldGen) -> 0)
                .setMaxVeinHeight((world, minableWorldGen) -> world.isUnderworld() ? 255 : 32)
                .setRandomVeinHeight((world, random, minableWorldGen) -> {
                    if (world.isUnderworld()) return random.nextInt(142);
                    return MinableWorldGen.Common.getVeinHeight(world, random, minableWorldGen);// in overworld
                });

        MinableWorldGen azuriteGen = new MinableWorldGen(Blocks.blockAzurite.blockID, 4)
                .setMinVeinHeight((world, minableWorldGen) -> 32)
                .setMaxVeinHeight((world, minableWorldGen) -> 96)
                .setRandomVeinHeight(MinableWorldGen.Common);

        event.register(Dimension.OVERWORLD, nickelGen, 10, true);
        event.register(Dimension.OVERWORLD, azuriteGen, 30, true);

        event.register(Dimension.UNDERWORLD, nickelGen, 30, true);
        event.register(Dimension.UNDERWORLD, tungstenGen, 5, true);
    }
}
