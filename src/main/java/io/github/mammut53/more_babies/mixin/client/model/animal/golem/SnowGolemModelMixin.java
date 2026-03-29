package io.github.mammut53.more_babies.mixin.client.model.animal.golem;

import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SnowGolemModel.class)
public abstract class SnowGolemModelMixin {

    @ModifyConstant(
            method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)V",
            constant = @Constant(floatValue = 5.0F)
    )
    private float modifyArmOffset(final float offset, final LivingEntityRenderState state) {
        if (state.isBaby) {
            return 2.5F;
        }
        return offset;
    }

}
