package com.github.mammut53.more_babies.client.renderer.entity;

import com.github.mammut53.more_babies.client.renderer.entity.layers.ItemInHandLayerIgnoreBaby;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Pillager;

public class BabyPillagerRenderer extends IllagerRenderer<Pillager> {
    private static final ResourceLocation PILLAGER = new ResourceLocation("textures/entity/illager/pillager.png");

    public BabyPillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new IllagerModel(context.bakeLayer(ModelLayers.PILLAGER)), 0.5F);
        this.addLayer(new ItemInHandLayerIgnoreBaby(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(Pillager pillager) {
        return PILLAGER;
    }

    @Override
    protected void scale(Pillager pillager, PoseStack poseStack, float f) {
        poseStack.scale(0.46875F, 0.46875F, 0.46875F);
    }
}
