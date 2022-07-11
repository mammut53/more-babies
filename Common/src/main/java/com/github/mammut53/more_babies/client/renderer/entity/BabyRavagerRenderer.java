package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RavagerRenderer;
import net.minecraft.world.entity.monster.Ravager;

public class BabyRavagerRenderer extends RavagerRenderer {

    public BabyRavagerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(Ravager ravager, PoseStack poseStack, float f) {
        //this.shadowRadius = 0.25F;
        poseStack.scale(0.46875F, 0.46875F, 0.46875F);
    }
}
