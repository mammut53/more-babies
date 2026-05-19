package io.github.mammut53.more_babies;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import io.github.mammut53.more_babies.registry.MoreBabiesItems;
import net.fabricmc.api.ModInitializer;

public class MoreBabiesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MidnightConfig.init(MoreBabies.MOD_ID, MoreBabiesConfig.class);

        MoreBabiesItems.init();
    }

}
