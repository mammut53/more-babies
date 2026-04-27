package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.ravager.BabyRavagerModel;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.ravager.RavagerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RavagerRenderer;
import net.minecraft.client.renderer.entity.state.RavagerRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Ravager;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RavagerRenderer.class)
public abstract class RavagerRendererMixin extends MobRenderer<Ravager, RavagerRenderState, RavagerModel> {

    @Shadow
    private static final Identifier TEXTURE_LOCATION = Identifier.withDefaultNamespace("textures/entity/illager/ravager.png");
    @Unique
    private static final Identifier more_babies$TEXTURE_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/illager/ravager_baby.png");

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<RavagerModel> more_babies$model;

    protected RavagerRendererMixin(final EntityRendererProvider.Context context, final RavagerModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new RavagerModel(context.bakeLayer(ModelLayers.RAVAGER)),
                new BabyRavagerModel(context.bakeLayer(MoreBabiesModelLayers.RAVAGER_BABY))
        );
    }

    @Override
    public @NonNull Identifier getTextureLocation(final RavagerRenderState state) {
        return state.isBaby ? more_babies$TEXTURE_BABY_LOCATION : TEXTURE_LOCATION;
    }

    @Override
    public void submit(final RavagerRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
