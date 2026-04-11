package io.github.mammut53.more_babies.client.renderer.entity.layers;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public interface BabySkeletonClothingLayer {
    void more_babies$setBabyLayerClothes(final EntityModelSet models, final ModelLayerLocation babyLayerLocation, final Identifier babyClothesLocation);
}
