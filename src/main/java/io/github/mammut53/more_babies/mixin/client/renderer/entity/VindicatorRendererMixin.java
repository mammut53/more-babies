package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.illager.BabyIllagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.VindicatorRenderer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.illager.Vindicator;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VindicatorRenderer.class)
public abstract class VindicatorRendererMixin extends IllagerRenderer<Vindicator, IllagerRenderState> {

    @Shadow
    @Final
    private static final Identifier VINDICATOR = Identifier.withDefaultNamespace("textures/entity/illager/vindicator.png");;
    @Unique
    private static final Identifier more_babies$BABY_VINDICATOR = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/illager/vindicator_baby.png");

    @Unique
    @Final
    @Mutable
    private IllagerModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private IllagerModel more_babies$babyModel;

    protected VindicatorRendererMixin(final EntityRendererProvider.Context context, final IllagerModel<IllagerRenderState> model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new IllagerModel<>(context.bakeLayer(ModelLayers.VINDICATOR));
        this.more_babies$babyModel = new BabyIllagerModel<>(context.bakeLayer(MoreBabiesModelLayers.VINDICATOR_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final IllagerRenderState state) {
        return state.isBaby ? more_babies$BABY_VINDICATOR : VINDICATOR;
    }

    @Override
    protected float getShadowRadius(final IllagerRenderState state) {
        float radius = super.getShadowRadius(state);
        return state.isBaby ? radius * 0.5F : radius;
    }

    @Override
    public void submit(final IllagerRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

}
