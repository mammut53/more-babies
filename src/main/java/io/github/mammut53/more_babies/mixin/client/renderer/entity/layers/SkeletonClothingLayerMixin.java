package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.client.model.monster.skeleton.BabySkeletonModel;
import io.github.mammut53.more_babies.client.renderer.entity.layers.BabySkeletonClothingLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SkeletonClothingLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SkeletonClothingLayer.class)
public abstract class SkeletonClothingLayerMixin<S extends SkeletonRenderState, M extends EntityModel<S>> extends RenderLayer<S, M> implements BabySkeletonClothingLayer {

    @Shadow
    @Final
    private SkeletonModel<S> layerModel;

    @Shadow
    @Final
    private Identifier clothesLocation;

    @Unique
    @Final
    @Mutable
    private SkeletonModel<S> more_babies$babyLayerModel;
    @Unique
    @Final
    @Mutable
    private Identifier more_babies$babyClothesLocation;

    protected SkeletonClothingLayerMixin(final RenderLayerParent<S, M> renderer) {
        super(renderer);
    }

    public void more_babies$setBabyLayerClothes(final EntityModelSet models, final ModelLayerLocation babyLayerLocation, final Identifier babyClothesLocation) {
        this.more_babies$babyClothesLocation = babyClothesLocation;
        this.more_babies$babyLayerModel = new BabySkeletonModel<>(models.bakeLayer(babyLayerLocation));
    }

    @ModifyArgs(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/SkeletonRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/SkeletonClothingLayer;coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/Identifier;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V"
            )
    )
    private void injectSubmit(final Args args, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final S state, final float yRot, final float xRot) {
        if (this.more_babies$babyLayerModel != null && this.more_babies$babyClothesLocation != null) {
            args.set(0, state.isBaby ? this.more_babies$babyLayerModel : this.layerModel);
            args.set(1, state.isBaby ? this.more_babies$babyClothesLocation : this.clothesLocation);
        }
    }


}
