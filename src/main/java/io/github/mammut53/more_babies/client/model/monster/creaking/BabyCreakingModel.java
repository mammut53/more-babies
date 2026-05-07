package io.github.mammut53.more_babies.client.model.monster.creaking;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.creaking.CreakingModel;

import java.util.Set;

public class BabyCreakingModel extends CreakingModel {

    public BabyCreakingModel(final ModelPart roots) {
        super(roots);
    }

    private static MeshDefinition createMesh() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition part = mesh.getRoot();

        final PartDefinition root = part.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        final PartDefinition upperBody = root.addOrReplaceChild("upper_body", CubeListBuilder.create(), PartPose.offset(-0.6667F, -13.1665F, 0.0F));

        upperBody.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -8.7502F, -2.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(24, 21).addBox(-2.5F, -11.7502F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(8, 31).addBox(2.5F, -10.7502F, 0.0F, 8.0F, 12.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(24, 9).addBox(-10.5834F, -12.7502F, 0.0F, 8.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -8.0833F, 0.0F));

        upperBody.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -2.1668F, -2.3333F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 0).addBox(-4.0F, -3.1668F, -2.3333F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.6666F, 0.6667F));

        upperBody.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 14).addBox(-1.3333F, -1.5002F, -1.6667F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-1.3333F, 12.4998F, -1.75F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.6666F, -6.3333F, 1.0F));

        upperBody.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 29).addBox(0.0F, -0.8335F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 11).addBox(0.0F, -3.75F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 16).addBox(0.0F, 10.1665F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.9999F, 0.3333F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 29).addBox(-0.9167F, -0.3334F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 35).addBox(-0.9167F, 10.4666F, -3.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, -10.6666F, 0.3333F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 27).addBox(-2.0F, -1.3334F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 29).addBox(-3.9999F, 11.4666F, -3.0F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(8, 27).addBox(-2.0F, -3.3334F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.6667F, -11.6666F, 0.3333F));

        return mesh;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = createMesh();
        return LayerDefinition.create(mesh, 64, 64);
    }

    public static LayerDefinition createEyesLayer() {
        MeshDefinition mesh = createMesh();
        mesh.getRoot().retainExactParts(Set.of("head"));
        return LayerDefinition.create(mesh, 64, 64);
    }

}
