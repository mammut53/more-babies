package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllusionerRenderer;
import net.minecraft.world.entity.monster.Illusioner;

public class BabyIllusionerRenderer extends IllusionerRenderer {

    public BabyIllusionerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Illusioner illusioner, PoseStack poseStack, float f) {
        poseStack.scale(0.46875F, 0.46875F, 0.46875F);
    }
}
