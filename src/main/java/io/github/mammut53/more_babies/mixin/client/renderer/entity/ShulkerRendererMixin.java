package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.shulker.BabyShulkerModel;
import io.github.mammut53.more_babies.client.renderer.MoreBabiesSheets;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.shulker.ShulkerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.ShulkerRenderer;
import net.minecraft.client.renderer.entity.state.ShulkerRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerRenderer.class)
public abstract class ShulkerRendererMixin extends MobRenderer<Shulker, ShulkerRenderState, ShulkerModel> {
    @Unique
    private static final Identifier more_babies$DEFAULT_BABY_TEXTURE_LOCATION = MoreBabiesSheets.DEFAULT_SHULKER_BABY_TEXTURE_LOCATION.texture().withPath(path -> "textures/" + path + ".png");
    @Unique
    private static final Identifier[] more_babies$BABY_TEXTURE_LOCATION = MoreBabiesSheets.SHULKER_BABY_TEXTURE_LOCATION
            .stream()
            .map(location -> location.texture().withPath(path -> "textures/" + path + ".png"))
            .toArray(Identifier[]::new);

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<ShulkerModel> more_babies$model;

    protected ShulkerRendererMixin(final EntityRendererProvider.Context context, final ShulkerModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new ShulkerModel(context.bakeLayer(ModelLayers.SHULKER)),
                new BabyShulkerModel(context.bakeLayer(MoreBabiesModelLayers.SHULKER_BABY))
        );
    }

    @Inject(
            method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/ShulkerRenderState;)Lnet/minecraft/resources/Identifier;",
            at = @At("RETURN"),
            cancellable = true
    )
    public void getTextureLocation(final ShulkerRenderState state, final CallbackInfoReturnable<Identifier> cir) {
        if (state.isBaby) {
            cir.setReturnValue(more_babies$getBabyTextureLocation(state.color));
        }
    }

    @Override
    public void submit(final ShulkerRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        if (state.isBaby) {
            poseStack.scale(2.0F, 2.0F, 2.0F);
        }

        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Unique
    private static Identifier more_babies$getBabyTextureLocation(@Nullable final DyeColor color) {
        return color == null ? more_babies$DEFAULT_BABY_TEXTURE_LOCATION : more_babies$BABY_TEXTURE_LOCATION[color.getId()];
    }

}
