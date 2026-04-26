package io.github.mammut53.more_babies.mixin.client.model.monster.guardian;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.guardian.GuardianModel;
import net.minecraft.client.renderer.entity.state.GuardianRenderState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuardianModel.class)
public abstract class GuardianModelMixin extends EntityModel<GuardianRenderState> {

    protected GuardianModelMixin(final ModelPart root) {
        super(root);
    }

    @Redirect(
            method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/GuardianRenderState;)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/model/geom/ModelPart;y:F",
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void redirectEyeYPut(final ModelPart instance, final float value, final GuardianRenderState state) {
        float instanceValue = value;

        if (value == 0.0F && state.isBaby) {
            instanceValue = -1.0F;
        }

        instance.y = instanceValue;
    }
}
