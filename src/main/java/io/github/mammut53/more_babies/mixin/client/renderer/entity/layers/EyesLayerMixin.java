package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import io.github.mammut53.more_babies.client.renderer.entity.layers.EyesLayerRenderTypeBaby;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EyesLayer.class)
public abstract class EyesLayerMixin<S extends EntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> {

    protected EyesLayerMixin(final RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    @ModifyArgs(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OrderedSubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;III)V"
            )
    )
    private void modifySubmitRenderType(final Args args) {
        final S state = args.get(1);
        final RenderType renderType = args.get(3);

        if (this instanceof final EyesLayerRenderTypeBaby eyesLayerBaby && state instanceof final LivingEntityRenderState livingState && livingState.isBaby) {
            args.set(3, eyesLayerBaby.more_babies$renderTypeBaby());
        } else {
            args.set(3, renderType);
        }
    }

}
