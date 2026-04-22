package io.github.mammut53.more_babies.client.model.monster.spider;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.spider.SpiderModel;

public class BabySpiderModel extends SpiderModel {

    public BabySpiderModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createSpiderBodyLayer() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();

        final float yOffset = 19.5F;
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 16).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, yOffset, -1.5F));
        root.addOrReplaceChild("body0", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, yOffset, 0.0F));
        root.addOrReplaceChild("body1", CubeListBuilder.create().texOffs(0, 6).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, yOffset, 4.5F));

        final CubeListBuilder rightLeg = CubeListBuilder.create().texOffs(9, 0).addBox(-7.5F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F));
        final CubeListBuilder leftLeg = CubeListBuilder.create().texOffs(9, 0).addBox(-0.5F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F));
        final float legZRot = (float) (Math.PI / 4);
        final float legYRotSpan = (float) (Math.PI / 8);
        root.addOrReplaceChild("right_hind_leg", rightLeg, PartPose.offsetAndRotation(-2.0F, yOffset, 1.0F, 0.0F, legZRot, -legZRot));
        root.addOrReplaceChild("left_hind_leg", leftLeg, PartPose.offsetAndRotation(2.0F, yOffset, 1.0F, 0.0F, -legZRot, legZRot));
        root.addOrReplaceChild("right_middle_hind_leg", rightLeg, PartPose.offsetAndRotation(-2.0F, yOffset, 0.5F, 0.0F, legYRotSpan, -0.5812F));
        root.addOrReplaceChild("left_middle_hind_leg", leftLeg, PartPose.offsetAndRotation(2.0F, yOffset, 0.5F, 0.0F, -legYRotSpan, 0.5812F));
        root.addOrReplaceChild("right_middle_front_leg",rightLeg, PartPose.offsetAndRotation(-2.0F, yOffset, 0.0F, 0.0F, -legYRotSpan, -0.5812F));
        root.addOrReplaceChild("left_middle_front_leg", leftLeg, PartPose.offsetAndRotation(2.0F, yOffset, 0.0F, 0.0F, legYRotSpan, 0.5812F));
        root.addOrReplaceChild("right_front_leg", rightLeg, PartPose.offsetAndRotation(-2.0F, yOffset, -0.5F, 0.0F, -legZRot, -legZRot));
        root.addOrReplaceChild("left_front_leg", leftLeg, PartPose.offsetAndRotation(2.0F, yOffset, -0.5F, 0.0F, legZRot, legZRot));

        return LayerDefinition.create(mesh, 32, 32);
    }
}
