package com.github.mammut53.more_babies.client.renderer.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PiglinRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class BabyPiglinBruteRenderer extends PiglinRenderer {

    public BabyPiglinBruteRenderer(EntityRendererProvider.Context context, ModelLayerLocation modelLayerLocation, ModelLayerLocation modelLayerLocation2, ModelLayerLocation modelLayerLocation3, boolean bl) {
        super(context, modelLayerLocation, modelLayerLocation2, modelLayerLocation3, bl);
    }

    @Override
    public ResourceLocation getTextureLocation(Mob mob) {
        return new ResourceLocation("textures/entity/piglin/piglin_brute.png");
    }
}
