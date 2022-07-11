package com.github.mammut53.more_babies.client.renderer.entity;

import com.github.mammut53.more_babies.client.model.BabyEndermanModel;
import com.github.mammut53.more_babies.client.renderer.entity.layers.BabyCarriedBlockLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EnderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class BabyEndermanRenderer extends MobRenderer<EnderMan, EndermanModel<EnderMan>> {
    private static final ResourceLocation ENDERMAN_LOCATION = new ResourceLocation("textures/entity/enderman/enderman.png");
    private final Random random = new Random();

    public BabyEndermanRenderer(EntityRendererProvider.Context context) {
        super(context, new BabyEndermanModel(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
        this.addLayer(new EnderEyesLayer(this));
        this.addLayer(new BabyCarriedBlockLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    public void render(EnderMan enderMan, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        BlockState blockState = enderMan.getCarriedBlock();
        EndermanModel<EnderMan> endermanModel = this.getModel();
        endermanModel.carrying = blockState != null;
        endermanModel.creepy = enderMan.isCreepy();
        super.render(enderMan, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    public Vec3 getRenderOffset(EnderMan enderMan, float f) {
        if (enderMan.isCreepy()) {
            return new Vec3(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(enderMan, f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EnderMan enderMan) {
        return ENDERMAN_LOCATION;
    }

    @Override
    protected void scale(EnderMan enderMan, PoseStack poseStack, float f) {
        poseStack.translate(0, 0.3, 0);
    }
}
