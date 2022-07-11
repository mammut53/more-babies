package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.ambient.Bat;

public class BabyBatRenderer extends BatRenderer {

    public BabyBatRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Bat bat, PoseStack poseStack, float f) {
        if (bat.isResting()) poseStack.translate(0, -0.05, 0);
        poseStack.scale(0.35F/2, 0.35F/2, 0.35F/2);
    }
}
