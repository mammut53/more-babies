package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.world.entity.animal.SnowGolem;

public class BabySnowGolemRenderer extends SnowGolemRenderer {

    public BabySnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(SnowGolem snowGolem, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
