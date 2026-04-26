package io.github.mammut53.more_babies.client.model.monster.guardian;

import io.github.mammut53.more_babies.client.model.geom.builders.LayerDefinitionMeshAccessor;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.guardian.GuardianModel;
import org.jspecify.annotations.NonNull;

public class BabyGuardianModel extends GuardianModel {

    public static final MeshTransformer BABY_SCALE = MeshTransformer.scaling(0.5F);

    public BabyGuardianModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        final LayerDefinition adultLayer = GuardianModel.createBodyLayer();
        final MeshDefinition mesh = ((LayerDefinitionMeshAccessor) adultLayer).more_babies$getMesh();
        final PartDefinition root = mesh.getRoot();
        final PartDefinition head = root.getChild("head");
        final PartDefinition tail0 = head.addOrReplaceChild(
                "tail0",
                CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(-3.0F, 13.0F, 7.0F, 6.0F, 6.0F, 8.0F),
                PartPose.ZERO
        );
        final PartDefinition tail1 = tail0.addOrReplaceChild(
                "tail1",
                CubeListBuilder.create()
                        .texOffs(0, 54)
                        .addBox(0.0F, 14.0F, 0.0F, 4.0F, 4.0F, 8.0F),
                PartPose.offset(-2.0F, 0.0F, 14.0F)
        );
        tail1.addOrReplaceChild(
                "tail2",
                CubeListBuilder.create().texOffs(40, 32)
                        .addBox(0.0F, 14.0F, 0.0F, 2.0F, 2.0F, 6.0F)
                        .texOffs(25, 19)
                        .addBox(1.0F, 10.0F, 4.0F, 1.0F, 10.0F, 9.0F),
                PartPose.offset(0.75F, 1.0F, 6.0F)
        );

        final LayerDefinition babyLayer = LayerDefinition.create(mesh, 128, 128);
        return babyLayer.apply(BABY_SCALE);
    }

    public static @NonNull LayerDefinition createElderGuardianLayer() {
        return BabyGuardianModel.createBodyLayer().apply(GuardianModel.ELDER_GUARDIAN_SCALE);
    }

}
