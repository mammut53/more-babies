package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;

public class BabyEnderDragonRenderer extends EnderDragonRenderer {

    public BabyEnderDragonRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    /* TODO IMPLEMENT EnderDragonRenderer
    @Override
    protected void scale(EnderDragon enderDragon, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }*/
}
