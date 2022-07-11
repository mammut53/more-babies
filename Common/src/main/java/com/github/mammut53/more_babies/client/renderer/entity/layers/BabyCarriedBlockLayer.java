package com.github.mammut53.more_babies.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.block.state.BlockState;

public class BabyCarriedBlockLayer extends CarriedBlockLayer {

    private final BlockRenderDispatcher blockRenderer;

    public BabyCarriedBlockLayer(RenderLayerParent<EnderMan, EndermanModel<EnderMan>> renderLayerParent, BlockRenderDispatcher blockRenderer) {
        super(renderLayerParent, blockRenderer);
        this.blockRenderer = blockRenderer;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, EnderMan enderMan, float f, float g, float h, float j, float k, float l) {
        BlockState blockState = enderMan.getCarriedBlock();
        if (blockState == null) return;

        poseStack.pushPose();
        poseStack.translate(0.0D, 0.6875D, -0.375D);
        poseStack.mulPose(Vector3f.XP.rotationDegrees(20.0F));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(45.0F));
        poseStack.translate(0.125D, 0.1875D, 0.125D);
        poseStack.scale(-0.25F, -0.25F, 0.25F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        this.blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource,  i, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
