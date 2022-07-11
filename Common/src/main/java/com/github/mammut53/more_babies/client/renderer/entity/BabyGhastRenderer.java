package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.world.entity.monster.Ghast;

public class BabyGhastRenderer extends GhastRenderer {

    public BabyGhastRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Ghast ghast, PoseStack poseStack, float f) {
        poseStack.scale(2.25F, 2.25F, 2.25F);
    }
}
