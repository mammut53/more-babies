package io.github.mammut53.more_babies.client.model.monster.shulker;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.shulker.ShulkerModel;
import net.minecraft.client.renderer.entity.state.ShulkerRenderState;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class BabyShulkerModel extends ShulkerModel {

    private final ModelPart lidPart;
    private final ModelPart headPart;

    public BabyShulkerModel(final ModelPart root) {
        super(root);
        this.lidPart = root.getChild("lid");
        this.headPart = root.getChild("head");
    }

    private static MeshDefinition createShellMesh() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "lid", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F), PartPose.offset(0.0F, 24.0F, 0.0F)
        );
        root.addOrReplaceChild(
                "base", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F), PartPose.offset(0.0F, 24.0F, 0.0F)
        );
        return mesh;
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = createShellMesh();
        final PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F), PartPose.offset(0.0F, 17.0F, 0.0F)
        );
        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(final @NonNull ShulkerRenderState state) {
        super.resetPose();
        final float bs = (0.5F + state.peekAmount) * (float) Math.PI;
        final float q = -1.0F + Mth.sin(bs);
        float extra = 0.0F;
        if (bs > (float) Math.PI) {
            extra = Mth.sin(state.ageInTicks * 0.1F) * 0.7F;
        }

        this.lidPart.setPos(0.0F, 20.0F + Mth.sin(bs) * 4.0F + extra, 0.0F);
        if (state.peekAmount > 0.3F) {
            this.lidPart.yRot = q * q * q * q * (float) Math.PI * 0.125F;
        } else {
            this.lidPart.yRot = 0.0F;
        }

        this.headPart.xRot = state.xRot * (float) (Math.PI / 180.0);
        this.headPart.yRot = (state.yHeadRot - 180.0F - state.yBodyRot) * (float) (Math.PI / 180.0);
    }

}
