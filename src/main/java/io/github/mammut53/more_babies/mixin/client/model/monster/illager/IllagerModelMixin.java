package io.github.mammut53.more_babies.mixin.client.model.monster.illager;

import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(IllagerModel.class)
public abstract class IllagerModelMixin<S extends IllagerRenderState> extends EntityModel<S> implements ArmedModel<S>, HeadedModel {

    protected IllagerModelMixin(final ModelPart root) {
        super(root);
    }

    @ModifyConstant(
            method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/IllagerRenderState;)V",
            constant = @Constant(floatValue = 5.0F)
    )
    private float modifyLeftArmOffset(final float offset, final IllagerRenderState state) {
        if (state.isBaby) {
            return 3.0F;
        }
        return offset;
    }

    @ModifyConstant(
            method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/IllagerRenderState;)V",
            constant = @Constant(floatValue = -5.0F)
    )
    private float modifyRightArmOffset(final float offset, final IllagerRenderState state) {
        if (state.isBaby) {
            return -3.0F;
        }
        return offset;
    }

}
