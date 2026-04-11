package io.github.mammut53.more_babies.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

public class BabyHumanoidModel<T extends HumanoidRenderState> extends HumanoidModel<T> {

    public BabyHumanoidModel(final ModelPart root) {
        super(root);
    }

    public static MeshDefinition createMesh(final CubeDeformation g, final float yOffset) {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();

        final PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, g), PartPose.offset(0.0F, 15.0F+ yOffset, 0.0F));

        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, g.extend(0.375F)), PartPose.ZERO);

        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 12).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 2.0F, g.extend(0.01F)), PartPose.offset(0.0F, 15.0F + yOffset, 0.0F));

        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(20, 12).addBox(-1.5F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, g), PartPose.offset(-2.5F, 16.0F + yOffset, 0.0F));

        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(20, 12).mirror().addBox(-0.5F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, g).mirror(false), PartPose.offset(2.5F, 16.0F + yOffset, 0.0F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, g), PartPose.offset(-0.95F, 18.0F + yOffset, 0.0F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-1.0F, 2.0F, -1.0F, 2.0F, 4.0F, 2.0F, g).mirror(false), PartPose.offset(0.95F, 18.0F + yOffset, 0.0F));

        return mesh;
    }

}
