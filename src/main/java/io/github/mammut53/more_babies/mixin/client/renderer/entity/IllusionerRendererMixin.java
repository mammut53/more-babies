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
import net.minecraft.client.renderer.entity.IllusionerRenderer;
import net.minecraft.client.renderer.entity.state.IllusionerRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.illager.Illusioner;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IllusionerRenderer.class)
public abstract class IllusionerRendererMixin extends IllagerRenderer<Illusioner, IllusionerRenderState> {

    @Shadow
    private static final Identifier ILLUSIONER = Identifier.withDefaultNamespace("textures/entity/illager/illusioner.png");;
    @Unique
    private static final Identifier more_babies$BABY_ILLUSIONER = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/illager/illusioner_baby.png");

    @Unique
    @Final
    @Mutable
    private IllagerModel<IllusionerRenderState> more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private IllagerModel<IllusionerRenderState> more_babies$babyModel;

    protected IllusionerRendererMixin(final EntityRendererProvider.Context context, final IllagerModel<IllusionerRenderState> model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new IllagerModel<>(context.bakeLayer(ModelLayers.ILLUSIONER));
        this.more_babies$babyModel = new BabyIllagerModel<>(context.bakeLayer(MoreBabiesModelLayers.ILLUSIONER_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final IllusionerRenderState state) {
        return state.isBaby ? more_babies$BABY_ILLUSIONER : ILLUSIONER;
    }

    @Override
    protected float getShadowRadius(final IllusionerRenderState state) {
        float radius = super.getShadowRadius(state);
        return state.isBaby ? radius * 0.5F : radius;
    }

    @Inject(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/IllusionerRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
            at = @At("HEAD")
    )
    public void injectSubmit(final IllusionerRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera, final CallbackInfo ci) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

}
