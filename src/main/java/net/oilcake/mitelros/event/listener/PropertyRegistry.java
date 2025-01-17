package net.oilcake.mitelros.event.listener;

import net.oilcake.mitelros.api.ITFPlugin;
import net.oilcake.mitelros.registry.ITFRegistryImpl;
import net.oilcake.mitelros.registry.VanillaPlugin;
import net.xiaoyu233.fml.FishModLoader;

public class PropertyRegistry implements Runnable {
    @Override
    public void run() {
        ITFRegistryImpl itfRegistry = new ITFRegistryImpl();
        new VanillaPlugin().register(itfRegistry);
        FishModLoader.getEntrypointContainers("itf-reborn", ITFPlugin.class)
                .forEach(x -> x.getEntrypoint().register(itfRegistry));
    }
}
