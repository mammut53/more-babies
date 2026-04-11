package io.github.mammut53.more_babies.client.model.monster.skeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.mammut53.more_babies.client.model.BabyHumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.world.entity.HumanoidArm;
import org.jspecify.annotations.NonNull;

public class BabySkeletonModel<S extends SkeletonRenderState> extends SkeletonModel<S> {
   public BabySkeletonModel(final ModelPart root) {
      super(root);
   }

   public static LayerDefinition createBodyLayer() {
      final MeshDefinition mesh = BabyHumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
      final PartDefinition root = mesh.getRoot();

      createDefaultSkeletonMesh(root);

      return LayerDefinition.create(mesh, 64, 32);
   }

   protected static void createDefaultSkeletonMesh(final PartDefinition root) {
      root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));

      final PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, 0.0F));
      head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -6.15F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.ZERO);

      root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 16.0F, 0.0F));

      root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(36, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 16.0F, 0.0F));

      root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(40, 0).addBox(-0.5F, 2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 18.0F, 0.0F));

      root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(40, 0).addBox(-0.5F, 2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 18.0F, 0.0F));
   }

   public static LayerDefinition createSingleModelDualBodyLayer() {
      final MeshDefinition meshdefinition = new MeshDefinition();
      final PartDefinition root = meshdefinition.getRoot();

      root.addOrReplaceChild("body", CubeListBuilder.create()
              .texOffs(4, 12)
              .addBox(-2.0F, 1.0F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.01F))
              .texOffs(12, 31)
              .addBox(-2.0F, 1.0F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0125F)), PartPose.offset(0.0F, 14.0F, 0.0F));

      final PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create()
              .texOffs(0, 0)
              .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
              .texOffs(0, 19)
              .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.15F)), PartPose.offset(0.0F, 15.0F, 0.0F));

      head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

      root.addOrReplaceChild("right_arm", CubeListBuilder.create()
              .texOffs(16, 12)
              .addBox(-0.5F, -0.8332F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
              .texOffs(24, 31)
              .addBox(-1.0208F, -1.3436F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offset(-2.75F, 16.0F, 0.0F));

      root.addOrReplaceChild("left_arm", CubeListBuilder.create()
              .texOffs(32, 31)
              .addBox(-1.0F, -1.3332F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.25F))
              .texOffs(16, 12)
              .addBox(-0.4792F, -0.8436F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.75F, 16.0F, 0.0F));

      root.addOrReplaceChild("right_leg", CubeListBuilder.create()
              .texOffs(0, 12)
              .addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
              .texOffs(0, 31)
              .addBox(-1.0F, -0.752F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offset(-1.0F, 20.0F, 0.0F));

      root.addOrReplaceChild("left_leg", CubeListBuilder.create()
              .texOffs(0, 12)
              .addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
              .texOffs(2, 31)
              .addBox(-1.0F, -0.752F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.25F)), PartPose.offset(1.0F, 20.0F, 0.0F));

      return LayerDefinition.create(meshdefinition, 64, 64);
   }

   @Override
   public void translateToHand(final @NonNull SkeletonRenderState state, final @NonNull HumanoidArm arm, final @NonNull PoseStack poseStack) {
      this.root().translateAndRotate(poseStack);
      final ModelPart part = this.getArm(arm);
      part.translateAndRotate(poseStack);
   }
}
