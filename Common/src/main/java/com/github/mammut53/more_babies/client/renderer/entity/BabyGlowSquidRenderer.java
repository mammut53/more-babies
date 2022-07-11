package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.SquidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GlowSquidRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.GlowSquid;

public class BabyGlowSquidRenderer extends GlowSquidRenderer {

    public BabyGlowSquidRenderer(EntityRendererProvider.Context context) {
        super(context, new SquidModel(context.bakeLayer(ModelLayers.GLOW_SQUID)));
    }

    @Override
    protected void setupRotations(GlowSquid glowSquid, PoseStack poseStack, float f, float g, float h) {
        float i = Mth.lerp(h, glowSquid.xBodyRotO, glowSquid.xBodyRot);
        float j = Mth.lerp(h, glowSquid.zBodyRotO, glowSquid.zBodyRot);

        poseStack.translate(0.0D, 0.25D, 0.0D);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - g));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(i));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(j));

        poseStack.translate(0.0D, -0.6D, 0.0D);
    }

    @Override
    protected void scale(GlowSquid glowSquid, PoseStack poseStack, float f) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
    }
}
