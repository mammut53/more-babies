package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.ElderGuardianRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Guardian;

public class BabyElderGuardianRenderer extends ElderGuardianRenderer {

    public BabyElderGuardianRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Guardian guardian, PoseStack poseStack, float f) {
        poseStack.scale(1.2F, 1.2F, 1.2F);
    }
}
