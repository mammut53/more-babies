package io.github.mammut53.more_babies.client.model.monster.warden;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.warden.WardenModel;
import org.jspecify.annotations.NonNull;

import java.util.Set;

public class BabyWardenModel extends WardenModel implements WardenResetArmPoses {

    public BabyWardenModel(final ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();

        final PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        final PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -5.75F, -2.25F, 9.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.5F, 0.0F));

        body.addOrReplaceChild("right_ribcage", CubeListBuilder.create().texOffs(30, 6).addBox(-1.0F, -5.25F, -0.3F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -0.5F, -2.0F));

        body.addOrReplaceChild("left_ribcage", CubeListBuilder.create().texOffs(30, 6).mirror().addBox(-3.0F, -5.25F, -0.3F, 4.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.5F, -0.5F, -2.0F));

        final PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 44).addBox(-6.0F, -12.0F, -4.0F, 12.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.75F, 0.75F));

        head.addOrReplaceChild("right_tendril", CubeListBuilder.create().texOffs(40, 0).addBox(-12.0F, -10.0F, 0.0F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -9.0F, 0.0F));

        head.addOrReplaceChild("left_tendril", CubeListBuilder.create().texOffs(40, 0).mirror().addBox(0.0F, -10.0F, 0.0F, 12.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(6.0F, -9.0F, 0.0F));

        body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 29).addBox(-2.0F, 0.25F, -1.75F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -6.0F, 0.5F));

        body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(22, 25).addBox(-2.0F, 0.25F, -1.75F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -6.0F, 0.5F));

        bone.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(38, 33).addBox(-1.55F, 0.25F, -1.25F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.95F, -6.5F, 0.0F));

        bone.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(38, 24).addBox(-1.45F, 0.25F, -1.25F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.95F, -6.5F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    public static @NonNull LayerDefinition createTendrilsLayer() {
        return createBodyLayer().apply(mesh -> {
            mesh.getRoot().retainExactParts(Set.of("left_tendril", "right_tendril"));
            return mesh;
        });
    }

    public static @NonNull LayerDefinition createHeartLayer() {
        return createBodyLayer().apply(mesh -> {
            mesh.getRoot().retainExactParts(Set.of("body"));
            return mesh;
        });
    }

    public static @NonNull LayerDefinition createBioluminescentLayer() {
        return createBodyLayer().apply(mesh -> {
            mesh.getRoot().retainExactParts(Set.of("head", "left_arm", "right_arm", "left_leg", "right_leg"));
            return mesh;
        });
    }

    public static @NonNull LayerDefinition createPulsatingSpotsLayer() {
        return createBodyLayer().apply(mesh -> {
            mesh.getRoot().retainExactParts(Set.of("body", "head", "left_arm", "right_arm", "left_leg", "right_leg"));
            return mesh;
        });
    }

    @Override
    public void more_babies$restArmPoses() {
        this.leftArm.yRot = 0.0F;
        this.leftArm.z = 0.5F;
        this.leftArm.x = -6.5F;
        this.leftArm.y = -6.0F;
        this.rightArm.yRot = 0.0F;
        this.rightArm.z = 0.5F;
        this.rightArm.x = 6.5F;
        this.rightArm.y = -6.0F;
    }

}
