package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.blaze.BabyBlazeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.BlazeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Blaze;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlazeRenderer.class)
public abstract class BlazeRendererMixin extends MobRenderer<Blaze, LivingEntityRenderState, BlazeModel> {

    @Shadow
    private static final Identifier BLAZE_LOCATION = Identifier.withDefaultNamespace("textures/entity/blaze/blaze.png");
    @Unique
    private static final Identifier more_babies$BABY_BLAZE_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/blaze/blaze_baby.png");

    @Unique
    @Final
    @Mutable
    private BlazeModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private BlazeModel more_babies$babyModel;

    protected BlazeRendererMixin(final EntityRendererProvider.Context context) {
        super(context, new BlazeModel(context.bakeLayer(ModelLayers.BLAZE)), 0.5F);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new BlazeModel(context.bakeLayer(ModelLayers.BLAZE));
        this.more_babies$babyModel = new BabyBlazeModel(context.bakeLayer(MoreBabiesModelLayers.BLAZE_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final LivingEntityRenderState state) {
        return state.isBaby ? more_babies$BABY_BLAZE_LOCATION : BLAZE_LOCATION;
    }

    @Override
    public void submit(final LivingEntityRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
