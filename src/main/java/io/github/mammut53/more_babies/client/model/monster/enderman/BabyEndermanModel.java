package io.github.mammut53.more_babies.client.model.monster.enderman;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.enderman.EndermanModel;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import org.jspecify.annotations.NonNull;

public class BabyEndermanModel<T extends EndermanRenderState> extends EndermanModel<T> {

    public BabyEndermanModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();

        final PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 5.5F, 0.0F));

        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(-0.375F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(60, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 6.0F, 0.0F));

        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(60, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 6.0F, 0.0F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(60, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 9.5F, 0.0F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(60, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 9.5F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(final @NonNull T state) {
        super.setupAnim(state);

        if (state.isCreepy && state.isBaby) {
            // y is already modified by super.setupAnim(state) using amt=5.0F, so we reverse it in our calculation.
            final float amt = -5.0F + (5.0F * 0.75F);
            this.head.y -= amt;
            this.hat.y += amt;
        }
    }

}
