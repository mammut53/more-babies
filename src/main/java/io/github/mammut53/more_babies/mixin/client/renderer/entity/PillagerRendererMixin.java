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
import net.minecraft.client.renderer.entity.PillagerRenderer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.illager.Pillager;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PillagerRenderer.class)
public abstract class PillagerRendererMixin extends IllagerRenderer<Pillager, IllagerRenderState> {

    @Shadow
    private static final Identifier PILLAGER = Identifier.withDefaultNamespace("textures/entity/illager/pillager.png");;
    @Unique
    private static final Identifier more_babies$BABY_PILLAGER = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/illager/pillager_baby.png");

    @Unique
    @Final
    @Mutable
    private IllagerModel<IllagerRenderState> more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private IllagerModel<IllagerRenderState> more_babies$babyModel;

    protected PillagerRendererMixin(final EntityRendererProvider.Context context, final IllagerModel<IllagerRenderState> model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new IllagerModel<>(context.bakeLayer(ModelLayers.PILLAGER));
        this.more_babies$babyModel = new BabyIllagerModel<>(context.bakeLayer(MoreBabiesModelLayers.PILLAGER_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final IllagerRenderState state) {
        return state.isBaby ? more_babies$BABY_PILLAGER : PILLAGER;
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
