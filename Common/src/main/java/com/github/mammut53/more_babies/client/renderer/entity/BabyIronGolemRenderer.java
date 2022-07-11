package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.world.entity.animal.IronGolem;

public class BabyIronGolemRenderer extends IronGolemRenderer {

    public BabyIronGolemRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(IronGolem ironGolem, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
