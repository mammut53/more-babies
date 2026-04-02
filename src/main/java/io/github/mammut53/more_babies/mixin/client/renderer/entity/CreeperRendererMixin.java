package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.creeper.BabyCreeperModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Creeper;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperRenderer.class)
public abstract class CreeperRendererMixin extends MobRenderer<Creeper, CreeperRenderState, CreeperModel> {

    @Shadow
    private static final Identifier CREEPER_LOCATION = Identifier.withDefaultNamespace("textures/entity/creeper/creeper.png");
    @Unique
    private static final Identifier more_babies$CREEPER_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/creeper/creeper_baby.png");

    @Unique
    @Final
    @Mutable
    private CreeperModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private CreeperModel more_babies$babyModel;

    protected CreeperRendererMixin(final EntityRendererProvider.Context context) {
        super(context, new CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5F);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new CreeperModel(context.bakeLayer(ModelLayers.CREEPER));
        this.more_babies$babyModel = new BabyCreeperModel(context.bakeLayer(MoreBabiesModelLayers.CREEPER_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final CreeperRenderState state) {
        return state.isBaby ? more_babies$CREEPER_LOCATION : CREEPER_LOCATION;
    }

    @Override
    public void submit(final CreeperRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
