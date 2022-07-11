package com.github.mammut53.more_babies.client;

import com.github.mammut53.more_babies.MoreBabiesConstants;
import com.github.mammut53.more_babies.client.renderer.entity.*;
import com.github.mammut53.more_babies.registry.MoreBabiesForgeRegistry;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreBabiesConstants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoreBabiesForgeClient {

    @SubscribeEvent
    public static void onRegisterRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_BAT.get(), BabyBatRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_BLAZE.get(), BabyBlazeRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_CAVE_SPIDER.get(), BabyCaveSpiderRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_CREEPER.get(), BabyCreeperRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_DOLPHIN.get(), BabyDolphinRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_ELDER_GUARDIAN.get(), BabyElderGuardianRenderer::new);
        //event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_ENDER_DRAGON.get(), BabyEnderDragonRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_ENDERMAN.get(), BabyEndermanRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_EVOKER.get(), BabyEvokerRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_GHAST.get(), BabyGhastRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_GIANT.get(), (context) -> new GiantMobRenderer(context, 6.0F));
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_GLOW_SQUID.get(), BabyGlowSquidRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_GUARDIAN.get(), BabyGuardianRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_ILLUSIONER.get(), BabyIllusionerRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_IRON_GOLEM.get(), BabyIronGolemRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_PARROT.get(), BabyParrotRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_PIGLIN_BRUTE.get(),  (context) -> new BabyPiglinBruteRenderer(context, ModelLayers.PIGLIN_BRUTE, ModelLayers.PIGLIN_BRUTE_INNER_ARMOR, ModelLayers.PIGLIN_BRUTE_OUTER_ARMOR, false));
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_PILLAGER.get(), BabyPillagerRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_RAVAGER.get(), BabyRavagerRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_SALMON.get(), BabySalmonRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_SHULKER.get(), BabyShulkerRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_SKELETON.get(), SkeletonRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_SNOW_GOLEM.get(), BabySnowGolemRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_SPIDER.get(), BabySpiderRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_SQUID.get(), (context) -> new BabySquidRenderer<>(context, new SquidModel<>(context.bakeLayer(ModelLayers.SQUID))));
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_STRAY.get(), StrayRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_VINDICATOR.get(), BabyVindicatorRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_WANDERING_TRADER.get(), BabyWanderingTraderRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_WITCH.get(), BabyWitchRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_WITHER.get(), BabyWitherBossRenderer::new);
        event.registerEntityRenderer(MoreBabiesForgeRegistry.BABY_WITHER_SKELETON.get(), WitherSkeletonRenderer::new);
    }

}
