package com.github.mammut53.more_babies.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Vindicator;

public class BabyVindicatorRenderer extends IllagerRenderer<Vindicator> {

    private static final ResourceLocation VINDICATOR = new ResourceLocation("textures/entity/illager/vindicator.png");

    public BabyVindicatorRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel<>(context.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()) {
            public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Vindicator vindicator, float f, float g, float h, float j, float k, float l) {
                if (!vindicator.isAggressive()) return;

                poseStack.scale(2F, 2F, 2F);
                poseStack.translate(0, -0.75, 0);
                super.render(poseStack, multiBufferSource, i, vindicator, f, g, h, j, k, l);
            }
        });
    }

    public ResourceLocation getTextureLocation(Vindicator vindicator) {
        return VINDICATOR;
    }

    @Override
    protected void scale(Vindicator vindicator, PoseStack poseStack, float f) {
        poseStack.scale(0.46875F, 0.46875F, 0.46875F);
    }
}
