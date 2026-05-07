package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.guardian.GuardianModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.GuardianRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Guardian;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuardianRenderer.class)
public abstract class GuardianRendererMixin extends MobRenderer<Guardian, GuardianRenderState, GuardianModel> {

    @Shadow
    private static final Identifier GUARDIAN_LOCATION = Identifier.withDefaultNamespace("textures/entity/guardian/guardian.png");
    @Unique
    private static final Identifier more_babies$GUARDIAN_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/guardian/guardian_baby.png");

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<GuardianModel> more_babies$model;

    protected GuardianRendererMixin(final EntityRendererProvider.Context context, final GuardianModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;FLnet/minecraft/client/model/geom/ModelLayerLocation;)V",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final float shadow, final ModelLayerLocation modelId, final CallbackInfo ci) {
        final ModelLayerLocation babyModelLayer = MoreBabiesModelLayers.GUARDIAN_MODEL_LAYER_TO_BABY.get(modelId);
        final GuardianModel adultModel = new GuardianModel(context.bakeLayer(modelId));
        this.more_babies$model = new AdultAndBabyModelPair<>(
                adultModel,
                babyModelLayer == null ? adultModel : new GuardianModel(context.bakeLayer(babyModelLayer))
        );
    }

    @Override
    public @NonNull Identifier getTextureLocation(final GuardianRenderState state) {
        return state.isBaby ? more_babies$GUARDIAN_BABY_LOCATION : GUARDIAN_LOCATION;
    }

    @Override
    public void submit(final GuardianRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
