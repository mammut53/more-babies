package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.witch.BabyWitchModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.witch.WitchModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WitchRenderer;
import net.minecraft.client.renderer.entity.state.WitchRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Witch;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitchRenderer.class)
public abstract class WitchRendererMixin extends MobRenderer<Witch, WitchRenderState, WitchModel> {

    @Shadow
    @Final
    private static final Identifier WITCH_LOCATION = Identifier.withDefaultNamespace("textures/entity/witch/witch.png");
    @Unique
    private static final Identifier more_babies$WITCH_LOCATION = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/witch/witch_baby.png");

    @Unique
    private WitchModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private WitchModel more_babies$babyModel;

    protected WitchRendererMixin(final EntityRendererProvider.Context context, final WitchModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new WitchModel(context.bakeLayer(ModelLayers.WITCH));
        this.more_babies$babyModel = new BabyWitchModel(context.bakeLayer(MoreBabiesModelLayers.WITCH_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final WitchRenderState state) {
        return state.isBaby ? more_babies$WITCH_LOCATION : WITCH_LOCATION;
    }

    @Override
    public void submit(final WitchRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

}
