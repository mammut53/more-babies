package io.github.mammut53.more_babies.client.model.geom;

import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.resources.Identifier;

public class MoreBabiesModelLayers {

    private MoreBabiesModelLayers() {
        throw new UnsupportedOperationException();
    }

    public static final ModelLayerLocation BLAZE_BABY = register("blaze_baby");

    private static ModelLayerLocation register(final String model) {
        return register(model, "main");
    }

    private static ModelLayerLocation register(final String model, final String layer) {
        return createLocation(model, layer);
    }

    private static ModelLayerLocation createLocation(final String model, final String layer) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, model), layer);
    }

    private static ArmorModelSet<ModelLayerLocation> registerArmorSet(final String modelId) {
        return new ArmorModelSet<>(register(modelId, "helmet"), register(modelId, "chestplate"), register(modelId, "leggings"), register(modelId, "boots"));
    }

}
