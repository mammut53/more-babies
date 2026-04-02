package io.github.mammut53.more_babies.client.model.monster.creeper;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.creeper.CreeperModel;

public class BabyCreeperModel extends CreeperModel {

    public BabyCreeperModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(final CubeDeformation g) {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, g), PartPose.offset(0.0F, 15.0F, 0.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 2.0F, g), PartPose.offset(0.0F, 15.0F, 0.0F));

        final CubeListBuilder leg = CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F,g);
        root.addOrReplaceChild("right_hind_leg", leg, PartPose.offset(-1.0F, 21.0F, 2.0F));
        root.addOrReplaceChild("left_hind_leg", leg, PartPose.offset(1.0F, 21.0F, 2.0F));
        root.addOrReplaceChild("right_front_leg", leg, PartPose.offset(-1.0F, 21.0F, -2.0F));
        root.addOrReplaceChild("left_front_leg", leg, PartPose.offset(1.0F, 21.0F, -2.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

}
