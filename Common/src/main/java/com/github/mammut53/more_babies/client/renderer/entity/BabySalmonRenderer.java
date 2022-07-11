package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SalmonRenderer;
import net.minecraft.world.entity.animal.Salmon;

public class BabySalmonRenderer extends SalmonRenderer {

    public BabySalmonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void setupRotations(Salmon salmon, PoseStack poseStack, float f, float g, float h) {
        super.setupRotations(salmon, poseStack, f, g, h);

        if (salmon.isInWater()) {
            poseStack.translate(0, 0.0, 0.2);
            return;
        }
        poseStack.translate(0, 0.1, 0.2);
    }

    @Override
    protected void scale(Salmon salmon, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
