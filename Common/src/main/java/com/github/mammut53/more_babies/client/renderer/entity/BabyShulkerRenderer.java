package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ShulkerRenderer;
import net.minecraft.world.entity.monster.Shulker;

public class BabyShulkerRenderer extends ShulkerRenderer {

    public BabyShulkerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Shulker shulker, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
