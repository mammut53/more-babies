package io.github.mammut53.more_babies.client.model.animal.golem;

import net.minecraft.client.model.animal.golem.IronGolemModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BabyIronGolemModel extends IronGolemModel {

    public BabyIronGolemModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -11.0F, -2.5F, 8.0F, 9.0F, 7.0F).texOffs(24, 0).addBox(-1.0F, -4.0F, -3.5F, 2.0F, 2.0F, 1.0F),
                PartPose.offset(0.0F, 9.0F, -2.0F)
        );
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 40)
                        .addBox(-4.5F, -2.0F, -3.0F, 9.0F, 7.0F, 6.0F)
                        .texOffs(0, 70)
                        .addBox(-2.5F, 4.0F, -1.5F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 9.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "right_arm", CubeListBuilder.create().texOffs(60, 21).addBox(-6.5F, -2.5F, -1.5F, 2.0F, 15.0F, 3.0F), PartPose.offset(0.0F, 9.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_arm", CubeListBuilder.create().texOffs(60, 58).addBox(4.5F, -2.5F, -1.5F, 2.0F, 15.0F, 3.0F), PartPose.offset(0.0F, 9.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "right_leg", CubeListBuilder.create().texOffs(37, 0).addBox(0.0F, -3.0F, -1.5F, 3.0F, 8.0F, 3.0F), PartPose.offset(-4.0F, 19.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_leg", CubeListBuilder.create().texOffs(60, 0).mirror().addBox(-4.0F, -3.0F, -1.5F, 3.0F, 8.0F, 3.0F), PartPose.offset(5.0F, 19.0F, 0.0F)
        );
        return LayerDefinition.create(mesh, 128, 128);
    }
}
