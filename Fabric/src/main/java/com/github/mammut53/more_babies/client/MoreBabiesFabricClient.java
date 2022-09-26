package com.github.mammut53.more_babies.client;

import com.github.mammut53.more_babies.client.renderer.entity.*;
import com.github.mammut53.more_babies.client.renderer.item.MoreBabiesItemProperties;
import com.github.mammut53.more_babies.registry.MoreBabiesFabricRegistry;
import com.github.mammut53.more_babies.registry.MoreBabiesItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class MoreBabiesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_BAT, BabyBatRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_BLAZE, BabyBlazeRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_CAVE_SPIDER, BabyCaveSpiderRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_CREEPER, BabyCreeperRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_DOLPHIN, BabyDolphinRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_ELDER_GUARDIAN, BabyElderGuardianRenderer::new);
        //EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_ENDER_DRAGON, EnderDragonRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_ENDERMAN, BabyEndermanRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_EVOKER, BabyEvokerRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_GHAST, BabyGhastRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_GIANT, (context) -> new GiantMobRenderer(context, 6.0F));
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_GLOW_SQUID, BabyGlowSquidRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_GUARDIAN, BabyGuardianRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_ILLUSIONER, BabyIllusionerRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_IRON_GOLEM, BabyIronGolemRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_PARROT, BabyParrotRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_PIGLIN_BRUTE,  (context) -> new BabyPiglinBruteRenderer(context, ModelLayers.PIGLIN_BRUTE, ModelLayers.PIGLIN_BRUTE_INNER_ARMOR, ModelLayers.PIGLIN_BRUTE_OUTER_ARMOR, false));
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_PILLAGER, BabyPillagerRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_RAVAGER, BabyRavagerRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_SALMON, BabySalmonRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_SHULKER, BabyShulkerRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_SKELETON, SkeletonRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_SNOW_GOLEM, BabySnowGolemRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_SPIDER, BabySpiderRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_SQUID, (context) -> new BabySquidRenderer<>(context, new SquidModel<>(context.bakeLayer(ModelLayers.SQUID))));
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_STRAY, StrayRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_VINDICATOR, BabyVindicatorRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_WANDERING_TRADER, BabyWanderingTraderRenderer::new);
        //EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_WARDEN, BabyWardenRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_WITCH, BabyWitchRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_WITHER, BabyWitherBossRenderer::new);
        EntityRendererRegistry.register(MoreBabiesFabricRegistry.BABY_WITHER_SKELETON, WitherSkeletonRenderer::new);

        ItemProperties.register(MoreBabiesItems.CURSED_CLOCK, new ResourceLocation( "time"), MoreBabiesItemProperties.CURSED_CLOCK_ITEM_PROPERTY_FUNCTION);
        ItemProperties.register(MoreBabiesItems.CURSED_CLOCK_ON_A_STICK, new ResourceLocation( "time"), MoreBabiesItemProperties.CURSED_CLOCK_ITEM_PROPERTY_FUNCTION);
    }
}
