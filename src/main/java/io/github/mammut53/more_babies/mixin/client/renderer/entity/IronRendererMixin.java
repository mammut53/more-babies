package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.animal.golem.BabyIronGolemModel;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import net.minecraft.client.model.animal.golem.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.golem.IronGolem;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolemRenderer.class)
public abstract class IronRendererMixin extends MobRenderer<IronGolem, IronGolemRenderState, IronGolemModel> {

    @Shadow
    private static final Identifier GOLEM_LOCATION = Identifier.withDefaultNamespace("textures/entity/iron_golem/iron_golem.png");
    @Unique
    private static final Identifier more_babies$GOLEM_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/iron_golem/iron_golem_baby.png");

    @Unique
    @Final
    @Mutable
    private IronGolemModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private IronGolemModel more_babies$babyModel;

    protected IronRendererMixin(final EntityRendererProvider.Context context) {
        super(context, new IronGolemModel(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.5F);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new IronGolemModel(context.bakeLayer(ModelLayers.IRON_GOLEM));
        this.more_babies$babyModel = new BabyIronGolemModel(context.bakeLayer(MoreBabiesModelLayers.IRON_GOLEM_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final IronGolemRenderState state) {
        return state.isBaby ? more_babies$GOLEM_LOCATION : GOLEM_LOCATION;
    }

    @Override
    public void submit(final IronGolemRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
