package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.client.renderer.entity.layers.BabyLivingEntityEmissiveLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.function.Function;

@Mixin(LivingEntityEmissiveLayer.class)
public abstract class LivingEntityEmissiveLayerMixin<S extends LivingEntityRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> implements BabyLivingEntityEmissiveLayer<S, M> {

    @Shadow
    @Final
    private Function<S, Identifier> textureProvider;

    @Unique
    private M more_babies$babyModel;

    protected LivingEntityEmissiveLayerMixin(final RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    public Function<S, Identifier> more_babies$getOriginalTextureProvider() {
        return textureProvider;
    }

    public void more_babies$setBabyModel(final M babyModel) {
        this.more_babies$babyModel = babyModel;
    }

    @ModifyArgs(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OrderedSubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/texture/UvMapping;I)V"
            )
    )
    private void modifySubmitModelArgs(final Args args, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final S state, final float yRot, final float xRot) {
        if (state.isBaby && more_babies$babyModel != null) {
            args.set(0, more_babies$babyModel);
        }
    }

}
