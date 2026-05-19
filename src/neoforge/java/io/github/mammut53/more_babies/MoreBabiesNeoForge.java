package io.github.mammut53.more_babies;

import eu.midnightdust.lib.config.MidnightConfig;
import io.github.mammut53.more_babies.config.MoreBabiesConfig;
import io.github.mammut53.more_babies.registry.MoreBabiesItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(MoreBabies.MOD_ID)
public class MoreBabiesNeoForge {

    public MoreBabiesNeoForge(final IEventBus modBus) {
        MidnightConfig.init(MoreBabies.MOD_ID, MoreBabiesConfig.class);

        MoreBabiesItems.register(modBus);
    }

}

