package com.github.mammut53.more_babies.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ItemInHandLayerIgnoreBaby<T extends LivingEntity, M extends EntityModel<T> & ArmedModel> extends ItemInHandLayer<T, M> {

    public ItemInHandLayerIgnoreBaby(RenderLayerParent<T, M> renderLayerParent, ItemInHandRenderer itemInHandRenderer) {
        super(renderLayerParent, itemInHandRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
        boolean bl = livingEntity.getMainArm() == HumanoidArm.RIGHT;
        ItemStack itemStack = bl ? livingEntity.getOffhandItem() : livingEntity.getMainHandItem();
        ItemStack itemStack2 = bl ? livingEntity.getMainHandItem() : livingEntity.getOffhandItem();

        if (itemStack.isEmpty() && itemStack2.isEmpty()) return;

        poseStack.pushPose();
        this.renderArmWithItem(livingEntity, itemStack2, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, poseStack, multiBufferSource, i);
        this.renderArmWithItem(livingEntity, itemStack, ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND, HumanoidArm.LEFT, poseStack, multiBufferSource, i);
        poseStack.popPose();
    }
}
