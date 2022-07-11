package com.github.mammut53.more_babies.client.renderer.entity;

import com.github.mammut53.more_babies.world.entity.BabySpider;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;

public class BabySpiderRenderer extends SpiderRenderer<BabySpider> {

    public BabySpiderRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(BabySpider babySpider, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
