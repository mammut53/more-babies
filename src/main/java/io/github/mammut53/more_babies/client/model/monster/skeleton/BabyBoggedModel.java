package io.github.mammut53.more_babies.client.model.monster.skeleton;

import io.github.mammut53.more_babies.client.model.BabyHumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.skeleton.BoggedModel;

public class BabyBoggedModel extends BoggedModel {

   public BabyBoggedModel(final ModelPart root) {
      super(root);
   }

   public static LayerDefinition createBodyLayer() {
      final MeshDefinition mesh = BabyHumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
      final PartDefinition root = mesh.getRoot();

      BabySkeletonModel.createDefaultSkeletonMesh(root);

      final PartDefinition mushrooms = root.getChild("head").addOrReplaceChild("mushrooms", CubeListBuilder.create(), PartPose.ZERO);
      mushrooms.addOrReplaceChild(
         "red_mushroom_1",
         CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
         PartPose.offsetAndRotation(2.0F + 0.35F, -7.0F, 2.0F - 0.35F, 0.0F, (float) (Math.PI / 4), 0.0F)
      );
      mushrooms.addOrReplaceChild(
         "red_mushroom_2",
         CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
         PartPose.offsetAndRotation(2.0F - 0.35F, -7.0F, 2.0F - 0.35F, 0.0F, (float) (Math.PI * 3.0 / 4.0), 0.0F)
      );

      return LayerDefinition.create(mesh, 64, 32);
   }
}
