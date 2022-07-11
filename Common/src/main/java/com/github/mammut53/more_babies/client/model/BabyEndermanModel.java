package com.github.mammut53.more_babies.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.geom.ModelPart;

public class BabyEndermanModel extends EndermanModel {

    private final boolean scaleHead = true;
    private final float babyYHeadOffset = 14;
    private final float babyZHeadOffset = 0;
    private final float babyHeadScale = 2;
    private final float babyBodyScale = 2;
    private final float bodyYOffset = 14;

    public BabyEndermanModel(ModelPart modelPart) {
        super(modelPart);
    }

    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        if (this.young) {
            poseStack.pushPose();

            float l;
            if (this.scaleHead) {
                l = 1.5F / this.babyHeadScale;
                poseStack.scale(l, l, l);
            }

            poseStack.translate(0.0D, (this.babyYHeadOffset / 16.0F), (this.babyZHeadOffset / 16.0F));

            this.headParts().forEach((modelPart) -> {
                modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k);
            });

            poseStack.popPose();
            poseStack.pushPose();

            l = 1.0F / this.babyBodyScale;
            poseStack.scale(l, l, l);

            poseStack.translate(0.0D, (this.bodyYOffset / 16.0F), 0.0D);

            this.bodyParts().forEach((modelPart) -> {
                modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k);
            });
            poseStack.popPose();
        } else {
            this.headParts().forEach((modelPart) -> {
                modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k);
            });
            this.bodyParts().forEach((modelPart) -> {
                modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k);
            });
        }

    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head, this.hat);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg);
    }
}
