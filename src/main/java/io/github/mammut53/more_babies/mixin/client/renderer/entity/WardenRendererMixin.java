package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.warden.BabyWardenModel;
import io.github.mammut53.more_babies.client.renderer.entity.layers.BabyLivingEntityEmissiveLayer;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.warden.WardenModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WardenRenderer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.WardenRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.warden.Warden;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Map;
import java.util.function.Function;

@Mixin(WardenRenderer.class)
public abstract class WardenRendererMixin extends MobRenderer<Warden, WardenRenderState, WardenModel> {

    @Shadow
    private static final Identifier TEXTURE = Identifier.withDefaultNamespace("textures/entity/warden/warden.png");
    @Shadow
    private static final Identifier BIOLUMINESCENT_LAYER_TEXTURE = Identifier.withDefaultNamespace("textures/entity/warden/warden_bioluminescent_layer.png");
    @Shadow
    private static final Identifier HEART_TEXTURE = Identifier.withDefaultNamespace("textures/entity/warden/warden_heart.png");
    @Shadow
    private static final Identifier PULSATING_SPOTS_TEXTURE_1 = Identifier.withDefaultNamespace("textures/entity/warden/warden_pulsating_spots_1.png");
    @Shadow
    private static final Identifier PULSATING_SPOTS_TEXTURE_2 = Identifier.withDefaultNamespace("textures/entity/warden/warden_pulsating_spots_2.png");

    @Unique
    private static final Identifier more_babies$TEXTURE_BABY = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/warden/warden_baby.png");
    @Unique
    private static final Identifier more_babies$BIOLUMINESCENT_LAYER_TEXTURE_BABY = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/warden/warden_baby_bioluminescent_layer.png");
    @Unique
    private static final Identifier more_babies$HEART_TEXTURE_BABY = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/warden/warden_baby_heart.png");
    @Unique
    private static final Identifier more_babies$PULSATING_SPOTS_TEXTURE_1_BABY = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/warden/warden_baby_pulsating_spots_1.png");
    @Unique
    private static final Identifier more_babies$PULSATING_SPOTS_TEXTURE_2_BABY = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/warden/warden_baby_pulsating_spots_2.png");

    @Unique
    private static final Map<Identifier, ModelLayerLocation> more_babies$LAYER_TEXTURE_TO_MODEL_LAYER = Map.ofEntries(
            Map.entry(BIOLUMINESCENT_LAYER_TEXTURE, MoreBabiesModelLayers.WARDEN_BABY_BIOLUMINESCENT),
            Map.entry(HEART_TEXTURE, MoreBabiesModelLayers.WARDEN_BABY_HEART),
            Map.entry(PULSATING_SPOTS_TEXTURE_1, MoreBabiesModelLayers.WARDEN_BABY_PULSATING_SPOTS),
            Map.entry(PULSATING_SPOTS_TEXTURE_2, MoreBabiesModelLayers.WARDEN_BABY_PULSATING_SPOTS)
    );

    @Unique
    private static final Map<Identifier, Identifier> more_babies$LAYER_TEXTURE_TO_BABY_LAYER_TEXTURE = Map.ofEntries(
            Map.entry(BIOLUMINESCENT_LAYER_TEXTURE, more_babies$BIOLUMINESCENT_LAYER_TEXTURE_BABY),
            Map.entry(HEART_TEXTURE, more_babies$HEART_TEXTURE_BABY),
            Map.entry(PULSATING_SPOTS_TEXTURE_1, more_babies$PULSATING_SPOTS_TEXTURE_1_BABY),
            Map.entry(PULSATING_SPOTS_TEXTURE_2, more_babies$PULSATING_SPOTS_TEXTURE_2_BABY)
    );

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<WardenModel> more_babies$model;

    protected WardenRendererMixin(final EntityRendererProvider.Context context, final WardenModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new WardenModel(context.bakeLayer(ModelLayers.WARDEN)),
                new BabyWardenModel(context.bakeLayer(MoreBabiesModelLayers.WARDEN_BABY))
        );
    }

    @ModifyArgs(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/WardenRenderer;addLayer(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z"
            )
    )
    private void modifyConstructorAddLayer(final Args args, final EntityRendererProvider.Context context) {
        final RenderLayer<WardenRenderState, WardenModel> layer = args.get(0);
        if (layer instanceof LivingEntityEmissiveLayer<WardenRenderState, WardenModel> emissiveLayer) {
            @SuppressWarnings("unchecked")
            final BabyLivingEntityEmissiveLayer<WardenRenderState, WardenModel> babyEmissiveLayer = (BabyLivingEntityEmissiveLayer<WardenRenderState, WardenModel>) emissiveLayer;
            final Function<WardenRenderState, Identifier> originalTextureProvider = babyEmissiveLayer.more_babies$getOriginalTextureProvider();
            final Identifier originalTexture = originalTextureProvider.apply(new WardenRenderState());
            final ModelLayerLocation babyTexture = more_babies$LAYER_TEXTURE_TO_MODEL_LAYER.get(originalTexture);
            if (babyTexture != null) {
                babyEmissiveLayer.more_babies$setBabyModel(new BabyWardenModel(context.bakeLayer(babyTexture)));
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
        final Function<WardenRenderState, Identifier> originalTextureProvider = args.get(1);
        final Function<WardenRenderState, Identifier> textureProvider = (state) -> {
            final Identifier originalTexture = originalTextureProvider.apply(state);
            return state.isBaby ? more_babies$LAYER_TEXTURE_TO_BABY_LAYER_TEXTURE.getOrDefault(originalTexture, originalTexture) : originalTexture;
        };
        args.set(1, textureProvider);
    }

    @Override
    public @NonNull Identifier getTextureLocation(final WardenRenderState state) {
        return state.isBaby ? more_babies$TEXTURE_BABY : TEXTURE;
    }

    @Override
    public void submit(final WardenRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
