package io.github.mammut53.more_babies;

import io.github.mammut53.more_babies.registry.MoreBabiesRegistry;
import net.fabricmc.api.ModInitializer;

public class MoreBabiesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MoreBabiesRegistry.registerModelLayers();
    }

}
