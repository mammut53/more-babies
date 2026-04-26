package io.github.mammut53.more_babies.client.model.geom;

import com.google.common.collect.ImmutableMap;
import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

public class MoreBabiesModelLayers {

    public static final ModelLayerLocation BLAZE_BABY = register("blaze_baby");
    public static final ModelLayerLocation BOGGED_BABY = register("bogged_baby");
    public static final ArmorModelSet<ModelLayerLocation> BOGGED_BABY_ARMOR = registerArmorSet("bogged_baby");
    public static final ModelLayerLocation BOGGED_BABY_OUTER_LAYER = register("bogged_baby", "outer");
    public static final ModelLayerLocation CAVE_SPIDER_BABY = register("cave_spider_baby");
    public static final ModelLayerLocation CREEPER_BABY = register("creeper_baby");
    public static final ModelLayerLocation CREEPER_BABY_ARMOR = register("creeper_baby_armor");
    public static final ModelLayerLocation ELDER_GUARDIAN = register("elder_guardian");
    public static final ModelLayerLocation ENDERMAN_BABY = register("enderman");
    public static final ModelLayerLocation EVOKER_BABY = register("evoker_baby");
    public static final ModelLayerLocation GHAST_BABY = register("ghast_baby");
    public static final ModelLayerLocation GIANT_BABY = register("giant");
    public static final ArmorModelSet<ModelLayerLocation> GIANT_BABY_ARMOR = registerArmorSet("giant");
    public static final ModelLayerLocation GUARDIAN = register("guardian");
    public static final ModelLayerLocation ILLUSIONER_BABY = register("illusioner_baby");
    public static final ModelLayerLocation IRON_GOLEM_BABY = register("iron_golem_baby");
    public static final ModelLayerLocation ZOMBIE_NAUTILUS_BABY = register("zombie_nautilus_baby");
    public static final ModelLayerLocation ZOMBIE_NAUTILUS_CORAL_BABY = register("zombie_nautilus_coral_baby");
    public static final ModelLayerLocation PARCHED_BABY = register("parched_baby");
    public static final ArmorModelSet<ModelLayerLocation> PARCHED_BABY_ARMOR = registerArmorSet("parched_baby");
    public static final ModelLayerLocation PARCHED_OUTER_LAYER_BABY = register("parched_baby", "outer");
    public static final ModelLayerLocation PIGLIN_BRUTE_BABY = register("piglin_brute_baby");
    public static final ArmorModelSet<ModelLayerLocation> PIGLIN_BRUTE_BABY_ARMOR = registerArmorSet("piglin_brute_baby");
    public static final ModelLayerLocation PILLAGER_BABY = register("pillager_baby");
    public static final ModelLayerLocation SHULKER_BABY = register("shulker_baby");
    public static final ModelLayerLocation SKELETON_BABY = register("skeleton_baby");
    public static final ArmorModelSet<ModelLayerLocation> SKELETON_BABY_ARMOR = registerArmorSet("skeleton_baby");
    public static final ModelLayerLocation SNOW_GOLEM_BABY = register("snow_golem_baby");
    public static final ModelLayerLocation SPIDER_BABY = register("spider_baby");
    public static final ModelLayerLocation STRAY = register("stray_baby");
    public static final ArmorModelSet<ModelLayerLocation> STRAY_BABY_ARMOR = registerArmorSet("stray_baby");
    public static final ModelLayerLocation STRAY_BABY_OUTER_LAYER = register("stray_baby", "outer");
    public static final ModelLayerLocation VINDICATOR_BABY = register("vindicator_baby");
    public static final ModelLayerLocation WITCH_BABY = register("witch_baby");
    public static final ModelLayerLocation WITHER_SKELETON_BABY = register("wither_skeleton_baby");
    public static final ArmorModelSet<ModelLayerLocation> WITHER_SKELETON_BABY_ARMOR = registerArmorSet("wither_skeleton");

    public static final Map<ModelLayerLocation, ModelLayerLocation> GUARDIAN_MAP = createGuardianBabyMap();
    public static final Map<ModelLayerLocation, ModelLayerLocation> SPIDER_MAP = createSpiderBabyMap();

    private static Map<ModelLayerLocation, ModelLayerLocation> createGuardianBabyMap() {
        final Map<ModelLayerLocation, ModelLayerLocation> result = new HashMap<>();
        result.put(ModelLayers.ELDER_GUARDIAN, ELDER_GUARDIAN);
        result.put(ModelLayers.GUARDIAN, GUARDIAN);
        return ImmutableMap.copyOf(result);
    }

    private static Map<ModelLayerLocation, ModelLayerLocation> createSpiderBabyMap() {
        final Map<ModelLayerLocation, ModelLayerLocation> result = new HashMap<>();
        result.put(ModelLayers.SPIDER, SPIDER_BABY);
        result.put(ModelLayers.CAVE_SPIDER, CAVE_SPIDER_BABY);
        return ImmutableMap.copyOf(result);
    }

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
