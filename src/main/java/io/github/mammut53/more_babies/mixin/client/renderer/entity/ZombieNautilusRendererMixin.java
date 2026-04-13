package io.github.mammut53.more_babies.mixin.client.renderer.entity;


import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import net.minecraft.client.model.animal.nautilus.NautilusModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.ZombieNautilusRenderer;
import net.minecraft.client.renderer.entity.state.NautilusRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilus;
import net.minecraft.world.entity.animal.nautilus.ZombieNautilusVariant;
import net.minecraft.world.entity.variant.ModelAndTexture;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ZombieNautilusRenderer.class)
public abstract class ZombieNautilusRendererMixin extends MobRenderer<ZombieNautilus, NautilusRenderState, NautilusModel> {

    @Unique
    @Final
    @Mutable
    private Map<ZombieNautilusVariant.ModelType, ModelAndTexture<NautilusModel>> more_babies$babyModels;

    protected ZombieNautilusRendererMixin(final EntityRendererProvider.Context context, final NautilusModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructorBabyModels(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$babyModels = Maps.newEnumMap(
                Map.of(
                        ZombieNautilusVariant.ModelType.NORMAL,
                        new ModelAndTexture<>(new NautilusModel(context.bakeLayer(MoreBabiesModelLayers.ZOMBIE_NAUTILUS_BABY)), Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "entity/nautilus/zombie_nautilus_baby")),
                        ZombieNautilusVariant.ModelType.WARM,
                        new ModelAndTexture<>(new NautilusModel(context.bakeLayer(MoreBabiesModelLayers.ZOMBIE_NAUTILUS_CORAL_BABY)), Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "entity/nautilus/zombie_nautilus_coral_baby"))
                )
        );
    }

    @Inject(
            method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/NautilusRenderState;)Lnet/minecraft/resources/Identifier;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void injectGetTextureLocation(final NautilusRenderState state, final CallbackInfoReturnable<Identifier> cir) {
        final ModelAndTexture<NautilusModel> babyModelAndTexture = this.more_babies$getBabyModelAndTexture(state);
        if (babyModelAndTexture != null) {
            cir.setReturnValue(babyModelAndTexture.asset().texturePath());
        }
    }

    @Inject(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/NautilusRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/MobRenderer;submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/level/CameraRenderState;)V"
            )
    )
    public void submit(final NautilusRenderState state, final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final CameraRenderState camera, final CallbackInfo ci) {
        final ModelAndTexture<NautilusModel> babyModelAndTexture = this.more_babies$getBabyModelAndTexture(state);
        if (babyModelAndTexture != null) {
            this.model = babyModelAndTexture.model();
        }
    }

    @Unique
    private @Nullable ModelAndTexture<NautilusModel> more_babies$getBabyModelAndTexture(final NautilusRenderState state) {
        if (state.isBaby && state.variant != null) {
            return this.more_babies$babyModels.get(state.variant.modelAndTexture().model());
        }

        return null;
    }
}