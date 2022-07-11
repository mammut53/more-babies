package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.CaveSpiderRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.CaveSpider;

public class BabyCaveSpiderRenderer extends CaveSpiderRenderer {

    public BabyCaveSpiderRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(CaveSpider caveSpider, PoseStack poseStack, float f) {
        poseStack.scale(0.35F, 0.35F, 0.35F);
    }
}
