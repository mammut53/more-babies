package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SnowGolemHeadLayer.class)
public abstract class SnowGolemHeadLayerMixin extends RenderLayer<SnowGolemRenderState, SnowGolemModel> {

    protected SnowGolemHeadLayerMixin(final RenderLayerParent<SnowGolemRenderState, SnowGolemModel> renderer) {
        super(renderer);
    }

    @ModifyConstant(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;FF)V",
            constant = @Constant(floatValue = 0.625F)
    )
    private float modifyScaleX(final float scale, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final SnowGolemRenderState state, final float yRot, final float xRot) {
        if (state.isBaby) {
            return 0.4375F;
        }
        return scale;
    }

    @ModifyConstant(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;FF)V",
            constant = @Constant(floatValue = -0.625F)
    )
    private float modifyScaleYZ(final float scale, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final SnowGolemRenderState state, final float yRot, final float xRot) {
        if (state.isBaby) {
            return -0.4375F;
        }
        return scale;
    }

    @ModifyConstant(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/SnowGolemRenderState;FF)V",
            constant = @Constant(floatValue = -0.34375F)
    )
    private float modifyTranslateY(final float scale, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final SnowGolemRenderState state, final float yRot, final float xRot) {
        if (state.isBaby) {
            return -0.34375F + 1.5F * (1.0F / 16.0F);
        }
        return scale;
    }
}
