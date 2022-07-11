package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherBossRenderer;
import net.minecraft.world.entity.boss.wither.WitherBoss;

public class BabyWitherBossRenderer extends WitherBossRenderer {

    public BabyWitherBossRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(WitherBoss witherBoss, PoseStack poseStack, float f) {
        float g = 1.0F;
        int i = witherBoss.getInvulnerableTicks();
        if (i > 0) {
            g -= ((float)i - f) / 220.0F * 0.5F;
        }

        poseStack.scale(g, g, g);
    }
}
