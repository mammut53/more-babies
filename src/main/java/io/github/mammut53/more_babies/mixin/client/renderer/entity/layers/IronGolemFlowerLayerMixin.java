package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.golem.IronGolemModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.IronGolemFlowerLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolemFlowerLayer.class)
public abstract class IronGolemFlowerLayerMixin extends RenderLayer<IronGolemRenderState, IronGolemModel> {

    protected IronGolemFlowerLayerMixin(final RenderLayerParent<IronGolemRenderState, IronGolemModel> renderer) {
        super(renderer);
    }

    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/IronGolemRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void injectSubmit(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final IronGolemRenderState state, final float yRot, final float xRot, final CallbackInfo ci) {
        if (state.isBaby) {
            ci.cancel();
            if (!state.flowerBlock.isEmpty()) {
                poseStack.pushPose();
                final ModelPart arm = this.getParentModel().getFlowerHoldingArm();
                arm.translateAndRotate(poseStack);
                poseStack.translate(-1.1875F, 1.0625F, -0.9375F);
                poseStack.translate(0.5F, 0.5F, 0.5F);
                final float scale = 1.0F;
                poseStack.scale(scale, scale, scale);
                poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                poseStack.translate(-0.15625F, -0.34375F, -1.40625F);
                state.flowerBlock.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
                poseStack.popPose();
            }
        }
    }

}
