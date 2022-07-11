package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.world.entity.animal.Parrot;

public class BabyParrotRenderer extends ParrotRenderer {

    public BabyParrotRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Parrot parrot, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
