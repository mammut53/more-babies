package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitchRenderer;
import net.minecraft.world.entity.monster.Witch;

public class BabyWitchRenderer extends WitchRenderer {

    public BabyWitchRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Witch witch, PoseStack poseStack, float f) {
        this.shadowRadius = 0.25F;
        poseStack.scale(0.46878F, 0.46878F, 0.46878F);
    }
}
