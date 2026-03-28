package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.MoreBabies;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.npc.BabyVillagerModel;
import net.minecraft.client.model.npc.VillagerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.WanderingTraderRenderer;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTraderRenderer.class)
public abstract class WanderingTraderRendererMixin extends MobRenderer<WanderingTrader, VillagerRenderState, VillagerModel> {

    @Shadow
    private static final Identifier VILLAGER_BASE_SKIN = Identifier.withDefaultNamespace("textures/entity/wandering_trader/wandering_trader.png");;
    @Unique
    private static final Identifier more_babies$VILLAGER_BASE_SKIN = Identifier.fromNamespaceAndPath(MoreBabies.MOD_ID, "textures/entity/wandering_trader/wandering_trader_baby.png");

    @Unique
    @Final
    @Mutable
    private VillagerModel more_babies$adultModel;
    @Unique
    @Final
    @Mutable
    private VillagerModel more_babies$babyModel;

    protected WanderingTraderRendererMixin(final EntityRendererProvider.Context context, final VillagerModel model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, CallbackInfo ci) {
        this.more_babies$adultModel = new VillagerModel(context.bakeLayer(ModelLayers.WANDERING_TRADER));
        this.more_babies$babyModel = new BabyVillagerModel(context.bakeLayer(ModelLayers.VILLAGER_BABY));
    }

    @Override
    public @NonNull Identifier getTextureLocation(final VillagerRenderState state) {
        return state.isBaby ? more_babies$VILLAGER_BASE_SKIN : VILLAGER_BASE_SKIN;
    }

    @Override
    protected float getShadowRadius(final VillagerRenderState state) {
        float radius = super.getShadowRadius(state);
        return state.isBaby ? radius * 0.5F : radius;
    }

    @Override
    public void submit(final VillagerRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = state.isBaby ? this.more_babies$babyModel : this.more_babies$adultModel;
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
