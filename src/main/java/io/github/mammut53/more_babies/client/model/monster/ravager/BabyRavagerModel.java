package io.github.mammut53.more_babies.client.model.monster.ravager;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.ravager.RavagerModel;

public class BabyRavagerModel extends RavagerModel {

    public BabyRavagerModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();

        final PartDefinition neck = root.addOrReplaceChild(
                "neck",
                CubeListBuilder.create()
                        .texOffs(34, 50)
                        .addBox(-2.5F, -0.5F, -8.75F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 9.5F, 2.75F)
        );

        final PartDefinition head = neck.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-6.0F, -15.0F, -10.5F, 12.0F, 15.0F, 12.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0)
                        .addBox(-1.5F, -4.75F, -12.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 8.0F, -9.5F)
        );

        head.addOrReplaceChild(
                "right_horn",
                CubeListBuilder.create()
                        .texOffs(48, 32)
                        .addBox(-0.5F, -10.25F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-7.5F, -10.5F, -6.0F, 1.0996F, 0.0F, 0.0F)
        );

        head.addOrReplaceChild(
                "left_horn",
                CubeListBuilder.create()
                        .texOffs(48, 32)
                        .addBox(0.0F, -10.25F, -1.5F, 2.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(6.0F, -10.5F, -6.0F, 1.0996F, 0.0F, 0.0F)
        );

        head.addOrReplaceChild(
                "mouth",
                CubeListBuilder.create()
                        .texOffs(0, 27)
                        .addBox(-6.0F, 0.5F, -12.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -1.5F, 1.5F)
        );

        root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(-3.5F, -5.0F, -3.5F, 7.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 59)
                        .addBox(-3.0F, 3.0F, -3.5F, 6.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 13.5F, 1.0F, 1.5708F, 0.0F, 0.0F)
        );

        root.addOrReplaceChild(
                "right_hind_leg",
                CubeListBuilder.create()
                        .texOffs(64, 0)
                        .addBox(-2.0F, -0.5F, -2.25F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-4.0F, 6.5F, 9.0F)
        );

        root.addOrReplaceChild(
                "left_hind_leg",
                CubeListBuilder.create()
                        .texOffs(64, 0)
                        .addBox(-2.0F, -0.5F, -2.25F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4.0F, 6.5F, 9.0F)
        );

        root.addOrReplaceChild(
                "right_front_leg",
                CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(-2.0F, -0.5F, -2.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-4.0F, 6.5F, -2.5F)
        );

        root.addOrReplaceChild(
                "left_front_leg",
                CubeListBuilder.create()
                        .texOffs(48, 0)
                        .addBox(-2.0F, -0.5F, -2.5F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(4.0F, 6.5F, -2.5F)
        );

        return LayerDefinition.create(mesh, 128, 128);
    }

}
