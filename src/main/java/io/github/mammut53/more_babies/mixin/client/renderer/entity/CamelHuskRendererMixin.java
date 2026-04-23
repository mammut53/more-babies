package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.model.animal.camel.AdultCamelModel;
import net.minecraft.client.model.animal.camel.BabyCamelModel;
import net.minecraft.client.model.animal.camel.CamelModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.CamelHuskRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CamelRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.camel.Camel;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CamelHuskRenderer.class)
public abstract class CamelHuskRendererMixin extends MobRenderer<Camel, CamelRenderState, CamelModel> {

    @Shadow
    private static final Identifier CAMEL_HUSK_LOCATION = Identifier.withDefaultNamespace("textures/entity/camel/camel_husk.png");
    @Unique
    private static final Identifier more_babies$CAMEL_HUSK_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/camel/camel_husk_baby.png");

    @Unique
    @Final
    @Mutable
    private CamelModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private CamelModel more_babies$babyModel;

    protected CamelHuskRendererMixin(final EntityRendererProvider.Context context, final CamelModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new AdultCamelModel(context.bakeLayer(ModelLayers.CAMEL));
        this.more_babies$babyModel = new BabyCamelModel(context.bakeLayer(ModelLayers.CAMEL_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final CamelRenderState state) {
        return state.isBaby ? more_babies$CAMEL_HUSK_BABY_LOCATION : CAMEL_HUSK_LOCATION;
    }

    @Override
    public void submit(final CamelRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
