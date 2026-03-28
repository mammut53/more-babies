package io.github.mammut53.more_babies.client.model.monster.illager;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.world.entity.HumanoidArm;
import org.jspecify.annotations.NonNull;

public class BabyIllagerModel<S extends IllagerRenderState> extends IllagerModel<S> {

    public BabyIllagerModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();
        final PartDefinition head = root.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -3.5F, 8.0F, 8.0F, 7.0F), PartPose.offset(0.0F, 16.0F, 0.0F)
        );
        head.addOrReplaceChild(
                "hat",
                CubeListBuilder.create().texOffs(0, 30).addBox(-4.0F, -4.0F, -3.5F, 8.0F, 8.0F, 7.0F, new CubeDeformation(0.3F)),
                PartPose.offset(0.0F, -4.0F, 0.0F)
        );
        head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(23, 0).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 2.0F, 1.0F), PartPose.offset(0.0F, -2.0F, -4.0F));
        root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 15)
                        .addBox(-2.0F, -2.75F, -1.5F, 4.0F, 5.0F, 3.0F)
                        .texOffs(16, 21)
                        .addBox(-2.0F, -2.75F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.25F)),
                PartPose.offset(0.0F, 18.75F, 0.0F)
        );
        final PartDefinition arms = root.addOrReplaceChild(
                "arms",
                CubeListBuilder.create()
                        .texOffs(24, 17)
                        .addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F)
                        .texOffs(16, 15)
                        .addBox(2.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 17.5F + 0.9024F, -1.8175F, -1.0472F, 0.0F, 0.0F)
        );
        arms.addOrReplaceChild(
                "left_shoulder",
                CubeListBuilder.create()
                        .texOffs(16, 15)
                        .mirror()
                        .addBox(-4.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F),
                PartPose.ZERO
        );
        root.addOrReplaceChild(
                "right_leg", CubeListBuilder.create().texOffs(8, 23).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offset(-1.0F, 21.5F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_leg", CubeListBuilder.create().texOffs(0, 23).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 3.0F, 2.0F),
                PartPose.offset(1.0F, 21.5F, 0.0F)
        );
        root.addOrReplaceChild(
                "right_arm", CubeListBuilder.create().texOffs(36, 15).addBox(-1.0F, -2.4925F, -1.8401F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(-3.0F, 1.4025F, -0.9599F, -1.0472F, 0.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_arm", CubeListBuilder.create().texOffs(36, 15).mirror().addBox(5.0F, -2.4925F, -1.8401F, 2.0F, 4.0F, 2.0F),
                PartPose.offsetAndRotation(-3.0F, 1.4025F, -0.9599F, -1.0472F, 0.0F, 0.0F)
        );

        root.addOrReplaceChild(
                "right_arm",
                CubeListBuilder.create()
                        .texOffs(36, 15)
                        .addBox(-1.5F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offset(-2.5F, 17.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "left_arm",
                CubeListBuilder.create().texOffs(36, 15)
                        .mirror()
                        .addBox(-0.5F, -1.0F, -1.0F, 2.0F, 5.0F, 2.0F),
                PartPose.offset(2.5F, 17.0F, 0.0F)
        );
        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void translateToHand(final @NonNull IllagerRenderState state, final @NonNull HumanoidArm arm, final @NonNull PoseStack poseStack) {
        super.translateToHand(state, arm, poseStack);
        poseStack.translate(-0.5F * 0.0625F, 2.0F * 0.0625F, 0.0F);
    }
}
