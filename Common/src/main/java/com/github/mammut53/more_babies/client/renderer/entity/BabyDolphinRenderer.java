package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.DolphinRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.animal.Dolphin;

public class BabyDolphinRenderer extends DolphinRenderer {

    public BabyDolphinRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Dolphin dolphin, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
