package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.ghast.GhastModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GhastRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.GhastRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Ghast;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GhastRenderer.class)
public abstract class GhastRendererMixin extends MobRenderer<Ghast, GhastRenderState, GhastModel> {

    @Shadow
    private static final Identifier GHAST_LOCATION = Identifier.withDefaultNamespace("textures/entity/ghast/ghast.png");
    @Shadow
    private static final Identifier GHAST_SHOOTING_LOCATION = Identifier.withDefaultNamespace("textures/entity/ghast/ghast_shooting.png");
    @Unique
    private static final Identifier more_babies$GHAST_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/ghast/ghast_baby.png");
    @Unique
    private static final Identifier more_babies$GHAST_BABY_SHOOTING_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/ghast/ghast_baby_shooting.png");

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<GhastModel> more_babies$model;

    protected GhastRendererMixin(final EntityRendererProvider.Context context, final GhastModel model, final float shadow) {
        super(context, model, shadow);
    }


    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new GhastModel(context.bakeLayer(ModelLayers.GHAST)),
                new GhastModel(context.bakeLayer(MoreBabiesModelLayers.GHAST_BABY))
        );
    }

    @Override
    public @NonNull Identifier getTextureLocation(final GhastRenderState state) {
        if (state.isCharging) {
            return state.isBaby ? more_babies$GHAST_BABY_SHOOTING_LOCATION : GHAST_SHOOTING_LOCATION;
        } else {
            return state.isBaby ? more_babies$GHAST_BABY_LOCATION : GHAST_LOCATION;
        }
    }

    @Override
    public void submit(final GhastRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
