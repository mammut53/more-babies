package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.VindicatorRenderer;
import net.minecraft.world.entity.monster.Vindicator;

public class BabyVindicatorRenderer extends VindicatorRenderer {

    public BabyVindicatorRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Vindicator vindicator, PoseStack poseStack, float f) {
        poseStack.scale(0.46875F, 0.46875F, 0.46875F);
    }
}
