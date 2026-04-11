package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.skeleton.BabySkeletonModel;
import io.github.mammut53.more_babies.client.renderer.entity.AgeableMobRendererBypass;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ParchedRenderer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParchedRenderer.class)
public abstract class ParchedRendererMixin extends AbstractSkeletonRenderer<Skeleton, SkeletonRenderState> implements AgeableMobRendererBypass {

    @Shadow
    private static final Identifier PARCHED_SKELETON_LOCATION = Identifier.withDefaultNamespace("textures/entity/skeleton/parched.png");
    @Unique
    private static final Identifier more_babies$PARCHED_SKELETON_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/skeleton/parched_baby.png");

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<SkeletonModel<SkeletonRenderState>> more_babies$model;

    protected ParchedRendererMixin(final EntityRendererProvider.Context context, final ModelLayerLocation body, final ArmorModelSet<ModelLayerLocation> armorSet) {
        super(context, body, armorSet);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new SkeletonModel<>(context.bakeLayer(ModelLayers.PARCHED)),
                new BabySkeletonModel<>(context.bakeLayer(MoreBabiesModelLayers.PARCHED_BABY))
        );
    }

    @Override
    public @NonNull Identifier getTextureLocation(final SkeletonRenderState state) {
        return state.isBaby ? more_babies$PARCHED_SKELETON_BABY_LOCATION : PARCHED_SKELETON_LOCATION;
    }

    @Override
    public void submit(final SkeletonRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
