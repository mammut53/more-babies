package io.github.mammut53.more_babies.registry;

import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.Map;

public class MoreBabiesRegistry {

    private MoreBabiesRegistry() {
        throw new UnsupportedOperationException();
    }

    public static void registerModelLayers() {
        for (final Map.Entry<ModelLayerLocation, LayerDefinition> entry : MoreBabiesModelLayers.LAYERS.entrySet()) {
            ModelLayerRegistry.registerModelLayer(entry.getKey(), entry::getValue);
        }
    }

}
