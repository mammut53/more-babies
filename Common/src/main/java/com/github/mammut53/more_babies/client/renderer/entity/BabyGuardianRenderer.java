package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.world.entity.monster.Guardian;

public class BabyGuardianRenderer extends GuardianRenderer {

    public BabyGuardianRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Guardian guardian, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
