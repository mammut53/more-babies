package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.breeze.BabyBreezeModel;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.breeze.BreezeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.BreezeEyesLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(BreezeEyesLayer.class)
public abstract class BreezeEyesLayerMixin extends RenderLayer<BreezeRenderState, BreezeModel> {

    @Shadow
    private static final RenderType BREEZE_EYES = RenderTypes.breezeEyes(Identifier.withDefaultNamespace("textures/entity/breeze/breeze_eyes.png"));
    @Unique
    private static final RenderType BREEZE_BABY_EYES = RenderTypes.breezeEyes(Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/breeze/breeze_baby_eyes.png"));

    @Shadow
    @Final
    @Mutable
    private BreezeModel model;

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<BreezeModel> more_babies$model;

    protected BreezeEyesLayerMixin(final RenderLayerParent<BreezeRenderState, BreezeModel> renderer) {
        super(renderer);
    }


    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final RenderLayerParent<BreezeRenderState, BreezeModel> renderer, final EntityModelSet modelSet, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new BreezeModel(modelSet.bakeLayer(ModelLayers.BREEZE_EYES)),
                new BabyBreezeModel(modelSet.bakeLayer(MoreBabiesModelLayers.BREEZE_BABY_EYES))
        );
    }

    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/BreezeRenderState;FF)V",
            at = @At("HEAD")
    )
    private void injectSubmit(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final BreezeRenderState state, final float yRot, final float xRot, final CallbackInfo ci) {
        this.model = this.more_babies$model.getModel(state.isBaby);
    }

    @ModifyArgs(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/BreezeRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OrderedSubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"
            )
    )
    private void modifySubmitRenderType(final Args args, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final BreezeRenderState state, final float yRot, final float xRot) {
        args.set(0, this.more_babies$model.getModel(state.isBaby));
        args.set(3, state.isBaby ? BREEZE_BABY_EYES : BREEZE_EYES);
    }

}
