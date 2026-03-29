package io.github.mammut53.more_babies.client.model.animal.golem;

import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
public class BabySnowGolemModel extends SnowGolemModel {

    public BabySnowGolemModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();
        float yOffset = 14.5F;
        final CubeDeformation deformation = new CubeDeformation(-0.5F);
        root.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-1.0F)), PartPose.offset(0.0F, yOffset, 0.0F)
        );

        final CubeListBuilder arm = CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F);
        root.addOrReplaceChild("left_arm", arm, PartPose.offsetAndRotation(2.5F, 14.5F, 0.5F, 0.0F, 0.0F, 1.0F));
        root.addOrReplaceChild("right_arm", arm, PartPose.offsetAndRotation(-2.5F, 14.5F, -0.5F, 0.0F, (float) Math.PI, -1.0F));
        root.addOrReplaceChild(
                "upper_body",
                CubeListBuilder.create().texOffs(0, 16).addBox(-2.5F, -6.0F, -2.5F, 5.0F, 5.0F, 5.0F, deformation),
                PartPose.offset(0.0F, 19.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "lower_body",
                CubeListBuilder.create().texOffs(0, 26).addBox(-3.5F, -8.0F, -3.5F, 7.0F, 7.0F, 7.0F, deformation),
                PartPose.offset(0.0F, 25.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }
}
