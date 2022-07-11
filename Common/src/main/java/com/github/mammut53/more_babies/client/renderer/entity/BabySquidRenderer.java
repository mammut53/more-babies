package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SquidRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Squid;

public class BabySquidRenderer<T extends Squid> extends SquidRenderer<T> {

    public BabySquidRenderer(EntityRendererProvider.Context context, SquidModel<T> squidModel) {
        super(context, squidModel);
    }

    @Override
    protected void setupRotations(T squid, PoseStack poseStack, float f, float g, float h) {
        float i = Mth.lerp(h, squid.xBodyRotO, squid.xBodyRot);
        float j = Mth.lerp(h, squid.zBodyRotO, squid.zBodyRot);

        poseStack.translate(0.0D, 0.25D, 0.0D);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - g));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(i));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(j));

        poseStack.translate(0.0D, -0.6D, 0.0D);
    }

    @Override
    protected void scale(T squid, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}

