package io.github.mammut53.more_babies.client.model.geom;

import com.google.common.collect.ImmutableMap;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.monster.blaze.BabyBlazeModel;
import io.github.mammut53.more_babies.client.model.monster.illager.BabyIllagerModel;
import io.github.mammut53.more_babies.client.model.monster.witch.BabyWitchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.resources.Identifier;

import java.util.Map;

public class MoreBabiesModelLayers {

    public static final ModelLayerLocation BLAZE_BABY = register("blaze_baby");
    public static final ModelLayerLocation PILLAGER_BABY = register("pillager_baby");
    public static final ModelLayerLocation VINDICATOR_BABY = register("vindicator_baby");
    public static final ModelLayerLocation WITCH_BABY = register("witch_baby");

    public static final Map<ModelLayerLocation, LayerDefinition> LAYERS = ImmutableMap.<ModelLayerLocation, LayerDefinition>builder()
            .put(BLAZE_BABY, BabyBlazeModel.createBodyLayer())
            .put(PILLAGER_BABY, BabyIllagerModel.createBodyLayer())
            .put(VINDICATOR_BABY, BabyIllagerModel.createBodyLayer())
            .put(WITCH_BABY, BabyWitchModel.createBodyLayer())
            .build();

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

    private MoreBabiesModelLayers() {
        throw new UnsupportedOperationException();
    }

}
