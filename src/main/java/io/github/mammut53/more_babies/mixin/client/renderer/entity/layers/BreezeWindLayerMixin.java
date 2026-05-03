package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.monster.breeze.BreezeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.BreezeWindLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BreezeWindLayer.class)
public abstract class BreezeWindLayerMixin extends RenderLayer<BreezeRenderState, BreezeModel> {

    protected BreezeWindLayerMixin(final RenderLayerParent<BreezeRenderState, BreezeModel> renderer) {
        super(renderer);
    }

    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/BreezeRenderState;FF)V",
            at = @At("HEAD")
    )
    private void injectSubmitHead(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int packedLight, final BreezeRenderState state, final float limbSwing, final float limbSwingAmount, final CallbackInfo ci) {
        poseStack.pushPose();
        if (state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.translate(0.0D, 1.5D, 0.0D);
        }
    }

    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/BreezeRenderState;FF)V",
            at = @At("RETURN")
    )
    private void injectSubmitReturn(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int packedLight, final BreezeRenderState state, final float limbSwing, final float limbSwingAmount, final CallbackInfo ci) {
        poseStack.popPose();
    }

}
