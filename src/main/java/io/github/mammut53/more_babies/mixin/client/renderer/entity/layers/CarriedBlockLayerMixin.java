package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CarriedBlockLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CarriedBlockLayer.class)
public abstract class CarriedBlockLayerMixin {

    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/EndermanRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"
            )
    )
    private void injectSubmitBabyScale(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final EndermanRenderState state, final float yRot, final float xRot, final CallbackInfo ci) {
        if (state.isBaby) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.translate(-0.5, 0.875, 0.0);
        }
    }

}
