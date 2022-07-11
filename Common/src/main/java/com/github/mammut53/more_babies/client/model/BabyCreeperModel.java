package com.github.mammut53.more_babies.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.model.geom.ModelPart;

public class BabyCreeperModel extends CreeperModel {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;

    private final boolean scaleHead = true;
    private final float babyYHeadOffset = 14;
    private final float babyZHeadOffset = 0;
    private final float babyHeadScale = 2;
    private final float babyBodyScale = 2;
    private final float bodyYOffset = 24;

    public BabyCreeperModel(ModelPart modelPart) {
        super(modelPart);
        this.root = modelPart;
        this.head = modelPart.getChild("head");
        this.leftHindLeg = modelPart.getChild("right_hind_leg");
        this.rightHindLeg = modelPart.getChild("left_hind_leg");
        this.leftFrontLeg = modelPart.getChild("right_front_leg");
        this.rightFrontLeg = modelPart.getChild("left_front_leg");
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
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.root, this.rightHindLeg, this.leftHindLeg, this.leftFrontLeg, this.rightFrontLeg);
    }
}
