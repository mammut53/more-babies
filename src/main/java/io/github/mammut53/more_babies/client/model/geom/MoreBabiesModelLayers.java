package io.github.mammut53.more_babies.client.model.geom;

import com.google.common.collect.ImmutableMap;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.animal.golem.BabyIronGolemModel;
import io.github.mammut53.more_babies.client.model.animal.golem.BabySnowGolemModel;
import io.github.mammut53.more_babies.client.model.monster.blaze.BabyBlazeModel;
import io.github.mammut53.more_babies.client.model.monster.enderman.BabyEndermanModel;
import io.github.mammut53.more_babies.client.model.monster.illager.BabyIllagerModel;
import io.github.mammut53.more_babies.client.model.monster.witch.BabyWitchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.resources.Identifier;

import java.util.Map;

public class MoreBabiesModelLayers {

    public static final ModelLayerLocation BLAZE_BABY = register("blaze_baby");
    public static final ModelLayerLocation ENDERMAN_BABY = register("enderman");
    public static final ModelLayerLocation EVOKER_BABY = register("evoker_baby");
    public static final ModelLayerLocation ILLUSIONER_BABY = register("illusioner_baby");
    public static final ModelLayerLocation IRON_GOLEM_BABY = register("iron_golem_baby");
    public static final ModelLayerLocation PILLAGER_BABY = register("pillager_baby");
    public static final ModelLayerLocation SNOW_GOLEM_BABY = register("snow_golem_baby");
    public static final ModelLayerLocation VINDICATOR_BABY = register("vindicator_baby");
    public static final ModelLayerLocation WITCH_BABY = register("witch_baby");

    public static final Map<ModelLayerLocation, LayerDefinition> LAYERS = ImmutableMap.<ModelLayerLocation, LayerDefinition>builder()
            .put(BLAZE_BABY, BabyBlazeModel.createBodyLayer())
            .put(ENDERMAN_BABY, BabyEndermanModel.createBodyLayer())
            .put(EVOKER_BABY, BabyIllagerModel.createBodyLayer())
            .put(ILLUSIONER_BABY, BabyIllagerModel.createBodyLayer())
            .put(IRON_GOLEM_BABY, BabyIronGolemModel.createBodyLayer())
            .put(PILLAGER_BABY, BabyIllagerModel.createBodyLayer())
            .put(SNOW_GOLEM_BABY, BabySnowGolemModel.createBodyLayer())
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
