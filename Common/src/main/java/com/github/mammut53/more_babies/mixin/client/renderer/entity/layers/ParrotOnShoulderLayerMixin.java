package com.github.mammut53.more_babies.mixin.client.renderer.entity.layers;

import com.github.mammut53.more_babies.MoreBabiesCommon;
import com.github.mammut53.more_babies.client.renderer.entity.BabyParrotRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ParrotOnShoulderLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParrotOnShoulderLayer.class)
abstract class ParrotOnShoulderLayerMixin<R extends Player> extends RenderLayer<R, PlayerModel<R>> {

    @Final
    @Shadow
    private ParrotModel model;

    public ParrotOnShoulderLayerMixin(RenderLayerParent<R, PlayerModel<R>> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
    }

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/player/Player;FFFFZ)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Player player, float f, float g, float h, float j, boolean bl, CallbackInfo ci) {
        CompoundTag compoundTag = bl ? player.getShoulderEntityLeft() : player.getShoulderEntityRight();
        EntityType.byString(compoundTag.getString("id")).filter(
                (entityType) -> entityType == EntityType.PARROT || entityType == MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.PARROT)
        ).ifPresent((entityType) -> {
            poseStack.pushPose();
            VertexConsumer vertexConsumer = null;
            if (entityType == EntityType.PARROT) {
                vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(ParrotRenderer.PARROT_LOCATIONS[compoundTag.getInt("Variant")]));
                poseStack.translate(bl ? 0.4000000059604645D : -0.4000000059604645D, player.isCrouching() ? -1.2999999523162842D : -1.5D, 0.0D);
            } else if (entityType == MoreBabiesCommon.PARENT_BABY_RELATION.get(EntityType.PARROT)) {
                vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(BabyParrotRenderer.PARROT_LOCATIONS[compoundTag.getInt("Variant")]));
                poseStack.translate(bl ? 0.4000000059604645D : -0.4000000059604645D, player.isCrouching() ? -1.2999999523162842D + 0.75D : -1.5D + 0.75D, 0.0D);
                poseStack.scale(0.5F, 0.5F, 0.5F);
            }
            assert vertexConsumer != null;
            this.model.renderOnShoulder(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, f, g, h, j, player.tickCount);
            poseStack.popPose();
        });
        ci.cancel();
    }
}