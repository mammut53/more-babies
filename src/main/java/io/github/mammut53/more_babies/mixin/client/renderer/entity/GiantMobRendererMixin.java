package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import net.minecraft.client.model.AdultAndBabyModelPair;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.BabyZombieModel;
import net.minecraft.client.model.monster.zombie.GiantZombieModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GiantMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Giant;
import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GiantMobRenderer.class)
public abstract class GiantMobRendererMixin extends MobRenderer<Giant, ZombieRenderState, HumanoidModel<ZombieRenderState>> {

    @Shadow
    private static final Identifier ZOMBIE_LOCATION = Identifier.withDefaultNamespace("textures/entity/zombie/zombie.png");
    @Unique
    private static final Identifier more_babies$ZOMBIE_BABY_LOCATION = Identifier.withDefaultNamespace("textures/entity/zombie/zombie_baby.png");

    @Unique
    @Final
    @Mutable
    private AdultAndBabyModelPair<HumanoidModel<ZombieRenderState>> more_babies$model;

    protected GiantMobRendererMixin(final EntityRendererProvider.Context context, final HumanoidModel<ZombieRenderState> model, final float shadow) {
        super(context, model, shadow);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectConstructor(final EntityRendererProvider.Context context, final float scale, final CallbackInfo ci) {
        this.more_babies$model = new AdultAndBabyModelPair<>(
                new GiantZombieModel(context.bakeLayer(ModelLayers.GIANT)),
                new BabyZombieModel<>(context.bakeLayer(MoreBabiesModelLayers.GIANT_BABY))
        );
    }

    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/GiantMobRenderer;addLayer(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z"
            )
    )
    private boolean redirectConstructorAddLayer(final GiantMobRenderer instance, final RenderLayer<ZombieRenderState, HumanoidModel<ZombieRenderState>> renderLayer, final EntityRendererProvider.Context context, final float scale) {
        if (renderLayer instanceof HumanoidArmorLayer<?,?,?>) {
            final ArmorModelSet<HumanoidModel<ZombieRenderState>> adultArmor = ArmorModelSet.bake(ModelLayers.GIANT_ARMOR, context.getModelSet(), GiantZombieModel::new);
            final ArmorModelSet<HumanoidModel<ZombieRenderState>> babyArmor = ArmorModelSet.bake(MoreBabiesModelLayers.GIANT_BABY_ARMOR, context.getModelSet(), BabyZombieModel::new);
            return this.addLayer(new HumanoidArmorLayer<>(instance, adultArmor, babyArmor, context.getEquipmentRenderer()));
        }

        return this.addLayer(renderLayer);
    }

    @Override
    public @NonNull Identifier getTextureLocation(final ZombieRenderState state) {
        return state.isBaby ? more_babies$ZOMBIE_BABY_LOCATION : ZOMBIE_LOCATION;
    }

    @Override
    public void submit(final ZombieRenderState state, final @NonNull PoseStack poseStack, final @NonNull SubmitNodeCollector submitNodeCollector, final @NonNull CameraRenderState camera) {
        this.model = this.more_babies$model.getModel(state.isBaby);
        super.submit(state, poseStack, submitNodeCollector, camera);
    }
}
