package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.breeze.BabyBreezeModel;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.breeze.BreezeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.BreezeRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.breeze.Breeze;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BreezeRenderer.class)
public abstract class BreezeRendererMixin extends MobRenderer<Breeze, BreezeRenderState, BreezeModel> {

    @Shadow
    private static final Identifier TEXTURE_LOCATION = Identifier.withDefaultNamespace("textures/entity/breeze/breeze.png");
    @Unique
    private static final Identifier more_babies$TEXTURE_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/breeze/breeze_baby.png");

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<BreezeModel> more_babies$model;

    protected BreezeRendererMixin(final EntityRendererProvider.Context context, final BreezeModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new BreezeModel(context.bakeLayer(ModelLayers.BREEZE)),
                new BabyBreezeModel(context.bakeLayer(MoreBabiesModelLayers.BREEZE_BABY))
        );
    }

    @Override
    public @NonNull Identifier getTextureLocation(final BreezeRenderState state) {
        return state.isBaby ? more_babies$TEXTURE_BABY_LOCATION : TEXTURE_LOCATION;
    }

    @Override
    public void submit(final BreezeRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
