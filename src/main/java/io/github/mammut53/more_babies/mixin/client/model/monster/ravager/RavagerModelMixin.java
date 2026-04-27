package io.github.mammut53.more_babies.mixin.client.model.monster.ravager;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.ravager.RavagerModel;
import net.minecraft.client.renderer.entity.state.RavagerRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(RavagerModel.class)
public abstract class RavagerModelMixin extends EntityModel<RavagerRenderState> {

    protected RavagerModelMixin(final ModelPart root) {
        super(root);
    }

    @ModifyConstant(
            method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/RavagerRenderState;)V",
            constant = @Constant(floatValue = -7.0F)
    )
    private float modifyNeckY(final float neckY, final RavagerRenderState state) {
        return state.isBaby ? 10.0F : neckY;
    }

    @ModifyConstant(
            method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/RavagerRenderState;)V",
            constant = @Constant(floatValue = 5.5F)
    )
    private float modifyNeckZ(final float neckZ, final RavagerRenderState state) {
        return state.isBaby ? 2.75F : neckZ;
    }

}
