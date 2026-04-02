package io.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.creeper.BabyCreeperModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperPowerLayer.class)
public abstract class CreeperPowerLayerMixin extends EnergySwirlLayer<CreeperRenderState, CreeperModel> {

    @Shadow
    @Final
    @Mutable
    private CreeperModel model;

    @Unique
    @Final
    @Mutable
    private CreeperModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private CreeperModel more_babies$babyModel;

    protected CreeperPowerLayerMixin(final RenderLayerParent<CreeperRenderState, CreeperModel> renderer, final CreeperModel model) {
        super(renderer);
        this.model = model;
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final RenderLayerParent<CreeperRenderState, CreeperModel> renderer, final EntityModelSet modelSet, final CallbackInfo ci) {
        this.more_babies$adultModel = new CreeperModel(modelSet.bakeLayer(ModelLayers.CREEPER_ARMOR));
        this.more_babies$babyModel = new BabyCreeperModel(modelSet.bakeLayer(MoreBabiesModelLayers.CREEPER_BABY_ARMOR));
    }

    @Override
    public void submit(final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final int lightCoords, final CreeperRenderState state, final float yRot, final float xRot) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(poseStack, submitNodeCollector, lightCoords, state, yRot, xRot);
    }
}
