package io.github.mammut53.more_babies.client;

import io.github.mammut53.more_babies.client.registry.MoreBabiesRegistry;
import net.fabricmc.api.ClientModInitializer;

public class MoreBabiesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MoreBabiesRegistry.registerModelLayers();
    }

}
