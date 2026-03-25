package io.github.mammut53.more_babies.registry;

import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.blaze.BabyBlazeModel;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public class MoreBabiesRegistry {

    private MoreBabiesRegistry() {
        throw new UnsupportedOperationException();
    }

    public static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(MoreBabiesModelLayers.BLAZE_BABY, BabyBlazeModel::createBodyLayer);
    }

}
