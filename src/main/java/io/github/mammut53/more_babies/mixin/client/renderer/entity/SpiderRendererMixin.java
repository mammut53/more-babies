package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.spider.BabySpiderModel;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.monster.spider.SpiderModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.spider.Spider;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpiderRenderer.class)
public abstract class SpiderRendererMixin<T extends Spider> extends MobRenderer<T, LivingEntityRenderState, SpiderModel> {

    @Shadow
    private static final Identifier SPIDER_LOCATION = Identifier.withDefaultNamespace("textures/entity/spider/spider.png");
    @Unique
    private static final Identifier more_babies$SPIDER_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/spider/spider_baby.png");

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<SpiderModel> more_babies$model;

    protected SpiderRendererMixin(final EntityRendererProvider.Context context, final SpiderModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;Lnet/minecraft/client/model/geom/ModelLayerLocation;)V",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final ModelLayerLocation model, final CallbackInfo ci) {
        final ModelLayerLocation babyModelLayer = MoreBabiesModelLayers.SPIDER_MAP.get(model);
        final SpiderModel adultModel = new SpiderModel(context.bakeLayer(model));
        this.more_babies$model = new AdultAndBabyModelPair<>(
                adultModel,
                babyModelLayer == null ? adultModel : new BabySpiderModel(context.bakeLayer(babyModelLayer))
        );
    }

    @Override
    public @NonNull Identifier getTextureLocation(final LivingEntityRenderState state) {
        return state.isBaby ? more_babies$SPIDER_BABY_LOCATION : SPIDER_LOCATION;
    }

    @Override
    public void submit(final LivingEntityRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
