package io.github.mammut53.more_babies.mixin.client.renderer.entity;

import io.github.mammut53.more_babies.client.model.geom.MoreBabiesModelLayers;
import io.github.mammut53.more_babies.client.model.monster.skeleton.BabySkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractSkeletonRenderer.class)
public abstract class AbstractSkeletonRendererMixin<T extends AbstractSkeleton, S extends SkeletonRenderState> extends HumanoidMobRenderer<T, S, SkeletonModel<S>> {

    protected AbstractSkeletonRendererMixin(final EntityRendererProvider.Context context, final SkeletonModel<S> model, final float shadow) {
        super(context, model, shadow);
    }

    @Redirect(
            method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;Lnet/minecraft/client/renderer/entity/ArmorModelSet;Lnet/minecraft/client/model/monster/skeleton/SkeletonModel;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/AbstractSkeletonRenderer;addLayer(Lnet/minecraft/client/renderer/entity/layers/RenderLayer;)Z"
            )
    )
    @SuppressWarnings("unchecked")
    private boolean redirectConstructorAddLayer(final AbstractSkeletonRenderer<T, S> instance, final RenderLayer<S, SkeletonModel<S>> renderLayer, final EntityRendererProvider.Context context, final ArmorModelSet<ModelLayerLocation> armorSet, final SkeletonModel<S> bodyModel) {
        if (instance instanceof BoggedRenderer) {
            final ArmorModelSet<SkeletonModel<S>> adultArmor = ArmorModelSet.bake(ModelLayers.BOGGED_ARMOR, context.getModelSet(), SkeletonModel::new);
            final ArmorModelSet<SkeletonModel<S>> babyArmor = ArmorModelSet.bake(MoreBabiesModelLayers.BOGGED_BABY_ARMOR, context.getModelSet(), BabySkeletonModel::new);
            return this.addLayer(new HumanoidArmorLayer<>(instance, adultArmor, babyArmor, context.getEquipmentRenderer()));
        } else if (instance instanceof ParchedRenderer) {
            final ArmorModelSet<SkeletonModel<S>> adultArmor = ArmorModelSet.bake(ModelLayers.PARCHED_ARMOR, context.getModelSet(), SkeletonModel::new);
            final ArmorModelSet<SkeletonModel<S>> babyArmor = ArmorModelSet.bake(MoreBabiesModelLayers.PARCHED_BABY_ARMOR, context.getModelSet(), BabySkeletonModel::new);
            return this.addLayer(new HumanoidArmorLayer<>(instance, adultArmor, babyArmor, context.getEquipmentRenderer()));
        } else if (instance instanceof SkeletonRenderer) {
            final ArmorModelSet<SkeletonModel<S>> adultArmor = ArmorModelSet.bake(ModelLayers.SKELETON_ARMOR, context.getModelSet(), SkeletonModel::new);
            final ArmorModelSet<SkeletonModel<S>> babyArmor = ArmorModelSet.bake(MoreBabiesModelLayers.SKELETON_BABY_ARMOR, context.getModelSet(), BabySkeletonModel::new);
            return this.addLayer(new HumanoidArmorLayer<>(instance, adultArmor, babyArmor, context.getEquipmentRenderer()));
        } else if (instance instanceof StrayRenderer) {
            final ArmorModelSet<SkeletonModel<S>> adultArmor = ArmorModelSet.bake(ModelLayers.STRAY_ARMOR, context.getModelSet(), SkeletonModel::new);
            final ArmorModelSet<SkeletonModel<S>> babyArmor = ArmorModelSet.bake(MoreBabiesModelLayers.STRAY_BABY_ARMOR, context.getModelSet(), BabySkeletonModel::new);
            return this.addLayer(new HumanoidArmorLayer<>(instance, adultArmor, babyArmor, context.getEquipmentRenderer()));
        } else if (instance instanceof WitherSkeletonRenderer) {
            final ArmorModelSet<SkeletonModel<S>> adultArmor = ArmorModelSet.bake(ModelLayers.WITHER_SKELETON_ARMOR, context.getModelSet(), SkeletonModel::new);
            final ArmorModelSet<SkeletonModel<S>> babyArmor = ArmorModelSet.bake(MoreBabiesModelLayers.WITHER_SKELETON_BABY_ARMOR, context.getModelSet(), BabySkeletonModel::new);
            return this.addLayer(new HumanoidArmorLayer<>(instance, adultArmor, babyArmor, context.getEquipmentRenderer()));
        }

        return this.addLayer(renderLayer);
    }
}
