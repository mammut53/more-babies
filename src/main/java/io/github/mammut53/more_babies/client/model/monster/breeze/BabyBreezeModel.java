package io.github.mammut53.more_babies.client.model.monster.breeze;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.breeze.BreezeModel;

import java.util.Set;

public class BabyBreezeModel extends BreezeModel {

    public BabyBreezeModel(final ModelPart root) {
        super(root);
    }

    public static MeshDefinition createBaseMesh() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();

        final PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

        final PartDefinition rods = body.addOrReplaceChild("rods", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 0.0F));

        rods.addOrReplaceChild(
                "rod_1",
                CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(1.2991F, -1.5F, 0.75F, -2.7489F, -1.0472F, 3.1416F)
        );

        rods.addOrReplaceChild(
                "rod_2",
                CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.2991F, -1.5F, 0.75F, -2.7489F, 1.0472F, 3.1416F)
        );

        rods.addOrReplaceChild("rod_3",
                CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-0.5F, 0.0F, -1.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.5F, -1.5F, 0.3927F, 0.0F, 0.0F)
        );

        final PartDefinition head = body.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(2, 18)
                        .addBox(-4.0F, -3.875F, -3.15F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0)
                        .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 2.0F, 0.0F)
        );

        head.addOrReplaceChild(
                "eyes",
                CubeListBuilder.create()
                        .texOffs(2, 18)
                        .addBox(-4.0F, -3.875F, -3.15F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 0)
                        .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        final PartDefinition windBody = root.addOrReplaceChild("wind_body", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

        final PartDefinition windBottom = windBody.addOrReplaceChild(
                "wind_bottom",
                CubeListBuilder.create()
                        .texOffs(0, 41)
                        .addBox(-1.0F, -3.75F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 12.0F, 0.0F)
        );

        final PartDefinition windMid = windBottom.addOrReplaceChild(
                "wind_mid",
                CubeListBuilder.create()
                        .texOffs(38, 14).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(41, 16).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                        .texOffs(21, 36).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -3.5F, 0.0F)
        );

        windMid.addOrReplaceChild(
                "wind_top",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.5F, -4.0F, -4.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
                        .texOffs(3, 3)
                        .addBox(-3.0F, -4.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                        .texOffs(53, 28)
                        .addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -3.0F, 0.0F)
        );

        return mesh;
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = createBaseMesh();
        mesh.getRoot().retainPartsAndChildren(Set.of("head", "rods"));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public static LayerDefinition createWindLayer() {
        MeshDefinition mesh = createBaseMesh();
        mesh.getRoot().retainPartsAndChildren(Set.of("wind_body"));
        return LayerDefinition.create(mesh, 128, 128);
    }

    public static LayerDefinition createEyesLayer() {
        MeshDefinition mesh = createBaseMesh();
        mesh.getRoot().retainPartsAndChildren(Set.of("eyes"));
        return LayerDefinition.create(mesh, 32, 32);
    }
}
