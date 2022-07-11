package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WanderingTraderRenderer;
import net.minecraft.world.entity.npc.WanderingTrader;

public class BabyWanderingTraderRenderer extends WanderingTraderRenderer {

    public BabyWanderingTraderRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(WanderingTrader wanderingTrader, PoseStack poseStack, float f) {
        poseStack.scale(0.46878F, 0.46878F, 0.46878F);
    }
}
