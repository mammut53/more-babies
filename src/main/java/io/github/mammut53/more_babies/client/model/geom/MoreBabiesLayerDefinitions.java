package io.github.mammut53.more_babies.client.model.geom;

import com.google.common.collect.ImmutableMap;
import io.github.mammut53.more_babies.client.model.BabyHumanoidModel;
import io.github.mammut53.more_babies.client.model.animal.golem.BabyIronGolemModel;
import io.github.mammut53.more_babies.client.model.animal.golem.BabySnowGolemModel;
import io.github.mammut53.more_babies.client.model.monster.blaze.BabyBlazeModel;
import io.github.mammut53.more_babies.client.model.monster.breeze.BabyBreezeModel;
import io.github.mammut53.more_babies.client.model.monster.creaking.BabyCreakingModel;
import io.github.mammut53.more_babies.client.model.monster.creeper.BabyCreeperModel;
import io.github.mammut53.more_babies.client.model.monster.enderman.BabyEndermanModel;
import io.github.mammut53.more_babies.client.model.monster.guardian.BabyGuardianModel;
import io.github.mammut53.more_babies.client.model.monster.illager.BabyIllagerModel;
import io.github.mammut53.more_babies.client.model.monster.ravager.BabyRavagerModel;
import io.github.mammut53.more_babies.client.model.monster.shulker.BabyShulkerModel;
import io.github.mammut53.more_babies.client.model.monster.skeleton.BabyBoggedModel;
import io.github.mammut53.more_babies.client.model.monster.skeleton.BabySkeletonModel;
import io.github.mammut53.more_babies.client.model.monster.spider.BabySpiderModel;
import io.github.mammut53.more_babies.client.model.monster.warden.BabyWardenModel;
import io.github.mammut53.more_babies.client.model.monster.witch.BabyWitchModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.model.animal.nautilus.NautilusModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.model.monster.ghast.GhastModel;
import net.minecraft.client.model.monster.piglin.AbstractPiglinModel;
import net.minecraft.client.model.monster.piglin.BabyPiglinModel;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;

import java.util.Map;

public class MoreBabiesLayerDefinitions {

    private static final CubeDeformation BABY_OUTER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.5F, 0.3F);
    private static final CubeDeformation BABY_INNER_ARMOR_DEFORMATION = new CubeDeformation(-0.1F, 0.3F, 0.3F);
    private static final CubeDeformation BABY_PIGLIN_INNER_ARMOR_DEFORMATION = new CubeDeformation(0.7F);
    private static final CubeDeformation BABY_PIGLIN_OUTER_ARMOR_DEFORMATION = new CubeDeformation(0.7F);
    private static final PartPose BABY_PIGLIN_ARMOR_ARM_OFFSET = new PartPose(0.5F, -0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

    private MoreBabiesLayerDefinitions() {
        throw new UnsupportedOperationException();
    }

    public static Map<ModelLayerLocation, LayerDefinition> createRoots() {
        final ImmutableMap.Builder<ModelLayerLocation, LayerDefinition> result = ImmutableMap.builder();

        final ArmorModelSet<LayerDefinition> humanoidBabyArmor = HumanoidModel.createBabyArmorMeshSet(
                BABY_INNER_ARMOR_DEFORMATION,
                BABY_OUTER_ARMOR_DEFORMATION,
                PartPose.ZERO
        ).map(mesh -> LayerDefinition.create(mesh, 64, 64));

        final ArmorModelSet<LayerDefinition> piglinBabyArmor = AbstractPiglinModel.createBabyArmorMeshSet(
                BABY_PIGLIN_INNER_ARMOR_DEFORMATION,
                BABY_PIGLIN_OUTER_ARMOR_DEFORMATION,
                BABY_PIGLIN_ARMOR_ARM_OFFSET
        ).map(mesh -> LayerDefinition.create(mesh, 64, 64));

        final LayerDefinition babyZombieLayer = BabyZombieModel.createBodyLayer(CubeDeformation.NONE);
        final LayerDefinition skeletonBabyBodyLayer = BabySkeletonModel.createBodyLayer();
        final LayerDefinition spiderBabyBodyLayer = BabySpiderModel.createSpiderBodyLayer();

        result.put(MoreBabiesModelLayers.BLAZE_BABY, BabyBlazeModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.BOGGED_BABY, BabyBoggedModel.createBodyLayer());
        MoreBabiesModelLayers.BOGGED_BABY_ARMOR.putFrom(humanoidBabyArmor, result);
        result.put(MoreBabiesModelLayers.BOGGED_BABY_OUTER_LAYER, LayerDefinition.create(BabyHumanoidModel.createMesh(new CubeDeformation(0.2F), 0.0F), 64, 32));
        result.put(MoreBabiesModelLayers.BREEZE_BABY, BabyBreezeModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.BREEZE_BABY_WIND, BabyBreezeModel.createWindLayer());
        result.put(MoreBabiesModelLayers.BREEZE_BABY_EYES, BabyBreezeModel.createEyesLayer());
        result.put(MoreBabiesModelLayers.CREAKING_BABY, BabyCreakingModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.CREAKING_BABY_EYES, BabyCreakingModel.createEyesLayer());
        result.put(MoreBabiesModelLayers.CAVE_SPIDER_BABY, spiderBabyBodyLayer.apply(MeshTransformer.scaling(0.7F)));
        result.put(MoreBabiesModelLayers.CREEPER_BABY, BabyCreeperModel.createBodyLayer(CubeDeformation.NONE));
        result.put(MoreBabiesModelLayers.CREEPER_BABY_ARMOR, BabyCreeperModel.createBodyLayer(new CubeDeformation(1.0F)));
        result.put(MoreBabiesModelLayers.ELDER_GUARDIAN, BabyGuardianModel.createElderGuardianLayer());
        result.put(MoreBabiesModelLayers.ENDERMAN_BABY, BabyEndermanModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.EVOKER_BABY, BabyIllagerModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.GHAST_BABY, GhastModel.createBodyLayer().apply(HappyGhastModel.BABY_TRANSFORMER));
        final MeshTransformer giantScale = MeshTransformer.scaling(6.0F);
        result.put(MoreBabiesModelLayers.GIANT_BABY, babyZombieLayer.apply(giantScale));
        MoreBabiesModelLayers.GIANT_BABY_ARMOR.putFrom(humanoidBabyArmor.map(layer -> layer.apply(giantScale)), result);
        result.put(MoreBabiesModelLayers.GUARDIAN, BabyGuardianModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.ILLUSIONER_BABY, BabyIllagerModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.IRON_GOLEM_BABY, BabyIronGolemModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.ZOMBIE_NAUTILUS_BABY, NautilusModel.createBabyBodyLayer());
        result.put(MoreBabiesModelLayers.ZOMBIE_NAUTILUS_CORAL_BABY, NautilusModel.createBabyBodyLayer());
        result.put(MoreBabiesModelLayers.PARCHED_BABY, BabySkeletonModel.createSingleModelDualBodyLayer());
        MoreBabiesModelLayers.PARCHED_BABY_ARMOR.putFrom(humanoidBabyArmor, result);
        result.put(MoreBabiesModelLayers.PARCHED_OUTER_LAYER_BABY, LayerDefinition.create(BabyHumanoidModel.createMesh(new CubeDeformation(0.25F), 0.0F), 64, 32));
        result.put(MoreBabiesModelLayers.PIGLIN_BRUTE_BABY, BabyPiglinModel.createBodyLayer());
        MoreBabiesModelLayers.PIGLIN_BRUTE_BABY_ARMOR.putFrom(piglinBabyArmor, result);
        result.put(MoreBabiesModelLayers.PILLAGER_BABY, BabyIllagerModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.RAVAGER_BABY, BabyRavagerModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.SHULKER_BABY, BabyShulkerModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.SKELETON_BABY, skeletonBabyBodyLayer);
        MoreBabiesModelLayers.SKELETON_BABY_ARMOR.putFrom(humanoidBabyArmor, result);
        result.put(MoreBabiesModelLayers.SNOW_GOLEM_BABY, BabySnowGolemModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.SPIDER_BABY, spiderBabyBodyLayer);
        result.put(MoreBabiesModelLayers.STRAY, skeletonBabyBodyLayer);
        MoreBabiesModelLayers.STRAY_BABY_ARMOR.putFrom(humanoidBabyArmor, result);
        result.put(MoreBabiesModelLayers.STRAY_BABY_OUTER_LAYER, LayerDefinition.create(BabyHumanoidModel.createMesh(new CubeDeformation(0.25F), 0.0F), 64, 32));
        result.put(MoreBabiesModelLayers.VINDICATOR_BABY, BabyIllagerModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.WARDEN_BABY, BabyWardenModel.createBodyLayer());
        result.put(MoreBabiesModelLayers.WARDEN_BABY_TENDRILS, BabyWardenModel.createTendrilsLayer());
        result.put(MoreBabiesModelLayers.WARDEN_BABY_HEART, BabyWardenModel.createHeartLayer());
        result.put(MoreBabiesModelLayers.WARDEN_BABY_BIOLUMINESCENT, BabyWardenModel.createBioluminescentLayer());
        result.put(MoreBabiesModelLayers.WARDEN_BABY_PULSATING_SPOTS, BabyWardenModel.createPulsatingSpotsLayer());
        result.put(MoreBabiesModelLayers.WITCH_BABY, BabyWitchModel.createBodyLayer());
        MeshTransformer witherSkeletonScale = MeshTransformer.scaling(1.2F);
        result.put(MoreBabiesModelLayers.WITHER_SKELETON_BABY, skeletonBabyBodyLayer.apply(witherSkeletonScale));
        MoreBabiesModelLayers.WITHER_SKELETON_BABY_ARMOR.putFrom(humanoidBabyArmor.map(layer -> layer.apply(witherSkeletonScale)), result);

        return result.build();
    }

}
