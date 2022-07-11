package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.BlazeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Blaze;

public class BabyBlazeRenderer extends BlazeRenderer {

    public BabyBlazeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Blaze blaze, PoseStack poseStack, float f) {
        poseStack.scale(0.46875F, 0.46875F, 0.46875F);
    }
}

