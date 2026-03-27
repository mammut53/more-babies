package io.github.mammut53.more_babies.client.model.monster.witch;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.witch.WitchModel;
import net.minecraft.client.model.npc.BabyVillagerModel;

public class BabyWitchModel extends WitchModel {

    public BabyWitchModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = BabyVillagerModel.createBodyModel();
        final PartDefinition root = mesh.getRoot();
        final PartDefinition head = root.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -3.5F, 8.0F, 8.0F, 7.0F), PartPose.offset(0.0F, 16.0F, 0.0F)
        );
        final PartDefinition hat = head.addOrReplaceChild(
                "hat", CubeListBuilder.create().texOffs(0, 64).addBox(0.0F, 0.0F, 0.0F, 10.0F, 2.0F, 10.0F), PartPose.offset(-5.0F, -9.0F, -5.0F)
        );
        final PartDefinition hat2 = hat.addOrReplaceChild(
                "hat2",
                CubeListBuilder.create().texOffs(0, 76).addBox(0.0F, 0.0F, 0.0F, 7.0F, 4.0F, 7.0F),
                PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.05235988F, 0.0F, 0.02617994F)
        );
        final PartDefinition hat3 = hat2.addOrReplaceChild(
                "hat3",
                CubeListBuilder.create().texOffs(0, 87).addBox(0.0F, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offsetAndRotation(1.75F, -4.0F, 2.0F, -0.10471976F, 0.0F, 0.05235988F)
        );
        hat3.addOrReplaceChild(
                "hat4",
                CubeListBuilder.create().texOffs(0, 95).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)),
                PartPose.offsetAndRotation(1.75F, -2.0F, 2.0F, (float) (-Math.PI / 15), 0.0F, 0.10471976F)
        );
        final PartDefinition nose = head.getChild("nose");
        nose.addOrReplaceChild(
                "mole",
                CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 3.0F, -1.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.25F)),
                PartPose.offset(0.0F, -2.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 64, 128);
    }
}
