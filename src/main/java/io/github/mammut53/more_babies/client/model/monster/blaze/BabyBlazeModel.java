package io.github.mammut53.more_babies.client.model.monster.blaze;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class BabyBlazeModel extends BlazeModel {

    private static final float SCALE_FACTOR = 0.7F;
    private static final float RODS_OFFSET = 11.0F;

    public BabyBlazeModel(final ModelPart root) {
        super(root);
    }

    private static String getPartName(final int i) {
        return "part" + i;
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition mesh = new MeshDefinition();
        final PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(3, 3).addBox(-3.0F, -7.375F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.25F, 0.0F));
        final CubeListBuilder rod = CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F);

        float angle = 0.0F;

        for (int i = 0; i < 4; i++) {
            float x = Mth.cos(angle) * 9.0F;
            float y = -2.0F + Mth.cos(i * 2 * 0.25F);
            float z = Mth.sin(angle) * 9.0F;
            root.addOrReplaceChild(getPartName(i), rod, PartPose.offset(x * SCALE_FACTOR, y * SCALE_FACTOR + RODS_OFFSET, z * SCALE_FACTOR));
            angle += (float) (Math.PI / 2);
        }

        angle = (float) (Math.PI / 4);

        for (int i = 4; i < 8; i++) {
            float x = Mth.cos(angle) * 7.0F;
            float y = 2.0F + Mth.cos(i * 2 * 0.25F);
            float z = Mth.sin(angle) * 7.0F;
            root.addOrReplaceChild(getPartName(i), rod, PartPose.offset(x * SCALE_FACTOR, y * SCALE_FACTOR + RODS_OFFSET, z * SCALE_FACTOR));
            angle += (float) (Math.PI / 2);
        }

        angle = 0.47123894F;

        for (int i = 8; i < 12; i++) {
            float x = Mth.cos(angle) * 5.0F;
            float y = 11.0F + Mth.cos(i * 1.5F * 0.5F);
            float z = Mth.sin(angle) * 5.0F;
            root.addOrReplaceChild(getPartName(i), rod, PartPose.offset(x * SCALE_FACTOR, y * SCALE_FACTOR + RODS_OFFSET, z * SCALE_FACTOR));
            angle += (float) (Math.PI / 2);
        }

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(final @NonNull LivingEntityRenderState state) {
        super.resetPose();
        float angle = state.ageInTicks * (float)Math.PI * -0.1F;

        final ModelPart[] upperBodyParts = ((BlazeModelParts) this).more_babies$getUpperBodyParts();
        final ModelPart head = ((BlazeModelParts) this).more_babies$getHead();

        for(int i = 0; i < 4; i++) {
            upperBodyParts[i].y = (-2.0F + Mth.cos((((i * 2) + state.ageInTicks) * 0.25F))) * SCALE_FACTOR + RODS_OFFSET;
            upperBodyParts[i].x = Mth.cos(angle) * 9.0F * SCALE_FACTOR;
            upperBodyParts[i].z = Mth.sin(angle) * 9.0F * SCALE_FACTOR;
            angle += (float) (Math.PI / 2);
        }

        angle = ((float)Math.PI / 4F) + state.ageInTicks * (float)Math.PI * 0.03F;

        for(int i = 4; i < 8; i++) {
            upperBodyParts[i].y = (2.0F + Mth.cos((((i * 2) + state.ageInTicks) * 0.25F))) * SCALE_FACTOR + RODS_OFFSET;
            upperBodyParts[i].x = Mth.cos(angle) * 7.0F * SCALE_FACTOR;
            upperBodyParts[i].z = Mth.sin(angle) * 7.0F * SCALE_FACTOR;
            angle += (float) (Math.PI / 2);
        }

        angle = 0.47123894F + state.ageInTicks * (float)Math.PI * -0.05F;

        for(int i = 8; i < 12; i++) {
            upperBodyParts[i].y = (11.0F + Mth.cos(((i * 1.5F + state.ageInTicks) * 0.5F))) * SCALE_FACTOR + RODS_OFFSET;
            upperBodyParts[i].x = Mth.cos(angle) * 5.0F * SCALE_FACTOR;
            upperBodyParts[i].z = Mth.sin(angle) * 5.0F * SCALE_FACTOR;
            angle += (float) (Math.PI / 2);
        }

        head.yRot = state.yRot * ((float)Math.PI / 180F);
        head.xRot = state.xRot * ((float)Math.PI / 180F);
    }
}
