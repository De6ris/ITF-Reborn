package net.oilcake.mitelros.compat;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.oilcake.mitelros.config.ITFConfig;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> ITFConfig.getInstance().getConfigScreen(screen);
    }
}
