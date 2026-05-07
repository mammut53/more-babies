package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.creaking.BabyCreakingModel;
import io.github.mammut53.more_babies.client.renderer.entity.layers.BabyLivingEntityEmissiveLayer;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.creaking.CreakingModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.CreakingRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.CreakingRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.creaking.Creaking;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Map;
import java.util.function.Function;

@Mixin(CreakingRenderer.class)
public abstract class CreakingRendererMixin<T extends Creaking> extends MobRenderer<T, CreakingRenderState, CreakingModel> {

    @Shadow
    private static final Identifier TEXTURE_LOCATION = Identifier.withDefaultNamespace("textures/entity/creaking/creaking.png");
    @Shadow
    private static final Identifier EYES_TEXTURE_LOCATION = Identifier.withDefaultNamespace("textures/entity/creaking/creaking_eyes.png");
    @Unique
    private static final Identifier more_babies$TEXTURE_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/creaking/creaking_baby.png");
    @Unique
    private static final Identifier more_babies$EYES_TEXTURE_BABY_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/creaking/creaking_eyes.png");

    @Unique
    private static final Map<Identifier, ModelLayerLocation> more_babies$LAYER_TEXTURE_TO_MODEL_LAYER = Map.ofEntries(
            Map.entry(EYES_TEXTURE_LOCATION, MoreBabiesModelLayers.CREAKING_BABY_EYES)
    );

    @Unique
    private static final Map<Identifier, Identifier> more_babies$LAYER_TEXTURE_TO_BABY_LAYER_TEXTURE = Map.ofEntries(
            Map.entry(EYES_TEXTURE_LOCATION, more_babies$EYES_TEXTURE_BABY_LOCATION)
    );

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<CreakingModel> more_babies$model;

    protected CreakingRendererMixin(final EntityRendererProvider.Context context, final CreakingModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new CreakingModel(context.bakeLayer(ModelLayers.CREAKING)),
                new BabyCreakingModel(context.bakeLayer(MoreBabiesModelLayers.CREAKING_BABY))
        );
    }

    @ModifyArgs(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/CreakingRenderer;addLayer(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z"
            )
    )
    private void modifyConstructorAddLayer(final Args args, final EntityRendererProvider.Context context) {
        final RenderLayer<CreakingRenderState, CreakingModel> layer = args.get(0);
        if (layer instanceof LivingEntityEmissiveLayer<CreakingRenderState, CreakingModel> emissiveLayer) {
            @SuppressWarnings("unchecked") final BabyLivingEntityEmissiveLayer<CreakingRenderState, CreakingModel> babyEmissiveLayer = (BabyLivingEntityEmissiveLayer<CreakingRenderState, CreakingModel>) emissiveLayer;
            final Function<CreakingRenderState, Identifier> originalTextureProvider = babyEmissiveLayer.more_babies$getOriginalTextureProvider();
            final Identifier originalTexture = originalTextureProvider.apply(new CreakingRenderState());
            final ModelLayerLocation babyTexture = more_babies$LAYER_TEXTURE_TO_MODEL_LAYER.get(originalTexture);
            if (babyTexture != null) {
                babyEmissiveLayer.more_babies$setBabyModel(new BabyCreakingModel(context.bakeLayer(babyTexture)));
            }
        }
    }

    @ModifyArgs(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/LivingEntityEmissiveLayer;<init>(Lnet/minecraft/client/renderer/entity/RenderLayerParent;Ljava/util/function/Function;Lnet/minecraft/client/renderer/entity/layers/LivingEntityEmissiveLayer$AlphaFunction;Lnet/minecraft/client/model/EntityModel;Ljava/util/function/Function;Z)V"
            )
    )
    private void modifyConstructorLivingEntityEmissiveLayer(final Args args, final EntityRendererProvider.Context context) {
        final Function<CreakingRenderState, Identifier> originalTextureProvider = args.get(1);
        final Function<CreakingRenderState, Identifier> textureProvider = (state) -> {
            final Identifier originalTexture = originalTextureProvider.apply(state);
            return state.isBaby ? more_babies$LAYER_TEXTURE_TO_BABY_LAYER_TEXTURE.getOrDefault(originalTexture, originalTexture) : originalTexture;
        };
        args.set(1, textureProvider);
    }

    @Override
    public @NonNull Identifier getTextureLocation(final CreakingRenderState state) {
        return state.isBaby ? more_babies$TEXTURE_BABY_LOCATION : TEXTURE_LOCATION;
    }

    @Override
    public void submit(final CreakingRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
